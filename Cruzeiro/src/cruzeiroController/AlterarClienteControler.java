package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cruzeiroDao.ClienteDao;
import cruzeiroDao.ClienteDaoImpl;
import cruzeiroDao.PessoaDao;
import cruzeiroDao.PessoaDaoImpl;
import cruzeiroModel.Cliente;
import cruzeiroModel.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlterarClienteControler extends baseControler implements Initializable {
	
	@FXML
	private TextField txtNome, txtCpf, txtEmail, txtTel, txtIdade, txtSenha, txtTotal;
	@FXML
	private ComboBox<String> cboxPacote = new ComboBox<>();
	
	private List<String> pacotes = new ArrayList<>();
	private ObservableList<String> olPacotes;
	
	private Cliente cliente;
	private Pessoa pessoa;
	
	private PessoaDao pessoaDao = new PessoaDaoImpl();
	private ClienteDao clienteDao = new ClienteDaoImpl();
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) throws SQLException {
		alterar();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/GerenciarCliente.fxml"));
			root = loader.load();
			GerenciarClienteControler gerenciarClienteControler = loader.getController();
			gerenciarClienteControler.refresh();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao voltar para pagina de clientes " + e2.getMessage());
		}
	}
	
	
	public void alterar() throws SQLException {
		pessoa = cliente.getPessoa();
		pessoa.setNome(txtNome.getText());
		pessoa.setCpf(txtCpf.getText());
		pessoa.setTelefone(txtTel.getText());
		pessoa.setIdade(Integer.parseInt(txtIdade.getText()));
		cliente.setSenha(txtSenha.getText());
		cliente.setTotal(Double.parseDouble(txtTotal.getText()));
		pessoaDao.alterarPessoa(pessoa);
		clienteDao.alterar(cliente, pessoa);
	}
	
	public void setObjetos(Cliente cliente) {
		this.cliente = cliente;
		pessoa = cliente.getPessoa();
	}
	
	public void setarCampos() {
		txtNome.setText(pessoa.getNome());
		txtCpf.setText(pessoa.getCpf());
		txtEmail.setText(pessoa.getEmail());
		txtTel.setText(pessoa.getTelefone());
		txtIdade.setText(String.valueOf(pessoa.getIdade()));
		txtSenha.setText(cliente.getSenha());
		txtTotal.setText(String.valueOf(cliente.getTotal()));
		if (cliente.getPacote()==1)
			cboxPacote.getSelectionModel().select(1);
		else
			cboxPacote.getSelectionModel().select(2);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carregarComboBox();
		
	}
	
	public void carregarComboBox() {
		pacotes.add("Gold");
		pacotes.add("Silver");
		
		olPacotes = FXCollections.observableArrayList(pacotes);
		
		cboxPacote.setItems(olPacotes);
	}

}
