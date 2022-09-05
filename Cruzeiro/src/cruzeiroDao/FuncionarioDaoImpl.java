package cruzeiroDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cruzeiroModel.Funcionario;

public class FuncionarioDaoImpl implements FuncionarioDao {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void salvar(Funcionario funcionario) throws SQLException {
		String sql = "insert into funcionarios(endereco, senha, fk_Pessoas_id) values(?, ?, ?)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, funcionario.getEndereco());
			ps.setString(2, funcionario.getSenha());
			ps.setInt(3, funcionario.getIdPessoa());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			funcionario.setId(rs.getInt(1));
		} catch (Exception e) {
			System.out.println("Erro ao salvar funcionario " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
	}

	@Override
	public void alterar(Funcionario funcionario) throws SQLException {
		String sql = "update funcionarios endereco=?, senha=? where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, funcionario.getEndereco());
			ps.setString(2, funcionario.getSenha());
			ps.setInt(3, funcionario.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao alterar funcionario " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		String sql = "delete from funcionarios where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao excluir funcionarios " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public Funcionario pesquisarPorId(Integer id) throws SQLException {
		Funcionario funcionario = null;
		String sql = "select * from funcionarios where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(id);
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setIdPessoa(rs.getInt("fk_Funcionarios_id"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar funcionario " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return funcionario;
	}

	@Override
	public List<Funcionario> pesquisarTudo() throws SQLException {
		List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario funcionario;
		String sql = "select * from funcionarios";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setIdPessoa(rs.getInt("fk_Funcionarios_id"));
				
				funcionarios.add(funcionario);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar funcionarios " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return funcionarios;
	}

	@Override
	public List<Funcionario> pesquisarPorNome(String nome) throws SQLException {
		List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario funcionario;
		String sql = "select * from funcionarios where nome like ?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + nome + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setIdPessoa(rs.getInt("fk_Funcionarios_id"));
				
				funcionarios.add(funcionario);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar funcionarios pelo nome " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return funcionarios;
	}

}
