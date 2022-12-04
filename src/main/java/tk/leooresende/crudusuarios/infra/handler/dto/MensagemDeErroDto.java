package tk.leooresende.crudusuarios.infra.handler.dto;

public class MensagemDeErroDto {
	private String mensagem;

	public MensagemDeErroDto(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
