package tk.leooresende.crudusuarios.testes.util.factory;

import tk.leooresende.crudusuarios.infra.dto.formularios.RegistrarUsuarioForm;

public class RegistrarUsuarioFormFactory {
	private static final String senhaPadrao = "12345678";
	
	public static RegistrarUsuarioForm criarUsuarioFormValido() {
		return new RegistrarUsuarioForm("cristiano.ronaldo", RegistrarUsuarioFormFactory.senhaPadrao, "Cristiano Ronaldo dos Santos Aveiro", "cr7@gmail.com");
	}

	public static RegistrarUsuarioForm criarUsuarioFormValido2() {
		return new RegistrarUsuarioForm("neymar.junior", RegistrarUsuarioFormFactory.senhaPadrao, "Neymar Da Silva Santos Junior", "neymar@gmail.com");
	}
	
	public static RegistrarUsuarioForm criarUsuarioFormNulo() {
		return new RegistrarUsuarioForm(null, null, null, null);
	}

	public static RegistrarUsuarioForm criarUsuarioFormEmBranco() {
		return new RegistrarUsuarioForm("", "", "", "");
	}
	
	public static RegistrarUsuarioForm criarUsuarioFormComEmailInvalido() {
		return new RegistrarUsuarioForm("cristiano.ronaldo", RegistrarUsuarioFormFactory.senhaPadrao, "Cristiano Ronaldo dos Santos Aveiro", "wefewfwfeewf");
	}
	
	public static RegistrarUsuarioForm criarUsuarioFormComUsernameInvalido() {
		return new RegistrarUsuarioForm("c", RegistrarUsuarioFormFactory.senhaPadrao, "Cristiano Ronaldo dos Santos Aveiro", "cr7@gmail.com");
	}
	
	public static RegistrarUsuarioForm criarUsuarioFormComSenhaInvalida() {
		return new RegistrarUsuarioForm("cristiano.ronaldo", "c", "Cristiano Ronaldo dos Santos Aveiro", "cr7@gmail.com");
	}
}
