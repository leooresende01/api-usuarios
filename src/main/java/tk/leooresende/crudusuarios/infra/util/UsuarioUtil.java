package tk.leooresende.crudusuarios.infra.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tk.leooresende.crudusuarios.infra.handler.exception.EmailJaFoiValidadoException;
import tk.leooresende.crudusuarios.infra.handler.exception.EmailNaoValidadoException;
import tk.leooresende.crudusuarios.model.Situacao;
import tk.leooresende.crudusuarios.model.Usuario;

public class UsuarioUtil {

	private static final String DATA_PATTERN = "dd/MM/yyyy HH:mm:ss";

	public static String pegarDataFormatada(LocalDateTime data) {
		return data.format(DateTimeFormatter.ofPattern(UsuarioUtil.DATA_PATTERN));
	}

	public static void verificaSeOEmailJaFoiVerificado(Usuario usuario) {
		if (!usuario.getSituacao().equals(Situacao.VERIFICACAO_DO_EMAIL_PENDENTE)) throw new EmailJaFoiValidadoException();
	}

	public static void verificaASituacaoAtualDoUsuario(Usuario usuarioQueVaiSerAtualizado) {
		if (usuarioQueVaiSerAtualizado.getSituacao().equals(Situacao.VERIFICACAO_DO_EMAIL_PENDENTE)) throw new EmailNaoValidadoException();
	}
	
}
