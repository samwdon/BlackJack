public class HouseRules {
	
	 	private static int numberOfDecks = 6;
	    private static boolean doubleAfterSplit = false;
	    private static int minimumBet = 10;
		private static int maxNumberOfHands = 4;
		private static double blackjackPayout = 1.5;
	    
	    public HouseRules(){}

		public static int getNumberOfDecks() {
			return numberOfDecks;
		}

		public static boolean canDoubleAfterSplit() {
			return doubleAfterSplit;
		}

		public static int getMinimumBet() {
			return minimumBet;
		}

		public static int getMaxNumberOfHands() {
			return maxNumberOfHands;
		}

		public static double getBlackjackPayout() {
			return blackjackPayout;
		}
	   

}
