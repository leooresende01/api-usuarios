package tk.leooresende.crudusuarios.testes.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioAtualizadoForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioDeletadoForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioForm;
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

	public UsuarioDto registrarUsuario(UsuarioForm usuarioForm) {
		return this.usuarioService.registrarOUsuario(usuarioForm, this.urlApi);
	}

	public UsuarioDto atualizarUsuario(UsuarioAtualizadoForm usuarioAtualizadoForm, String usernameEmailOuId) {
		return this.usuarioService.atualizarInformacoesDoUsuario(usuarioAtualizadoForm, usernameEmailOuId, urlApi);
	}

	public void deletarUsuario(UsuarioDeletadoForm userDeletedForm, String usernameEmailOuId) {
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
}
