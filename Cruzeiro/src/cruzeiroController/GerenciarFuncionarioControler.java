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
import cruzeiroDao.FuncionarioDao;
import cruzeiroDao.FuncionarioDaoImpl;
import cruzeiroDao.PessoaDao;
import cruzeiroDao.PessoaDaoImpl;
import cruzeiroModel.Funcionario;
import cruzeiroModel.Pessoa;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GerenciarFuncionarioControler extends baseControler implements Initializable {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;
	private Funcionario funcionarioAtual;
	private Funcionario funcionario;
	
	private FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
	private PessoaDao pessoaDao = new PessoaDaoImpl();
	
	@FXML
	private TextField txtBusca;
	
	@FXML
	private ComboBox<String> cBoxCategorias = new ComboBox<>();
	
	private List<String> categoriasBusca = new ArrayList<>();
	
	private ObservableList<String> olCategorias;
	
	@FXML
	private ListView<Funcionario> funcionarioListView;
	
	@FXML
	public void onBtnClickPesquisar(ActionEvent e) {
		encherComboBox();
		try {
			pesquisar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao realizar pesquisa");
		}
	}
	
	@FXML
	public void onBtnClickRegistrarFuncionario(ActionEvent e) {
		Main.changeScreen("registrar funcionario");
	}
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/AlterarFuncionario.fxml"));
			root = loader.load();
			AlterarFuncionarioControler alterarFuncionarioControler = loader.getController();
			alterarFuncionarioControler.setObjetos(funcionarioAtual);
			alterarFuncionarioControler.setCampos();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao acessar pagina de alteração do funcionário " + e2.getMessage());
		}
	}
	
	@FXML
	public void onBtnClickExcluir(ActionEvent e) {
		try {
			
			pessoaDao.excluirPessoa(funcionarioAtual.getPessoa().getId());
			funcionarioDao.excluir(funcionarioAtual.getId());
			refresh();
		} catch (SQLException e2) {
			System.out.println("Erro ao excluir funcionario " + e2.getMessage());
		}
	}
	
	public void refresh() {
		funcionarioListView.getItems().removeAll(funcionarios);
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar funcionarios " + e.getMessage());
		}
		funcionarioListView.getItems().addAll(funcionarios);
	}
	
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		encherComboBox();
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar funcionarios " + e.getMessage());
		}
		
		funcionarioListView.getItems().addAll(funcionarios);
		funcionarioListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Funcionario>() {

			@Override
			public void changed(ObservableValue<? extends Funcionario> arg0, Funcionario arg1, Funcionario arg2) {
				// TODO Auto-generated method stub
				funcionarioAtual = funcionarioListView.getSelectionModel().getSelectedItem();
			}
		});
		
	}
	
	public List<Funcionario> pegarLista() throws SQLException {
		List<Funcionario> funcionarioCompleto = funcionarioDao.pesquisarTudo();
		for (Funcionario f : funcionarioCompleto) {
			f.setPessoa(pessoaDao.pesquisarPorIdPessoa(f.getIdPessoa()));
		}
		return funcionarioCompleto;
	}
	
	public void encherLista() throws SQLException {
		funcionarios = pegarLista();
	}
	
	public void pesquisar() throws SQLException {
		if (cBoxCategorias.getSelectionModel().getSelectedItem()=="Nome") {
			funcionarioListView.getItems().removeAll(funcionarios);
			pessoas = pessoaDao.pesquisarPorNome(txtBusca.getText());
			for (Pessoa p : pessoas) {
				funcionario = funcionarioDao.pesquisarPorIdPessoa(p.getId());
				funcionario.setPessoa(p);
				funcionarios.add(funcionario);
			}
			funcionarioListView.getItems().addAll(funcionarios);
		} else if (cBoxCategorias.getSelectionModel().getSelectedItem()=="cpf") {
			funcionarioListView.getItems().removeAll(funcionarios);
			pessoas = pessoaDao.pesquisarPorCpf(txtBusca.getText());
			for (Pessoa p : pessoas) {
				funcionario = funcionarioDao.pesquisarPorIdPessoa(p.getId());
				funcionario.setPessoa(p);
				funcionarios.add(funcionario);
			}
			funcionarioListView.getItems().addAll(funcionarios);
		} else
			System.out.println("Erro ao pesquisar ");
	}
	
	public void encherComboBox() {
		categoriasBusca.add("Nome");
		categoriasBusca.add("cpf");
		
		olCategorias = FXCollections.observableArrayList(categoriasBusca);
		
		cBoxCategorias.setItems(olCategorias);
	}

}
