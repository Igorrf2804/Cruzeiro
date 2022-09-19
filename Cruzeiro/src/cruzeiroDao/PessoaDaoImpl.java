package cruzeiroDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cruzeiroModel.Pessoa;

public class PessoaDaoImpl implements PessoaDao {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public Pessoa salvarPessoa(Pessoa pessoa) throws SQLException {
		String sql = "insert into Pessoas(nome, idade, cpf, email, telefone) values(?, ?, ?, ?, ?)";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getCpf());
			ps.setString(4, pessoa.getEmail());
			ps.setString(5, pessoa.getTelefone());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			pessoa.setId(rs.getInt(1));
			
		} catch (Exception e) {
			System.out.println("Erro ao salvar pessoa " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return pessoa;
	}

	@Override
	public void alterarPessoa(Pessoa pessoa) throws SQLException {
		String sql = "update Pessoas set nome=?, idade=?, cpf=?, email=?, telefone=? where id=?";
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getCpf());
			ps.setString(4, pessoa.getEmail());
			ps.setString(5, pessoa.getTelefone());
			ps.setInt(6, pessoa.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao alterar pessoa " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public void excluirPessoa(Integer id) throws SQLException {
		String sql = "delete from Pessoas where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao excluir pessoa " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
	}

	@Override
	public Pessoa pesquisarPorIdPessoa(Integer id) throws SQLException {
		Pessoa pessoa = null;
		String sql = "select * from Pessoas where id=?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(id);
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setTelefone(rs.getString("telefone"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pessoa " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		
		return pessoa;
	}

	@Override
	public List<Pessoa> pesquisarTodasPessoas() throws SQLException {
		List<Pessoa> pessoas = new ArrayList<>();
		Pessoa pessoa;
		String sql = "select * from Pessoas";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setTelefone(rs.getString("telefone"));
				
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pessoas " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return pessoas;
	}

	@Override
	public List<Pessoa> pesquisarPorNome(String nome) throws SQLException {
		List<Pessoa> pessoas = new ArrayList<>();
		Pessoa pessoa;
		String sql = "select * from Pessoas where nome like ?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setTelefone(rs.getString("telefone"));
				
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			System.out.println("Erro em pesquisar pessoas por nome " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return pessoas;
	}
	
	@Override
	public List<Pessoa> pesquisarPorCpf(String cpf) throws SQLException {
		List<Pessoa> pessoas = new ArrayList<>();
		Pessoa pessoa;
		String sql = "select * from Pessoas where cpf like ?";
		
		try {
			conn = FabricaConexao.abrirConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" +cpf + "%");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setTelefone(rs.getString("telefone"));
				
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			System.out.println("Erro em pesquisar pessoas por nome " + e.getMessage());
		} finally {
			FabricaConexao.fecharConexao(conn, ps, rs);
		}
		return pessoas;
	}

}
