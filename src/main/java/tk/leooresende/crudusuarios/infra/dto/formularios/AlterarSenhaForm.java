package tk.leooresende.crudusuarios.infra.dto.formularios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tk.leooresende.crudusuarios.model.Usuario;

public class AlterarSenhaForm {
	@NotBlank
	@Size(min = 8, max = 25)
	private String senhaAtual;
	@NotBlank
	@Size(min = 8, max = 25)
	private String novaSenha;

	public AlterarSenhaForm() {
	}

	public AlterarSenhaForm(@NotBlank @Size(min = 8, max = 25) String senhaAtual,
			@NotBlank @Size(min = 8, max = 25) String novaSenha) {
		this.senhaAtual = senhaAtual;
		this.novaSenha = novaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public void alterarSenhaDoUsuario(Usuario usuarioComASenhaAntiga) {
		usuarioComASenhaAntiga.setPassword(novaSenha);
	}
}
