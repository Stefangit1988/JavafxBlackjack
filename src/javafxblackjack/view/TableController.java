package javafxblackjack.view;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxblackjack.MainApp;

public class TableController {
	
	private HashMap<Integer, Integer> cardValue;
	
	private ArrayList<Integer> playerhandcontent = new ArrayList<Integer>();

	private ArrayList<Integer> bankhandcontent = new ArrayList<Integer>();

	private int playervalue;

	private int bankhandvalue;
	
	private int playermoney;
	
	private int bet;

	@FXML
    private AnchorPane anchor;
	
	@FXML
    private Pane toppane;
	
	@FXML
    private StackPane stackpane;
	
	@FXML
    private Button hitbutton;

    @FXML
    private Button standbutton;
	
    @FXML
    private Text handvalue;
    
    @FXML
    private Text bankvalue;
    
    
    @FXML
    private Text money;

    // method to display lossscreen
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
	ctrl.setMoney(playermoney);
	if(playermoney <50){
	ctrl.setLosetext("Game over!");}
	ctrl.afterPrpertiesSet();
	Scene lossScene = new Scene(lossview);
	lossStage.setScene(lossScene);
	lossStage.show();
 	}catch(Exception e){}
   	}
    
    // method to display winscreen
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
    
    // method to display bank turn screen after clicking stand
    public void standScreen(int playervalue,  ArrayList<Integer> playerHandContent,ArrayList<Integer> bankhandcontent,HashMap<Integer, Integer> cardValue){
    	try{
    	    Stage standStage = (Stage) anchor.getScene().getWindow();
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/StandTableView.fxml"));
    		AnchorPane standview = (AnchorPane) loader.load();
    		StandTableController ctrl = loader.getController();
    		ctrl.setHandvalue(""+playervalue);
    		ctrl.setplayervalue(playervalue);
    		ctrl.setplayHandContent(playerHandContent);
    		ctrl.setBankhandcontent(bankhandcontent);
    		ctrl.setCardValue(cardValue);
    		ctrl.setPlayermoney(playermoney);
    		ctrl.setBet(bet);
    		ctrl.setMoney(playermoney);
    		ctrl.afterPrpertiesSet();
    		Scene standScene = new Scene(standview);
    		standScene.getStylesheets().add(MainApp.class.getResource("javafxblackjack.css").toExternalForm());
    		standStage.setScene(standScene);
    		standStage.show();
    	    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }

    //method to display the explanation screen
    public void explanationScreen(){
    	try {
   		Stage explainStage = (Stage) anchor.getScene().getWindow();
   		FXMLLoader loader = new FXMLLoader();
   		loader.setLocation(MainApp.class.getResource("view/ExplanationScreen.fxml"));
   		AnchorPane explainview = (AnchorPane) loader.load();
   		Scene scene = new Scene(explainview);
   		explainStage.setScene(scene);
   		explainStage.show();
   	}catch(IOException e) {
           e.printStackTrace();}}
    
    public void aceReplacer(){
		if(playerhandcontent.contains(1)){
			playerhandcontent.set(playerhandcontent.indexOf(new Integer(1)),53);
		}else if(playerhandcontent.contains(2)){
			playerhandcontent.set(playerhandcontent.indexOf(new Integer(2)),54);
		}else if(playerhandcontent.contains(3)){
			playerhandcontent.set(playerhandcontent.indexOf(new Integer(3)),55);
		}else if(playerhandcontent.contains(4)){
			playerhandcontent.set(playerhandcontent.indexOf(new Integer(4)),56);
		}
	}
    
    @FXML// This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
		
    	
    	
		// Creates arrayList containing ID for cards.
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
		
		//Drawinging a card and checking the players handvalue when pressing the hit button
		 hitbutton.setOnAction(new EventHandler<ActionEvent>(){
			 int x = 527;
			 int y = 639;
			 ImageView card;
			 Image image;
			 
	            @Override
	            public void handle(ActionEvent event){
	            	// draws a random card and removes it from the deck
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
	               TranslateTransition transition = new TranslateTransition();
	               transition.setDuration(Duration.seconds(0.7));
	               transition.setNode(card);
	               transition.setToX(x);
	               transition.setToY(y);
	               transition.statusProperty().addListener((obs, oldStatus, newStatus) -> 
	               hitbutton.setDisable(newStatus==Animation.Status.RUNNING));
	               transition.play();
	               x+= 100;
	               // Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
	               if(x >= 1027){
	            	   x = 327;
	            	   y += 50;
	               }
	            
	               // wacht tot de kaart animatie af is
	               transition.setOnFinished(new EventHandler<ActionEvent>(){
	            	   @Override
	                   public void handle(ActionEvent arg0) {
	               
	            // adds the card ID to the players hand content.   
	            playerhandcontent.add(cardnumber);
	            // sets player hand value to value it previously had + the new card drawn   
	            playervalue = (playervalue + cardValue.get(cardnumber));
	            handvalue.setText("" + playervalue);
	            while(true){
	            //Checks to see if player has reached a win/lose condition.
	            if (playervalue > 21){
	            	//check if players hand contains ace, replace with a different ID, subtract 10 from value.
	            	if(playerhandcontent.contains(1)||playerhandcontent.contains(2)||playerhandcontent.contains(3)||playerhandcontent.contains(4)){
	            		aceReplacer();
	            		playervalue -= 10;
	            		handvalue.setText("" + playervalue);
	            		int x = 327;
	            		int y = 639;
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
	            		continue;
	            	}else{ 	// go to lose screen scene
	            			lossScreen( bankhandvalue, playervalue, bankhandcontent, playerhandcontent, bet);
	            			break;
	            	}
	            	} else if(playervalue == 21){
	            		// go to win screen
	            			winScreen( bankhandvalue, playervalue, bankhandcontent, playerhandcontent, bet);
	            			break;
	            	}else{break;}
	            //end of while loop
	            }
	            // end of transition.setonfinished
	            }});
	            } 
	            
		 });
		
		 //links the method going to bank player turn screen when pressing stand button
		 standbutton.setOnAction(new EventHandler<ActionEvent>(){
			 
			 @Override
	            public void handle(ActionEvent event){
				 standScreen(playervalue, playerhandcontent, bankhandcontent, cardValue);
	               // end of standbutton event
			 }
			 // end of standbutton.setonaction
		 });
		 
		 
		 
// end of initialize
    }

	
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

	public int getBankhandvalue() {
		return bankhandvalue;
	}

	public void setBankhandvalue(int bankhandvalue) {
		this.bankhandvalue = bankhandvalue;
	}

	
public HashMap<Integer, Integer> getCardValue() {
		return cardValue;
	}

	public void setCardValue(HashMap<Integer, Integer> cardValue) {
		this.cardValue = cardValue;
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

public int getPlayermoney() {
		return playermoney;
	}

	public void setPlayermoney(int playermoney) {
		this.playermoney = playermoney;
	}

public void afterPrpertiesSet() {
		
		bankvalue.setText("" + bankhandvalue);
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
		card = new ImageView();
		image = new Image("/images/" + bankhandcontent.get(0) + ".png");
		card.setImage(image);
		card.setX(x);
		card.setY(y);
		card.setFitHeight(150);
		card.setFitWidth(100);
		anchor.getChildren().add(card);
			x += 100;
			// Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
			
		
		card = new ImageView();
		image = new Image("/images/cardback.png");
		card.setImage(image);
		card.setX(x);
		card.setY(y);
		card.setFitHeight(150);
		card.setFitWidth(100);
		anchor.getChildren().add(card);
		x += 100;
	}
// end of table controller	
}

