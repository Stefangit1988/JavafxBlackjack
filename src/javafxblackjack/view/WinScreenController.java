package javafxblackjack.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxblackjack.MainApp;

public class WinScreenController {

private ArrayList<Integer> playerhandcontent = new ArrayList<Integer>();

private ArrayList<Integer> bankhandcontent = new ArrayList<Integer>();

private int playervalue;

private int bankvalue;

private int bet;

private int playermoney;

@FXML
private Text bankhandvalue;

@FXML
private AnchorPane anchor;

@FXML
private Text handvalue;

@FXML
private Button restartbutton;

@FXML
private Text money;

public void restartScreen(){
	try {
	Stage restartStage = (Stage) anchor.getScene().getWindow();
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(MainApp.class.getResource("view/Initialdraw.fxml"));
	AnchorPane tableview = (AnchorPane) loader.load();
	InitialDrawController ctrl = loader.getController();
	ctrl.setMoney(playermoney);
	ctrl.setPlayermoney(playermoney);
	Scene scene = new Scene(tableview);
	scene.getStylesheets().add(MainApp.class.getResource("javafxblackjack.css").toExternalForm());
	restartStage.setScene(scene);
	restartStage.show();
}catch(IOException e) {
    e.printStackTrace();}}
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	public void initialize() {
		restartbutton.setOnAction(new EventHandler<ActionEvent>(){
			
            @Override
            public void handle(ActionEvent event){
            	
            	money.setText(""+playermoney);
            	Text text = new Text();
            	text.setText("+" + (bet*2));
            	text.setX(235);
            	text.setY(406);
            	text.setFont(Font.font("Arial",36));
            	text.setFill(Color.BLACK);
            	anchor.getChildren().add(text);
            	
            	TranslateTransition transition = new TranslateTransition();
            	transition.setDuration(Duration.seconds(0.7));
	            transition.setNode(text);
	            transition.setToY(-228);
	            transition.statusProperty().addListener((obs, oldStatus, newStatus) -> 
	            restartbutton.setDisable(newStatus==Animation.Status.RUNNING));
	            transition.play();
	            
	            
	            transition.setOnFinished(new EventHandler<ActionEvent>() {

	                @Override
	                public void handle(ActionEvent event) {
	                    restartScreen();
	                }
	            });
            	
            }
});
	}
	
	public void afterPrpertiesSet() {
		
		//Creates a Hashmap containing values associated with card IDs
				HashMap<Integer, Integer> cardValue = new HashMap<Integer,Integer>();
				ArrayList<Integer> arl = new ArrayList<Integer>();
				// creates arraylist to assign values to card ID
				int[] ar = {11,10,10,10,10,9,8,7,6,5,4,3,2};
				for(int i: ar){
					for(int j = 0; j<4; j++){
						arl.add(i);
					}
				}
				//assigns card Id and values to hashmap
				for(int i = 1; i<53; i++){
				cardValue.put(i, arl.get(i-1));
				}
				cardValue.put(53, 1);
				cardValue.put(54, 1);
				cardValue.put(55, 1);
				cardValue.put(56, 1);
		
		bankvalue = 0;
		for(int i: bankhandcontent){
			bankvalue += cardValue.get(i);
		}
		bankhandvalue.setText("" + bankvalue);
		handvalue.setText(""+playervalue);
		
		int x = 327;
		int y = 639;
		ImageView card;
		Image image;

		// Places players card on the board from previous screen
		for (Integer i : playerhandcontent) {
			card = new ImageView();
			image = new Image("/images/" + i + ".png");
			card.setImage(image);
			card.setX(x);
			card.setY(y);
			card.setFitHeight(150);
			card.setFitWidth(100);
			anchor.getChildren().add(card);
			x += 100;
			// Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
			if (x >= 1027) {
				x = 327;
				y += 50;
			}
		}
		
		x = 327;
		y = 59;
		for (Integer i : bankhandcontent) {
			card = new ImageView();
			image = new Image("/images/" + i + ".png");
			card.setImage(image);
			card.setX(x);
			card.setY(y);
			card.setFitHeight(150);
			card.setFitWidth(100);
			anchor.getChildren().add(card);
			x += 100;
			// Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
			if (x >= 1027) {
				x = 327;
				y += 50;
			}
		}
		
		playermoney = (playermoney) +(bet*2);
		
	}

	// getters and setters
	public ArrayList<Integer> getPlayerhandcontent() {
		return playerhandcontent;
	}

	public void setPlayerhandcontent(ArrayList<Integer> playerhandcontent) {
		this.playerhandcontent = playerhandcontent;
	}

	public ArrayList<Integer> getBankhandcontent() {
		return bankhandcontent;
	}

	public void setBankhandcontent(ArrayList<Integer> bankhandcontent) {
		this.bankhandcontent = bankhandcontent;
	}

	public int getPlayervalue() {
		return playervalue;
	}

	public void setPlayervalue(int playervalue) {
		this.playervalue = playervalue;
	}

	public int getBankvalue() {
		return bankvalue;
	}

	public void setBankvalue(int bankvalue) {
		this.bankvalue = bankvalue;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getPlayermoney() {
		return playermoney;
	}

	public void setPlayermoney(int playermoney) {
		this.playermoney = playermoney;
	}

	public Text getMoney() {
		return money;
	}

	public void setMoney(int playermoney) {
		money.setText(""+playermoney);;
	}
	
}
