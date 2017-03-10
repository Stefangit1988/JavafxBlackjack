package javafxblackjack.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxblackjack.MainApp;

public class InitialDrawController {

	private int counter = 0;
	private int xplayer = 327;
	private int yplayer = 639;
	private int xbank = 327;
	private int ybank = 59;
	private int playermoney = 500;
	private int bet;
	 
	
	@FXML
    private Text bankvalue;

	@FXML
    private Text bankhandvalue;
	
    @FXML
    private Pane toppane;

    @FXML
    private AnchorPane anchor;

    @FXML
    private StackPane stackpane;

    @FXML
    private Button continuebutton;

    @FXML
    private Text handvalue;
    
    @FXML
    private Text money;
    
    @FXML
    private Button explanationbutton;
    
    public void lossScreen(int bankvalue,int playervalue, ArrayList<Integer> bankhandcontent, ArrayList<Integer> playerhandcontent, int bet) {
    	try{
    Stage lossStage = (Stage) anchor.getScene().getWindow();
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(MainApp.class.getResource("view/LossScreen.fxml"));
	AnchorPane lossview = (AnchorPane) loader.load();
	LossScreenController ctrl = loader.getController();
	ctrl.setBankhandcontent(bankhandcontent);
	ctrl.setBankvalue(bankvalue);
	ctrl.setPlayerhandcontent(playerhandcontent);
	ctrl.setPlayervalue(playervalue);
	ctrl.setBet(bet);
	ctrl.setPlayermoney(playermoney);
	if(playermoney <50){
		ctrl.setLosetext("Game over!");}
	ctrl.afterPrpertiesSet();
	Scene lossScene = new Scene(lossview);
	lossStage.setScene(lossScene);
	lossStage.show();
 	}catch(Exception e){}
   	}

    public void winScreen(int bankvalue,int playervalue, ArrayList<Integer> bankhandcontent, ArrayList<Integer> playerhandcontent, int bet) {
    	try{
    Stage winStage = (Stage) anchor.getScene().getWindow();
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(MainApp.class.getResource("view/WinScreen.fxml"));
	AnchorPane winview = (AnchorPane) loader.load();
	WinScreenController ctrl = loader.getController();
	ctrl.setBankhandcontent(bankhandcontent);
	ctrl.setBankvalue(bankvalue);
	ctrl.setPlayerhandcontent(playerhandcontent);
	ctrl.setPlayervalue(playervalue);
	ctrl.setBet(bet);
	ctrl.setPlayermoney(playermoney);
	ctrl.setMoney(playermoney);
	ctrl.afterPrpertiesSet();
	Scene winScene = new Scene(winview);
	winStage.setScene(winScene);
	winStage.show();
    	}catch(Exception e){e.printStackTrace();}
    	}
    
    public void playerturnscreen(BankHand bank, PlayerHand player, HashMap<Integer, Integer> cardValue, int bet){
    	try{
    	    Stage stage = (Stage) anchor.getScene().getWindow();
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/Tableview.fxml"));
    		AnchorPane winview = (AnchorPane) loader.load();
    		TableController ctrl = loader.getController();
    		ctrl.setBankhandcontent(bank.getBankhandContent());
    		ctrl.setBankhandvalue(bank.getBankhandValue());
    		ctrl.setPlayerhandcontent(player.getPlayerhandContent());
    		ctrl.setPlayervalue(player.getHandValue());
    		ctrl.setCardValue(cardValue);
    		ctrl.setBet(bet);
    		ctrl.setPlayermoney(playermoney);
    		ctrl.setMoney(playermoney);
    		ctrl.afterPrpertiesSet();
    		Scene scene = new Scene(winview);
    		scene.getStylesheets().add(MainApp.class.getResource("javafxblackjack.css").toExternalForm());
    		stage.setScene(scene);
    		stage.show();
    	    	}catch(Exception e){e.printStackTrace();}
    	    	}
    
    public void explanationScreen(){
    	try {
   		Stage explainStage = (Stage) anchor.getScene().getWindow();
   		FXMLLoader loader = new FXMLLoader();
   		loader.setLocation(MainApp.class.getResource("view/ExplanationScreen.fxml"));
   		AnchorPane explainview = (AnchorPane) loader.load();
   		ExplanationScreenController ctrl = loader.getController();
   		ctrl.setPlayermoney(playermoney);
   		Scene scene = new Scene(explainview);
   		explainStage.setScene(scene);
   		explainStage.show();
   	}catch(IOException e) {
           e.printStackTrace();}}
    
    @FXML// This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
    	ArrayList<Integer> arlist = new ArrayList<Integer>();
		for(int j = 0 ; j < 4; j++){
		for(int i = 1; i<53; i++){
			arlist.add(i);
		}
		}
		
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
		
		// creates an object of Playerhand and BankHand to track the value of the cards in their hands.
		PlayerHand player = new PlayerHand();
		BankHand bank = new BankHand();
		
		continuebutton.setOnAction(new EventHandler<ActionEvent>(){
			 
			 ImageView card;
			 ImageView card2;
			 Image image;
			 
			 @Override
		        public void handle(ActionEvent event){
				 
				 if(counter ==1){
					 
					 Label label = new Label();
					 label.setLayoutX(435);
					 label.setLayoutY(375);
					 label.setText("How much would you like to bet this round?\n           The minimum bet is 50.");
					 label.setFont(new Font("Arial", 20));
					 anchor.getChildren().add(label);
					 
					 TextField textfield = new TextField();
					 textfield.setPrefHeight(50);
					 textfield.setPrefWidth(200);
					 textfield.setLayoutX(500);
					 textfield.setLayoutY(324);
					// force the field to be numeric only
					 textfield.textProperty().addListener(new ChangeListener<String>() {
				      @Override
		     	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					            if (!newValue.matches("\\d*")) {
					                textfield.setText(newValue.replaceAll("[^\\d]", ""));
					            }
					        }
					    });
					 anchor.getChildren().add(textfield);
					 
					 continuebutton.setOnAction(new EventHandler<ActionEvent>(){
						 
						
						 @Override
					        public void handle(ActionEvent event){
					if(textfield.getText().trim().equals("")){
						label.setTextFill(Color.RED);
						label.setText("You need to input some amount");
					}else{
						if(Integer.parseInt(textfield.getText()) < 50){
							label.setTextFill(Color.RED);
							label.setText("The minimum bet is 50");
						}
						else if(Integer.parseInt(textfield.getText()) <= playermoney){
						 setBet(Integer.parseInt(textfield.getText()));
						 playermoney = playermoney-bet;
						 money.setText(""+playermoney);
						 
			            	Text text = new Text();
			            	text.setText("-" + bet);
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
				            continuebutton.setDisable(newStatus==Animation.Status.RUNNING));
				            transition.play();
						 
						 Random r = new Random();
			            	int cardnumber = arlist.get(r.nextInt(arlist.size()));
			            	arlist.remove(arlist.indexOf(cardnumber));
			            	// adds card to screen
			               card = new ImageView();
			               image = new Image("/images/"+cardnumber+".png");
			               card.setImage(image);
			               card.setX(0);
			               card.setY(0);
			               card.setFitHeight(150);
			               card.setFitWidth(100);
			               anchor.getChildren().add(card);
			               
			               int cardnumber2 = arlist.get(r.nextInt(arlist.size()));
			            	arlist.remove(arlist.indexOf(cardnumber2));
			            	// adds card to screen
			               card2 = new ImageView();
			               if(counter ==1){
			            	   image = new Image("/images/cardback.png");
			               }else{
			               image = new Image("/images/"+cardnumber2+".png");}
			               card2.setImage(image);
			               card2.setX(0);
			               card2.setY(0);
			               card2.setFitHeight(150);
			               card2.setFitWidth(100);
			               anchor.getChildren().add(card2);
			               
			               
			               TranslateTransition transitionbank = new TranslateTransition();
			               transitionbank.setDuration(Duration.seconds(0.7));
			               transitionbank.setNode(card2);
			               transitionbank.setToX(xbank);
			               transitionbank.setToY(ybank);
			               transitionbank.play();
			               xbank += 100;
			               
			               TranslateTransition transitionplayer = new TranslateTransition();
			               transitionplayer.setDuration(Duration.seconds(0.7));
			               transitionplayer.setNode(card);
			               transitionplayer.setToX(xplayer);
			               transitionplayer.setToY(yplayer);
			               transitionplayer.statusProperty().addListener((obs, oldStatus, newStatus) -> 
			               continuebutton.setDisable(newStatus==Animation.Status.RUNNING));
			               transitionplayer.play();
			               xplayer+= 100;
			              
			               // Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
			               if(xplayer >= 1027){
			            	   xplayer = 327;
			            	   yplayer += 50;
			               }
			               if(xbank >= 1027){
			            	   xbank = 327;
			            	   ybank += 50;
			               }
			               transitionplayer.setOnFinished(new EventHandler<ActionEvent>(){
			            	   @Override
			                   public void handle(ActionEvent arg0) {
			               
			            // adds the card ID to the players hand content.   
			            player.addPlayerhandContent(cardnumber);
			            // sets player hand value to value it previously had + the new card drawn   
			            player.setHandValue(player.getHandValue()+cardValue.get(cardnumber));
			            handvalue.setText("" + player.getHandValue());
			            
			            // adds new card to the banks hand of card ID's
			            if(counter==1){
			            	bank.setFaceDownCard(cardnumber2);
			            	bank.addBankhandContent(cardnumber2);
			            }else{
			            bank.addBankhandContent(cardnumber2);
			            
				        // sets bank hand value to value it previously had + the new card drawn   
				        bank.setBankhandValue(bank.getBankhandValue()+cardValue.get(cardnumber2));
				        bankhandvalue.setText("" + bank.getBankhandValue());
			            }
			            counter++;
			            
			            while(true){
			            //Checks to see if player has reached a win/lose condition.
			            if (player.getHandValue() > 21){
			            	//check if players hand contains ace, replace with a different ID, subtract 10 from value.
			            	if(player.getPlayerhandContent().contains(1)||player.getPlayerhandContent().contains(2)||player.getPlayerhandContent().contains(3)||player.getPlayerhandContent().contains(4)){
			            		player.aceReplacer();
			            		player.setHandValue(player.getHandValue()-10);
			            		handvalue.setText("" + player.getHandValue());
			            		int x = 327;
			            		int y = 639;
			            		for (Integer i : player.getPlayerhandContent()) {
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
			            		continue;
			            	}else{ 	// go to lose screen scene
			            			lossScreen( bank.getBankhandValue(), player.getHandValue(), bank.getBankhandContent(), player.getPlayerhandContent(), bet);
			            			break;
			            	}
			            	} else if(player.getHandValue() == 21){
			            		// go to win screen
			            			winScreen( bank.getBankhandValue(), player.getHandValue(), bank.getBankhandContent(), player.getPlayerhandContent(), bet);
			            			break;
			            	}else{playerturnscreen(bank,player, cardValue, bet); break;}
			            
			            
			            //end of while loop
			            }
			            // end of transition.setonfinished
			            
			            }});
					 
						 
					 }else {
						 label.setTextFill(Color.RED);
						 label.setText("You do not have that much money");}
					}
						 }});
					
				 }else{
				 
				 Random r = new Random();
	            	int cardnumber = arlist.get(r.nextInt(arlist.size()));
	            	arlist.remove(arlist.indexOf(cardnumber));
	            	// adds card to screen
	               card = new ImageView();
	               image = new Image("/images/"+cardnumber+".png");
	               card.setImage(image);
	               card.setX(0);
	               card.setY(0);
	               card.setFitHeight(150);
	               card.setFitWidth(100);
	               anchor.getChildren().add(card);
	               
	               int cardnumber2 = arlist.get(r.nextInt(arlist.size()));
	            	arlist.remove(arlist.indexOf(cardnumber2));
	            	// adds card to screen
	               card2 = new ImageView();
	               if(counter ==1){
	            	   image = new Image("/images/cardback.png");
	               }else{
	               image = new Image("/images/"+cardnumber2+".png");}
	               card2.setImage(image);
	               card2.setX(0);
	               card2.setY(0);
	               card2.setFitHeight(150);
	               card2.setFitWidth(100);
	               anchor.getChildren().add(card2);
	               
	               
	               TranslateTransition transitionbank = new TranslateTransition();
	               transitionbank.setDuration(Duration.seconds(0.7));
	               transitionbank.setNode(card2);
	               transitionbank.setToX(xbank);
	               transitionbank.setToY(ybank);
	               transitionbank.play();
	               xbank += 100;
	               
	               TranslateTransition transitionplayer = new TranslateTransition();
	               transitionplayer.setDuration(Duration.seconds(0.7));
	               transitionplayer.setNode(card);
	               transitionplayer.setToX(xplayer);
	               transitionplayer.setToY(yplayer);
	               transitionplayer.statusProperty().addListener((obs, oldStatus, newStatus) -> 
	               continuebutton.setDisable(newStatus==Animation.Status.RUNNING));
	               transitionplayer.play();
	               xplayer+= 100;
	              
	               // Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
	               if(xplayer >= 1027){
	            	   xplayer = 327;
	            	   yplayer += 50;
	               }
	               if(xbank >= 1027){
	            	   xbank = 327;
	            	   ybank += 50;
	               }
	               transitionplayer.setOnFinished(new EventHandler<ActionEvent>(){
	            	   @Override
	                   public void handle(ActionEvent arg0) {
	               
	            // adds the card ID to the players hand content.   
	            player.addPlayerhandContent(cardnumber);
	            // sets player hand value to value it previously had + the new card drawn   
	            player.setHandValue(player.getHandValue()+cardValue.get(cardnumber));
	            handvalue.setText("" + player.getHandValue());
	            
	            // adds new card to the banks hand of card ID's
	            if(counter==1){
	            	bank.setFaceDownCard(cardnumber2);
	            	bank.addBankhandContent(cardnumber2);
	            }else{
	            bank.addBankhandContent(cardnumber2);
	            
		        // sets bank hand value to value it previously had + the new card drawn   
		        bank.setBankhandValue(bank.getBankhandValue()+cardValue.get(cardnumber2));
		        bankhandvalue.setText("" + bank.getBankhandValue());
	            }
	            counter++;
	            
	            while(true){
	            //Checks to see if player has reached a win/lose condition.
	            if (player.getHandValue() > 21){
	            	//check if players hand contains ace, replace with a different ID, subtract 10 from value.
	            	if(player.getPlayerhandContent().contains(1)||player.getPlayerhandContent().contains(2)||player.getPlayerhandContent().contains(3)||player.getPlayerhandContent().contains(4)){
	            		player.aceReplacer();
	            		player.setHandValue(player.getHandValue()-10);
	            		handvalue.setText("" + player.getHandValue());
	            		int x = 327;
	            		int y = 639;
	            		for (Integer i : player.getPlayerhandContent()) {
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
	            		continue;
	            	}else{ 	// go to lose screen scene
	            			lossScreen( bank.getBankhandValue(), player.getHandValue(), bank.getBankhandContent(), player.getPlayerhandContent(), bet);
	            			break;
	            	}
	            	} else if(player.getHandValue() == 21){
	            		// go to win screen
	            			winScreen( bank.getBankhandValue(), player.getHandValue(), bank.getBankhandContent(), player.getPlayerhandContent(), bet);
	            			break;
	            	}else{break;}
	            
	            
	            //end of while loop
	            }
	            if(!player.getPlayerhandContent().isEmpty()){
	   			 explanationbutton.setDisable(true);
	   		 }
	            // end of transition.setonfinished
	            }});
			 }	 
			 }});
		
		
		
		explanationbutton.setOnAction(new EventHandler<ActionEvent>(){
			
            @Override
            public void handle(ActionEvent event){
            	explanationScreen();
            }   
});
		
    }

    public void afterPrpertiesSet() {
    	money.setText(""+playermoney);
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
		money.setText(""+playermoney);
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

}
