package cruzeiroDao;

import java.sql.SQLException;
import java.util.List;

import cruzeiroModel.Rota;

public interface RotaDao {
	
	void salvar(Rota rota) throws SQLException;
	
	void alterar(Rota rota) throws SQLException;
	
	void excluir(Integer id) throws SQLException;
	
	Rota pesquisarPorId(Integer id) throws SQLException;
	
//	List<Rota> pesquisarLocar(String local) throws SQLException;
	
	void registrarPedido(Integer idCliente, Integer idRota) throws SQLException;

}
