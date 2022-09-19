package cruzeiroDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cruzeiroModel.Menu;

public class MenuDaoImpl implements MenuDao {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public void salvar(Menu menu) throws SQLException {
		String sql = "insert into Menu(tipo, nome, descricao, ingredientes, preco) values(?, ?, ?, ?, ?)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, menu.getTipo());
			ps.setString(2, menu.getNome());
			ps.setString(3, menu.getDesc());
			ps.setString(4, menu.getIngredientes());
			ps.setDouble(5, menu.getPreco());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			rs.next();
			menu.setId(rs.getInt(1));
		} catch (Exception e) {
			System.out.println("Erro ao salvar menu " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public void alterar(Menu menu) throws SQLException {
		String sql = "update Menu set tipo=?, nome=?, descricao=?, ingredientes=?, preco=? where id=?";
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, menu.getTipo());
			ps.setString(2, menu.getNome());
			ps.setString(3, menu.getDesc());
			ps.setString(4, menu.getIngredientes());
			ps.setDouble(5, menu.getPreco());
			ps.setInt(6, menu.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao alterar menu " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		String sql = "delete from Menu where id=?";
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao excluir menu " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public Menu pesquisarPorId(Integer id) throws SQLException {
		Menu menu = null;
		String sql = "select * from Menu where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				menu = new Menu();
				menu.setId(id);
				menu.setTipo(rs.getInt("tipo"));
				menu.setNome(rs.getString("nome"));
				menu.setDesc(rs.getString("descricao"));
				menu.setIngredientes(rs.getString("ingredientes"));
				menu.setPreco(rs.getDouble("preco"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar menu " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return menu;
	}

	@Override
	public List<Menu> pesquisarTudo() throws SQLException {
		List<Menu> menuCompleto = new ArrayList<>();
		Menu menu;
		String sql = "select * from Menu";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setTipo(rs.getInt("tipo"));
				menu.setNome(rs.getString("nome"));
				menu.setDesc(rs.getString("descricao"));
				menu.setIngredientes(rs.getString("ingredientes"));
				menu.setPreco(rs.getDouble("preco"));
				
				menuCompleto.add(menu);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar menu completo " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return menuCompleto;
	}

	@Override
	public List<Menu> pesquisarPorTipo(Integer opcao) throws SQLException {
		List<Menu> menuPesquisado = new ArrayList<>();
		Menu menu;
		String sql = "select * from Menu where tipo=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, opcao);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setTipo(rs.getInt("tipo"));
				menu.setNome(rs.getString("nome"));
				menu.setDesc(rs.getString("descricao"));
				menu.setIngredientes(rs.getString("ingredientes"));
				menu.setPreco(rs.getDouble("preco"));
				
				menuPesquisado.add(menu);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar menu por tipo " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return menuPesquisado;
	}
	
	public void registrarPedido(Integer idCliente, Integer idMenu, int quantidade) throws SQLException {
		String sql = "insert into Consome(fk_Clientes_id, fk_Menu_id, quantidade) values(?, ?, ?)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCliente);
			ps.setInt(2, idMenu);
			ps.setInt(3, quantidade);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao registrar pedido " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

}
