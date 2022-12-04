package tk.leooresende.crudusuarios.testes.util.factory;

import java.util.Random;

import tk.leooresende.crudusuarios.infra.dto.formularios.UsuarioDeletadoForm;

public class UsuarioDeletadoFormFactory {
	private static final String senhaPadrao = "12345678";
	
	public static UsuarioDeletadoForm pegarUserDeletedFormComASenhaValida() {
		return new UsuarioDeletadoForm(UsuarioDeletadoFormFactory.senhaPadrao);
	}
	
	public static UsuarioDeletadoForm pegarUserDeletedFormComSenhaInvalida() {
		Integer senhaAleatoria = new Random().nextInt(10000000, 99999999);
		return new UsuarioDeletadoForm(senhaAleatoria.toString());
	}
	
	public static UsuarioDeletadoForm pegarUserDeletedFormComASenhaNula() {
		return new UsuarioDeletadoForm(null);
	}
	
	public static UsuarioDeletadoForm pegarUserDeletedFormComASenhaVazia() {
		return new UsuarioDeletadoForm("");
	}
}
