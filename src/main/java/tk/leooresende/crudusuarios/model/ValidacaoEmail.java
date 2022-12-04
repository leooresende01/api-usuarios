package tk.leooresende.crudusuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "validacoes_email")
public class ValidacaoEmail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VALIDACAO_ID")
	private Integer id;

	@Column(name = "USUARIO_ID", nullable = false)
	private Integer idDoUsuario;

	@Column(name = "CODIGO_DE_VALIDACAO", nullable = false)
	private Integer codigoDeVerificacao;

	public ValidacaoEmail() {
	}

	public ValidacaoEmail(Integer idDoUsuario, Integer codigoDeVerificacao) {
		this.idDoUsuario = idDoUsuario;
		this.codigoDeVerificacao = codigoDeVerificacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDoUsuario() {
		return idDoUsuario;
	}

	public void setIdDoUsuario(Integer idDoUsuario) {
		this.idDoUsuario = idDoUsuario;
	}

	public Integer getCodigoDeVerificacao() {
		return codigoDeVerificacao;
	}

	public void setCodigoDeVerificacao(Integer codigoDeVerificacao) {
		this.codigoDeVerificacao = codigoDeVerificacao;
	}
}
