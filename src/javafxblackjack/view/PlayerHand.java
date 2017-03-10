package javafxblackjack.view;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerHand extends BlackjackHand{
	private ArrayList<Integer> playerhandContent = new ArrayList<Integer>();
	private int handValue = 0;
	
	// method for checking the hand for win conditions
	public int winCheck(int handValue){
		return 1;
		}		
	
	public void playerContinue(){
		
		Scanner scan = new Scanner(System.in);
		if(scan.nextLine().equalsIgnoreCase("yes")){
			// start again
		}else if(scan.nextLine().equalsIgnoreCase("no")){
			// terminate program
		} else{
			System.out.println("You have to enter either \" yes \" or \"no \" ");
		}
	}

	// method to add cards to playerhandcontent
	public void addPlayerhandContent(int newcard){
		playerhandContent.add(newcard);
	}
	
	// method for removing the first ace from the hand.
	public void aceReplacer(){
		if(playerhandContent.contains(1)){
			playerhandContent.set(playerhandContent.indexOf(new Integer(1)),53);
		}else if(playerhandContent.contains(new Integer(2))){
			playerhandContent.set(playerhandContent.indexOf(new Integer(2)),54);
		}else if(playerhandContent.contains(3)){
			playerhandContent.set(playerhandContent.indexOf(new Integer(3)),55);
		}else if(playerhandContent.contains(4)){
			playerhandContent.set(playerhandContent.indexOf(new Integer(4)),56);
		}
	}
	
	//getters and setters
	public int getHandValue() {
		return handValue;
	}

	public void setHandValue(int handValue) {
		this.handValue = handValue;
	}

	public ArrayList<Integer> getPlayerhandContent() {
		return playerhandContent;
	}

	public void setPlayerhandContent(ArrayList<Integer> playerhandContent) {
		this.playerhandContent = playerhandContent;
	}

	
	
	
	
	

	
	
	
}
