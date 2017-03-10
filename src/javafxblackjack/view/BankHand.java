package javafxblackjack.view;

import java.util.ArrayList;

public class BankHand extends BlackjackHand {

private ArrayList<Integer> bankhandContent = new ArrayList<Integer>();
private int bankhandValue = 0;
private int faceDownCard;
	


// Method for checking if the bank hand wins/loses and the logic for drawing more cards or not	
public int winCheck(int handValue){
		showHand();
		if(handValue == 21){
			return 0;
		}else if(handValue > 21){
			for(String i: getHandContent()){
				if(i.contains("Ace")){
					int acePlace = getHandContent().indexOf(i);
					ArrayList <String> aceReplaced = getHandContent();
					aceReplaced.set(acePlace, "1 of Spades");
					setHandContent(aceReplaced);
					return 3;
				}
			}
			return 1;
		}else{
			return 2;
		}
		}	

public int bankDraw(){
	if(getHandValue() >= 19){
		return 0;
	} else {
		return 1;
	} 
}

// method for displaying hand contents and values
public void showHand(){
	System.out.println("The bank's hand contains " + getHandContent());
	System.out.println("The value of the bank's hand is " + getHandValue());
}

// method for adding cards to bankhandContent
public void addBankhandContent(int newcard){
	bankhandContent.add(newcard);
}

public void aceReplacer(){
	if(bankhandContent.contains(1)){
		bankhandContent.set(bankhandContent.indexOf(new Integer(1)),53);
	}else if(bankhandContent.contains(new Integer(2))){
		bankhandContent.set(bankhandContent.indexOf(new Integer(2)),53);
	}else if(bankhandContent.contains(3)){
		bankhandContent.set(bankhandContent.indexOf(new Integer(3)),53);
	}else if(bankhandContent.contains(4)){
		bankhandContent.set(bankhandContent.indexOf(new Integer(4)),53);
	}
}

//getters and setters
public ArrayList<Integer> getBankhandContent() {
	return bankhandContent;
}

public void setBankhandContent(ArrayList<Integer> bankhandContent) {
	this.bankhandContent = bankhandContent;
}

public int getBankhandValue() {
	return bankhandValue;
}

public void setBankhandValue(int bankhandValue) {
	this.bankhandValue = bankhandValue;
}
public int getFaceDownCard() {
	return faceDownCard;
}

public void setFaceDownCard(int faceDownCard) {
	this.faceDownCard = faceDownCard;
}
}


