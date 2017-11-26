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
@Table(name="salgado")
@NamedQueries({
	 
	@NamedQuery(name = "SalgadoEntity.findAll",query= "SELECT p FROM SalgadoEntity p")
 
})
public class SalgadoEntity {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer codigo;
 
	@Column(name = "nome")
	private String  nome;
 
	@Column(name = "tipo")
	private String  tipo;
 
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;
 
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

 
}
