package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Managedbean.Cliente;

public class ClienteDao {
	private Connection conexao;

	public ClienteDao() {

		this.conexao = new ConnectionFactory().getConnection();

	}

	public void adiciona(Cliente cliente) {

		String sql = "INSERT INTO clientePessoaFisica " + "(cpf, nome,  login, senha, endereco, telefone)" + "VALUES (?,?,?,?,?,?)";
		try {

			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getLogin());
			stmt.setString(4, cliente.getSenha());
			stmt.setString(5, cliente.getEndereco());
			stmt.setString(6, cliente.getTelefone());
			
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Cliente> listar() {

		try {

			// Cria uma lista do tipo cliente - Recebendo os clientes do banco
			List<Cliente> clientes = new ArrayList<Cliente>();

			// "Prepara" comando SQL a ser executato
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM clientePessoaFisica ");

			// Ler os resultados de uma consulta
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// Cria o objeto cliente
				Cliente cliente = new Cliente();

				// Pega os valores nas colunas do BD
				cliente.setId(rs.getInt("id"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));
				
				// Adiciona o objeto a lista
				clientes.add(cliente);
			}
			// Fecha o ResultSet
			rs.close();

			// Fecha o PreparedStatement
			stmt.close();

			// Retorna a lista de clientes
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Cliente cliente) {
		String sql = "UPDATE clientePessoaFisica SET cpf=?, nome=?, login=?," + "senha=?, endereco=?, telefone=? WHERE id=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getLogin());
			stmt.setString(4, cliente.getSenha());
			stmt.setString(5, cliente.getEndereco());
			stmt.setString(6, cliente.getTelefone());
			stmt.setInt(7, cliente.getId());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void revome(Cliente cliente) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM clientePessoaFisica WHERE cpf=?");
			stmt.setString(1, cliente.getCpf());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Cliente> buscar(String cpf) {
		try {

			// Cria uma lista do tipo cliente - Recebendo os clientes do banco
			List<Cliente> clientes = new ArrayList<Cliente>();

			// "Prepara" comando SQL a ser executato
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM clientePessoaFisica WHERE cpf=?");
			stmt.setString(1, cpf);
			// Ler os resultados de uma consulta
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// Cria o objeto cliente
				Cliente cliente = new Cliente();

				// Pega os valores nas colunas do BD
				cliente.setId(rs.getInt("id"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));
				
				// Adiciona o objeto a lista
				clientes.add(cliente);
			}
			// Fecha o ResultSet
			rs.close();

			// Fecha o PreparedStatement
			stmt.close();

			// Retorna a lista de clientes
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
