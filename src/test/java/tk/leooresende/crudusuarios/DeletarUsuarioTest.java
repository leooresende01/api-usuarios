package tk.leooresende.crudusuarios;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioDeletadoForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioForm;
import tk.leooresende.crudusuarios.infra.handler.exception.SenhaInvalidaException;
import tk.leooresende.crudusuarios.infra.handler.exception.UsuarioNaoExisteException;
import tk.leooresende.crudusuarios.testes.util.UsuarioServiceTestUtil;
import tk.leooresende.crudusuarios.testes.util.factory.UsuarioDeletadoFormFactory;
import tk.leooresende.crudusuarios.testes.util.factory.UsuarioFormFactory;

@SpringBootTest
public class DeletarUsuarioTest {
	@Autowired
	private UsuarioServiceTestUtil userTestUtil;

	private UsuarioDto usuarioDto;

	private UsuarioDeletadoForm userDeletedForm;

	private UsuarioForm usuarioForm;

	private final Integer idInvalido = -1;

	@BeforeEach
	void criarFormularioDeUsuario() {
		this.usuarioForm = UsuarioFormFactory.criarUsuarioFormValido();
		this.userDeletedForm = UsuarioDeletadoFormFactory.pegarUserDeletedFormComASenhaValida();
		this.usuarioDto = this.userTestUtil.registrarUsuario(usuarioForm);
	}

	@Test
	void deveriaDeletarOUsuarioCasoASenhaForValida() {
		assertDoesNotThrow(() -> this.userTestUtil.deletarUsuario(userDeletedForm, this.usuarioDto.getUsername()));
	}

	@Test
	void deveriaLancarUmaExcessaoCasoASenhaForInvalida() {
		assertThrows(SenhaInvalidaException.class,
				() -> this.userTestUtil.deletarUsuario(
						UsuarioDeletadoFormFactory.pegarUserDeletedFormComSenhaInvalida(),
						this.usuarioDto.getUsername()));
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOUsuarioQueVaiSerDeletadoNaoExista() {
		assertThrows(UsuarioNaoExisteException.class,
				() -> this.userTestUtil.deletarUsuario(UsuarioDeletadoFormFactory.pegarUserDeletedFormComASenhaValida(),
						this.idInvalido.toString()));
	}
	
	@Test
	void deveriaLancarUmaExcessaoCasoASenhaEnviadaForNulaOuEstejaVazia() {
		assertThrows(Exception.class,
				() -> this.userTestUtil.deletarUsuario(UsuarioDeletadoFormFactory.pegarUserDeletedFormComASenhaNula(),
						this.idInvalido.toString()));
		assertThrows(Exception.class,
				() -> this.userTestUtil.deletarUsuario(UsuarioDeletadoFormFactory.pegarUserDeletedFormComASenhaVazia(),
						this.idInvalido.toString()));
	}

	@AfterEach
	void deletarUsuarioCasoEleFoiCriado() {
		try {
			this.userTestUtil.deletarUsuario(this.userDeletedForm, this.usuarioDto.getId().toString());
		} catch (Exception ex) {

		}
	}
}
