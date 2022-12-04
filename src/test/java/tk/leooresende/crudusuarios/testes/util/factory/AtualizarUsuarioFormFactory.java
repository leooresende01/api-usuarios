package tk.leooresende.crudusuarios.testes.util.factory;

import tk.leooresende.crudusuarios.infra.dto.formularios.AtualizarUsuarioForm;

public class AtualizarUsuarioFormFactory {

	public static AtualizarUsuarioForm criarUsuarioAtualizadoForm() {
		return new AtualizarUsuarioForm("leo.messi", "Lionel Andrés Messi Cuccittini", "leo.123@gmail.com");
	}

	public static AtualizarUsuarioForm criarUsuarioAtualizadoFormNulo() {
		return new AtualizarUsuarioForm(null, null, null);
	}

	public static AtualizarUsuarioForm criarUsuarioAtualizadoFormEmBranco() {
		return new AtualizarUsuarioForm("", "", "");
	}
	
	public static AtualizarUsuarioForm criarUsuarioAtualizadoFormComEmailEmUmFormatoInvalido() {
		return new AtualizarUsuarioForm("leo.messi", "Lionel Andrés Messi Cuccittini", "leo123owef");
	}
	
	public static AtualizarUsuarioForm criarUsuarioAtualizadoFormComUsernameEmUmFormatoInvalido() {
		return new AtualizarUsuarioForm("l", "Lionel Andrés Messi Cuccittini", "leo.123@gmail.com");
	}
}
