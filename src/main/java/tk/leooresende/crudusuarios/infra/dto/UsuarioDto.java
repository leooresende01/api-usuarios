package tk.leooresende.crudusuarios.infra.dto;

import java.util.List;

import tk.leooresende.crudusuarios.infra.util.UsuarioUtil;
import tk.leooresende.crudusuarios.model.Situacao;
import tk.leooresende.crudusuarios.model.Usuario;

public class UsuarioDto {
	private Integer id;
	private String username;
	private String nomeCompleto;
	private String email;
	private Situacao situacao;
	private String dataDeRegistro;
	private String ultimaAlteracao;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.username = usuario.getUsername();
		this.nomeCompleto = usuario.getNomeCompleto();
		this.email = usuario.getEmail();
		this.situacao = usuario.getSituacao();
		this.dataDeRegistro = UsuarioUtil.pegarDataFormatada(usuario.getDataDeRegistro());
		this.ultimaAlteracao = UsuarioUtil.pegarDataFormatada(usuario.getUltimaAlteracao());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro(String dataDeRegistro) {
		this.dataDeRegistro = dataDeRegistro;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public static List<UsuarioDto> mapearListaDeUsuariosParaDto(List<Usuario> usuarios) {
		return usuarios.stream()
				.map(UsuarioDto::new).toList();
	}
}
