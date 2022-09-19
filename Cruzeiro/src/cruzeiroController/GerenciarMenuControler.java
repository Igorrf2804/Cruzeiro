package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import cruzeiroDao.MenuDao;
import cruzeiroDao.MenuDaoImpl;
import cruzeiroModel.Menu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class GerenciarMenuControler extends baseControler implements Initializable {
	
	MenuDao menuDao;
	Menu menuAtual;
	
	private Parent root;
	private Stage stage;
	private Scene scene;

	List<Menu> menuCompleto;
	
	@FXML
	private ListView<Menu> menuListView;
	
	public List<Menu> pegarLista() throws SQLException {
		menuDao = new MenuDaoImpl();
		List<Menu> menu = menuDao.pesquisarTudo();
		return menu;
	}
	
	@FXML
	public void onBtnClickRegistrarMenu(ActionEvent e) {
		Main.changeScreen("registrar menu");
	}
	
	@FXML
	public void onBtnClickAlterarMenu(ActionEvent e)  {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/AlterarMenu.fxml"));
			root = loader.load();
			AlterarMenuControler alterarMenuControler = loader.getController();
			alterarMenuControler.setarMenu(menuAtual);
			alterarMenuControler.setarCampos();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			
		} catch (IOException e2) {
			System.out.println("Erro ao trocar para página de alterar menu " + e2.getMessage());
		}
	}
	
	@FXML
	public void onBtnClickExcluir() {
		try {
			menuDao.excluir(menuAtual.getId());
		} catch (SQLException e) {
			System.out.println("Erro ao excluir menu " + e.getMessage());
		}
		refresh();
	}
	
	public void encherLista() throws SQLException {
		menuCompleto = pegarLista();
	}
	
	
	public void refresh() {
		menuListView.getItems().removeAll(menuCompleto);
		try {
			encherLista();
		} catch (SQLException e2) {
			System.out.println("erro");
		}
		menuListView.getItems().addAll(menuCompleto);
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		menuListView.getItems().add(pegarLista());
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar lista");
		}
		menuListView.getItems().addAll(menuCompleto);
		menuListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {

			@Override
			public void changed(ObservableValue<? extends Menu> arg0, Menu arg1, Menu arg2) {
				// TODO Auto-generated method stub
				menuAtual = menuListView.getSelectionModel().getSelectedItem();
			}
		});
		
	}
	
	

}
