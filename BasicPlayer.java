import java.util.ArrayList;

public class BasicPlayer implements Player{

	private ArrayList<Hand> hands;
	private double cash;
	private double maxCash = 0;	//peak winnings
	private Strategy strategy;
	protected Game game;
	private double numWinningHands = 0;
	private double numLosingHands = 0;
	private double numPushHands = 0;
	private double totalHands = 0;
	private static int currentId = 0;
	private int id;
	
	public BasicPlayer(Game game){
		id = currentId++;
		hands = new ArrayList<Hand>();
		hands.add( new Hand() );
		strategy = new Strategy(this);
		this.game = game;
	}
	
	/**
	 * Uses Strategy class to determine the action to take based on basic strategy
	 */
	public void takeTurn(){
		int dealerShowing = game.getDealerFaceUpCard().getRank();
		for(int i = 0; i < hands.size(); i++){
			Hand hand = hands.get(i);
			Decision decision = strategy.getBestMove(dealerShowing, hand);
			while(hand.getTotal() <= 21 && decision != Decision.Stand){
				executeDecision(hand, decision);
				decision = strategy.getBestMove(dealerShowing, hand);
			}
		}
	}
	
	/**
	 * Acts upon the decision found in the Strategy class
	 */
	private void executeDecision(Hand hand, Decision decision){

		switch(decision){
		case Hit:	
			hand.addCard( game.dealCard() );
			break;
		case DoubleDown:	
			hand.addCard( game.dealCard() );
			hand.doubleBet();
			break;
		case Split:	
			Hand split = new Hand(hand.getCurrentBet());
			split.setCameFromSplit(true);
			split.addCard( hand.removeCard(1) );
			hand.addCard(game.dealCard());
			hand.setCameFromSplit(true);
			split.addCard(game.dealCard());
			hands.add(split);
			break;
		default:	//do nothing on a stand
			break;
		}
	}
	
	/**
	 * Determines player's pay-out and clears hands
	 */
	public void finishHand( int dealerTotal ){
		for(Hand hand : hands){
			String result = hand.getTotal() + " against " + dealerTotal;
			
			//win
			if( ( hand.getTotal() <= 21 && hand.getTotal() > dealerTotal) || dealerTotal > 21 ){
				double winnings = hand.getCurrentBet();
				//blackjack
				if(hand.isBlackJack()){
					winnings = HouseRules.getBlackjackPayout() * hand.getCurrentBet();
				}
				cash += winnings;
				numWinningHands++;
				result += " win +$" + winnings;
			}
			//loss
			else if(hand.getTotal() > 21 || dealerTotal > hand.getTotal()){
				cash -= hand.getCurrentBet();
				numLosingHands++;
				result += " lose -$" + hand.getCurrentBet();
				
			}
			//push
			else{
				numPushHands++;
				result += " push";
			}
			totalHands++;
			System.out.println(result);
		}
		maxCash = Math.max(cash, maxCash);
		hands.clear();
		hands.add( new Hand() );
	}
	
	/**
	 * @return true if all of the players hands are bust, false if at least one is not
	 */
	public boolean allHandsBust(){
		for(Hand h : hands){
			if(h.getTotal() <= 21){
				return false;
			}
		}
		return true;
	}

	public Hand getFirstHand(){
		return hands.get(0);
	}
	
	public ArrayList<Hand> getHands(){
		return hands;
	}

	public int numberOfHands(){
		return hands.size();
	}
	
	public int getHandId(Hand hand){
		return hands.indexOf(hand);
	}
	
	public double getMaxCash(){
		return maxCash;
	}

	public int id(){
		return id;
	}

	public double getCash() {
		return cash;
	}
	
	public double getNumWinningHands() {
		return numWinningHands;
	}

	public double getNumLosingHands() {
		return numLosingHands;
	}

	public double getNumPushHands() {
		return numPushHands;
	}

	public double getTotalHands() {
		return totalHands;
	}

	public double winPercentage() {
		return numWinningHands / totalHands;
	}
	
	public double pushPercentage(){
		return numPushHands / totalHands;
	}
	
	public double lossPercentage(){
		return numLosingHands / totalHands;
	}
	
	public String toString(){
		
		String handsStr = "";
		for(Hand h : hands){
			handsStr += h.toString() + " ";
		}
		if(handsStr.trim().equals("")){
			handsStr = "nothing";
		}
		return "Player " + id + " has " + handsStr;
	}
	
	

}
