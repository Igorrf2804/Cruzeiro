package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cruzeiroDao.FuncionarioDao;
import cruzeiroDao.FuncionarioDaoImpl;
import cruzeiroDao.PessoaDao;
import cruzeiroDao.PessoaDaoImpl;
import cruzeiroModel.Funcionario;
import cruzeiroModel.Pessoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrarFuncionarioControler extends baseControler implements Initializable {
	
	@FXML
	private TextField txtNome, txtCpf, txtEmail, txtTel, txtIdade, txtEndereco, txtLogin, txtSenha;
	
	private Funcionario funcionario = new Funcionario();
	private FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
	private Pessoa pessoa = new Pessoa();
	private PessoaDao pessoaDao = new PessoaDaoImpl();
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	public void onBtnClickRegistrar(ActionEvent e) throws SQLException {
		registrar();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/GerenciarFuncionario.fxml"));
			root = loader.load();
			GerenciarFuncionarioControler controler = loader.getController();
			controler.refresh();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao voltar para página de funcionarios " + e2.getMessage());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void registrar() throws SQLException {
		Pessoa p;
		pessoa.setNome(txtNome.getText());
		pessoa.setCpf(txtCpf.getText());
		pessoa.setEmail(txtEmail.getText());
		pessoa.setTelefone(txtTel.getText());
		pessoa.setIdade(Integer.parseInt(txtIdade.getText()));
		funcionario.setLogin(txtLogin.getText());
		funcionario.setEndereco(txtEndereco.getText());
		funcionario.setSenha(txtSenha.getText());
		p = pessoaDao.salvarPessoa(pessoa);
		funcionario.setIdPessoa(p.getId());
		funcionarioDao.salvar(funcionario);
	}

}
