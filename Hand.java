import java.util.ArrayList;

public class Hand {
	
	private boolean soft = false;
	private boolean cameFromSplit = false;
	private ArrayList<Card> hand;
	private int currentBet;
	private int total = 0;
	private boolean isBlackJack = false;
	
	public Hand(){
		hand = new ArrayList<Card>();
		currentBet = HouseRules.getMinimumBet();
	}

	public Hand(int bet){
		hand = new ArrayList<Card>();
		currentBet = bet;
	}
	
	/**
	 * @return int representing the total of the hand, will return largest possible soft hand if there are any aces
	 */
	private int calculateTotal(){
		int total = 0;
		int numberOfAces = 0;
		soft = false;
		for(Card c : hand){
			if(c.getRank() == 1){
				numberOfAces++;
				total += 11;
			}
			else{
				total += c.getRank();
			}
		}
		int i = 0;
        while (total > 21 && i < numberOfAces) {
            total -= 10;
            i++;
        }
        //at least one ace is counted as 11
        if(i < numberOfAces){
        	soft = true;
        }
        if(total == 21 && hand.size() == 2){
        	isBlackJack = true;
        }
		return total;
	}
	
	/**
	 * @return returns true if the player can double down on the hand based on house rules
	 */
	public boolean canDouble(){
		if(hand.size() == 2){
			if ((cameFromSplit && HouseRules.canDoubleAfterSplit()) || !cameFromSplit) {
                return true;
            }
		}
		return false;	
	}
	
	public void addCard(Card c){
		hand.add(c);
		total = calculateTotal();
	}
	
	public boolean isSoft(){
		return soft;
	}
	
	public boolean isBlackJack(){
		return isBlackJack;
	}
	
	public boolean isPair(){
		return hand.size() == 2 && getCard(0).sameRankAs( getCard(1) );
	}
	
	public void doubleBet(){
		currentBet *= 2;
	}
	
	public int getTotal(){
		return total;
	}
	
	public Card getCard(int index){
		if(index >= hand.size()){
			throw new IllegalArgumentException("Card index out of range");
		}
		return hand.get(index);
	}
	
	public Card removeCard(int index){
		return hand.remove(index);
	}
	
	public int getCurrentBet() {
		return currentBet;
	}
	
	public void setCameFromSplit(boolean cameFromSplit) {
		this.cameFromSplit = cameFromSplit;
	}

	public boolean cameFromSplit() {
		return cameFromSplit;
	}

	/**
	 * @return returns true if the hand is a pair of aces
	 */
	public boolean isAces() {
		return hand.size() == 2 && hand.get(0).isAce() && hand.get(1).isAce();
	}
	
	public String toString(){
		String output = "[";
		int count = 0;
		for(Card c : hand){
			output += c.toString();
			if(count != hand.size()-1){
				output += ", ";
			}
			count++;
		}
		output += " (total: ";
		if(soft){
			output += "soft ";
		}else{
			output += "hard ";
		}
		output +=  total + ") (bet: " + currentBet + ")]";
		return output;
	}

}
