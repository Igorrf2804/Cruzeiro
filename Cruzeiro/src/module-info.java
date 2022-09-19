module Cruzeiro {
	exports application;
	exports cruzeiroDao;
	exports cruzeiroModel;
	exports cruzeiroController;

	requires java.desktop;
	requires java.sql;
	requires transitive javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;

	

	
	opens cruzeiroController to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
}