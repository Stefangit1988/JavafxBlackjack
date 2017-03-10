package javafxblackjack.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxblackjack.MainApp;

public class ExplanationScreenController {

	private int playermoney;
	
	@FXML
	private Button backbutton;

	@FXML
	private AnchorPane anchor;
	
	public void restartScreen(){
		try {
		Stage restartStage = (Stage) anchor.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/Initialdraw.fxml"));
		AnchorPane tableview = (AnchorPane) loader.load();
		InitialDrawController ctrl = loader.getController();
		ctrl.setPlayermoney(playermoney);
		ctrl.afterPrpertiesSet();
		Scene scene = new Scene(tableview);
		scene.getStylesheets().add(MainApp.class.getResource("javafxblackjack.css").toExternalForm());
		restartStage.setScene(scene);
		restartStage.show();
	}catch(IOException e) {
	    e.printStackTrace();}}
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	public void initialize() {
		
		backbutton.setOnAction(new EventHandler<ActionEvent>(){
			
            @Override
            public void handle(ActionEvent event){
            	restartScreen();
            }
});
		
		
	}

	public int getPlayermoney() {
		return playermoney;
	}

	public void setPlayermoney(int playermoney) {
		this.playermoney = playermoney;
	}
	
	
}
