package cruzeiroDao;

import java.sql.SQLException;
import java.util.List;

import cruzeiroModel.Funcionario;

public interface FuncionarioDao {
	
	void salvar(Funcionario funcionario) throws SQLException;
	
	void alterar(Funcionario funcionario) throws SQLException;
	
	void excluir(Integer id) throws SQLException;
	
	Funcionario pesquisarPorId(Integer id) throws SQLException;
	
	List<Funcionario> pesquisarTudo() throws SQLException;
	
	List<Funcionario> pesquisarPorNome(String nome) throws SQLException;

}
