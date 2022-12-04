package tk.leooresende.crudusuarios.testes.util.factory;

import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioAtualizadoForm;

public class UsuarioAtualizadoFormFactory {

	public static UsuarioAtualizadoForm criarUsuarioAtualizadoForm() {
		return new UsuarioAtualizadoForm("leo.messi", "Lionel Andrés Messi Cuccittini", "leo.123@gmail.com");
	}

	public static UsuarioAtualizadoForm criarUsuarioAtualizadoFormNulo() {
		return new UsuarioAtualizadoForm(null, null, null);
	}

	public static UsuarioAtualizadoForm criarUsuarioAtualizadoFormEmBranco() {
		return new UsuarioAtualizadoForm("", "", "");
	}
	
	public static UsuarioAtualizadoForm criarUsuarioAtualizadoFormComEmailEmUmFormatoInvalido() {
		return new UsuarioAtualizadoForm("leo.messi", "Lionel Andrés Messi Cuccittini", "leo123owef");
	}
	
	public static UsuarioAtualizadoForm criarUsuarioAtualizadoFormComUsernameEmUmFormatoInvalido() {
		return new UsuarioAtualizadoForm("l", "Lionel Andrés Messi Cuccittini", "leo.123@gmail.com");
	}
}
