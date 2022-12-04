package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class CodigoDeVerificacaoInvalidoException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Esse codigo é invalido";
	private static final HttpStatus STATUS_CODE = HttpStatus.BAD_REQUEST;
	
	public CodigoDeVerificacaoInvalidoException() {
		super(CodigoDeVerificacaoInvalidoException.MENSAGEM, CodigoDeVerificacaoInvalidoException.STATUS_CODE);
	}
}
