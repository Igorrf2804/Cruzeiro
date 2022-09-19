package cruzeiroController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import cruzeiroDao.RotaDao;
import cruzeiroDao.RotaDaoImpl;
import cruzeiroModel.Rota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlterarRotaControler extends baseControler implements Initializable {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	private Rota rota;
	private RotaDao rotaDao = new RotaDaoImpl();
	
	@FXML
	private DatePicker dataSaidaDatePicker, dataVoltaDatePicker;
	
	@FXML
	private Label lblErro;
	
	@FXML
	private TextField txtLocal, txtRota, txtHoraSaida, txtHoraVolta, txtPreco; 
	
	
	@FXML
	public void onBtnClickAlterar(ActionEvent e) throws SQLException {
		alterar();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruzeiroView/GerenciarRota.fxml"));
			root = loader.load();
			GerenciarRotaControler controler = loader.getController();
			controler.refresh();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e2) {
			System.out.println("Erro ao voltar para eventos " + e2.getMessage());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	} 
	
	public void setObjeto(Rota rota) {
		this.rota = rota;
	}
	
	public void alterar() throws SQLException {
		if (!((verificarHora(txtHoraSaida.getText()))&&(verificarHora(txtHoraVolta.getText())))) {
			lblErro.setText("Erro no horário");
		} else {
			rota.setLocal(txtLocal.getText());
			rota.setRota(txtRota.getText());
			rota.setPreco(Double.parseDouble(txtPreco.getText()));
			rota.setHoraSaida(LocalTime.parse(txtHoraSaida.getText()));
			rota.setHoraVolta(LocalTime.parse(txtHoraVolta.getText()));
			LocalDate data1 = dataSaidaDatePicker.getValue();
			LocalDate data2 = dataVoltaDatePicker.getValue();
			java.util.Date d1 = Date.from(data1.atStartOfDay(ZoneId.systemDefault()).toInstant());
			java.util.Date d2 = Date.from(data2.atStartOfDay(ZoneId.systemDefault()).toInstant());
			rota.setDataSaida(d1);
			rota.setDataVolta(d2);
			rotaDao.alterar(rota);
		}
			
	}
	
	public void setarCampos() {
		txtLocal.setText(rota.getLocal());
		txtRota.setText(rota.getRota());
		DateTimeFormatter convertor = DateTimeFormatter.ofPattern("hh:mm");
		String horario1 = rota.getHoraSaida().format(convertor);
		String horario2 = rota.getHoraVolta().format(convertor);
		txtHoraSaida.setText(horario1);
		txtHoraVolta.setText(horario2);
		txtPreco.setText(String.valueOf(rota.getPreco()));
		dataSaidaDatePicker.setValue(rota.getDataSaida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		dataVoltaDatePicker.setValue(rota.getDataVolta().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	public boolean verificarHora(String horario) {
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
