package br.com.sees.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sees.model.ClienteModel;
import br.com.sees.model.UsuarioModel;
import br.com.sees.repository.entity.ClienteEntity;
import br.com.sees.repository.entity.UsuarioEntity;
import br.com.sees.uteis.Uteis;

public class ClienteRepository {
	
	 
	 
		@Inject
		ClienteEntity clienteEntity;
	 
		EntityManager entityManager;
	 
		/***
		 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
		 * @param clienteModel
		 */
		public void SalvarNovoRegistro(ClienteModel clienteModel){
	 
			entityManager =  Uteis.JpaEntityManager();
	 
			clienteEntity = new ClienteEntity();
			
			clienteEntity.setCpf(clienteModel.getCpf());
			clienteEntity.setNome(clienteModel.getNome());
			clienteEntity.setLogin(clienteModel.getLogin());
			clienteEntity.setSenha(clienteModel.getSenha());
			clienteEntity.setEndereco(clienteModel.getEndereco());
			clienteEntity.setTelefone(clienteModel.getTelefone());
	 
			UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, clienteModel.getUsuarioModel().getCodigo()); 
	 
			clienteEntity.setUsuarioEntity(usuarioEntity);
	 
			entityManager.persist(clienteEntity);
	 
		}
		
		/***
		 * MÉTODO PARA CONSULTAR A PESSOA
		 * @return
		 */
		public List<ClienteModel> GetClientes(){
	 
			List<ClienteModel> clientesModel = new ArrayList<ClienteModel>();
	 
			entityManager =  Uteis.JpaEntityManager();
	 
			Query query = entityManager.createNamedQuery("ClienteEntity.findAll");
	 
			@SuppressWarnings("unchecked")
			Collection<ClienteEntity> clientesEntity = (Collection<ClienteEntity>)query.getResultList();
	 
			ClienteModel clienteModel = null;
	 
			for (ClienteEntity clienteEntity : clientesEntity) {
	 
				clienteModel = new ClienteModel();
				clienteModel.setCodigo(clienteEntity.getCodigo());
				clienteModel.setCpf(clienteEntity.getCpf());
				clienteModel.setNome(clienteEntity.getNome());
				clienteModel.setLogin(clienteEntity.getLogin());
				clienteModel.setSenha(clienteEntity.getSenha());
				clienteModel.setEndereco(clienteEntity.getEndereco());
				clienteModel.setTelefone(clienteEntity.getTelefone());
	 
	 
				UsuarioEntity usuarioEntity =  clienteEntity.getUsuarioEntity();			
	 
				UsuarioModel usuarioModel = new UsuarioModel();
				usuarioModel.setUsuario(usuarioEntity.getUsuario());
	 
				clienteModel.setUsuarioModel(usuarioModel);
	 
				clientesModel.add(clienteModel);
			}
	 
			return clientesModel;
	 
		}
		
		/***
		 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
		 * @param codigo
		 * @return
		 */
		private ClienteEntity GetCliente(int codigo){
	 
			entityManager =  Uteis.JpaEntityManager();
	 
			return entityManager.find(ClienteEntity.class, codigo);
		}
		
		/***
		 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
		 * @param clienteModel
		 */
		public void AlterarRegistro(ClienteModel clienteModel){
	 
			entityManager =  Uteis.JpaEntityManager();
	 
			ClienteEntity clienteEntity = this.GetCliente(clienteModel.getCodigo());
			
			clienteEntity.setCpf(clienteModel.getCpf());
			clienteEntity.setNome(clienteModel.getNome());
			clienteEntity.setLogin(clienteModel.getLogin());
			clienteEntity.setSenha(clienteModel.getSenha());
			clienteEntity.setEndereco(clienteModel.getEndereco());
			clienteEntity.setTelefone(clienteModel.getTelefone());
	 
			entityManager.merge(clienteEntity);
		}
		
		/***
		 * EXCLUI UM REGISTRO DO BANCO DE DADOS
		 * @param codigo
		 */
		public void ExcluirRegistro(int codigo){
	 
			entityManager =  Uteis.JpaEntityManager();		
	 
			ClienteEntity clienteEntity = this.GetCliente(codigo);
	 
			entityManager.remove(clienteEntity);
		}
	}
