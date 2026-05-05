package application.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import application.DBConnection;
import application.Main;
import application.Taker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TakerScreen 
{
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;
    
    @FXML
    private TextField textAreaSearch;
    
    @FXML
    private ComboBox<String> comboBoxSearch;

    @FXML
    private TableColumn<Taker, String> columnName;

    @FXML
    private TableColumn<Taker, String> columnPhoneNumber;

    @FXML
    private TableColumn<Taker, String> columnSurname;
    
    @FXML
    private TableColumn<Taker, String> columnEmail;

    @FXML
    private TableColumn<Taker, String> columnTakersID;
    
    @FXML
    private TableColumn<Taker, String> columnHasTakenBook;

    @FXML
    private TableView<Taker> tableView;
    
    ObservableList<Taker> listOfTakers = FXCollections.observableArrayList();
    
    String categories[] = {"Name", "Surname", "Phone Number", "Email", "Taker's ID", "Has taken book"};
    
    public void Populate()
    {
    	columnName.setCellValueFactory(new PropertyValueFactory<Taker, String>("name"));
    	columnSurname.setCellValueFactory(new PropertyValueFactory<Taker, String>("surname"));
    	columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Taker, String>("phoneNumber"));
    	columnEmail.setCellValueFactory(new PropertyValueFactory<Taker, String>("email"));
    	columnTakersID.setCellValueFactory(new PropertyValueFactory<Taker, String>("takersID"));
    	columnHasTakenBook.setCellValueFactory(new PropertyValueFactory<Taker, String>("hasTakenBook"));
    	
    	tableView.getItems().clear();
    	
        try 
        (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM takers");
            ResultSet rs = stmt.executeQuery();
        ) 
        {
            while(rs.next()) 
            {
            	if(rs.getInt(6) == 0)
            	{
            		listOfTakers.add(new Taker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), "BT" + rs.getString(5), "No"));
            	}
            	else
            	{
            		listOfTakers.add(new Taker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), "BT" + rs.getString(5), "Yes"));
            	}
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    	
    	tableView.setItems(listOfTakers);
    }
    
    public boolean AddTaker(String name, String surname, String phoneNumber, String email, String takersID, String hasTakenBook)
    {
	    try
	    (
	    	Connection conn = DBConnection.getConnection();
	    	PreparedStatement stmt = conn.prepareStatement("INSERT INTO takers (Name, Surname, Phone_Number, Email, Takers_ID, Has_taken_book) VALUES (?, ?, ?, ?, ?, ?)");
	    )
	    {
	    	String stringHasTakenBook = null;
			takersID = takersID.replaceAll("\\D+", "");
			if(hasTakenBook.toLowerCase() == "yes")
			{
				hasTakenBook = "1";
				stringHasTakenBook = "Yes";
			}
			else
			{
				hasTakenBook = "0";
				stringHasTakenBook = "No";
			}
			
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setString(3, phoneNumber);
			stmt.setString(4, email);
			stmt.setString(5, takersID);
			stmt.setString(6, hasTakenBook);
			stmt.executeUpdate();
		    listOfTakers.add(new Taker(name, surname, phoneNumber, email, "BT" + takersID, stringHasTakenBook));
			
			return true;
	    }
		catch(SQLIntegrityConstraintViolationException e) 
		{
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Database Error");
	        errorAlert.setHeaderText("Invalid input Detected");
	        errorAlert.setContentText("The entered taker's ID already exists. Please check your inputs.");
	        errorAlert.showAndWait();
	        return false;
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
    public boolean UpdateTaker(String name, String surname, String phoneNumber, String email, String takersID, String hasTakenBook)
    {
    	Taker taker = tableView.getSelectionModel().getSelectedItem();
    	String originalTakersID = taker.getTakersID().replaceAll("\\D+", "");
    	taker.setName(name);
    	taker.setSurname(surname);
    	taker.setPhoneNumber(phoneNumber);
    	taker.setEmail(email);
    	taker.setTakersID(takersID);
    	taker.setHasTakenBook(hasTakenBook);
		
		try 
        (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE takers SET Name = ?, Surname = ?, Phone_Number = ?, Email = ?, Takers_ID = ?, Has_taken_book = ? WHERE Takers_ID = ?");
        )  
        {
			takersID = takersID.replaceAll("\\D+", "");
			
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setString(3, phoneNumber);
			stmt.setString(4, email);
			stmt.setString(5, takersID);
			if(hasTakenBook.equalsIgnoreCase("yes"))
			{
				hasTakenBook = "1";
			}
			else
			{
				hasTakenBook = "0";
			}
			stmt.setString(6, hasTakenBook);
			stmt.setString(7, originalTakersID);
			stmt.executeUpdate();
			tableView.refresh();
			return true;
	    }
		catch(SQLIntegrityConstraintViolationException e) 
		{
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Database Error");
	        errorAlert.setHeaderText("Duplicate Entry Detected");
	        errorAlert.setContentText("The entered taker's ID already exists. Please check your inputs.");
	        errorAlert.showAndWait();
	        return false;
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
    public void DeleteTaker()
    {
    	Taker taker = tableView.getSelectionModel().getSelectedItem();
    	String takersID = taker.getTakersID();
    	
    	try 
        (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM takers WHERE Takers_ID = ?");
        ) 
        {
    		takersID = takersID.replaceAll("\\D+", "");
    		
    		stmt.setString(1, takersID);
    		stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    	
    	listOfTakers.remove(tableView.getSelectionModel().getSelectedItem());
    	tableView.getSelectionModel().getSelectedItem();
    }
    
    public void SearchTakers(String searchCategory, String searchQuery) 
    {
        ObservableList<Taker> filteredTakers = FXCollections.observableArrayList();

        if(searchCategory.equals("Taker's ID")) 
        {
            searchQuery = searchQuery.replaceAll("\\D+", "");

            if(searchQuery.isEmpty()) 
            {
                return;
            }

            if(searchCategory.equals("Taker's ID"))
            {
                searchCategory = "Takers_ID";
            }
        }
        
        if(searchCategory.equals("Has taken book"))
        {
            if(searchCategory.equals("Has taken book"))
            {
                searchCategory = "Has_taken_book";
                if(searchQuery.toLowerCase().equals("yes"))
                {
                	searchQuery = "1";
                }
                else if(searchQuery.toLowerCase().equals("no"))
                {
                	searchQuery = "0";
                }
            }
        }
        
        if(searchCategory.equals("Phone Number"))
        {
        	searchCategory = "Phone_Number";
        }
        
        String customQuery = "SELECT * FROM takers WHERE LOWER(" + searchCategory + ") LIKE ?";

        try 
        (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(customQuery)
        ) 
        {
            stmt.setString(1, "%" + searchQuery.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) 
            {
            	if(rs.getInt(6) == 0)
            	{
            		filteredTakers.add(new Taker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), "BT" + rs.getString(5), "No"));
            	}
            	else
            	{
            		filteredTakers.add(new Taker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), "BT" + rs.getString(5), "Yes"));
            	}
            }

            tableView.getItems().clear();
            tableView.setItems(filteredTakers);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void initialize()
    {
        comboBoxSearch.getItems().clear();
        comboBoxSearch.getItems().addAll(categories);
        comboBoxSearch.setValue(null);
    	
    	buttonBack.setOnAction(event ->
    	{
    		Main.stage5.hide();
    		Main.stage2.show();
    	});
    	
    	buttonAdd.setOnAction(event ->
    	{
    		Main.stage6.show();
    	});
    	
    	buttonDelete.setOnAction(event ->
    	{
    		DeleteTaker();
    	});
    	
    	buttonUpdate.setOnAction(event ->
    	{
    		Taker taker = tableView.getSelectionModel().getSelectedItem();
    		Main.UpdateTakerScreen.SetTextFields(taker.getName(), taker.getSurname(), taker.getPhoneNumber(), taker.getEmail(), taker.getTakersID(), taker.getHasTakenBook());
    		
    		Main.stage7.show();
    	});
    	
    	textAreaSearch.textProperty().addListener((observable, oldValue, newValue) -> 
    	{
    	    String category = comboBoxSearch.getValue();

    	    if(category == null || category.trim().isEmpty()) 
    	    {
    	        return;
    	    }

    	    if(newValue.trim().isEmpty()) 
    	    {
    	        Populate();
    	    } 
    	    else 
    	    {
    	        SearchTakers(category, newValue);
    	    }
    	});
    }
}