package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import cruzeiroDao.MenuDao;
import cruzeiroDao.MenuDaoImpl;
import cruzeiroModel.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrarMenuControler extends GerenciarMenuControler implements Initializable {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtDesc;
	
	@FXML
	private TextField txtIngre;
	
	@FXML
	private TextField txtPreco;
	
	@FXML
	private RadioButton rbComida, rbBebida;
	
	private MenuDao menuDao = new MenuDaoImpl();
	
	public String nome, desc, ingre;
	public double preco;
	public int type;
	
	@FXML
	public void onBtnClickRegistrar(ActionEvent e) throws SQLException {
		registrar();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/GerenciarMenu.fxml"));
			root = loader.load();
			GerenciarMenuControler menuControler = loader.getController();
			menuControler.refresh();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			
		} catch (IOException e2) {
			// TODO: handle exception
		}
//		Main.changeScreen("menu");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void registrar() throws SQLException {
		nome = txtNome.getText();
		desc = txtDesc.getText();
		ingre = txtIngre.getText();
		preco = Double.parseDouble(txtPreco.getText());
		if (rbComida.isSelected())
			type = 1;
		if (rbBebida.isSelected())
			type = 2;
		Menu menu = new Menu(type, nome, desc, ingre, preco);
		menuDao.salvar(menu);
	}
	
	
}
