package tk.leooresende.crudusuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioDeletadoForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioForm;
import tk.leooresende.crudusuarios.infra.handler.exception.CodigoDeVerificacaoInvalidoException;
import tk.leooresende.crudusuarios.infra.handler.exception.UsernameOuEmailJaUsadoException;
import tk.leooresende.crudusuarios.model.Situacao;
import tk.leooresende.crudusuarios.model.ValidacaoEmail;
import tk.leooresende.crudusuarios.testes.util.UsuarioServiceTestUtil;
import tk.leooresende.crudusuarios.testes.util.factory.UsuarioDeletadoFormFactory;
import tk.leooresende.crudusuarios.testes.util.factory.UsuarioFormFactory;

@SpringBootTest
public class RegistrarUsuarioTest {

	private final Integer codigoDeVerificacaoInvalido = -1;

	@Autowired
	private UsuarioServiceTestUtil userTestUtil;

	private UsuarioForm usuarioForm;
	private UsuarioDeletadoForm userDeletedForm;

	private UsuarioDto usuarioCriado;

	@BeforeEach
	void criarFormularioDeUsuario() {
		this.usuarioForm = UsuarioFormFactory.criarUsuarioFormValido();
		this.userDeletedForm = UsuarioDeletadoFormFactory.pegarUserDeletedFormComASenhaValida();
		this.usuarioCriado = this.userTestUtil.registrarUsuario(this.usuarioForm);
	}

	@Test
	void deveriaCriarOUsuarioNormalmente() {
		assertEquals(this.usuarioForm.getUsername(), this.usuarioCriado.getUsername());
		assertEquals(this.usuarioForm.getEmail(), this.usuarioCriado.getEmail());
		assertEquals(this.usuarioForm.getNomeCompleto(), this.usuarioCriado.getNomeCompleto());
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOUsuarioSejaCriadoMaisDeUmaVez() {
		assertThrows(UsernameOuEmailJaUsadoException.class, () -> this.userTestUtil.registrarUsuario(this.usuarioForm));
	}

	@Test
	void deveriaValidarOEmailEAtivarAContaApartirDoCodigoDeValidacao() {
		ValidacaoEmail validacaoEmail = this.userTestUtil.buscarCodigoDeValidacaoDoUsuario(this.usuarioCriado);

		UsuarioDto usuarioComEmailValidado = this.userTestUtil.validarEmail(validacaoEmail);
		assertEquals(usuarioComEmailValidado.getSituacao(), Situacao.ATIVO);

		UsuarioDto usuarioCriadoComInformacoesAtualizadasNoDB = this.userTestUtil
				.buscarUsuario(this.usuarioCriado.getId());
		assertEquals(usuarioCriadoComInformacoesAtualizadasNoDB.getSituacao(), Situacao.ATIVO);
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOCodigoDeVerificacaoForInvalido() {
		ValidacaoEmail validacaoEmail = new ValidacaoEmail(this.usuarioCriado.getId(),
				this.codigoDeVerificacaoInvalido);
		assertThrows(CodigoDeVerificacaoInvalidoException.class, () -> this.userTestUtil.validarEmail(validacaoEmail));
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOFormularioForInvalido() {
		assertThrows(Exception.class,
				() -> this.userTestUtil.registrarUsuario(UsuarioFormFactory.criarUsuarioFormNulo()));
		assertThrows(Exception.class,
				() -> this.userTestUtil.registrarUsuario(UsuarioFormFactory.criarUsuarioFormEmBranco()));
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOFormatoDoUsernameEmailOuSenhaForInvalido() {
		assertThrows(Exception.class,
				() -> this.userTestUtil.registrarUsuario(UsuarioFormFactory.criarUsuarioFormComUsernameInvalido()));
		assertThrows(Exception.class,
				() -> this.userTestUtil.registrarUsuario(UsuarioFormFactory.criarUsuarioFormComEmailInvalido()));
		assertThrows(Exception.class,
				() -> this.userTestUtil.registrarUsuario(UsuarioFormFactory.criarUsuarioFormComSenhaInvalida()));
	}

	@AfterEach
	void deletarUsuarioCasoEleFoiCriado() {
		try {
			this.userTestUtil.deletarUsuario(this.userDeletedForm, this.usuarioForm.getUsername());
		} catch (Exception ex) {

		}
	}
}
