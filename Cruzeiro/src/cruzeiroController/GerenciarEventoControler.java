package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import cruzeiroDao.EventoDao;
import cruzeiroDao.EventoDaoImpl;
import cruzeiroModel.Evento;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GerenciarEventoControler extends baseControler implements Initializable {
	
	private List<Evento> eventos;
	private Evento eventoAtual;
	
	private EventoDao eventoDao = new EventoDaoImpl();
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	
	@FXML
	private ListView<Evento> eventoListView;
	
	public List<Evento> pegarLista() throws SQLException {
		return eventoDao.pesquisarTudo();
	}
	
	public void encherLista() throws SQLException {
		eventos = pegarLista();
	}
	
	public void refresh() {
		eventoListView.getItems().removeAll(eventos);
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao recarregar Lista " + e.getMessage());
		}
		eventoListView.getItems().addAll(eventos);
	}
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/AlterarEvento.fxml"));
			root = loader.load();
			AlterarEventoControler alterarEventoControler = loader.getController();
			alterarEventoControler.setEvento(eventoAtual);
			alterarEventoControler.setCampos();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao tentar acessar página de alterar evento " + e2.getMessage());
		}
	}
	
	
	@FXML
	public void onBtnClickExcluir(ActionEvent e) {
		try {
			eventoDao.excluir(eventoAtual.getId());
		} catch (SQLException e2) {
			System.out.println("Erro ao excluir evento " + e2.getMessage());
		}
		refresh();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao carregar Lista " + e.getMessage());
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

}
