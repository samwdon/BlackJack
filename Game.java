import java.util.ArrayList;

public class Game {

	private ArrayList<Player> players;
	private Shoe shoe;
	private Dealer dealer;
	private int numberShoes = 0;

	public Game(int numPlayers){
		shoe = new Shoe();
		dealer = new Dealer(this);
		players = new ArrayList<Player>();
		for(int i = 0; i < numPlayers; i++){
			players.add( new BasicPlayer(this) );
		}
		players.add(dealer);
	}

	public Card dealCard() {
		return shoe.dealCard();
	}

	/**
	 * plays through the simulation until the specified number of shoes have been used
	 */
	public void start(int numberShuffles){
		while(numberShoes < numberShuffles){
			if(!initialDeal()){
				for(Player p : players){
					p.takeTurn();
				}
				for(Player p : players){
					int dealerTotal = dealer.getFirstHand().getTotal();
					p.finishHand(dealerTotal);
				}
			}
			if(shoe.needToShuffle()){
				shoe = new Shoe();
				numberShoes++;
			}
		}
	}

	/**
	 * Initial deal of cards for the hand, returns true if dealer has blackjack or if all other players have blackjack
	 * Returning true means that no more cards need to be dealt
	 * @return
	 */
	public boolean initialDeal(){
		for(int i = 0; i < 2 ; i++){
			for(Player p : players){
				p.getFirstHand().addCard(dealCard());
			}
		}
		//dealer has blackjack
		if(dealer.getFirstHand().isBlackJack()){
			for(Player p : players){
				p.finishHand(21);
			}
			return true;
		}
		if(allPlayersHaveBlackjack()){
			for(Player p : players){
				p.finishHand(dealer.getFirstHand().getTotal());
			}
			return true;
		}
		
		//the hand is not over after the initial deal
		return false;
	}
	
	/**
	 * @return boolean - whether or not all players (excluding dealer) have blackjack
	 */
	public boolean allPlayersHaveBlackjack(){
		for(BasicPlayer p : getPlayersNonDealer()){
			if(!p.getFirstHand().isBlackJack()){
				return false;
			}
		}
		return true;
	}

	public Card getDealerFaceUpCard(){
		return dealer.getFaceUpCard();
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	/**
	 * @return ArrayList of all Players that are not the dealer
	 */
	public ArrayList<BasicPlayer> getPlayersNonDealer(){
		ArrayList<BasicPlayer> notDealers = new ArrayList<BasicPlayer>();
		for(Player p : players){
			if(p != dealer){
				notDealers.add((BasicPlayer)p);
			}
		}
		return notDealers;
	}

}
