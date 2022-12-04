package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class ErrorException extends RuntimeException {
	private final String mensagem;
	private final HttpStatus statusCode;
	
	private static final long serialVersionUID = 1L;
	
	public ErrorException(String mensagem, HttpStatus statusCode) {
		this.mensagem = mensagem;
		this.statusCode = statusCode;
	}
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
}
