package cruzeiroDao;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class RegistrarRotaControler implements Initializable {
	
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	
	
	public void registrarRota() throws SQLException {
		String sql = "insert into rotas() where value()";
		try {
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
