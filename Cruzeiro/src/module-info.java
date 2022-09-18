module Cruzeiro {
	exports application;
	exports cruzeiroDao;
	exports cruzeiroModel;

	requires java.desktop;
	requires java.sql;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	
	
	opens application to javafx.graphics, javafx.fxml;
}