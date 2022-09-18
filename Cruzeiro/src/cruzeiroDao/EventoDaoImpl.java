package cruzeiroDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cruzeiroModel.Evento;

public class EventoDaoImpl implements EventoDao {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void salvar(Evento evento) throws SQLException {
		String sql = "insert into evento(nome, descricao, data, hora, faixa_etaria, preco) values(?, ?, ?, ?, ?, ?)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, evento.getNome());
			ps.setString(2, evento.getDesc());
			ps.setDate(3, evento.getData());
			ps.setTime(4, evento.getHora());
			ps.setString(5, evento.getFaixaEtaria());
			ps.setDouble(6, evento.getPreco());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			evento.setId(rs.getInt(1));
		} catch (Exception e) {
			System.out.println("Erro ao salvar evento " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

	@Override
	public void alterar(Evento evento) throws SQLException {
		String sql = "update evento set nome=?, descricao=?, data=?, hora=?, faixa_etaria=?, preco=? where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, evento.getNome());
			ps.setString(2, evento.getDesc());
			ps.setDate(3, evento.getData());
			ps.setTime(4, evento.getHora());
			ps.setString(5, evento.getFaixaEtaria());
			ps.setDouble(6, evento.getPreco());
			ps.setInt(7, evento.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao alterar evento " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		String sql = "delete from evento where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao excluir evento " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

	@Override
	public Evento pesquisarPorId(Integer id) throws SQLException {
		Evento evento = null;
		String sql = "select * from evento where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				evento = new Evento();
				evento.setId(id);
				evento.setNome(rs.getString("nome"));
				evento.setDesc(rs.getString("descricao"));
				evento.setData(rs.getDate("data"));
				evento.setHora(rs.getTime("hora"));
				evento.setFaixaEtaria(rs.getString("faixa_etaria"));
				evento.setPreco(rs.getDouble("preco"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar evento " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return evento;
	}

	@Override
	public List<Evento> pesquisarTudo() throws SQLException {
		List<Evento> eventos = new ArrayList<>();
		Evento evento;
		String sql = "select * from evento";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				evento = new Evento();
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setDesc(rs.getString("descricao"));
				evento.setData(rs.getDate("data"));
				evento.setHora(rs.getTime("hora"));
				evento.setFaixaEtaria(rs.getString("faixa_etaria"));
				evento.setPreco(rs.getDouble("preco"));
				
				eventos.add(evento);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar eventos " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return eventos;
	}
	
	public void registrarParticipacao(Integer idCliente, Integer idEvento) throws SQLException {
		String sql = "insert into participa(fk_Clientes_id, fk_Evento_id) values(?, ?)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCliente);
			ps.setInt(2, idEvento);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao registrar participação " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

}
