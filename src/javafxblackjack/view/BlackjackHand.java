package javafxblackjack.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BlackjackHand {

private ArrayList<String> handContent = new ArrayList<String>();
private int handValue = 0;
		
	// method for drawing a card and adding it to the hand
public void drawCard(ArrayList<String> deck){

Random r = new Random();	
String n = deck.get(r.nextInt(deck.size()));
deck.remove(n);
handContent.add(n);
}	
	
// method for calculating the value of the hand
public void calculateValue(HashMap<String,Integer> cardValue){
for(String i: handContent){
	
	String testee = i.substring(0,i.indexOf("of")).trim();
	handValue += cardValue.get(testee);
}	
}





// getters and setters

public ArrayList<String> getHandContent() {
	return handContent;
}

public void setHandContent(ArrayList<String> handContent) {
	this.handContent = handContent;
}

public int getHandValue() {
	return handValue;
}

public void setHandValue(int handValue) {
	handValue = handValue;
}






}
