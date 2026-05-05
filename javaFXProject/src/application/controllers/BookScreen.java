package application.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import application.Book;
import application.DBConnection;
import application.Main;
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

public class BookScreen 
{
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;
    
    @FXML
    private Button buttonLogout;
    
    @FXML
    private Button buttonTakers;
    
    @FXML
    private TextField textAreaSearch;
    
    @FXML
    private ComboBox<String> comboBoxSearch;

    @FXML
    private TableColumn<Book, String> columnAuthor;

    @FXML
    private TableColumn<Book, String> columnID;

    @FXML
    private TableColumn<Book, String> columnLoanDate;
    
    @FXML
    private TableColumn<Book, String> columnGenre;

    @FXML
    private TableColumn<Book, String> columnName;

    @FXML
    private TableColumn<Book, String> columnDueDate;
    
    @FXML
    private TableColumn<Book, String> columnTakersID;

    @FXML
    private TableView<Book> tableView;
    
    ObservableList<Book> listOfBooks = FXCollections.observableArrayList();
    
    String categories[] = {"Name", "Author", "Genre", "ID", "Taker's ID"};
    
    public boolean AddBook(String name, String author, String genre, String ID, String loanDate, String dueDate, String takersID)
    {
	    try
	    (
	    	Connection conn = DBConnection.getConnection();
	    	PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (Name, Author, Genre, ID, Loan_Date, Due_Date, Takers_ID) VALUES (?, ?, ?, ?, ?, ?, ?)");
	    )
	    {
			ID = ID.replaceAll("\\D+", "");
			takersID = takersID.replaceAll("\\D+", "");
			
			stmt.setString(1, name);
			stmt.setString(2, author);
			stmt.setString(3, genre);
			stmt.setString(4, ID);
			stmt.setString(5, loanDate);
			stmt.setString(6, dueDate);
			if(takersID.isEmpty()) 
			{
			    stmt.setNull(7, java.sql.Types.INTEGER);
			    stmt.executeUpdate();
				listOfBooks.add(new Book(name, author, genre, "BIL" + ID, loanDate, dueDate, takersID));
			} 
			else 
			{
			    stmt.setInt(7, Integer.parseInt(takersID));
			    stmt.executeUpdate();
			    listOfBooks.add(new Book(name, author, genre, "BIL" + ID, loanDate, dueDate, "BT" + takersID));
			}
			
			return true;
	    }
		catch(SQLIntegrityConstraintViolationException e) 
		{
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Database Error");
	        errorAlert.setHeaderText("Duplicate Entry Detected");
	        errorAlert.setContentText("The entered book ID and/or taker's ID already exists. Please check your inputs.");
	        errorAlert.showAndWait();
	        return false;
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
    public boolean UpdateBook(String name, String author, String genre, String ID, String loanDate, String dueDate, String takersID)
    {
    	Book book = tableView.getSelectionModel().getSelectedItem();
    	String originalID = book.getID().replaceAll("\\D+", "");
		book.setName(name);
		book.setAuthor(author);
		book.setGenre(genre);
		book.setID(ID);
		book.setLoanDate(loanDate);
		book.setDueDate(dueDate);
		book.setTakersID(takersID);
		
		try 
        (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE books SET Name = ?, Author = ?, Genre = ?, ID = ?, Loan_Date = ?, Due_Date = ?, Takers_ID = ? WHERE ID = ?");
        )  
        {
			ID = ID.replaceAll("\\D+", "");
			if(takersID != null)
			{
				takersID = takersID.replaceAll("\\D+", "");
			}
			
			stmt.setString(1, name);
			stmt.setString(2, author);
			stmt.setString(3, genre);
			stmt.setString(4, ID);
			if(loanDate == null && dueDate == null && takersID == null)
			{
				stmt.setString(5, null);
				stmt.setString(6, null);
				stmt.setString(7, null);
			}
			else
			{
				stmt.setString(5, loanDate);
				stmt.setString(6, dueDate);
				stmt.setString(7, takersID);
			}
			stmt.setString(8, originalID);
			stmt.executeUpdate();
			return true;
	    }
		catch(SQLIntegrityConstraintViolationException e) 
		{
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Database Error");
	        errorAlert.setHeaderText("Duplicate Entry Detected");
	        errorAlert.setContentText("The entered taker's ID and/or book ID already exists. Please check your inputs.");
	        errorAlert.showAndWait();
	        return false;
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
    public void DeleteBook()
    {
    	Book book = tableView.getSelectionModel().getSelectedItem();
    	String ID = book.getID();
    	
    	try 
        (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE ID = ?");
        ) 
        {
    		ID = ID.replaceAll("\\D+", "");
    		
    		stmt.setString(1, ID);
    		stmt.executeUpdate();
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    	
    	listOfBooks.remove(tableView.getSelectionModel().getSelectedItem());
    	tableView.getSelectionModel().getSelectedItem();
    }
    
    public void Populate()
    {
    	columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
    	columnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    	columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	columnLoanDate.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
    	columnDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    	columnTakersID.setCellValueFactory(new PropertyValueFactory<>("takersID"));
    	
    	tableView.getItems().clear();
    	
        try 
        (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
            ResultSet rs = stmt.executeQuery();
        ) 
        {
            while(rs.next()) 
            {
            	String takersID = null;
            	
            	if(rs.getString(7) != null)
            	{
            		takersID = "BT" + rs.getString(7);
            		
                    listOfBooks.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), "BIL" + rs.getString(4), rs.getString(5), rs.getString(6), takersID));
            	}
            	else
            	{
                    listOfBooks.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), "BIL" + rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            	}
            }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }

        tableView.setItems(listOfBooks);
    }
    
    public void SearchBooks(String searchCategory, String searchQuery) 
    {
        ObservableList<Book> filteredBooks = FXCollections.observableArrayList();

        if(searchCategory.equals("Taker's ID") || searchCategory.equals("ID")) 
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
        
        String customQuery = "SELECT * FROM books WHERE LOWER(" + searchCategory + ") LIKE ?";

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
                String takersID = rs.getString(7) != null ? "BT" + rs.getString(7) : null;
                filteredBooks.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), "BIL" + rs.getString(4), rs.getString(5), rs.getString(6), takersID));
            }

            tableView.getItems().clear();
            tableView.setItems(filteredBooks);
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
    	
    	Populate();
    	
    	buttonLogout.setOnAction(event ->
    	{
    		Main.stage2.hide();
    		Main.stage1.show();
    	});
    	
    	buttonAdd.setOnAction(event ->
    	{
    		Main.stage3.show();
    	});
    	
    	buttonDelete.setOnAction(event ->
    	{
    		DeleteBook();
    	});
    	
    	buttonUpdate.setOnAction(event ->
    	{
    		Book book = tableView.getSelectionModel().getSelectedItem();
    		Main.UpdateBookScreen.SetTextFields(book.getName(), book.getAuthor(), book.getGenre(), book.getID(), book.getLoanDate(), book.getDueDate(), book.getTakersID());
    		
    		Main.stage4.show();
    	});
    	
    	buttonTakers.setOnAction(event ->
    	{
    		Main.TakerScreen.Populate();
    		Main.stage5.show();
    		Main.stage2.hide();
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
    	        SearchBooks(category, newValue);
    	    }
    	});
    }
}