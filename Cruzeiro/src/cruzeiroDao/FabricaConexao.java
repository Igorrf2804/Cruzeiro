package cruzeiroDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FabricaConexao {
	
	public static Connection abrirConexao() throws SQLException  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 return DriverManager.getConnection("jdbc:mysql://cruzeiro_db.mysql.dbaas.com.br/cruzeiro_db?autoReconnect=true&useSSL=false", "cruzeiro_db", "MyyLeqIu@DiX04");
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao conectar com Banco de Dados" + e.getMessage());
		}
		return null;
	}
//	jdbc:mysql://localhost:3306/cruzeiro_db
	
	public static void fecharConexao(Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException {
        
        if(rs!=null) {
            rs.close();
        }
        conn.close();
        psmt.close();
    }

}
	