package tk.leooresende.crudusuarios.infra.dto;

public class AlterarSenhaDto {
	private final String mensagem = "Senha alterada com sucesso!";
	private UsuarioDto usuario;

	public AlterarSenhaDto(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

}
