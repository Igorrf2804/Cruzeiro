package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cruzeiroDao.EventoDao;
import cruzeiroDao.EventoDaoImpl;
import cruzeiroModel.Evento;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlterarEventoControler extends baseControler implements Initializable {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private DatePicker dataDatePicker;
	
	@FXML
	private TextField txtNome, txtDesc, txtHora, txtPreco;
	
	@FXML
	private Label lblErro;
	
	@FXML
	private ComboBox<String> cBoxFaixaEtaria = new ComboBox<>();
	
	private List<String> faixaEtarias = new ArrayList<>();
	private ObservableList<String> olFaixaEtarias;
	
	private Evento evento;
	private EventoDao eventoDao = new EventoDaoImpl();
	
	LocalDate data;
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) throws SQLException {
		alterar();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/GerenciarEvento.fxml"));
			root = loader.load();
			GerenciarEventoControler gerenciarEventoControler = loader.getController();
			gerenciarEventoControler.refresh();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao voltar para página de eventos " + e2.getMessage());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		encherCBox();
		
	}
	
	public void alterar() throws SQLException {
		if (vetificarHora(txtHora.getText())) {
			evento.setNome(txtNome.getText());
			evento.setDesc(txtDesc.getText());
			evento.setHora(LocalTime.parse(txtHora.getText()));
			data = dataDatePicker.getValue();
			java.util.Date d = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
			evento.setData(d);
			evento.setPreco(Double.parseDouble(txtPreco.getText()));
			evento.setFaixaEtaria(cBoxFaixaEtaria.getSelectionModel().getSelectedItem());
			eventoDao.alterar(evento);
		} else {
			lblErro.setText("Erro no horário usado");
		}
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public void setCampos() {
		txtNome.setText(evento.getNome());
		txtDesc.setText(evento.getDesc());
		DateTimeFormatter convertor = DateTimeFormatter.ofPattern("hh:mm");
		String horario = evento.getHora().format(convertor);
		txtHora.setText(horario);
		txtPreco.setText(String.valueOf(evento.getPreco()));
		dataDatePicker.setValue(evento.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	public void encherCBox() {
		faixaEtarias.add("infantil");
		faixaEtarias.add("juvenil");
		faixaEtarias.add("maiores de idade");
		faixaEtarias.add("idosos");
		
		olFaixaEtarias = FXCollections.observableArrayList(faixaEtarias);
		
		cBoxFaixaEtaria.setItems(olFaixaEtarias);
	}
	
	public boolean vetificarHora(String horario) {
		char n1;
		char n2;
		char p;
		char n3;
		char n4;
		char c = ':';
		if (horario.length()!=5) {
			lblErro.setText("Erro no horário");
		} else {
			n1 = horario.charAt(0);
			n2 = horario.charAt(1);
			p = horario.charAt(2);
			n3 = horario.charAt(3);
			n4 = horario.charAt(4);
			if (p!=c)
				lblErro.setText("Erro no horário");
			else if (((int)n1<=2)&&((int)n1>=0))
				if (((int)n2<=9)&&((int)n2>=0))
					if (((int)n3<=6)&&((int)n3>=0))
						if (((int)n4<=9)&&((int)n4>=0))
							return true;
		}
		return false;
	}

}
