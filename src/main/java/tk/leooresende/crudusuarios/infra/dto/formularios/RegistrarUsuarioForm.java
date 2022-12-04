package tk.leooresende.crudusuarios.infra.dto.formularios;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tk.leooresende.crudusuarios.model.Situacao;
import tk.leooresende.crudusuarios.model.Usuario;

public class RegistrarUsuarioForm {
	@NotBlank
	@Size(min = 8, max = 25)
	private String username;

	@NotBlank
	@Size(min = 8, max = 25)
	private String password;

	@NotBlank
	private String nomeCompleto;

	@NotBlank
	@Email
	private String email;

	public RegistrarUsuarioForm(@NotBlank @Size(min = 8, max = 25) String username,
			@NotBlank @Size(min = 8, max = 25) String password, @NotBlank String nomeCompleto,
			@NotBlank @Email String email) {
		this.username = username;
		this.password = password;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
	}

	public RegistrarUsuarioForm() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Usuario criarUsuarioApartirDoFormulario() {
		LocalDateTime dataAtual = LocalDateTime.now();
		return new Usuario(this.username, this.password, this.nomeCompleto, this.email,
				Situacao.VERIFICACAO_DO_EMAIL_PENDENTE, dataAtual, dataAtual);
	}
}
