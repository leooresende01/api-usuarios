package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class EmailNaoValidadoException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Para realizar essa ação é necessario validar o email com o link enviado para ele";
	private static final HttpStatus STATUS_CODE = HttpStatus.BAD_REQUEST;

	public EmailNaoValidadoException() {
		super(EmailNaoValidadoException.MENSAGEM, EmailNaoValidadoException.STATUS_CODE);
	}
}
