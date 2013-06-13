public class Card {
	
	private int suit;
	private int number;
	private int rank;
	private boolean shuffle = false;
	private static String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
	private static String[] faceCards = {"Ace", "Jack", "Queen", "King"};

	
	public Card(int suit, int number){
		if(suit < 0 || suit > 3 || number < 1 || number > 13){
			throw new IllegalArgumentException("Suit or number out of range");
		}
		this.suit = suit;
		this.number = number;
		if(number == 11 || number == 12 || number == 13){
			rank = 10;
		}
		else{
			rank = number;
		}
	}
	
	public void setAsShuffle(){
		this.shuffle = true;
	}
	
	public int getSuit(){
		return suit;
	}
	
	public int getRank(){
		return rank;
	}
	
	public boolean isAce(){
		return number == 1;
	}
	
	public boolean isShuffleCard(){
		return shuffle;
	}

	public boolean sameRankAs(Card card) {
		return this.rank == card.rank;
	}
	
	public String toString(){
		String rankStr = "" + number;
		if(number == 1 || number > 10){
			rankStr = faceCards[ Math.max(0, number - 10)];
		}
		return rankStr + " " + suits[suit];
	}

}
