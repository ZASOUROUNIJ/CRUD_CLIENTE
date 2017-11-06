package br.com.sees.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 
 
@Entity
@Table(name="clientePessoaFisica")
@NamedQueries({
	 
	@NamedQuery(name = "ClienteEntity.findAll",query= "SELECT p FROM ClienteEntity p")
 
})
public class ClienteEntity {
    
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer codigo;
 
	@Column(name = "cpf")
	private String  cpf;
 
	@Column(name = "nome")
	private String  nome;
 
	@Column(name = "login")
	private String login;
 
	@Column(name = "senha")
	private String  senha;
 
	@Column(name = "endereco")
	private String  endereco;
 
	@Column(name = "telefone")
	private String  telefone;
 
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
 
	
 
}
