package tk.leooresende.crudusuarios.infra.dto.formularios;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tk.leooresende.crudusuarios.model.Usuario;

public class UsuarioAtualizadoForm {
	@NotBlank
	@Size(min = 8, max = 25)
	private String username;

	@NotBlank
	private String nomeCompleto;

	@NotBlank
	@Email
	private String email;

	public UsuarioAtualizadoForm() {
	}

	public UsuarioAtualizadoForm(@NotBlank @Size(min = 8, max = 25) String username, @NotBlank String nomeCompleto,
			@NotBlank @Email String email) {
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void atualizarInformacoesDoUsuarioApartirDoFormulario(Usuario usuario) {
		usuario.setNomeCompleto(this.nomeCompleto);
		usuario.setUltimaAlteracao(LocalDateTime.now());
	}
}
