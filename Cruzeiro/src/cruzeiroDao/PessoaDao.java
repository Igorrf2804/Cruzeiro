package cruzeiroDao;

import java.sql.SQLException;
import java.util.List;

import cruzeiroModel.Pessoa;

public interface PessoaDao {

	Pessoa salvarPessoa(Pessoa pessoa) throws SQLException;
	
	void alterarPessoa(Pessoa pessoa) throws SQLException;
	
	void excluirPessoa(Integer id) throws SQLException;
	
	Pessoa pesquisarPorIdPessoa(Integer id) throws SQLException;
	
	List<Pessoa> pesquisarTodasPessoas() throws SQLException;
	
	List<Pessoa> pesquisarPorNome(String nome) throws SQLException;
	
	List<Pessoa> pesquisarPorCpf(String cpf) throws SQLException;
	
}
