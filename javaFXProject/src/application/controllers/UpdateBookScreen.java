package application.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UpdateBookScreen 
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

    public void SetTextFields(String name, String author, String genre, String ID, String loanDate, String dueDate, String takersID)
    {
    	if(loanDate == null && dueDate == null && takersID == null)
    	{
	    	textName.setText(name);
			textAuthor.setText(author);
			textGenre.setText(genre);
			textID.setText(ID);
    		textLoanDate.setValue(null);
    		textLoanDate.setPromptText("Loan Date");
    		textDueDate.setValue(null);
    		textDueDate.setPromptText("Due Date");
    		textTakersID.setText(null);
    	}
    	else
    	{
	    	LocalDate AcLoanDate = LocalDate.parse(loanDate);
	    	LocalDate AcDueDate = LocalDate.parse(dueDate);
	    	
	    	textName.setText(name);
			textAuthor.setText(author);
			textGenre.setText(genre);
			textID.setText(ID);
			textLoanDate.setValue(AcLoanDate);
			textDueDate.setValue(AcDueDate);
			textTakersID.setText(takersID);
    	}
    }
    
    public void initialize()
    {	
    	buttonConfirm.setOnAction(event ->
    	{
	    	String loanDate = null;
	    	String dueDate = null;
    		LocalDate date1 = textLoanDate.getValue();
    	    LocalDate date2 = textDueDate.getValue();
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	    
    	    if(date1 != null && date2 != null)
    	    {
    	    	loanDate = date1.format(formatter);
    	   		dueDate = date2.format(formatter);
    	    }
			
			boolean success = application.Main.BookScreen.UpdateBook(textName.getText(), textAuthor.getText(), textGenre.getText(), textID.getText(), loanDate, dueDate, textTakersID.getText());
			
	        if(success) 
	        {
	    		textName.clear();
	    		textAuthor.clear();
	    		textGenre.clear();
	    		textID.clear();
	    		textLoanDate.getEditor().clear();
	    		textDueDate.getEditor().clear();
	    		textTakersID.clear();
	    		
	    		application.Main.stage4.hide();
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
    		
    		application.Main.stage4.hide();
    	});
    }
}