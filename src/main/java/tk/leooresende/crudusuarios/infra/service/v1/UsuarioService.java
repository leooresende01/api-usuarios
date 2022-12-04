package tk.leooresende.crudusuarios.infra.service.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tk.leooresende.crudusuarios.infra.dto.EmailDto;
import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioAtualizadoForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioDeletadoForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioForm;
import tk.leooresende.crudusuarios.infra.handler.exception.CodigoDeVerificacaoInvalidoException;
import tk.leooresende.crudusuarios.infra.handler.exception.SenhaInvalidaException;
import tk.leooresende.crudusuarios.infra.handler.exception.UsernameOuEmailJaUsadoException;
import tk.leooresende.crudusuarios.infra.handler.exception.UsuarioNaoExisteException;
import tk.leooresende.crudusuarios.infra.repository.v1.UsuarioRepository;
import tk.leooresende.crudusuarios.infra.repository.v1.ValidacaoEmailRepository;
import tk.leooresende.crudusuarios.infra.util.EmailUtil;
import tk.leooresende.crudusuarios.infra.util.UsuarioUtil;
import tk.leooresende.crudusuarios.infra.util.service.EmailService;
import tk.leooresende.crudusuarios.model.Situacao;
import tk.leooresende.crudusuarios.model.Usuario;
import tk.leooresende.crudusuarios.model.ValidacaoEmail;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ValidacaoEmailRepository validRepo;

	@Autowired
	private PasswordEncoder passEncoder;

	public List<UsuarioDto> buscarTodosOsUsuarios() {
		List<Usuario> usuarios = this.userRepo.findAll();
		return UsuarioDto.mapearListaDeUsuariosParaDto(usuarios);
	}

	@Transactional
	public UsuarioDto registrarOUsuario(@Valid UsuarioForm userForm, String urlApi) {
		this.verificaSeOUsernameOuEmailJaEstaSendoUsado(userForm.getUsername(), userForm.getEmail());

		Usuario usuarioCriado = userForm.criarUsuarioApartirDoFormulario();
		Usuario usuarioSalvoNoDB = salvarNovoUsuarioNoDB(usuarioCriado);

		this.enviarCodigoDeVerificacaoPorEmail(usuarioSalvoNoDB, urlApi);
		return new UsuarioDto(usuarioSalvoNoDB);
	}

	@Transactional
	public UsuarioDto atualizarInformacoesDoUsuario(@Valid UsuarioAtualizadoForm userForm, String usernameEmailOrId,
			String urlApi) {
		Usuario usuarioQueVaiSerAtualizado = this.buscarUsuarioPeloUsernameEmailOuIdNoDB(usernameEmailOrId);
		this.verificarSeOUsuarioSolicitouAAlteracaoDoEmailOuUsername(userForm, usuarioQueVaiSerAtualizado, urlApi);

		userForm.atualizarInformacoesDoUsuarioApartirDoFormulario(usuarioQueVaiSerAtualizado);

		Usuario usuarioComAsInformacoesAtualizadas = this.salvarUsuarioNoDB(usuarioQueVaiSerAtualizado);
		return new UsuarioDto(usuarioComAsInformacoesAtualizadas);
	}

	@Transactional
	public void deletarUsuario(@Valid UsuarioDeletadoForm userDeletedForm, String usernameEmailOrId) {
		Usuario usuario = this.buscarUsuarioPeloUsernameEmailOuIdNoDB(usernameEmailOrId);
		this.verificarSeASenhaEstaCorreta(userDeletedForm.getPassword(), usuario.getPassword());
		this.userRepo.deleteById(usuario.getId());
		this.validRepo.deleteByIdDoUsuario(usuario.getId());

	}

	public UsuarioDto buscarUsuarioPeloUsernameEmailOuId(String usernameEmailOrId) {
		Usuario usuario = this.buscarUsuarioPeloUsernameEmailOuIdNoDB(usernameEmailOrId);
		return new UsuarioDto(usuario);
	}

	@Transactional
	public UsuarioDto validarEmailDoUsuario(Integer userId, Integer codigoDeVerificacao) {
		Usuario usuario = this.buscarUsuarioPeloIdNoDB(userId);
		UsuarioUtil.verificaSeOEmailJaFoiVerificado(usuario);

		ValidacaoEmail validacaoEmail = this.verificarSeOCodigoEhValido(userId, codigoDeVerificacao);

		usuario.definirOUsuarioComoAtivo();

		Usuario usuarioComOEmailVerificado = salvarUsuarioNoDB(usuario);
		this.validRepo.deleteById(validacaoEmail.getId());

		return new UsuarioDto(usuarioComOEmailVerificado);
	}

	private ValidacaoEmail verificarSeOCodigoEhValido(Integer userId, Integer codigoDeVerificacao) {
		Optional<ValidacaoEmail> validacaoEmailOptional = this.validRepo.findByIdDoUsuario(userId);
		if (validacaoEmailOptional.isEmpty())
			throw new UsuarioNaoExisteException();

		ValidacaoEmail validacaoEmail = validacaoEmailOptional.get();
		if (validacaoEmail.getCodigoDeVerificacao().equals(codigoDeVerificacao))
			return validacaoEmail;
		throw new CodigoDeVerificacaoInvalidoException();
	}

	private void enviarCodigoDeVerificacaoPorEmail(Usuario usuario, String urlApi) {
		Integer codigoDoEmailAleatorio = EmailUtil.gerarCodigoAleatorio();
		String mensagem = EmailUtil.criarMensagemDeEmail(usuario, urlApi, codigoDoEmailAleatorio);

		String assuntoDoEmailDeVerificacao = EmailUtil.pegarAssuntoDoEmailDeVerificacao();
		EmailDto emailDto = new EmailDto(usuario.getEmail(), mensagem, assuntoDoEmailDeVerificacao);

		this.guardarCodigoDeVerificacaoNoDB(usuario, codigoDoEmailAleatorio);
		this.emailService.enviarEmail(emailDto);
	}

	private void guardarCodigoDeVerificacaoNoDB(Usuario usuario, Integer codigoDoEmailAleatorio) {
		Optional<ValidacaoEmail> validacaoEmailUsuarioOptional = this.validRepo.findByIdDoUsuario(usuario.getId());
		ValidacaoEmail validacaoEmail = new ValidacaoEmail(usuario.getId(), codigoDoEmailAleatorio);
		if (validacaoEmailUsuarioOptional.isPresent()) {
			validacaoEmail = validacaoEmailUsuarioOptional.get();
			validacaoEmail.setCodigoDeVerificacao(codigoDoEmailAleatorio);
		}
		this.validRepo.save(validacaoEmail);
	}

	private Usuario salvarUsuarioNoDB(Usuario usuario) {
		return this.userRepo.save(usuario);
	}
	
	private Usuario salvarNovoUsuarioNoDB(Usuario usuario) {
		usuario.setPassword(this.passEncoder.encode(usuario.getPassword()));
		return this.userRepo.save(usuario);
	}
	
	private Usuario buscarUsuarioPeloUsernameOuEmailNoDB(String usernameOrEmail) {
		return this.userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(UsuarioNaoExisteException::new);
	}

	private Usuario buscarUsuarioPeloIdNoDB(Integer userId) {
		return this.userRepo.findById(userId).orElseThrow(UsuarioNaoExisteException::new);
	}

	private Usuario buscarUsuarioPeloUsernameEmailOuIdNoDB(String usernameEmailOrId) {
		try {
			return this.buscarUsuarioPeloUsernameOuEmailNoDB(usernameEmailOrId);
		} catch (UsuarioNaoExisteException ex) {
			try {
				return this.buscarUsuarioPeloIdNoDB(Integer.valueOf(usernameEmailOrId));
			} catch (NumberFormatException ex2) {
				throw ex;
			}
		}
	}

	private void verificarSeOUsuarioSolicitouAAlteracaoDoEmailOuUsername(UsuarioAtualizadoForm userForm,
			Usuario usuario, String urlApi) {
		boolean emailFoiAlterado = !usuario.getEmail().equals(userForm.getEmail());
		boolean usernameFoiAlterado = !usuario.getUsername().equals(userForm.getUsername());
		if (emailFoiAlterado) {
			this.verificaSeOEmailJaEstaSendoUsado(userForm.getEmail());
			usuario.setEmail(userForm.getEmail());
			usuario.setSituacao(Situacao.VERIFICACAO_DO_EMAIL_PENDENTE);
			this.enviarCodigoDeVerificacaoPorEmail(usuario, urlApi);
		}
		if (usernameFoiAlterado) {
			this.verificaSeOUsernameJaEstaSendoUsado(userForm.getUsername());
			usuario.setUsername(userForm.getUsername());
		}
	}

	private void verificaSeOEmailJaEstaSendoUsado(String email) {
		Boolean usuarioJaFoiRegistrado = this.userRepo.existsByEmail(email);
		if (usuarioJaFoiRegistrado)
			throw new UsernameOuEmailJaUsadoException();
	}

	private void verificaSeOUsernameJaEstaSendoUsado(String username) {
		Boolean usuarioJaFoiRegistrado = this.userRepo.existsByUsername(username);
		if (usuarioJaFoiRegistrado)
			throw new UsernameOuEmailJaUsadoException();
	}

	private void verificaSeOUsernameOuEmailJaEstaSendoUsado(String username, String email) {
		Boolean usuarioJaFoiRegistrado = this.userRepo.existsByUsernameOrEmail(username, email);
		if (usuarioJaFoiRegistrado)
			throw new UsernameOuEmailJaUsadoException();
	}
	
	private void verificarSeASenhaEstaCorreta(String senhaDoFormulario, String senhaCorretaDoUsuario) {
		Boolean aSenhaEstaCorreta = this.passEncoder.matches(senhaDoFormulario, senhaCorretaDoUsuario);
		if (!aSenhaEstaCorreta) {
			throw new SenhaInvalidaException();
		}
	}
}
