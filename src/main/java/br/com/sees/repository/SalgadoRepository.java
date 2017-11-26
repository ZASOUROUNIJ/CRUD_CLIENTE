package br.com.sees.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sees.model.SalgadoModel;
import br.com.sees.model.UsuarioModel;
import br.com.sees.repository.entity.SalgadoEntity;
import br.com.sees.repository.entity.UsuarioEntity;
import br.com.sees.uteis.Uteis;

public class SalgadoRepository {
	@Inject
	SalgadoEntity salgadoEntity;
 
	EntityManager entityManager;
 
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * @param clienteModel
	 */
	public void SalvarNovoRegistro(SalgadoModel salgadoModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		salgadoEntity = new SalgadoEntity();
		
		salgadoEntity.setNome(salgadoModel.getNome());
		salgadoEntity.setTipo(salgadoModel.getTipo());
 
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, salgadoModel.getUsuarioModel().getCodigo()); 
 
		salgadoEntity.setUsuarioEntity(usuarioEntity);
 
		entityManager.persist(salgadoEntity);
 
	}
	
	/***
	 * MÉTODO PARA CONSULTAR A PESSOA
	 * @return
	 */
	public List<SalgadoModel> GetSalgados(){
 
		List<SalgadoModel> salgadosModel = new ArrayList<SalgadoModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("SalgadoEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<SalgadoEntity> salgadosEntity = (Collection<SalgadoEntity>)query.getResultList();
 
		SalgadoModel salgadoModel = null;
 
		for (SalgadoEntity salgadoEntity : salgadosEntity) {
 
			salgadoModel = new SalgadoModel();
			salgadoModel.setCodigo(salgadoEntity.getCodigo());
			salgadoModel.setNome(salgadoEntity.getNome());
			salgadoModel.setTipo(salgadoEntity.getTipo());
 
 
			UsuarioEntity usuarioEntity =  salgadoEntity.getUsuarioEntity();			
 
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
 
			salgadoModel.setUsuarioModel(usuarioModel);
 
			salgadosModel.add(salgadoModel);
		}
 
		return salgadosModel;
 
	}
	
	/***
	 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	private SalgadoEntity GetSalgado(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(SalgadoEntity.class, codigo);
	}
	
	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param clienteModel
	 */
	public void AlterarRegistro(SalgadoModel salgadoModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		SalgadoEntity salgadoEntity = this.GetSalgado(salgadoModel.getCodigo());
		
		salgadoEntity.setNome(salgadoModel.getNome());
		salgadoEntity.setTipo(salgadoModel.getTipo());
 
		entityManager.merge(salgadoEntity);
	}
	
	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();		
 
		SalgadoEntity salgadoEntity = this.GetSalgado(codigo);
 
		entityManager.remove(salgadoEntity);
	}
}
