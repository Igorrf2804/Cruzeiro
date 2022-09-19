package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import cruzeiroDao.RotaDao;
import cruzeiroDao.RotaDaoImpl;
import cruzeiroModel.Rota;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class GerenciarRotaControler extends baseControler implements Initializable {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	private List<Rota> rotas;
	private Rota rotaAtual;
	
	private RotaDao rotaDao = new RotaDaoImpl();
	
	@FXML
	private ListView<Rota> rotaListView;
	
	public List<Rota> pegarLista() throws SQLException {
		return rotaDao.pesquisarTudo();
	}
	
	public void encherLista() throws SQLException {
		rotas = pegarLista();
	}
	
	public void refresh() {
		rotaListView.getItems().removeAll(rotas);
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao recarregar lista " + e.getMessage());
		}
		rotaListView.getItems().addAll(rotas);
	}
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/AlterarRota.fxml"));
			root = loader.load();
			AlterarRotaControler alterarRotaControler = loader.getController();
			alterarRotaControler.setObjeto(rotaAtual);
			alterarRotaControler.setarCampos();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao acessar página de alteração da rota " + e2.getMessage());
		}
	}
	
	@FXML
	public void onBtnClickExcluir(ActionEvent e) {
		try {
			rotaDao.excluir(rotaAtual.getId());
		} catch (SQLException e2) {
			System.out.println("Erro ao excluir rota " + e2.getMessage());
		}
		refresh();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao encher lista " + e.getMessage());
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

}
