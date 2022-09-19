package cruzeiroController;

import java.io.IOException;
import java.net.URL;
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

public class AlterarFuncionarioControler extends baseControler implements Initializable {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextField txtNome, txtCpf, txtEmail, txtTel, txtIdade, txtEndereco, txtLogin, txtSenha;
	
	private Funcionario funcionario;
	private Pessoa pessoa;
	private FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
	private PessoaDao pessoaDao = new PessoaDaoImpl();
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) throws SQLException {
		alterar();
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
	
	public void setObjetos(Funcionario funcionario) {
		this.funcionario = funcionario;
		pessoa = funcionario.getPessoa();
	}
	
	public void setCampos() {
		txtNome.setText(pessoa.getNome());
		txtCpf.setText(pessoa.getCpf());
		txtEmail.setText(pessoa.getEmail());
		txtTel.setText(pessoa.getTelefone());
		txtIdade.setText(String.valueOf(pessoa.getIdade()));
		txtEndereco.setText(funcionario.getEndereco());
		txtLogin.setText(funcionario.getLogin());
		txtSenha.setText(funcionario.getSenha());
	}
	
	public void alterar() throws SQLException {
		pessoa.setNome(txtNome.getText());
		pessoa.setCpf(txtCpf.getText());
		pessoa.setEmail(txtEmail.getText());
		pessoa.setTelefone(txtTel.getText());
		pessoa.setIdade(Integer.parseInt(txtIdade.getText()));
		funcionario.setLogin(txtLogin.getText());
		funcionario.setEndereco(txtEndereco.getText());
		funcionario.setSenha(txtSenha.getText());
		pessoaDao.alterarPessoa(pessoa);
		funcionarioDao.alterar(funcionario);
	}
}
