package tk.leooresende.crudusuarios.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USUARIO_ID")
	private Integer id;

	@Column(name = "NOME_DE_USUARIO")
	private String username;

	@Column(name = "SENHA", nullable = false)
	private String password;

	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "SITUACAO")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@Column(name = "DATA_DE_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataDeRegistro;

	@Column(name = "ULTIMA_MODIFICACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime ultimaAlteracao;

	@Column(name = "USUARIO_PERFIL")
	@Enumerated(EnumType.STRING)
	private Perfil perfilDoUsuario = Perfil.ROLE_USUARIO;

	public Usuario() {
	}

	public Usuario(String username, String password, String nomeCompleto, String email, Situacao situacao,
			LocalDateTime dataDeRegistro, LocalDateTime ultimaAlteracao) {
		this.username = username;
		this.password = password;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.situacao = situacao;
		this.dataDeRegistro = dataDeRegistro;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Perfil getPerfilDoUsuario() {
		return perfilDoUsuario;
	}

	public void setPerfilDoUsuario(Perfil perfilDoUsuario) {
		this.perfilDoUsuario = perfilDoUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public LocalDateTime getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro(LocalDateTime dataDeRegistro) {
		this.dataDeRegistro = dataDeRegistro;
	}

	public LocalDateTime getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void definirOUsuarioComoAtivo() {
		this.situacao = Situacao.ATIVO;
	}
}
