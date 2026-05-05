package application;

import application.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application 
{
	public static Stage stage1;
	public static Stage stage2;
	public static Stage stage3;
	public static Stage stage4;
	public static Stage stage5;
	public static Stage stage6;
	public static Stage stage7;
	
	public static LoginScreen LoginScreen;
	public static BookScreen BookScreen;
	public static AddBookScreen AddBookScreen;
	public static UpdateBookScreen UpdateBookScreen;
	public static TakerScreen TakerScreen;
	public static AddTakerScreen AddTakerScreen;
	public static UpdateTakerScreen UpdateTakerScreen;
	
	@Override
	public void start(Stage primaryStage)
	{
		try 
		{
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/application/views/loginScene.fxml"));
			Parent root1 = loader1.load();
			Scene scene1 = new Scene(root1);
			stage1 = primaryStage;
			stage1.setScene(scene1);
			stage1.show();
	        stage1.setResizable(false);
	        stage1.setTitle("Login Screen");
			
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/application/views/viewBooks.fxml"));
			Parent root2 = loader2.load();
			Scene scene2 = new Scene(root2);
			stage2 = new Stage();
			stage2.setScene(scene2);
			stage2.hide();
	        stage2.setResizable(false);
	        stage2.setTitle("Book Management System");
			
			FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/application/views/addBook.fxml"));
			Parent root3 = loader3.load();
			Scene scene3 = new Scene(root3);
			stage3 = new Stage();
			stage3.setScene(scene3);
			stage3.hide();
	        stage3.setResizable(false);
	        stage3.setTitle("Add Book");
			
			FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/application/views/updateBook.fxml"));
			Parent root4 = loader4.load();
			Scene scene4 = new Scene(root4);
			stage4 = new Stage();
			stage4.setScene(scene4);
			stage4.hide();
	        stage4.setResizable(false);
	        stage4.setTitle("Update");
			
			FXMLLoader loader5 = new FXMLLoader(getClass().getResource("/application/views/viewTakers.fxml"));
			Parent root5 = loader5.load();
			Scene scene5 = new Scene(root5);
			stage5 = new Stage();
			stage5.setScene(scene5);
			stage5.hide();
	        stage5.setResizable(false);
	        stage5.setTitle("Taker Management System");
			
			FXMLLoader loader6 = new FXMLLoader(getClass().getResource("/application/views/addTaker.fxml"));
			Parent root6 = loader6.load();
			Scene scene6 = new Scene(root6);
			stage6 = new Stage();
			stage6.setScene(scene6);
			stage6.hide();
	        stage6.setResizable(false);
	        stage6.setTitle("Add Taker");
			
			FXMLLoader loader7 = new FXMLLoader(getClass().getResource("/application/views/updateTaker.fxml"));
			Parent root7 = loader7.load();
			Scene scene7 = new Scene(root7);
			stage7 = new Stage();
			stage7.setScene(scene7);
			stage7.hide();
	        stage7.setResizable(false);
	        stage7.setTitle("Update");
			
			LoginScreen = loader1.getController();
			BookScreen = loader2.getController();
			AddBookScreen = loader3.getController();
			UpdateBookScreen = loader4.getController();
			TakerScreen = loader5.getController();
			AddTakerScreen = loader6.getController();
			UpdateTakerScreen = loader7.getController();
			
			root1.setId("pane");
			scene1.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			root2.setId("pane");
			scene2.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			root3.setId("pane");
			scene3.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			root4.setId("pane");
			scene4.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			root5.setId("pane");
			scene5.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			root6.setId("pane");
			scene6.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			root7.setId("pane");
			scene7.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}