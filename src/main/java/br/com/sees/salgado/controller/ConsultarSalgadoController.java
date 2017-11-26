package br.com.sees.salgado.controller;

import java.io.Serializable;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import br.com.sees.model.SalgadoModel;
import br.com.sees.repository.SalgadoRepository;
 
@Named(value="consultarSalgadoController")
@ViewScoped
public class ConsultarSalgadoController implements Serializable  {
	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private SalgadoModel salgadoModel;
 
	@Produces 
	private List<SalgadoModel> salgados;
 
	@Inject transient
	private SalgadoRepository salgadoRepository;
 
	public List<SalgadoModel> getSalgados() {
		return salgados;
	}
	public void setSalgados(List<SalgadoModel> salgados) {
		this.salgados = salgados;
	}		
	public SalgadoModel getSalgadoModel() {
		return salgadoModel;
	}
	public void setPessoaModel(SalgadoModel salgadoModel) {
		this.salgadoModel = salgadoModel;
	}
 
	/***
	 * CARREGA AS PESSOAS NA INICIALIZA��O 
	 */
	@PostConstruct
	public void init(){
 
		//RETORNAR AS PESSOAS CADASTRADAS
		this.salgados = salgadoRepository.GetSalgados();
	}
	/***
	 * CARREGA INFORMA��ES DE UMA PESSOA PARA SER EDITADA
	 * @param pessoaModel
	 */
	public void Editar(SalgadoModel salgadoModel){
 
		this.salgadoModel = salgadoModel;
 
	}
 
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
 
		this.salgadoRepository.AlterarRegistro(this.salgadoModel);	
 
 
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
 
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param pessoaModel
	 */
	public void ExcluirSalgado(SalgadoModel salgadoModel){
 
		//EXCLUI A PESSOA DO BANCO DE DADOS
		this.salgadoRepository.ExcluirRegistro(salgadoModel.getCodigo());
 
		//REMOVENDO A PESSOA DA LISTA
		//ASSIM QUE � A PESSOA � REMOVIDA DA LISTA O DATATABLE � ATUALIZADO
		this.salgados.remove(salgadoModel);
 
	}
}
