package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class EmailNaoPodeSerEnviadoException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Ocorreu um erro interno ao enviar o email";
	private static final HttpStatus STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR;

	public EmailNaoPodeSerEnviadoException() {
		super(EmailNaoPodeSerEnviadoException.MENSAGEM, EmailNaoPodeSerEnviadoException.STATUS_CODE);
	}
}
