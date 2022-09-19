package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlterarMenuControler extends baseControler implements Initializable {
	
	@FXML
	private TextField txtNome, txtDesc, txtIngre, txtPreco;
	
	@FXML
	private RadioButton rbComida, rbBebida;
	
	private Menu menu;
	private MenuDao menuDao = new MenuDaoImpl();
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) {
		alterar();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/GerenciarMenu.fxml"));
			root = loader.load();
			GerenciarMenuControler menuControler = loader.getController();
			menuControler.refresh();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			
		} catch (IOException e2) {
			System.out.println("Erro ao retornar para página de menu " + e2.getMessage());
		}
	}
	
	
	public void setarMenu(Menu menu) {
		this.menu = menu;
	}
	
	public void setarCampos() {
		txtNome.setText(menu.getNome());
		txtDesc.setText(menu.getDesc());
		txtIngre.setText(menu.getIngredientes());
		txtPreco.setText(String.valueOf(menu.getPreco()));
		if (menu.getTipo() == 1)
			rbComida.setSelected(true);
		if (menu.getTipo() == 2)
			rbBebida.setSelected(true);
	}

	public void alterar() {
		menu.setNome(txtNome.getText());
		menu.setDesc(txtDesc.getText());
		menu.setIngredientes(txtIngre.getText());
		menu.setPreco(Double.parseDouble(txtPreco.getText()));
		if (rbComida.isSelected())
			menu.setTipo(1);
		else if (rbBebida.isSelected())
			menu.setTipo(2);
		try {
			menuDao.alterar(menu);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar menu " + e.getMessage());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
