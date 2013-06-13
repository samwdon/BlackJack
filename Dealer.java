public class Dealer implements Player{
	
	private Hand hand;
	private Game game;

	public Dealer(Game game) {
		this.game = game;
		hand = new Hand();
	}
	
	/**
	 * Dealer hits on anything lower than 17 (also hits on soft 18)
	 */
	public void takeTurn(){
		int total = hand.getTotal();
		boolean allPlayersBust = true;
		for(BasicPlayer p : game.getPlayersNonDealer()){
			if(!p.allHandsBust()){
				allPlayersBust = false;
				break;
			}
		}
		//Don't take turn if all players have already busted, default win
		if(!allPlayersBust){
			while(total < 17 || (total < 18 && hand.isSoft())){
				hand.addCard( game.dealCard() );
				total = hand.getTotal();
			}
		}
	}
	
	public Card getFaceUpCard(){
		return hand.getCard(0);
	}
	
	/**
	 * Resets the dealer's hand
	 */
	public void finishHand( int dealerTotal ){
		hand = new Hand();
	}
	
	public Hand getFirstHand(){
		return hand;
	}
	
	public String toString(){
		return "Dealer has " + hand.toString();
	}

}
