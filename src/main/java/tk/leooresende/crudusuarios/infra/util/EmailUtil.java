package tk.leooresende.crudusuarios.infra.util;

import java.util.Random;

import tk.leooresende.crudusuarios.model.Usuario;

public class EmailUtil {
	
	private static final String ASSUNTO = "Por favor verifique seu endereço de email";
	private static Object PATH_VERIFY_EMAIL = "/api/v1/usuarios/";
	public static String criarMensagemDeEmail(Usuario usuario, String urlApi, Integer codigoDoEmailAleatorio) {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Olá ");
		mensagem.append(usuario.getNomeCompleto());
		mensagem.append("!");
		mensagem.append(System.lineSeparator());
		mensagem.append(System.lineSeparator());
		mensagem.append("Precisamos confirmar que seu endereço de e-mail é válido. "
				+ "Por favor, clique no link abaixo para confirmar que você recebeu este e-mail.");
		mensagem.append(System.lineSeparator());
		mensagem.append(System.lineSeparator());
		mensagem.append(urlApi);
		mensagem.append(EmailUtil.PATH_VERIFY_EMAIL);
		mensagem.append(usuario.getId());
		mensagem.append("/");
		mensagem.append("validarEmail?codigo=");
		mensagem.append(codigoDoEmailAleatorio);
		mensagem.append(System.lineSeparator());
		mensagem.append(System.lineSeparator());
		mensagem.append("Codigo de verificação: ");
		mensagem.append(codigoDoEmailAleatorio);
		return mensagem.toString();
	}

	public static String pegarAssuntoDoEmailDeVerificacao() {
		return EmailUtil.ASSUNTO;
	}

	public static Integer gerarCodigoAleatorio() {
		Random random = new Random();
		return random.nextInt(10000, 99999);
	}
}
