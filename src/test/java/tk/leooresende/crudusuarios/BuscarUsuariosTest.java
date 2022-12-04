package tk.leooresende.crudusuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tk.leooresende.crudusuarios.infra.dto.UsuarioDto;
import tk.leooresende.crudusuarios.infra.dto.formularios.DeletarUsuarioForm;
import tk.leooresende.crudusuarios.infra.dto.formularios.RegistrarUsuarioForm;
import tk.leooresende.crudusuarios.infra.handler.exception.UsuarioNaoExisteException;
import tk.leooresende.crudusuarios.testes.util.UsuarioServiceTestUtil;
import tk.leooresende.crudusuarios.testes.util.factory.DeletarUsuarioFormFactory;
import tk.leooresende.crudusuarios.testes.util.factory.RegistrarUsuarioFormFactory;



@SpringBootTest
public class BuscarUsuariosTest {

	@Autowired
	private UsuarioServiceTestUtil userTestUtil;
	private RegistrarUsuarioForm usuarioForm;
	private DeletarUsuarioForm userDeletedForm;
	private UsuarioDto usuarioDto;
	private final Integer idDeUsuarioInvalido = -1;

	@BeforeEach
	void criarFormularioDeUsuario() {
		this.usuarioForm = RegistrarUsuarioFormFactory.criarUsuarioFormValido();
		this.userDeletedForm = DeletarUsuarioFormFactory.pegarUserDeletedFormComASenhaValida();
		this.usuarioDto = this.userTestUtil.registrarUsuario(usuarioForm);
	}

	@Test
	void deveriaBuscarOUsuarioRegistradoEAsInformacoesSeremAMesma() {
		UsuarioDto usuarioBuscado = this.userTestUtil.buscarUsuario(this.usuarioDto.getId());

		assertEquals(this.usuarioDto.getUsername(), usuarioBuscado.getUsername());
		assertEquals(this.usuarioDto.getEmail(), usuarioBuscado.getEmail());
		assertEquals(this.usuarioDto.getNomeCompleto(), usuarioBuscado.getNomeCompleto());
		assertEquals(this.usuarioDto.getDataDeRegistro(), usuarioBuscado.getDataDeRegistro());
		assertEquals(this.usuarioDto.getUltimaAlteracao(), usuarioBuscado.getUltimaAlteracao());
		assertEquals(this.usuarioDto.getSituacao(), usuarioBuscado.getSituacao());
	}

	@Test
	void deveriaLancarUmaExcessaoCasoOIdDoUsuarioSejaInvalidoOuInexistente() {
		assertThrows(UsuarioNaoExisteException.class, () -> this.userTestUtil.buscarUsuario(this.idDeUsuarioInvalido));
	}

	@AfterEach
	void deletarUsuarioCasoEleFoiCriado() {
		try {
			this.userTestUtil.deletarUsuario(this.userDeletedForm, this.usuarioDto.getId().toString());
		} catch (Exception ex) {

		}
	}
}
