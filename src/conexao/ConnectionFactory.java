package conexao;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectionFactory {
	
	
	
	public Connection getConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/sees?useSSL=false&serverTimezone=UTC","root","root");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
}
