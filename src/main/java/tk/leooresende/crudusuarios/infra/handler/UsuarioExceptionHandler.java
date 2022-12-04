package tk.leooresende.crudusuarios.infra.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import tk.leooresende.crudusuarios.infra.handler.dto.MensagemDeErroDto;
import tk.leooresende.crudusuarios.infra.handler.dto.MensagemValidacaoErroDto;
import tk.leooresende.crudusuarios.infra.handler.exception.ErrorException;

@RestControllerAdvice
public class UsuarioExceptionHandler {
	private final String metodoHttpNaoDisponivelMensagem = "Esse metodo HTTP não está disponivel para essa rota";
	private final String dadosNaoEnviadosMensagem = "Alguns dados não foram enviados ou são invalidos";
	private final String corpoDaRequisicaoVazioMensagem = "O corpo da requisição não pode ser vazio e deve ser um JSON";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<MensagemValidacaoErroDto>> tratarErrosDeValidacaoDoFormulario(
			MethodArgumentNotValidException ex) {
		List<MensagemValidacaoErroDto> validacoesMensagemDto = ex.getFieldErrors().stream()
				.map(objError -> new MensagemValidacaoErroDto(objError.getField(), objError.getDefaultMessage()))
				.toList();
		return ResponseEntity.badRequest().body(validacoesMensagemDto);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class, NumberFormatException.class })
	public ResponseEntity<MensagemDeErroDto> tratarDadoEnviadoInvalido(Exception ex) {
		MensagemDeErroDto mensagemDeErroDto = new MensagemDeErroDto(this.dadosNaoEnviadosMensagem);
		return ResponseEntity.badRequest().body(mensagemDeErroDto);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<MensagemDeErroDto> tratarNenhumDadoFoiEnviadoNoCorpo(HttpMessageNotReadableException  ex) {
		MensagemDeErroDto mensagemDeErroDto = new MensagemDeErroDto(this.corpoDaRequisicaoVazioMensagem);
		return ResponseEntity.badRequest().body(mensagemDeErroDto);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<MensagemDeErroDto> tratarMetodoHttpNaoPermitido(HttpRequestMethodNotSupportedException ex) {
		MensagemDeErroDto mensagemDeErroDto = new MensagemDeErroDto(this.metodoHttpNaoDisponivelMensagem);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(mensagemDeErroDto);
	}
	
	@ExceptionHandler(ErrorException.class)
	public ResponseEntity<MensagemDeErroDto> tratarErrosDeExcessoesPersonalizadas(ErrorException ex) {
		MensagemDeErroDto mensagemDeErroDto = new MensagemDeErroDto(ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(mensagemDeErroDto);
	}
}
