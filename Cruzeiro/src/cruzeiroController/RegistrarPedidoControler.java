package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import cruzeiroDao.ClienteDao;
import cruzeiroDao.ClienteDaoImpl;
import cruzeiroDao.MenuDao;
import cruzeiroDao.MenuDaoImpl;
import cruzeiroModel.Cliente;
import cruzeiroModel.Menu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class RegistrarPedidoControler extends baseControler implements Initializable {
	
	@FXML
	private ListView<Menu> menuListView;
	
	@FXML
	private Spinner<Integer> spQtd;
	
	int qtdAtual;
	Menu menuAtual;
	private Cliente cliente;
	private List<Menu> menuCompleto;
	
	private MenuDao menuDao = new MenuDaoImpl();
	private ClienteDao clienteDao = new ClienteDaoImpl();
	
	@FXML
	public void onBtnClickRegistrar(ActionEvent e) {
		if (cliente.getPacote()==1)
			menuAtual.setPreco(0);
		realizarRegistro();
		Main.changeScreen("cliente");
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar items do menu " + e.getMessage());
		}
		
		SpinnerValueFactory<Integer> valorSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
		valorSpinner.setValue(1);
		
		spQtd.setValueFactory(valorSpinner);
		
		spQtd.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				qtdAtual = spQtd.getValue();
			}
			
		});
		
		menuListView.getItems().addAll(menuCompleto);
		menuListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {

			@Override
			public void changed(ObservableValue<? extends Menu> arg0, Menu arg1, Menu arg2) {
				// TODO Auto-generated method stub
				menuAtual = menuListView.getSelectionModel().getSelectedItem();
			}
		});
		
		
	}
	
	public void realizarRegistro() {
		try {
			clienteDao.pedirMenu(cliente, menuAtual, qtdAtual);
		} catch (SQLException e) {
			System.out.println("Erro ao tentar registrar transação " + e.getMessage());
		}
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Menu> pegarLista() throws SQLException{
		return menuDao.pesquisarTudo();
	}
	
	public void encherLista() throws SQLException {
		menuCompleto = pegarLista();
	}

}
