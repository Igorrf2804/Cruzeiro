package cruzeiroController;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import cruzeiroDao.ClienteDao;
import cruzeiroDao.ClienteDaoImpl;
import cruzeiroDao.EventoDao;
import cruzeiroDao.EventoDaoImpl;
import cruzeiroModel.Cliente;
import cruzeiroModel.Evento;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class RegistrarParticipacaoControler extends baseControler implements Initializable {
	
	@FXML
	private ListView<Evento> eventoListView;
	
	private List<Evento> eventos;
	private Evento eventoAtual;
	private Cliente cliente;
	private EventoDao eventoDao = new EventoDaoImpl();
	private ClienteDao clienteDao = new ClienteDaoImpl();
	
	
	@FXML
	public void onBtnClickRegistrarParticipacao(ActionEvent e) {
		if (cliente.getPacote()==1)
			eventoAtual.setPreco(0);
		registrarParticipacao();
		Main.changeScreen("cliente");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar eventos " + e.getMessage());
		}
		eventoListView.getItems().addAll(eventos);
		eventoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Evento>() {

			@Override
			public void changed(ObservableValue<? extends Evento> arg0, Evento arg1, Evento arg2) {
				// TODO Auto-generated method stub
				eventoAtual = eventoListView.getSelectionModel().getSelectedItem();
			}
		});
	}
	
	public void registrarParticipacao() {
		try {
			clienteDao.participarEvento(cliente, eventoAtual);
		} catch (SQLException e) {
			System.out.println("Erro ao registrar participação " + e.getMessage());
		}
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Evento> pegarLista() throws SQLException {
		return eventoDao.pesquisarTudo();
	}
	
	public void encherLista() throws SQLException {
		eventos = pegarLista();
	}

}
