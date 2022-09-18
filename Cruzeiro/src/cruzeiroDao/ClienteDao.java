package cruzeiroDao;

import java.sql.SQLException;
import java.util.List;

import cruzeiroModel.Cliente;
import cruzeiroModel.Evento;
import cruzeiroModel.Menu;
import cruzeiroModel.Pessoa;
import cruzeiroModel.Rota;

public interface ClienteDao {
	
	void alterar(Cliente cliente, Pessoa pessoa) throws SQLException;
	
	void excluirCliente(Cliente cliente, int idade) throws SQLException;
	
	Cliente pesquisarPorId(Integer id, int idade) throws SQLException;
	
	List<Cliente> pesquisarTudoCliente() throws SQLException;
	
	List<Cliente> pesquisarTudoClienteMenor() throws SQLException;
	
	void pedirMenu(Cliente cliente, Menu menu, int quantidade) throws SQLException;
	
	void pedirRota(Cliente cliente, Rota rota) throws SQLException;
	
	void participarEvento(Cliente cliente, Evento evento) throws SQLException;

}
