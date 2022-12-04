package tk.leooresende.crudusuarios;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tk.leooresende.crudusuarios.infra.SenhaNaoMudouException;
import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.AlterarSenhaForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.DeletarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.RegistrarUsuarioForm;
import tk.leooresende.crudusuarios.infra.handler.exception.SenhaInvalidaException;
import tk.leooresende.crudusuarios.infra.handler.exception.UsuarioNaoExisteException;
import tk.leooresende.crudusuarios.testes.util.UsuarioServiceTestUtil;
import tk.leooresende.crudusuarios.testes.util.factory.AlterarSenhaFormFactory;
import tk.leooresende.crudusuarios.testes.util.factory.DeletarUsuarioFormFactory;
import tk.leooresende.crudusuarios.testes.util.factory.RegistrarUsuarioFormFactory;

@SpringBootTest
public class AlterarSenhaTest {
	@Autowired
	private UsuarioServiceTestUtil userTestUtil;

	private UsuarioDto usuarioDto;

	private DeletarUsuarioForm userDeletedForm;

	private RegistrarUsuarioForm usuarioForm;

	private final Integer idInvalido = -1;

	private AlterarSenhaForm formularioDeAlteracaoDeSenha;

	@BeforeEach
	void criarFormularioDeUsuario() {
		this.usuarioForm = RegistrarUsuarioFormFactory.criarUsuarioFormValido();
		this.usuarioDto = this.userTestUtil.registrarUsuario(usuarioForm);
		this.formularioDeAlteracaoDeSenha = AlterarSenhaFormFactory.criarFormularioDeAtualizacaoDeSenhaValido();
		this.userDeletedForm = DeletarUsuarioFormFactory.pegarUserDeletedFormComASenhaValida();
	}

	@Test
	void deveriaAlterarASenhaNormalmente() {
		assertDoesNotThrow(
				() -> this.userTestUtil.atualizarSenha(this.formularioDeAlteracaoDeSenha, this.usuarioDto.getId()));
		this.userDeletedForm = new DeletarUsuarioForm(this.formularioDeAlteracaoDeSenha.getNovaSenha());
	}

	@Test
	void deveriaLancarUmaExcessaoCasoASenhaAntigaForInvalida() {
		AlterarSenhaForm formularioDeAlteracaoDeSenhaInvalido = AlterarSenhaFormFactory
				.criarFormularioDeAtualizacaoDeSenhaComSenhaAntigaErrada();
		assertThrows(SenhaInvalidaException.class,
				() -> this.userTestUtil.atualizarSenha(formularioDeAlteracaoDeSenhaInvalido, this.usuarioDto.getId()));
	}

	@Test
	void deveriaLancarUmaExcessaoCasoANovaSenhaForAMesmaQueAAntiga() {
		AlterarSenhaForm formularioDeAlteracaoDeSenhaComAMesmaSenha = AlterarSenhaFormFactory
				.criarFormularioDeAtualizacaoDeSenhaComAMesmaSenha();
		assertThrows(SenhaNaoMudouException.class,
				() -> this.userTestUtil.atualizarSenha(formularioDeAlteracaoDeSenhaComAMesmaSenha, this.usuarioDto.getId()));
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOUsuarioNaoExista() {
		assertThrows(UsuarioNaoExisteException.class,
				() -> this.userTestUtil.atualizarSenha(this.formularioDeAlteracaoDeSenha, this.idInvalido));
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOFormularioDeAtualizacaoSejaNuloOuEstejaEmBranco() {
		assertThrows(Exception.class, () -> this.userTestUtil.atualizarSenha(
				AlterarSenhaFormFactory.criarFormularioDeAtualizacaoDeSenhaNulo(), this.usuarioDto.getId()));
		assertThrows(Exception.class, () -> this.userTestUtil.atualizarSenha(
				AlterarSenhaFormFactory.criarFormularioDeAtualizacaoDeSenhaVazio(), this.usuarioDto.getId()));
	}

	@AfterEach
	void deletarUsuarioCasoEleFoiCriado() {
		try {
			this.userTestUtil.deletarUsuario(this.userDeletedForm, this.usuarioDto.getId().toString());
		} catch (Exception ex) {

		}
	}
}
