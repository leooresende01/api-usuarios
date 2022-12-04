package tk.leooresende.crudusuarios.infra.dto;

public class EmailDto {
	private String destinatario;
	private String mensagem;
	private String assunto;

	public EmailDto(String destinatario, String mensagem, String assunto) {
		this.destinatario = destinatario;
		this.mensagem = mensagem;
		this.assunto = assunto;
	}

	public String getRecipient() {
		return destinatario;
	}

	public void setRecipient(String recipient) {
		this.destinatario = recipient;
	}

	public String getMsgBody() {
		return mensagem;
	}

	public void setMsgBody(String msgBody) {
		this.mensagem = msgBody;
	}

	public String getSubject() {
		return assunto;
	}

	public void setSubject(String subject) {
		this.assunto = subject;
	}
}
