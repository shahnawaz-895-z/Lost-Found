module sdaproj {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.xml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
