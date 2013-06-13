import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Shoe {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private static int numberOfDecks;
	private boolean shuffleCardHit = false;	//whether or not the shuffle card has been reached
	
	public Shoe(){
		numberOfDecks = HouseRules.getNumberOfDecks();
		for(int i = 0; i < numberOfDecks; i++){
			cards.addAll( genDeck() );
		}
		Collections.shuffle(cards);
		
		//Set the shuffle card between 3/4th to 7/8th through the deck
		Random rand = new Random();
		double randDouble = rand.nextDouble()/4.0 + .5;	//min of .5, max of .75
		int setShuffle = (int) (numberOfDecks/2.0 * 52 * randDouble + (numberOfDecks/2.0 * 52) );
		cards.get(setShuffle).setAsShuffle();
	}

	/**
	 * Creates an ordered deck of 52 cards
	 * @return ArrayList of Card Objects
	 */
	private ArrayList<Card> genDeck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		for(int suit = 0; suit < 4; suit++){
			for(int number = 1; number <= 13; number++){
				deck.add( new Card(suit, number) );
			}
		}
		return deck;
	}
	
	/**
	 * @return Returns the next card in the shoe while checking for the shuffle point
	 */
	public Card dealCard(){
		Card next = cards.remove(0);
		if(next.isShuffleCard()){
			shuffleCardHit = true;
		}
		return next;
	}
	
	public boolean needToShuffle(){
		return shuffleCardHit;
	}
	
	public int getNumberOfCards(){
		return cards.size();
	}
	
}
