module javaFXProject 
{
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires java.sql;

    opens application to javafx.graphics, javafx.fxml, javafx.base;
    opens application.controllers to javafx.fxml;
    
    exports application;
}
