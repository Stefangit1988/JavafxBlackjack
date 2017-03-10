package javafxblackjack.view;

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

public class StandTableController {

	private HashMap<Integer, Integer> cardValue; 
	
	private ArrayList<Integer> bankhandcontent;

	private int playervalue;

	private int bankvalue;
	
	private int playermoney;
	
	private int bet;
	
	private int x;
	
	private int y;
	
	@FXML
	private ArrayList<Integer> playerHandContent;

	@FXML
	private Text bankhandvalue;

	@FXML
	private Pane toppane;

	@FXML
	private AnchorPane anchor;

	@FXML
	private StackPane stackpane;

	@FXML
	private Text handvalue;
	
	@FXML
    private Button continuebutton;
	
	@FXML
    private Text money;

	public void lossScreen(int playervalue, ArrayList<Integer> bankhandcontent, ArrayList<Integer> playerhandcontent) {
    	try{
    Stage lossStage = (Stage) anchor.getScene().getWindow();
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(MainApp.class.getResource("view/LossScreen.fxml"));
	AnchorPane lossview = (AnchorPane) loader.load();
	LossScreenController ctrl = loader.getController();
	ctrl.setBankhandcontent(bankhandcontent);
	ctrl.setPlayerhandcontent(playerhandcontent);
	ctrl.setPlayervalue(playervalue);
	ctrl.setPlayermoney(playermoney);
	ctrl.setBet(bet);
	ctrl.setMoney(playermoney);
	if(playermoney <50){
		ctrl.setLosetext("Game over!");}
	ctrl.afterPrpertiesSet();
	Scene lossScene = new Scene(lossview);
	lossStage.setScene(lossScene);
	lossStage.show();
 	}catch(Exception e){}
   	}

	public void winScreen(int bankvalue,int playervalue, ArrayList<Integer> bankhandcontent, ArrayList<Integer> playerhandcontent) {
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
	ctrl.setPlayermoney(playermoney);
	ctrl.setBet(bet);
	ctrl.setMoney(playermoney);
	ctrl.afterPrpertiesSet();
	Scene winScene = new Scene(winview);
	winStage.setScene(winScene);
	winStage.show();
    	}catch(Exception e){e.printStackTrace();}
    	}
	
	public void drawScreen(int bankvalue,int playervalue, ArrayList<Integer> bankhandcontent, ArrayList<Integer> playerhandcontent){
		try{
		    Stage DrawStage = (Stage) anchor.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DrawScreen.fxml"));
			AnchorPane drawview = (AnchorPane) loader.load();
			DrawScreenController ctrl = loader.getController();
			ctrl.setBankhandcontent(bankhandcontent);
			ctrl.setBankvalue(bankvalue);
			ctrl.setPlayerhandcontent(playerhandcontent);
			ctrl.setPlayervalue(playervalue);
			ctrl.setPlayermoney(playermoney);
			ctrl.setMoney(playermoney);
			ctrl.setBet(bet);
			ctrl.afterPrpertiesSet();
			Scene drawScene = new Scene(drawview);
			DrawStage.setScene(drawScene);
			DrawStage.show();
		    	}catch(Exception e){}
		    	}
	
	public void compareScore(int bankvalue, int playervalue, ArrayList<Integer> bankcontent, ArrayList<Integer> playercontent){
		if(playervalue > bankvalue){
			winScreen(bankvalue,playervalue,bankcontent,playercontent);
		}else if(bankvalue > playervalue){
			lossScreen(playervalue,bankcontent,playercontent);
		}else{
			drawScreen(bankvalue,playervalue,bankcontent,playercontent);
		}
	}
	
	public void drawCard(ArrayList<Integer> arlist){
		Random r = new Random();
    	int cardnumber = arlist.get(r.nextInt(arlist.size()));
    	arlist.remove(arlist.indexOf(cardnumber));
    	// adds card to screen
       ImageView card = new ImageView();
       Image image = new Image("/images/"+cardnumber+".png");
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
       continuebutton.setDisable(newStatus==Animation.Status.RUNNING));
       transition.play();
       this.x+= 100;
       // Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
       if(this.x >= 1027){
    	   this.x = 327;
    	   this.y += 50;
       }
		
       transition.setOnFinished(new EventHandler<ActionEvent>(){
    	   
    	   @Override
           public void handle(ActionEvent arg0) {
       // adds new card to the banks hand of card ID's
       bankhandcontent.add(cardnumber);
        // sets bank hand value to value it previously had + the new card drawn   
        bankvalue+=cardValue.get(cardnumber);
        bankhandvalue.setText("" + bankvalue);
        
      //Checks to see if bank has reached a win/lose condition.
        
        
        if (bankvalue > 21){
        	//check if players hand contains ace, replace with a different ID, subtract 10 from value, and rechecks win status
        	if(bankhandcontent.contains(1)||bankhandcontent.contains(2)||bankhandcontent.contains(3)||bankhandcontent.contains(4)){
        		aceReplacer();
        		bankvalue -= 10;
        		bankhandvalue.setText("" + bankvalue);
        		int xtemp = 327;
        		int ytemp = 59;
        		for (Integer i : bankhandcontent) {
        			ImageView card = new ImageView();
        			Image image = new Image("/images/" + i + ".png");
        			card.setImage(image);
        			card.setX(xtemp);
        			card.setY(ytemp);
        			card.setFitHeight(150);
        			card.setFitWidth(100);
        			anchor.getChildren().add(card);
        			xtemp += 100;
        			// Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
        			if (xtemp >= 1027) {
        				xtemp = 327;
        				ytemp += 50;
        			}
        		}
        		drawCard(arlist);
        	}else { 	// go to win screen scene
        			winScreen(bankvalue, playervalue,bankhandcontent,playerHandContent);
        	}
        } else if(bankvalue == 21){
        		// go to loss screen
        			lossScreen( playervalue,bankhandcontent,playerHandContent);
        			
        }else if(bankvalue >= 17){
        		compareScore(bankvalue,playervalue,bankhandcontent,playerHandContent);
        		
        }else{drawCard(arlist);}
        }});
    	   }
	
	
	public void aceReplacer(){
		if(bankhandcontent.contains(1)){
			bankhandcontent.set(bankhandcontent.indexOf(new Integer(1)),53);
		}else if(bankhandcontent.contains(2)){
			bankhandcontent.set(bankhandcontent.indexOf(new Integer(2)),54);
		}else if(bankhandcontent.contains(3)){
			bankhandcontent.set(bankhandcontent.indexOf(new Integer(3)),55);
		}else if(bankhandcontent.contains(4)){
			bankhandcontent.set(bankhandcontent.indexOf(new Integer(4)),56);
		}
	}
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
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
				
		// event when continuebutton is pushed
		continuebutton.setOnAction(new EventHandler<ActionEvent>(){
			
			 ImageView card;
			 Image image;
			  
			@Override
	        public void handle(ActionEvent event){
				
				// check before drawing a card if a win/lose condition has occured
				
				
		            if (bankvalue > 21){
		            	//check if players hand contains ace, replace with a different ID, subtract 10 from value, and rechecks win status
		            	if(bankhandcontent.contains(1)||bankhandcontent.contains(2)||bankhandcontent.contains(3)||bankhandcontent.contains(4)){
		            		aceReplacer();
		            		bankvalue -= 10;
		            		bankhandvalue.setText("" + bankvalue);
		            		int x = 327;
		            		int y = 639;
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
		            				drawCard(arlist);
		            			}
		            		}
		            		
		            	}else{ 	// go to lose screen scene
		            			winScreen(bankvalue, playervalue,bankhandcontent,playerHandContent);
		            			
		            	}
		            	} else if(bankvalue == 21){
		            		// go to win screen
		            			lossScreen( playervalue,bankhandcontent,playerHandContent);
		            			
		            	}else if(bankvalue >= 17){
		            		compareScore(bankvalue,playervalue,bankhandcontent,playerHandContent);
		            		
		            	}else{drawCard(arlist);}
		            
				
			}
		});
	}

	public Text getBankvalue() {
		return bankhandvalue;
	}

	public void setBankvalue(Text bankvalue) {
		this.bankhandvalue = bankvalue;
	}

	public Pane getToppane() {
		return toppane;
	}

	public void setToppane(Pane toppane) {
		this.toppane = toppane;
	}

	public StackPane getStackpane() {
		return stackpane;
	}

	public void setStackpane(StackPane stackpane) {
		this.stackpane = stackpane;
	}

	public Text getHandvalue() {
		return handvalue;
	}

	public void setHandvalue(String handvalueinput) {
		handvalue.setText(handvalueinput);
	}

	public void setplayHandContent(ArrayList<Integer> playerHandContentInput) {
		playerHandContent = playerHandContentInput;
	}

	public void setplayervalue(int playervalue){
		this.playervalue = playervalue;
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

	
	public void setBankhandvalue(Text bankhandvalue) {
		this.bankhandvalue = bankhandvalue;
	}

	public void setBankvalue(int bankvalue) {
		this.bankvalue = bankvalue;
	}

	public Text getBankhandvalue() {
		return bankhandvalue;
	}

	
	public HashMap<Integer, Integer> getCardValue() {
		return cardValue;
	}

	public void setCardValue(HashMap<Integer, Integer> cardValue) {
		this.cardValue = cardValue;
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

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public void afterPrpertiesSet() {

		int xplayer = 327;
		int yplayer = 639;
		int xbank = 327;
		int ybank = 59;
		ImageView card;
		Image image;

		// Places players card on the board from previous screen
		for (Integer i : playerHandContent) {
			card = new ImageView();
			image = new Image("/images/" + i + ".png");
			card.setImage(image);
			card.setX(xplayer);
			card.setY(yplayer);
			card.setFitHeight(150);
			card.setFitWidth(100);
			anchor.getChildren().add(card);
			xplayer += 100;
			// Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
			if (xplayer >= 1027) {
				xplayer = 327;
				yplayer += 50;
			}
		}
		for (Integer i : bankhandcontent) {
			card = new ImageView();
			image = new Image("/images/" + i + ".png");
			card.setImage(image);
			card.setX(xbank);
			card.setY(ybank);
			card.setFitHeight(150);
			card.setFitWidth(100);
			anchor.getChildren().add(card);
			bankvalue += cardValue.get(i);
			xbank += 100;
			// Checks if the x value has become too high to fit onto the screen, resets x and increase y to create another row
			if (xbank >= 1027) {
				xbank = 327;
				ybank += 50;
			}
		}
		
		bankhandvalue.setText("" + bankvalue);

		x=xbank;
		y=ybank;
	
}
}
