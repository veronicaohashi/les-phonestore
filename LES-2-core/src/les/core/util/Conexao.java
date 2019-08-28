
package les.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		
		String usuario = "admin";
		String senha = "admin";
		String url = "jdbc:postgresql://localhost:5432/les_development";

		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, usuario, senha);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return conn;	
		
		

	}

}

