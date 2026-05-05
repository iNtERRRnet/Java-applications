package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBookScreen 
{
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonConfirm;

    @FXML
    private TextField textAuthor;

    @FXML
    private DatePicker textDueDate;

    @FXML
    private TextField textGenre;

    @FXML
    private TextField textID;

    @FXML
    private DatePicker textLoanDate;

    @FXML
    private TextField textName;

    @FXML
    private TextField textTakersID;

    public void initialize()
    {
    	buttonConfirm.setOnAction(event ->
    	{
    		String loanDate = null;
    		String dueDate = null;
    		
    		LocalDate date1 = textLoanDate.getValue();
    	    LocalDate date2 = textDueDate.getValue();
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    	    if(date1 != null)
    	    {
    	    	loanDate = date1.format(formatter);
    	    }
    	    if(date2 != null)
    	    {
    	    	dueDate = date2.format(formatter);
    	    }

			boolean success = application.Main.BookScreen.AddBook(textName.getText(), textAuthor.getText(), textGenre.getText(), textID.getText(), loanDate, dueDate, textTakersID.getText());
			
	        if(success) 
	        {
	            textName.clear();
	            textAuthor.clear();
	            textGenre.clear();
	            textID.clear();
	            textLoanDate.getEditor().clear();
	            textDueDate.getEditor().clear();
	            textTakersID.clear();

	            application.Main.stage3.hide();
	        }
    	});
    	
    	buttonCancel.setOnAction(event ->
    	{
    		textName.clear();
    		textAuthor.clear();
    		textGenre.clear();
    		textID.clear();
    		textLoanDate.getEditor().clear();
    		textDueDate.getEditor().clear();
    		textTakersID.clear();
    		
    		application.Main.stage3.hide();
    	});
    }
}