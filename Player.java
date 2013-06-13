public interface Player {
	
	public void takeTurn();
	public void finishHand( int dealerTotal );
	public String toString();
	public Hand getFirstHand();

}
