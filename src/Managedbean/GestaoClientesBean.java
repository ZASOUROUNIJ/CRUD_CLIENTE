package Managedbean;

import conexao.ClienteDao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class GestaoClientesBean {

	private Cliente cliente;
	private ClienteDao clienteDao;
	private List<Cliente> clientes;
	private List<Cliente> clientesBusca;
	
	
	public List<Cliente> getClientes() {
		return this.clientes = this.clienteDao.listar();
	}
	
	public List<Cliente> getClientesBusca() {
		return this.clientesBusca = this.clienteDao.buscar(this.cliente.getCpf());
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public GestaoClientesBean(){
		this.clientes = new ArrayList<Cliente>();
		this.clienteDao = new ClienteDao();
		this.cliente = new Cliente();
	}
	
	public void incluir(){
		this.clienteDao.adiciona(this.cliente);
		this.cliente = new Cliente();
	}

	public void altera(){
		this.clienteDao.altera(this.cliente);
		this.cliente = new Cliente();
	}
	
	public void excluir(){
		this.clienteDao.revome(this.cliente);
		this.cliente = new Cliente();
	}
}
