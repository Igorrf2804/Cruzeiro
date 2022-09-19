package cruzeiroController;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControler implements Initializable {
	
	
	@FXML
	private Label lblErro;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField pwSenha;
	
	@FXML
	private Button btnEntrar;
	
	@FXML
	void onBtnClickLogar(ActionEvent event) {
		
		logar();
	}
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void logar() {
		if (txtUsername.getText().equals("admin")&&pwSenha.getText().equals("123")) {
			Main.changeScreen("index");
			limpar();
		}
		else 
			lblErro.setText("Usuario ou senha inválidos");
	} 
	
	public void limpar() {
		txtUsername.setText("");
		pwSenha.setText("");
		lblErro.setText("");
	}
	

}
