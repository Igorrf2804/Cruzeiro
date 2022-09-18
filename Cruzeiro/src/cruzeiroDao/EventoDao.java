package cruzeiroDao;

import java.sql.SQLException;
import java.util.List;

import cruzeiroModel.Evento;

public interface EventoDao {
	
	void salvar(Evento evento) throws SQLException;
	
	void alterar(Evento evento) throws SQLException;
	
	void excluir(Integer id) throws SQLException;
	
	Evento pesquisarPorId(Integer id) throws SQLException;
	
	List<Evento> pesquisarTudo() throws SQLException;

	void registrarParticipacao(Integer idCliente, Integer idEvento) throws SQLException;
}
