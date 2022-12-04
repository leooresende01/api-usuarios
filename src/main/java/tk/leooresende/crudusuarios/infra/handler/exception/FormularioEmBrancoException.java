package tk.leooresende.crudusuarios.infra.handler.exception;

import org.springframework.http.HttpStatus;

public class FormularioEmBrancoException extends ErrorException {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Algum valor do formulario está em branco";
	private static final HttpStatus STATUS_CODE = HttpStatus.BAD_REQUEST;
	
	public FormularioEmBrancoException() {
		super(FormularioEmBrancoException.MENSAGEM, FormularioEmBrancoException.STATUS_CODE);
	}

}
