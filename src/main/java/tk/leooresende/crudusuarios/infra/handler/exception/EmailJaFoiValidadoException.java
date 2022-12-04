package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class EmailJaFoiValidadoException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "O email desse usuario ja foi validado";
	private static final HttpStatus STATUS_CODE = HttpStatus.BAD_REQUEST;

	public EmailJaFoiValidadoException() {
		super(EmailJaFoiValidadoException.MENSAGEM, EmailJaFoiValidadoException.STATUS_CODE);
	}
}
