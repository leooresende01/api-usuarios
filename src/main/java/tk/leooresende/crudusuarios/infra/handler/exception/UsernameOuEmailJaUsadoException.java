package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class UsernameOuEmailJaUsadoException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Esse username ou email já está sendo utilizado por outro usuario";
	private static final HttpStatus STATUS_CODE = HttpStatus.BAD_REQUEST;

	public UsernameOuEmailJaUsadoException() {
		super(UsernameOuEmailJaUsadoException.MENSAGEM, UsernameOuEmailJaUsadoException.STATUS_CODE);
	}
}
