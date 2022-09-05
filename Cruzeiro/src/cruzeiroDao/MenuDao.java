package cruzeiroDao;

import java.sql.SQLException;
import java.util.List;

import cruzeiroModel.Menu;

public interface MenuDao {
	
	void salvar(Menu menu) throws SQLException;
	
	void alterar(Menu menu) throws SQLException;
	
	void excluir(Integer id) throws SQLException;
	
	Menu pesquisarPorId(Integer id) throws SQLException;
	
	List<Menu> pesquisarTudo() throws SQLException;
	
	List<Menu> pesquisarPorTipo(Integer opcao) throws SQLException;
	
	void registrarPedido(Integer idCliente, Integer idMenu, int quantidade) throws SQLException;

}
