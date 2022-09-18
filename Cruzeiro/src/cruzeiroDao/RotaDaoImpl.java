package cruzeiroDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cruzeiroModel.Rota;

public class RotaDaoImpl implements RotaDao{
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void salvar(Rota rota) throws SQLException {
		String sql = "insert into rota(local, rota, data_saida, hora_saida, data_volta, hora_volta, preco, fk_Funcionarios_id)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, rota.getLocal());
			ps.setString(2, rota.getRota());
			ps.setDate(3, rota.getDataSaida());
			ps.setTime(4, rota.getHoraSaida());
			ps.setDate(5, rota.getDataVolta());
			ps.setTime(6, rota.getHoraVolta());
			ps.setDouble(7, rota.getPreco());
			ps.setInt(8, rota.getIdFuncionario());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			rota.setId(rs.getInt(1));
		} catch (Exception e) {
			System.out.println("Erro ao salvar rota " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
		
	}

	@Override
	public void alterar(Rota rota) throws SQLException {
		String sql = "update rota set local=?, rota=?, data_saida=?, hora_saida=?, data_volta=?, hora_volta=?, preco=? where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, rota.getLocal());
			ps.setString(2, rota.getRota());
			ps.setDate(3, rota.getDataSaida());
			ps.setTime(4, rota.getHoraSaida());
			ps.setDate(5, rota.getDataVolta());
			ps.setTime(6, rota.getHoraVolta());
			ps.setDouble(7, rota.getPreco());
			ps.setInt(8, rota.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao alterar rota " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		String sql = "delete from rota where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao excluir rota " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public Rota pesquisarPorId(Integer id) throws SQLException {
		Rota rota = null;
		String sql = "select * from rota where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				rota = new Rota();
				rota.setId(id);
				rota.setLocal(rs.getString("local"));
				rota.setRota(rs.getString("rota"));
				rota.setDataSaida(rs.getDate("data_saida"));
				rota.setHoraSaida(rs.getTime("hora_saida"));
				rota.setDataVolta(rs.getDate("data_volta"));
				rota.setHoraVolta(rs.getTime("hora_volta"));
				rota.setPreco(rs.getDouble("preco"));
				rota.setIdFuncionario(rs.getInt("fk_Funcionarios_id"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar rota " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return rota;
	}

//	@Override
//	public List<Rota> pesquisarLocal(String local) throws SQLException {
//		
//		return null;
//	}
	
	public void registrarPedido(Integer idCliente, Integer idRota) throws SQLException {
		String sql = "insert into realiza(fk_Clientes_id, fk_Rota_id) values(?, ?)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCliente);
			ps.setInt(2, idRota);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao solicitar rota " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

}
