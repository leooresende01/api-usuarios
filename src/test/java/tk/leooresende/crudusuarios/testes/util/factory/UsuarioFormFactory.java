package tk.leooresende.crudusuarios.testes.util.factory;

import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioForm;

public class UsuarioFormFactory {
	private static final String senhaPadrao = "12345678";
	
	public static UsuarioForm criarUsuarioFormValido() {
		return new UsuarioForm("cristiano.ronaldo", UsuarioFormFactory.senhaPadrao, "Cristiano Ronaldo dos Santos Aveiro", "cr7@gmail.com");
	}

	public static UsuarioForm criarUsuarioFormValido2() {
		return new UsuarioForm("neymar.junior", UsuarioFormFactory.senhaPadrao, "Neymar Da Silva Santos Junior", "neymar@gmail.com");
	}
	
	public static UsuarioForm criarUsuarioFormNulo() {
		return new UsuarioForm(null, null, null, null);
	}

	public static UsuarioForm criarUsuarioFormEmBranco() {
		return new UsuarioForm("", "", "", "");
	}
	
	public static UsuarioForm criarUsuarioFormComEmailInvalido() {
		return new UsuarioForm("cristiano.ronaldo", UsuarioFormFactory.senhaPadrao, "Cristiano Ronaldo dos Santos Aveiro", "wefewfwfeewf");
	}
	
	public static UsuarioForm criarUsuarioFormComUsernameInvalido() {
		return new UsuarioForm("c", UsuarioFormFactory.senhaPadrao, "Cristiano Ronaldo dos Santos Aveiro", "cr7@gmail.com");
	}
	
	public static UsuarioForm criarUsuarioFormComSenhaInvalida() {
		return new UsuarioForm("cristiano.ronaldo", "c", "Cristiano Ronaldo dos Santos Aveiro", "cr7@gmail.com");
	}
}
