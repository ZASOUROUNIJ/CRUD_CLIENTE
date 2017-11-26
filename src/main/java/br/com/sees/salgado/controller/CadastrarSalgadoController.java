package br.com.sees.salgado.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sees.model.SalgadoModel;
import br.com.sees.repository.SalgadoRepository;
import br.com.sees.usuario.controller.UsuarioController;
import br.com.sees.uteis.Uteis;

@Named(value="cadastrarSalgadoController")
@RequestScoped
public class CadastrarSalgadoController {
	@Inject
	SalgadoModel salgadoModel;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	SalgadoRepository salgadoRepository;
 
 
	public SalgadoModel getSalgadoModel() {
		return salgadoModel;
	}
 
	public void setSalgadoModel(SalgadoModel salgadoModel) {
		this.salgadoModel = salgadoModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void SalvarNovoSalgado(){
 
		salgadoModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
 
		salgadoRepository.SalvarNovoRegistro(this.salgadoModel);
 
		this.salgadoModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
}
