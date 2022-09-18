package cruzeiroDao;

import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cruzeiroModel.Cliente;
import cruzeiroModel.Evento;
import cruzeiroModel.Menu;
import cruzeiroModel.Pessoa;
import cruzeiroModel.Rota;

public class ClienteDaoImpl implements ClienteDao {
	
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	PessoaDao pessoaDao;
	MenuDao menuDao;
	EventoDao eventoDao;
	RotaDao rotaDao;

	@Override
	public void alterar(Cliente cliente, Pessoa pessoa) throws SQLException {
		String sql;
		
		if (pessoa.getIdade() <=17) 
			sql = "update clientemenor set pacote=? where id=?";
		else
			sql = "update clientes set pacote=?, total_a_pagar=?, senha=? where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			if (pessoa.getIdade()<=17) {
				ps.setInt(1, cliente.getPacote());
				ps.setInt(2, cliente.getId());
			} else {
				ps.setInt(1, cliente.getPacote());
				ps.setDouble(2, cliente.getTotal());
				ps.setString(3, cliente.getSenha());
				ps.setInt(4, cliente.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Erro ao alterar cliente " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

	@Override
	public void excluirCliente(Cliente cliente, int idade) throws SQLException {
		String sql;
		pessoaDao = new PessoaDaoImpl();
		
		pessoaDao.excluirPessoa(cliente.getIdPessoa());
		
		if (idade<=17) 
			sql = "delete from clientemenor where id=?";
		else
			sql = "delete from clientes where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cliente.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("erro ao excluir cliente " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

	@Override
	public Cliente pesquisarPorId(Integer id, int idade) throws SQLException {
		Cliente cliente = null;
		String sql;
		
		if (idade<=17)
			sql = "select * from clientemenor where fk_Pessoas_id=?";
		else
			sql = "select * from clientes where fk_Pessoas_id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (idade<=17) {
				if (rs.next()) {
					cliente = new Cliente();
					cliente.setId(id);
					cliente.setPacote(rs.getInt("pacote"));
					cliente.setIdPessoa(rs.getInt("fk_Pessoas_id"));
				}
			} else {
				if (rs.next()) {
					cliente = new Cliente();
					cliente.setId(id);
					cliente.setPacote(rs.getInt("pacote"));
					cliente.setSenha(rs.getString("senha"));
					cliente.setTotal(rs.getDouble("total_a_pagar"));
					cliente.setIdPessoa(rs.getInt("fk_Pessoas_id"));
				}
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar cliente " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return cliente;
	}

	@Override
	public List<Cliente> pesquisarTudoCliente() throws SQLException {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente;
		String sql = "select * from clientes";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setPacote(rs.getInt("pacote"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setTotal(rs.getDouble("total_a_pagar"));
				cliente.setIdPessoa(rs.getInt("fk_Pessoas_id"));
				
				clientes.add(cliente);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar clientes " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return clientes;
	}

	@Override
	public List<Cliente> pesquisarTudoClienteMenor() throws SQLException {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente;
		String sql = "select * from clientemenor";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setPacote(rs.getInt("pacote"));
				cliente.setIdPessoa(rs.getInt("fk_Pessoas_id"));
				
				clientes.add(cliente);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar clientes menores " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return clientes;
	}

	public void pedirMenu(Cliente cliente, Menu menu, int quantidade) throws SQLException {
		String sql = "update clientes set total_a_pagar=? where id=?";
		cliente.setTotal(menu.getPreco()*quantidade);
		menuDao = new MenuDaoImpl();
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, cliente.getTotal());
			ps.setInt(2, cliente.getId());
			
			ps.executeUpdate();
			menuDao.registrarPedido(cliente.getId(), menu.getId(), quantidade);
		} catch (Exception e) {
			System.out.println("Erro ao pedir " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}
	
	public void pedirRota(Cliente cliente, Rota rota) throws SQLException {
		String sql = "update clientes set total_a_pagar=? where id=?";
		cliente.setTotal(rota.getPreco());
		rotaDao = new RotaDaoImpl();
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, cliente.getTotal());
			ps.setInt(2, cliente.getId());
			
			ps.executeUpdate();
			rotaDao.registrarPedido(cliente.getId(), rota.getId());
		} catch (Exception e) {
			System.out.println("Erro ao pedir rota " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}
	
	public void participarEvento(Cliente cliente, Evento evento) throws SQLException {
		String sql = "update clientes set total_a_pagar where id=?";
		cliente.setTotal(evento.getPreco());
		eventoDao = new EventoDaoImpl();
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, cliente.getTotal());
			ps.setInt(2, cliente.getId());
			
			ps.executeUpdate();
			eventoDao.registrarParticipacao(cliente.getId(), evento.getId());
		} catch (Exception e) {
			System.out.println("Erro ao solicitar participação no evento " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}
}
