package tk.leooresende.crudusuarios.testes.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.AlterarSenhaForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.AtualizarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.DeletarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.RegistrarUsuarioForm;
import tk.leooresende.crudusuarios.infra.repository.v1.ValidacaoEmailRepository;
import tk.leooresende.crudusuarios.infra.service.v1.UsuarioService;
import tk.leooresende.crudusuarios.model.ValidacaoEmail;

@Component
public class UsuarioServiceTestUtil {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ValidacaoEmailRepository validRepo;

	private final String urlApi = "http://localhost";

	public UsuarioDto registrarUsuario(RegistrarUsuarioForm usuarioForm) {
		return this.usuarioService.registrarOUsuario(usuarioForm, this.urlApi);
	}

	public UsuarioDto atualizarUsuario(AtualizarUsuarioForm usuarioAtualizadoForm, String usernameEmailOuId) {
		return this.usuarioService.atualizarInformacoesDoUsuario(usuarioAtualizadoForm, usernameEmailOuId, urlApi);
	}

	public void deletarUsuario(DeletarUsuarioForm userDeletedForm, String usernameEmailOuId) {
		this.usuarioService.deletarUsuario(userDeletedForm, usernameEmailOuId);
	}

	public ValidacaoEmail buscarCodigoDeValidacaoDoUsuario(UsuarioDto usuarioCriado) {
		return this.validRepo.findByIdDoUsuario(usuarioCriado.getId()).get();
	}

	public UsuarioDto validarEmail(ValidacaoEmail validacaoEmail) {
		return this.usuarioService.validarEmailDoUsuario(validacaoEmail.getIdDoUsuario(),
				validacaoEmail.getCodigoDeVerificacao());
	}

	public UsuarioDto buscarUsuario(Integer id) {
		return this.usuarioService.buscarUsuarioPeloUsernameEmailOuId(id.toString());
	}

	public void atualizarSenha(AlterarSenhaForm formularioDeAlteracaoDeSenha, Integer id) {
		this.usuarioService.alterarSenhaDoUsuario(formularioDeAlteracaoDeSenha, id.toString());
	}
}
