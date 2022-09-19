package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {
	
	private static Stage stage;
	private static Scene loginScene;	
	private static Scene registrarRotaScene;
	private static Scene registrarFuncionarioScene;
	private static Scene registrarEventoScene;
	private static Scene registrarMenuScene;
	private static Scene menuScene;
	private static Scene funcionarioScene;
	private static Scene rotaScene;
	private static Scene clienteScene;
	private static Scene eventoScene;
	private static Scene indexScene;
	
	@Override
	public void start(Stage primaryStage)  {
		
		stage = primaryStage;
		try {
			
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/cruzeiroView/Login.fxml"));
			loginScene = new Scene(fxmlLogin);
			
			Parent fxmlIndex = FXMLLoader.load(getClass().getResource("/cruzeiroView/Index.fxml"));
			indexScene = new Scene(fxmlIndex);
			
			Parent fxmlMenu = FXMLLoader.load(getClass().getResource("/cruzeiroView/GerenciarMenu.fxml"));
			menuScene = new Scene(fxmlMenu);
			
			Parent fxmlEvento = FXMLLoader.load(getClass().getResource("/cruzeiroView/GerenciarEvento.fxml"));
			eventoScene = new Scene(fxmlEvento);
			
			Parent fxmlCliente = FXMLLoader.load(getClass().getResource("/cruzeiroView/GerenciarCliente.fxml"));
			clienteScene = new Scene(fxmlCliente);
			
			Parent fxmlFuncionario = FXMLLoader.load(getClass().getResource("/cruzeiroView/GerenciarFuncionario.fxml"));
			funcionarioScene = new Scene(fxmlFuncionario);
			
			Parent fxmlRota = FXMLLoader.load(getClass().getResource("/cruzeiroView/GerenciarRota.fxml"));
			rotaScene = new Scene(fxmlRota);
			
			Parent fxmlRegistrarMenu = FXMLLoader.load(getClass().getResource("/cruzeiroView/RegistrarMenu.fxml"));
			registrarMenuScene = new Scene(fxmlRegistrarMenu);
			
			
			Parent fxmlRegistrarFuncionario = FXMLLoader.load(getClass().getResource("/cruzeiroView/RegistrarFuncionario.fxml"));
			registrarFuncionarioScene = new Scene(fxmlRegistrarFuncionario);
			
			
			primaryStage.setTitle("Blue Route");
			
			primaryStage.setScene(loginScene);
			primaryStage.show();
			primaryStage.centerOnScreen();
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				sair(primaryStage);
				});
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void changeScreen(String scr) {
		switch(scr) {
			case "login":
				stage.setScene(loginScene);
				break;
			case "index":
				stage.setScene(indexScene);
				break;
			case "cliente":
				stage.setScene(clienteScene);
				break;
			case "funcionario":
				stage.setScene(funcionarioScene);
				break;
			case "menu":
				stage.setScene(menuScene);
				break;
			case "evento":
				stage.setScene(eventoScene);
				break;
			case "rota":
				stage.setScene(rotaScene);
				break;
			case "registrar funcionario":
				stage.setScene(registrarFuncionarioScene);
				break;
			case "registrar menu":
				stage.setScene(registrarMenuScene);
				break;

//			case "registrar rota":
//				stage.setScene(registrarRotaScene);
//				break;
//			case "registrar evento":
//				stage.setScene(registrarEventoScene);
//				break;
				}
	}
	
	public void sair(Stage stage) {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Sair");
		alerta.setHeaderText("Você está prestes a fechar o programa!");
		alerta.setContentText("Você realmente deseja fechar o sistema ?");
		
		if(alerta.showAndWait().get()== ButtonType.OK) {
			System.out.println("Voce fechou o promgrama com êxito!");
			stage.close();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
