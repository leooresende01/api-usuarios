package tk.leooresende.crudusuarios.testes.util.factory;

import java.util.Random;

import tk.leooresende.crudusuarios.infra.dto.formularios.AlterarSenhaForm;

public class AlterarSenhaFormFactory {
	private static final String SENHA_PADRAO = "12345678";

	public static AlterarSenhaForm criarFormularioDeAtualizacaoDeSenhaComSenhaAntigaErrada() {
		Random random = new Random();
		return new AlterarSenhaForm(String.valueOf(random.nextInt(10000000, 99999999)),
				String.valueOf(random.nextInt(10000000, 99999999)));
	}

	public static AlterarSenhaForm criarFormularioDeAtualizacaoDeSenhaValido() {
		Random random = new Random();
		return new AlterarSenhaForm(AlterarSenhaFormFactory.SENHA_PADRAO,
				String.valueOf(random.nextInt(10000000, 99999999)));
	}

	public static AlterarSenhaForm criarFormularioDeAtualizacaoDeSenhaNulo() {
		return new AlterarSenhaForm(null, null);
	}
	
	public static AlterarSenhaForm criarFormularioDeAtualizacaoDeSenhaVazio() {
		return new AlterarSenhaForm("", "");
	}

	public static AlterarSenhaForm criarFormularioDeAtualizacaoDeSenhaComAMesmaSenha() {
		return new AlterarSenhaForm(AlterarSenhaFormFactory.SENHA_PADRAO, AlterarSenhaFormFactory.SENHA_PADRAO);
	}
}
