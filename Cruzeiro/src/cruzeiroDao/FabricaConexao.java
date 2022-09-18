package cruzeiroDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class FabricaConexao {
	
	public static Connection abrirConexao() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 return DriverManager.getConnection("jdbc:mysql://localhost:3306/blue_route?useTimezone=true&serverTimezone=UTC", "root", "c6-alN6zd");
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao conectar com Banco de Dados");
		}
		return null;
	}
	
	public static void fecharConexao(Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException {
        
        if(rs!=null) {
            rs.close();
        }
        conn.close();
        psmt.close();
    }

}
