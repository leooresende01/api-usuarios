package tk.leooresende.crudusuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.AtualizarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.DeletarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.RegistrarUsuarioForm;
import tk.leooresende.crudusuarios.infra.handler.exception.UsernameOuEmailJaUsadoException;
import tk.leooresende.crudusuarios.infra.handler.exception.UsuarioNaoExisteException;
import tk.leooresende.crudusuarios.testes.util.UsuarioServiceTestUtil;
import tk.leooresende.crudusuarios.testes.util.factory.AtualizarUsuarioFormFactory;
import tk.leooresende.crudusuarios.testes.util.factory.DeletarUsuarioFormFactory;
import tk.leooresende.crudusuarios.testes.util.factory.RegistrarUsuarioFormFactory;


@SpringBootTest
public class AtualizarUsuarioTest {
	@Autowired
	private UsuarioServiceTestUtil userTestUtil;

	private UsuarioDto usuarioDto;

	private DeletarUsuarioForm userDeletedForm;

	private RegistrarUsuarioForm usuarioForm;
	
	private final Integer idInvalido = -1;

	@BeforeEach
	void criarFormularioDeUsuario() {
		this.usuarioForm = RegistrarUsuarioFormFactory.criarUsuarioFormValido();
		this.userDeletedForm = DeletarUsuarioFormFactory.pegarUserDeletedFormComASenhaValida();
		this.usuarioDto = this.userTestUtil.registrarUsuario(usuarioForm);
	}

	@Test
	void deveriaAtualizarOUsuarioComAsNovasInformacoes() {
		AtualizarUsuarioForm usuarioAtualizadoForm = AtualizarUsuarioFormFactory.criarUsuarioAtualizadoForm();
		UsuarioDto usuarioAtualizado = this.userTestUtil.atualizarUsuario(usuarioAtualizadoForm,
				this.usuarioDto.getId().toString());
		assertEquals(usuarioAtualizadoForm.getUsername(), usuarioAtualizado.getUsername());
		assertEquals(usuarioAtualizadoForm.getEmail(), usuarioAtualizado.getEmail());
		assertEquals(usuarioAtualizadoForm.getNomeCompleto(), usuarioAtualizado.getNomeCompleto());
	}

	@Test
	void deveriaLancarUmaExcessaoCasoTenteAtualizarOEmailOuUsernameParaUmJaExistente() {
		RegistrarUsuarioForm usuarioFormAlternativo = RegistrarUsuarioFormFactory.criarUsuarioFormValido2();
		this.userTestUtil.registrarUsuario(usuarioFormAlternativo);

		AtualizarUsuarioForm usuarioAtualizadoFormTesteUsername = AtualizarUsuarioFormFactory.criarUsuarioAtualizadoForm();
		usuarioAtualizadoFormTesteUsername.setUsername(usuarioFormAlternativo.getUsername());

		assertThrows(UsernameOuEmailJaUsadoException.class,
				() -> this.userTestUtil.atualizarUsuario(usuarioAtualizadoFormTesteUsername, this.usuarioDto.getId().toString()));
		
		
		AtualizarUsuarioForm usuarioAtualizadoFormTesteEmail = AtualizarUsuarioFormFactory.criarUsuarioAtualizadoForm();
		usuarioAtualizadoFormTesteEmail.setEmail(usuarioFormAlternativo.getEmail());
		assertThrows(UsernameOuEmailJaUsadoException.class,
				() -> this.userTestUtil.atualizarUsuario(usuarioAtualizadoFormTesteEmail, this.usuarioDto.getId().toString()));
		
		this.userTestUtil.deletarUsuario(userDeletedForm, usuarioFormAlternativo.getUsername());
	}
	
	@Test
	void deveriaLancarUmaExcessaoCasoOUsuarioQueVaiSerAtualizadoNaoExista() {
		assertThrows(UsuarioNaoExisteException.class, () -> this.userTestUtil
				.atualizarUsuario(AtualizarUsuarioFormFactory.criarUsuarioAtualizadoForm(), this.idInvalido.toString()));
	}
	
	@Test
	void deveriaLancarUmaExcessaoCasoOFormularioDeAtualizacaoSejaNuloOuEstejaEmBranco() {
		assertThrows(Exception.class, () -> this.userTestUtil
				.atualizarUsuario(AtualizarUsuarioFormFactory.criarUsuarioAtualizadoFormNulo(), this.idInvalido.toString()));
		assertThrows(Exception.class, () -> this.userTestUtil
				.atualizarUsuario(AtualizarUsuarioFormFactory.criarUsuarioAtualizadoFormEmBranco(), this.idInvalido.toString()));
	}
	
	@Test
	void deveriaLancarUmaExcessaoCasoOFormatoDoUsernameOuEmailForInvalido() {
		assertThrows(Exception.class, () -> this.userTestUtil
				.atualizarUsuario(AtualizarUsuarioFormFactory.criarUsuarioAtualizadoFormComEmailEmUmFormatoInvalido(), this.idInvalido.toString()));
		assertThrows(Exception.class, () -> this.userTestUtil
				.atualizarUsuario(AtualizarUsuarioFormFactory.criarUsuarioAtualizadoFormComUsernameEmUmFormatoInvalido(), this.idInvalido.toString()));
	}
	
	@AfterEach
	void deletarUsuarioCasoEleFoiCriado() {
		try {
			this.userTestUtil.deletarUsuario(this.userDeletedForm, this.usuarioDto.getId().toString());
		} catch (Exception ex) {

		}
	}

}
