package cruzeiroController;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class baseControler implements Initializable {

	@FXML
	private Button btnClientes;
	
	@FXML
	private Button btnMenu;
	
	@FXML
	private Button btnEventos;
	
	@FXML
	private Button btnRotas;
	
	public void onBtnClickCliente(ActionEvent e) {
		Main.changeScreen("cliente");
	}
	
	public void onBtnClickEventos(ActionEvent e) {
		Main.changeScreen("evento");
	}
	
	public void onBtnClickRotas(ActionEvent e) {
		Main.changeScreen("rota");
	}
	
	public void onBtnClickMenu(ActionEvent e) {
		Main.changeScreen("menu");
	}
	
	public void onBtnClickSair(ActionEvent e) {
		Main.changeScreen("login");
	}
	
	public void onBtnClickFuncionario(ActionEvent e) {
		Main.changeScreen("funcionario");
	}
	
    
		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
		
}
