package cruzeiroController;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.print.attribute.standard.DateTimeAtCompleted;

import cruzeiroDao.FabricaConexao;
import javafx.fxml.Initializable;

public class RegistrarRotaControler implements Initializable {
	
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public String local;
	public String rota;
	public Date dataSaida;
	public Date dataVolta;
	
	
	public void registrarRota() throws SQLException {
		String sql = "insert into rotas(local, rota, data_horaIda, data_horaVolta) where value(?, ?, ?, ?)";
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, local);
			ps.setString(2, rota);
			ps.setDate(3, dataSaida);
			ps.setDate(4, dataVolta);
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			rs.next();
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
