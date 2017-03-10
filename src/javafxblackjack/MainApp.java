package javafxblackjack;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		tableInit();
	}

	
	public void tableInit(){
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/Initialdraw.fxml"));
		AnchorPane tableview = (AnchorPane) loader.load();
		Scene scene = new Scene(tableview);
		scene.getStylesheets().add(MainApp.class.getResource("javafxblackjack.css").toExternalForm());
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}catch(IOException e) {
        e.printStackTrace();}}
	
	public static void main(String[] args) {
		launch(args);
	}
}
