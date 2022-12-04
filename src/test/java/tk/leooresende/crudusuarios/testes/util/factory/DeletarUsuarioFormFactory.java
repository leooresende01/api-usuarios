package tk.leooresende.crudusuarios.testes.util.factory;

import java.util.Random;

import tk.leooresende.crudusuarios.infra.dto.formularios.DeletarUsuarioForm;

public class DeletarUsuarioFormFactory {
	private static final String senhaPadrao = "12345678";
	
	public static DeletarUsuarioForm pegarUserDeletedFormComASenhaValida() {
		return new DeletarUsuarioForm(DeletarUsuarioFormFactory.senhaPadrao);
	}
	
	public static DeletarUsuarioForm pegarUserDeletedFormComSenhaInvalida() {
		Integer senhaAleatoria = new Random().nextInt(10000000, 99999999);
		return new DeletarUsuarioForm(senhaAleatoria.toString());
	}
	
	public static DeletarUsuarioForm pegarUserDeletedFormComASenhaNula() {
		return new DeletarUsuarioForm(null);
	}
	
	public static DeletarUsuarioForm pegarUserDeletedFormComASenhaVazia() {
		return new DeletarUsuarioForm("");
	}
}
