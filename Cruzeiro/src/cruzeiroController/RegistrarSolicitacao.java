package cruzeiroController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import cruzeiroDao.ClienteDao;
import cruzeiroDao.ClienteDaoImpl;
import cruzeiroDao.RotaDao;
import cruzeiroDao.RotaDaoImpl;
import cruzeiroModel.Cliente;
import cruzeiroModel.Rota;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class RegistrarSolicitacao extends baseControler implements Initializable {
	
	@FXML
	private ListView<Rota> rotaListView;
	
	private List<Rota> rotas = new ArrayList<>();
	private Rota rotaAtual;
	private Cliente cliente;
	
	private RotaDao rotaDao = new RotaDaoImpl();
	private ClienteDao clienteDao = new ClienteDaoImpl();
	
	@FXML
	public void onBtnClickRegistrarSolicitacao(ActionEvent e) {
		if (cliente.getPacote()==1) {
			rotaAtual.setPreco(0);		
			}
		realizarSolicitacao();
		Main.changeScreen("cliente");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar rotas " + e.getMessage());
		}
		rotaListView.getItems().addAll(rotas);
		rotaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rota>() {

			@Override
			public void changed(ObservableValue<? extends Rota> arg0, Rota arg1, Rota arg2) {
				// TODO Auto-generated method stub
				rotaAtual = rotaListView.getSelectionModel().getSelectedItem();
			}
		});
	}
	
	public void realizarSolicitacao() {
		try {
			clienteDao.pedirRota(cliente, rotaAtual);
		} catch (SQLException e) {
			System.out.println("Erro ao registrar solicitação " + e.getMessage());
		}
	}
	
	public List<Rota> pegarLista() throws SQLException {
		return rotaDao.pesquisarTudo();
	}
	
	public void encherLista() throws SQLException {
		rotas = pegarLista();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
