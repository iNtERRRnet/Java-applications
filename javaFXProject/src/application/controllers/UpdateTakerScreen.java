package application.controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateTakerScreen 
{
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonConfirm;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textHasTakenBook;

    @FXML
    private TextField textName;

    @FXML
    private TextField textPhoneNumber;

    @FXML
    private TextField textSurname;

    @FXML
    private TextField textTakersID;

    public void SetTextFields(String name, String surname, String phoneNumber, String email, String takersID, String hasTakenBook)
    {
    	textName.setText(name);
		textSurname.setText(surname);
		textPhoneNumber.setText(phoneNumber);
		textEmail.setText(email);
		textTakersID.setText(takersID);
		textHasTakenBook.setText(hasTakenBook);
    }
    
    public void initialize()
    {	
    	buttonConfirm.setOnAction(event ->
    	{
    		boolean success = Main.TakerScreen.UpdateTaker(textName.getText(), textSurname.getText(), textPhoneNumber.getText(), textEmail.getText(), textTakersID.getText(), textHasTakenBook.getText());
    		
    		if(success)
    		{
	    		textName.clear();
	    		textSurname.clear();
	    		textPhoneNumber.clear();
	    		textEmail.clear();
	    		textTakersID.clear();
	    		textHasTakenBook.clear();
	    		
	    		Main.stage7.hide();
    		}
    	});
    	
    	buttonCancel.setOnAction(event ->
    	{
    		textName.clear();
    		textSurname.clear();
    		textPhoneNumber.clear();
    		textEmail.clear();
    		textTakersID.clear();
    		textHasTakenBook.clear();
    		
    		Main.stage7.hide();
    	});
    }
}