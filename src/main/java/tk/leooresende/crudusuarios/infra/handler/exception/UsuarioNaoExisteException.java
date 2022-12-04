package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNaoExisteException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Não existe nenhum usuario com esse username, e-mail ou id";
	private static final HttpStatus STATUS_CODE = HttpStatus.NOT_FOUND;

	public UsuarioNaoExisteException() {
		super(UsuarioNaoExisteException.MENSAGEM, UsuarioNaoExisteException.STATUS_CODE);
	}
}
