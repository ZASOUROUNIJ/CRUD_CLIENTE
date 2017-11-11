package br.com.sees.cliente.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import br.com.sees.model.ClienteModel;
import br.com.sees.repository.ClienteRepository;
import br.com.sees.usuario.controller.UsuarioController;
import br.com.sees.uteis.Uteis;
 
@Named(value="cadastrarClienteController")
@RequestScoped
public class CadastrarClienteController {
 
	@Inject
	ClienteModel clienteModel;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	ClienteRepository clienteRepository;
 
 
	public ClienteModel getClienteModel() {
		return clienteModel;
	}
 
	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void SalvarNovoCliente(){
 
		clienteModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
 
		clienteRepository.SalvarNovoRegistro(this.clienteModel);
 
		this.clienteModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
 
}