package tk.leooresende.crudusuarios.infra.dto.formularios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeletarUsuarioForm {
	@NotBlank
	@Size(min = 8, max = 25)
	private String password;

	public DeletarUsuarioForm() {
	}

	public DeletarUsuarioForm(@NotBlank @Size(min = 8, max = 25) String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
