package application.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreen 
{
    @FXML
    private Label labelIncorrect;
	
    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField textBoxPassword;

    @FXML
    private TextField textBoxUsername;

    public void initialize() throws IOException
    {	
    	InputStream input = LoginScreen.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(input);
    	
    	buttonLogin.setOnAction(event ->
    	{
    		if(textBoxUsername.getText().equals(prop.getProperty("username")) && textBoxPassword.getText().equals(prop.getProperty("password")))
    		{
    			Main.stage1.hide();
    			Main.stage2.show();
    			labelIncorrect.setText("");
    			textBoxUsername.clear();
    			textBoxPassword.clear();
    		}
    		else
    		{
    			labelIncorrect.setText("Incorrect username and/or password");
    		}
    	});
    }
}