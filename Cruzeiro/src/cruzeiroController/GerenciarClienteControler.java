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

public class GerenciarClienteControler extends baseControler implements Initializable {
	
	@FXML
	private ListView<Cliente> clienteListView;
	
	@FXML
	private TextField txtBusca;
	
	@FXML
	private ComboBox<String> cBoxCliente;
	
	private List<Cliente> clientes;
	private Cliente clienteAtual;
	private ClienteDao clienteDao = new ClienteDaoImpl();
	private PessoaDao pessoaDao = new PessoaDaoImpl();
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	
	
	private List<String> categoriasBusca = new ArrayList<>();
	private ObservableList<String> olCategorias;
	
	
	@FXML
	public void onBtnClickPesquisar(ActionEvent e) {
		
	}
	
	@FXML
	public void onBtnClickRegistrarPedido(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/RegistrarPedido.fxml"));
			root = loader.load();
			RegistrarPedidoControler registrarPedidoControler = loader.getController();
			registrarPedidoControler.setCliente(clienteAtual);
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao trocar para página de registrar pedido " + e2.getMessage());
		}
	}
	
	@FXML
	public void onBtnClickPartiparEvento(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/RegistrarParticipacao.fxml"));
			root = loader.load();
			RegistrarParticipacaoControler participacaoControler = loader.getController();
			participacaoControler.setCliente(clienteAtual);
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao trocar para página de registrar em evento " + e2.getMessage());
		}
	}

	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/AlterarCliente.fxml"));
			root = loader.load();
			AlterarClienteControler alterarClienteControler = loader.getController();
			alterarClienteControler.setObjetos(clienteAtual);
			alterarClienteControler.setarCampos();
			alterarClienteControler.carregarComboBox();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao tentar alterar Cliente " + e2.getMessage());
		}
	}
	
	@FXML
	public void onBtnClickSolicitarRota(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/RegistrarSolititacao.fxml"));
			root = loader.load();
			RegistrarSolicitacao registrarSolicitacao = loader.getController();
			registrarSolicitacao.setCliente(clienteAtual);
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (Exception e2) {
			System.out.println("Erro ao ir para página de solicitar rota " + e2.getMessage());
		}
	}
	
	@FXML
	public void onBtnClickExcluir(ActionEvent e) {
		try {
			Pessoa p = clienteAtual.getPessoa();
			clienteDao.excluirCliente(clienteAtual, p.getIdade());
			pessoaDao.excluirPessoa(clienteAtual.getIdPessoa());
		} catch (SQLException e2) {
			System.out.println("Erro ao excluir Cliente " + e2.getMessage());
		}
		refresh();
	}
	
	public List<Cliente> pegarLista() throws SQLException {
		List<Cliente> clientesPesquisados = clienteDao.pesquisarTudoCliente();
		for (Cliente c : clientesPesquisados) {
			c.setPessoa(pessoaDao.pesquisarPorIdPessoa(c.getIdPessoa()));
		}
		return clientesPesquisados;
	}
	
	public void encherLista() throws SQLException {
		clientes = pegarLista();
	}
	
	public void refresh() {
		clienteListView.getItems().removeAll(clientes);
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao recarregar lista " + e.getMessage());
		}
		clienteListView.getItems().addAll(clientes);
	}


	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		encherComboBox();
		try {
			encherLista();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar lista");
		}
		
		clienteListView.getItems().addAll(clientes);
		clienteListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {

			@Override
			public void changed(ObservableValue<? extends Cliente> arg0, Cliente arg1, Cliente arg2) {
				// TODO Auto-generated method stub
				clienteAtual = clienteListView.getSelectionModel().getSelectedItem();
			}
		});
		
		
	}
	
	public void encherComboBox() {
		categoriasBusca.add("Nome");
		categoriasBusca.add("cpf");
		
		olCategorias = FXCollections.observableArrayList(categoriasBusca);
		
		cBoxCliente.setItems(olCategorias);
	}

}
