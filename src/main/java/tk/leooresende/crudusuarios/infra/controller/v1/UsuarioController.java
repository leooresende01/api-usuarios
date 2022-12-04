package tk.leooresende.crudusuarios.infra.controller.v1;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import tk.leooresende.crudusuarios.infra.dto.AlterarSenhaDto;
import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.AlterarSenhaForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.AtualizarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.DeletarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.RegistrarUsuarioForm;
import tk.leooresende.crudusuarios.infra.service.v1.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	private static final String API_USUARIO_PATH = "/api/v1/usuarios/";
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> buscarTodosOsUsuarioRegistrados() {
		List<UsuarioDto> usuariosDto = this.usuarioService.buscarTodosOsUsuarios();
		return ResponseEntity.ok(usuariosDto);
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> registrarUmUsuario(@RequestBody @Valid RegistrarUsuarioForm userForm,
			UriComponentsBuilder uri) throws URISyntaxException {
		String urlApi = uri.toUriString();
		UsuarioDto usuarioRegistrado = this.usuarioService.registrarOUsuario(userForm, urlApi);
		return ResponseEntity
				.created(new URI(urlApi + UsuarioController.API_USUARIO_PATH + usuarioRegistrado.getUsername()))
				.body(usuarioRegistrado);
	}

	@PutMapping("/{usernameEmailOrId}")
	public ResponseEntity<UsuarioDto> atualizarUsuarioPeloUsernameEmailOuId(
			@RequestBody @Valid AtualizarUsuarioForm userAtualizadoForm, @PathVariable String usernameEmailOrId,
			UriComponentsBuilder uri) throws URISyntaxException {
		String urlApi = uri.toUriString();
		UsuarioDto usuarioAtualizado = this.usuarioService.atualizarInformacoesDoUsuario(userAtualizadoForm,
				usernameEmailOrId, urlApi);
		return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@PatchMapping("/{usernameEmailOrId}/alterarSenha")
	public ResponseEntity<AlterarSenhaDto> alterarSenhaDeUmUsuario(@RequestBody @Valid AlterarSenhaForm alterarSenhaForm,
			@PathVariable String usernameEmailOrId) {
		AlterarSenhaDto alterarSenhaDto = this.usuarioService.alterarSenhaDoUsuario(alterarSenhaForm, usernameEmailOrId);
		return ResponseEntity.ok(alterarSenhaDto);
	}
	
	@DeleteMapping("/{usernameEmailOrId}")
	public ResponseEntity<Void> deletarUsuarioPeloUsernameEmailOuId(
			@RequestBody @Valid DeletarUsuarioForm userDeletedForm, @PathVariable String usernameEmailOrId) {
		this.usuarioService.deletarUsuario(userDeletedForm, usernameEmailOrId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{usernameEmailOrId}")
	public ResponseEntity<UsuarioDto> buscarUsuarioPeloUsernameOmailOuId(@PathVariable String usernameEmailOrId) {
		UsuarioDto usuario = this.usuarioService.buscarUsuarioPeloUsernameEmailOuId(usernameEmailOrId);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/{userId}/validarEmail")
	public ResponseEntity<UsuarioDto> validarOEmailDoUsuario(@RequestParam(required = true) Integer codigo,
			@PathVariable Integer userId) {
		UsuarioDto usuarioComOEmailVerificado = this.usuarioService.validarEmailDoUsuario(userId, codigo);
		return ResponseEntity.ok(usuarioComOEmailVerificado);
	}
}
