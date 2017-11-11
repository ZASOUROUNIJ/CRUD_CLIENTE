package br.com.sees.cliente.controller;

import java.io.Serializable;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import br.com.sees.model.ClienteModel;
import br.com.sees.repository.ClienteRepository;
 
@Named(value="consultarClienteController")
@ViewScoped
public class ConsultarClienteController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject transient
	private ClienteModel clienteModel;
 
	@Produces 
	private List<ClienteModel> clientes;
 
	@Inject transient
	private ClienteRepository clienteRepository;
 
	public List<ClienteModel> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteModel> clientes) {
		this.clientes = clientes;
	}		
	public ClienteModel getClienteModel() {
		return clienteModel;
	}
	public void setPessoaModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}
 
	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
 
		//RETORNAR AS PESSOAS CADASTRADAS
		this.clientes = clienteRepository.GetClientes();
	}
	/***
	 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
	 * @param pessoaModel
	 */
	public void Editar(ClienteModel clienteModel){
 
		this.clienteModel = clienteModel;
 
	}
 
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
 
		this.clienteRepository.AlterarRegistro(this.clienteModel);	
 
 
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
 
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param pessoaModel
	 */
	public void ExcluirCliente(ClienteModel clienteModel){
 
		//EXCLUI A PESSOA DO BANCO DE DADOS
		this.clienteRepository.ExcluirRegistro(clienteModel.getCodigo());
 
		//REMOVENDO A PESSOA DA LISTA
		//ASSIM QUE É A PESSOA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.clientes.remove(clienteModel);
 
	}
}
