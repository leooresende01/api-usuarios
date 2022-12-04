package tk.leooresende.crudusuarios.infra;

import org.springframework.http.HttpStatus;

import tk.leooresende.crudusuarios.infra.handler.exception.ErrorException;

public class SenhaNaoMudouException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "A nova senha deve ser diferente da antiga";
	private static final HttpStatus STATUS_CODE = HttpStatus.BAD_REQUEST;
	
	public SenhaNaoMudouException() {
		super(SenhaNaoMudouException.MENSAGEM, SenhaNaoMudouException.STATUS_CODE);
	}
	
	
}
