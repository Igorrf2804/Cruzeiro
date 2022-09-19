package cruzeiroDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import cruzeiroModel.Rota;

public class RotaDaoImpl implements RotaDao{
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private java.util.Date dSaida;
	private java.util.Date dVolta;
	private java.time.LocalTime hSaida;
	private java.time.LocalTime hVolta;
	
	private java.sql.Date dataSaida;
	private java.sql.Date dataVolta;
	private java.sql.Time horaSaida;
	private java.sql.Time horaVolta;

//	@Override
//	public void salvar(Rota rota) throws SQLException {
//		String sql = "insert into Rota(local, rota, data_saida, hora_saida, data_volta, hora_volta, preco)";
//		
//		try {
//			
//			dataSaida = new java.sql.Date(rota.getDataSaida().getTime());
//			dataVolta = new java.sql.Date(rota.getDataVolta().getTime());
//			horaSaida = Time.valueOf(rota.getHoraSaida());
//			horaVolta = Time.valueOf(rota.getHoraVolta());
//			
//			conn = FabricaConexao.abrirConexao();
//			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, rota.getLocal());
//			ps.setString(2, rota.getRota());
//			ps.setDate(3, dataSaida);
//			ps.setTime(4, horaSaida);
//			ps.setDate(5, dataVolta);
//			ps.setTime(6, horaVolta);
//			ps.setDouble(7, rota.getPreco());
//			ps.setInt(8, rota.getIdFuncionario());
//			
//			ps.executeUpdate();
//			rs = ps.getGeneratedKeys();
//			rs.next();
//			rota.setId(rs.getInt(1));
//		} catch (Exception e) {
//			System.out.println("Erro ao salvar rota " + e.getMessage());
//		} finally {
//			FabricaConexao.fecharConexao(conn, ps, rs);
//		}
//		
//		
//	}

	@Override
	public void alterar(Rota rota) throws SQLException {
		String sql = "update Rota set local=?, rota=?, data_saida=?, hora_saida=?, data_volta=?, hora_volta=?, preco=? where id=?";
		
		try {
			
			dataSaida = new java.sql.Date(rota.getDataSaida().getTime());
			dataVolta = new java.sql.Date(rota.getDataVolta().getTime());
			horaSaida = Time.valueOf(rota.getHoraSaida());
			horaVolta = Time.valueOf(rota.getHoraVolta());
			
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, rota.getLocal());
			ps.setString(2, rota.getRota());
			ps.setDate(3, dataSaida);
			ps.setTime(4, horaSaida);
			ps.setDate(5, dataVolta);
			ps.setTime(6, horaVolta);
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
		String sql = "delete from Rota where id=?";
		
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
		String sql = "select * from Rota where id=?";
		
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
				
				dataSaida = (rs.getDate("data_saida"));
				dSaida = new java.util.Date(dataSaida.getTime());
				rota.setDataSaida(dSaida);
				
				horaSaida = (rs.getTime("hora_saida"));
				hSaida = horaSaida.toLocalTime();
				rota.setHoraSaida(hSaida);
				
				dataVolta = (rs.getDate("data_volta"));
				dVolta = new java.util.Date(dataVolta.getTime());
				rota.setDataVolta(dVolta);
				
				horaVolta = (rs.getTime("hora_volta"));
				hVolta = horaVolta.toLocalTime();
				rota.setHoraVolta(hVolta);
				
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
//	}
	
	public void registrarPedido(Integer idCliente, Integer idRota) throws SQLException {
		String sql = "insert into Realiza(fk_Clientes_id, fk_Rota_id) values(?, ?)";
		
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

	@Override
	public List<Rota> pesquisarTudo() throws SQLException {
		Rota rota;
		List<Rota> rotas = new ArrayList<>();
		String sql = "select * from Rota";
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				rota = new Rota();
				rota.setId(rs.getInt("id"));
				rota.setLocal(rs.getString("local"));
				rota.setRota(rs.getString("rota"));
				
				dataSaida = (rs.getDate("data_saida"));
				dSaida = new java.util.Date(dataSaida.getTime());
				rota.setDataSaida(dSaida);
				
				horaSaida = (rs.getTime("hora_saida"));
				hSaida = horaSaida.toLocalTime();
				rota.setHoraSaida(hSaida);
				
				dataVolta = (rs.getDate("data_volta"));
				dVolta = new java.util.Date(dataVolta.getTime());
				rota.setDataVolta(dVolta);
				
				horaVolta = (rs.getTime("hora_volta"));
				hVolta = horaVolta.toLocalTime();
				rota.setHoraVolta(hVolta);
				
				rota.setPreco(rs.getDouble("preco"));
				rota.setIdFuncionario(rs.getInt("fk_Funcionarios_id"));
				
				rotas.add(rota);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao realizar pesquisa de rotas " + e.getMessage());
		}
		return rotas;
	}

}
