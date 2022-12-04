package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class SenhaInvalidaException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "A senha informada está incorreta ou é invalida";
	private static final HttpStatus STATUS_CODE = HttpStatus.UNAUTHORIZED;

	public SenhaInvalidaException() {
		super(SenhaInvalidaException.MENSAGEM, SenhaInvalidaException.STATUS_CODE);
	}
}
