package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class UsuarioJaFoiRegistradoException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Esse username ou email já está sendo usado por outro usuario"; 
	private static final HttpStatus STATUS_CODE = HttpStatus.BAD_REQUEST;
	
	public UsuarioJaFoiRegistradoException() {
		super(UsuarioJaFoiRegistradoException.MENSAGEM, UsuarioJaFoiRegistradoException.STATUS_CODE);
	}
}
