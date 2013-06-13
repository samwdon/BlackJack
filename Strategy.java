public class Strategy {
	
	private Decision[][] hardStrategies = new Decision[10][10];
	private Decision[][] softStrategies = new Decision[9][10];
	private Decision[][] doubleStrategies = new Decision[10][10];
	
	private BasicPlayer player;

	public Strategy(BasicPlayer player){
		populateHardStrategies();
		populateSoftStrategies();
		populateDoubleStrategies();
		this.player = player;
	}

	public Decision getBestMove(int dealerShowing, Hand hand){
		Decision decision = null;
		
		if(hand.getTotal() > 21){
			return Decision.Stand;	//return stand for a bust so it isn't counted in stats
		}
		
		//rare case when hand is a pair of aces with max number of hands
		if(hand.isAces() && hand.cameFromSplit() && player.numberOfHands() == HouseRules.getMaxNumberOfHands()){
			return Decision.Stand;	//cannot do anything on hand that was from split aces
		}
		
		if( hand.isPair() && player.numberOfHands() < HouseRules.getMaxNumberOfHands()){
			int playerArrayIndex = hand.getCard(0).getRank() - 1;
			decision = doubleStrategies[playerArrayIndex][dealerShowing - 1];
		} else if( hand.isSoft() ){
			int playerArrayIndex = Math.max(0, hand.getTotal() - 13); // will be -1 if there are pair of aces but maxNumberHands reached
			decision = softStrategies[playerArrayIndex][dealerShowing - 1];
		} else{
			int playerArrayIndex = Math.max(0, hand.getTotal() - 8);	//anything 5-8 should hit
			if(hand.getTotal() >= 17){
				playerArrayIndex = 9;	//anything 17 or higher should hit
			}
			decision = hardStrategies[playerArrayIndex][dealerShowing - 1];
		}
		
		if(decision == Decision.Split_Or_Hit){
			if(HouseRules.canDoubleAfterSplit()){
				decision = Decision.Split;
			}else{
				decision = Decision.Hit;
			}
		}
		
		if(decision == Decision.DoubleDown && !hand.canDouble()){
				decision = Decision.Hit;
		}
		
		return decision;
	}

	public int getBestMove(int dealerShowing, Hand playerHand, int count){
		
		return 0;
	}
	
	public int getBestMove(int dealerShowing, Hand playerHand, int count, double depth){
		
		return 0;
	}
	
	private void populateHardStrategies() {
		
		//Hard 8
		hardStrategies[0][0] = Decision.Hit;	//Dealer Ace
		hardStrategies[0][1] = Decision.Hit;	//Dealer 2
		hardStrategies[0][2] = Decision.Hit;	//Dealer 3
		hardStrategies[0][3] = Decision.Hit;	//Dealer 4
		hardStrategies[0][4] = Decision.Hit;	//Dealer 5
		hardStrategies[0][5] = Decision.Hit;	//Dealer 6	
		hardStrategies[0][6] = Decision.Hit;	//Dealer 7
		hardStrategies[0][7] = Decision.Hit;	//Dealer 8
		hardStrategies[0][8] = Decision.Hit;	//Dealer 9
		hardStrategies[0][9] = Decision.Hit;	//Dealer 10

		//Hard 9
		hardStrategies[1][0] = Decision.Hit;		//Dealer Ace
		hardStrategies[1][1] = Decision.Hit;		//Dealer 2
		hardStrategies[1][2] = Decision.DoubleDown;	//Dealer 3
		hardStrategies[1][3] = Decision.DoubleDown;	//Dealer 4
		hardStrategies[1][4] = Decision.DoubleDown;	//Dealer 5
		hardStrategies[1][5] = Decision.DoubleDown;	//Dealer 6	
		hardStrategies[1][6] = Decision.Hit;		//Dealer 7
		hardStrategies[1][7] = Decision.Hit;		//Dealer 8
		hardStrategies[1][8] = Decision.Hit;		//Dealer 9
		hardStrategies[1][9] = Decision.Hit;		//Dealer 10

		//Hard 10
		hardStrategies[2][0] = Decision.Hit;		//Dealer Ace
		hardStrategies[2][1] = Decision.DoubleDown;	//Dealer 2
		hardStrategies[2][2] = Decision.DoubleDown;	//Dealer 3
		hardStrategies[2][3] = Decision.DoubleDown;	//Dealer 4
		hardStrategies[2][4] = Decision.DoubleDown;	//Dealer 5
		hardStrategies[2][5] = Decision.DoubleDown;	//Dealer 6	
		hardStrategies[2][6] = Decision.DoubleDown;	//Dealer 7
		hardStrategies[2][7] = Decision.DoubleDown;	//Dealer 8
		hardStrategies[2][8] = Decision.DoubleDown;	//Dealer 9
		hardStrategies[2][9] = Decision.Hit;		//Dealer 10

		//Hard 11
		hardStrategies[3][0] = Decision.Hit;		//Dealer Ace
		hardStrategies[3][1] = Decision.DoubleDown;	//Dealer 2
		hardStrategies[3][2] = Decision.DoubleDown;	//Dealer 3
		hardStrategies[3][3] = Decision.DoubleDown;	//Dealer 4
		hardStrategies[3][4] = Decision.DoubleDown;	//Dealer 5
		hardStrategies[3][5] = Decision.DoubleDown;	//Dealer 6	
		hardStrategies[3][6] = Decision.DoubleDown;	//Dealer 7
		hardStrategies[3][7] = Decision.DoubleDown;	//Dealer 8
		hardStrategies[3][8] = Decision.DoubleDown;	//Dealer 9
		hardStrategies[3][9] = Decision.DoubleDown;	//Dealer 10

		//Hard 12
		hardStrategies[4][0] = Decision.Hit;	//Dealer Ace
		hardStrategies[4][1] = Decision.Hit;	//Dealer 2
		hardStrategies[4][2] = Decision.Hit;	//Dealer 3
		hardStrategies[4][3] = Decision.Stand;	//Dealer 4
		hardStrategies[4][4] = Decision.Stand;	//Dealer 5
		hardStrategies[4][5] = Decision.Stand;	//Dealer 6	
		hardStrategies[4][6] = Decision.Hit;	//Dealer 7
		hardStrategies[4][7] = Decision.Hit;	//Dealer 8
		hardStrategies[4][8] = Decision.Hit;	//Dealer 9
		hardStrategies[4][9] = Decision.Hit;	//Dealer 10

		//Hard 13
		hardStrategies[5][0] = Decision.Hit;	//Dealer Ace
		hardStrategies[5][1] = Decision.Stand;	//Dealer 2
		hardStrategies[5][2] = Decision.Stand;	//Dealer 3
		hardStrategies[5][3] = Decision.Stand;	//Dealer 4
		hardStrategies[5][4] = Decision.Stand;	//Dealer 5
		hardStrategies[5][5] = Decision.Stand;	//Dealer 6	
		hardStrategies[5][6] = Decision.Hit;	//Dealer 7
		hardStrategies[5][7] = Decision.Hit;	//Dealer 8
		hardStrategies[5][8] = Decision.Hit;	//Dealer 9
		hardStrategies[5][9] = Decision.Hit;	//Dealer 10

		//Hard 14
		hardStrategies[6][0] = Decision.Hit;	//Dealer Ace
		hardStrategies[6][1] = Decision.Stand;	//Dealer 2
		hardStrategies[6][2] = Decision.Stand;	//Dealer 3
		hardStrategies[6][3] = Decision.Stand;	//Dealer 4
		hardStrategies[6][4] = Decision.Stand;	//Dealer 5
		hardStrategies[6][5] = Decision.Stand;	//Dealer 6	
		hardStrategies[6][6] = Decision.Hit;	//Dealer 7
		hardStrategies[6][7] = Decision.Hit;	//Dealer 8
		hardStrategies[6][8] = Decision.Hit;	//Dealer 9
		hardStrategies[6][9] = Decision.Hit;	//Dealer 10

		//Hard 15
		hardStrategies[7][0] = Decision.Hit;	//Dealer Ace
		hardStrategies[7][1] = Decision.Stand;	//Dealer 2
		hardStrategies[7][2] = Decision.Stand;	//Dealer 3
		hardStrategies[7][3] = Decision.Stand;	//Dealer 4
		hardStrategies[7][4] = Decision.Stand;	//Dealer 5
		hardStrategies[7][5] = Decision.Stand;	//Dealer 6	
		hardStrategies[7][6] = Decision.Hit;	//Dealer 7
		hardStrategies[7][7] = Decision.Hit;	//Dealer 8
		hardStrategies[7][8] = Decision.Hit;	//Dealer 9
		hardStrategies[7][9] = Decision.Hit;	//Dealer 10

		//Hard 16
		hardStrategies[8][0] = Decision.Hit;	//Dealer Ace
		hardStrategies[8][1] = Decision.Stand;	//Dealer 2
		hardStrategies[8][2] = Decision.Stand;	//Dealer 3
		hardStrategies[8][3] = Decision.Stand;	//Dealer 4
		hardStrategies[8][4] = Decision.Stand;	//Dealer 5
		hardStrategies[8][5] = Decision.Stand;	//Dealer 6	
		hardStrategies[8][6] = Decision.Hit;	//Dealer 7
		hardStrategies[8][7] = Decision.Hit;	//Dealer 8
		hardStrategies[8][8] = Decision.Hit;	//Dealer 9
		hardStrategies[8][9] = Decision.Hit;	//Dealer 10

		//Hard 17
		hardStrategies[9][0] = Decision.Stand;	//Dealer Ace
		hardStrategies[9][1] = Decision.Stand;	//Dealer 2
		hardStrategies[9][2] = Decision.Stand;	//Dealer 3
		hardStrategies[9][3] = Decision.Stand;	//Dealer 4
		hardStrategies[9][4] = Decision.Stand;	//Dealer 5
		hardStrategies[9][5] = Decision.Stand;	//Dealer 6	
		hardStrategies[9][6] = Decision.Stand;	//Dealer 7
		hardStrategies[9][7] = Decision.Stand;	//Dealer 8
		hardStrategies[9][8] = Decision.Stand;	//Dealer 9
		hardStrategies[9][9] = Decision.Stand;	//Dealer 10
		
	}
	
	private void populateSoftStrategies(){

		//Soft 13
		softStrategies[0][0] = Decision.Hit;		//Dealer Ace
		softStrategies[0][1] = Decision.Hit;		//Dealer 2
		softStrategies[0][2] = Decision.Hit;		//Dealer 3
		softStrategies[0][3] = Decision.Hit;		//Dealer 4
		softStrategies[0][4] = Decision.DoubleDown;	//Dealer 5
		softStrategies[0][5] = Decision.DoubleDown;	//Dealer 6	
		softStrategies[0][6] = Decision.Hit;		//Dealer 7
		softStrategies[0][7] = Decision.Hit;		//Dealer 8
		softStrategies[0][8] = Decision.Hit;		//Dealer 9
		softStrategies[0][9] = Decision.Hit;		//Dealer 10

		//Soft 14
		softStrategies[1][0] = Decision.Hit;		//Dealer Ace
		softStrategies[1][1] = Decision.Hit;		//Dealer 2
		softStrategies[1][2] = Decision.Hit;		//Dealer 3
		softStrategies[1][3] = Decision.Hit;		//Dealer 4
		softStrategies[1][4] = Decision.DoubleDown;	//Dealer 5
		softStrategies[1][5] = Decision.DoubleDown;	//Dealer 6	
		softStrategies[1][6] = Decision.Hit;		//Dealer 7
		softStrategies[1][7] = Decision.Hit;		//Dealer 8
		softStrategies[1][8] = Decision.Hit;		//Dealer 9
		softStrategies[1][9] = Decision.Hit;		//Dealer 10

		//Soft 15
		softStrategies[2][0] = Decision.Hit;		//Dealer Ace
		softStrategies[2][1] = Decision.Hit;		//Dealer 2
		softStrategies[2][2] = Decision.Hit;		//Dealer 3
		softStrategies[2][3] = Decision.DoubleDown;	//Dealer 4
		softStrategies[2][4] = Decision.DoubleDown;	//Dealer 5
		softStrategies[2][5] = Decision.DoubleDown;	//Dealer 6	
		softStrategies[2][6] = Decision.Hit;		//Dealer 7
		softStrategies[2][7] = Decision.Hit;		//Dealer 8
		softStrategies[2][8] = Decision.Hit;		//Dealer 9
		softStrategies[2][9] = Decision.Hit;		//Dealer 10

		//Soft 16
		softStrategies[3][0] = Decision.Hit;		//Dealer Ace
		softStrategies[3][1] = Decision.Hit;		//Dealer 2
		softStrategies[3][2] = Decision.Hit;		//Dealer 3
		softStrategies[3][3] = Decision.DoubleDown;	//Dealer 4
		softStrategies[3][4] = Decision.DoubleDown;	//Dealer 5
		softStrategies[3][5] = Decision.DoubleDown;	//Dealer 6	
		softStrategies[3][6] = Decision.Hit;		//Dealer 7
		softStrategies[3][7] = Decision.Hit;		//Dealer 8
		softStrategies[3][8] = Decision.Hit;		//Dealer 9
		softStrategies[3][9] = Decision.Hit;		//Dealer 10

		//Soft 17
		softStrategies[4][0] = Decision.Hit;		//Dealer Ace
		softStrategies[4][1] = Decision.Hit;		//Dealer 2
		softStrategies[4][2] = Decision.DoubleDown;	//Dealer 3
		softStrategies[4][3] = Decision.DoubleDown;	//Dealer 4
		softStrategies[4][4] = Decision.DoubleDown;	//Dealer 5
		softStrategies[4][5] = Decision.DoubleDown;	//Dealer 6	
		softStrategies[4][6] = Decision.Hit;		//Dealer 7
		softStrategies[4][7] = Decision.Hit;		//Dealer 8
		softStrategies[4][8] = Decision.Hit;		//Dealer 9
		softStrategies[4][9] = Decision.Hit;		//Dealer 10

		//Soft 18
		softStrategies[5][0] = Decision.Hit;		//Dealer Ace
		softStrategies[5][1] = Decision.Stand;		//Dealer 2
		softStrategies[5][2] = Decision.DoubleDown;	//Dealer 3
		softStrategies[5][3] = Decision.DoubleDown;	//Dealer 4
		softStrategies[5][4] = Decision.DoubleDown;	//Dealer 5
		softStrategies[5][5] = Decision.DoubleDown;	//Dealer 6	
		softStrategies[5][6] = Decision.Stand;		//Dealer 7
		softStrategies[5][7] = Decision.Stand;		//Dealer 8
		softStrategies[5][8] = Decision.Hit;		//Dealer 9
		softStrategies[5][9] = Decision.Hit;		//Dealer 10

		//Soft 19
		softStrategies[6][0] = Decision.Stand;	//Dealer Ace
		softStrategies[6][1] = Decision.Stand;	//Dealer 2
		softStrategies[6][2] = Decision.Stand;	//Dealer 3
		softStrategies[6][3] = Decision.Stand;	//Dealer 4
		softStrategies[6][4] = Decision.Stand;	//Dealer 5
		softStrategies[6][5] = Decision.Stand;	//Dealer 6	
		softStrategies[6][6] = Decision.Stand;	//Dealer 7
		softStrategies[6][7] = Decision.Stand;	//Dealer 8
		softStrategies[6][8] = Decision.Stand;	//Dealer 9
		softStrategies[6][9] = Decision.Stand;	//Dealer 10

		//Soft 20
		softStrategies[7][0] = Decision.Stand;	//Dealer Ace
		softStrategies[7][1] = Decision.Stand;	//Dealer 2
		softStrategies[7][2] = Decision.Stand;	//Dealer 3
		softStrategies[7][3] = Decision.Stand;	//Dealer 4
		softStrategies[7][4] = Decision.Stand;	//Dealer 5
		softStrategies[7][5] = Decision.Stand;	//Dealer 6	
		softStrategies[7][6] = Decision.Stand;	//Dealer 7
		softStrategies[7][7] = Decision.Stand;	//Dealer 8
		softStrategies[7][8] = Decision.Stand;	//Dealer 9
		softStrategies[7][9] = Decision.Stand;	//Dealer 10
		
		//Soft 21 (Blackjack)
		softStrategies[8][0] = Decision.Stand;	//Dealer Ace
		softStrategies[8][1] = Decision.Stand;	//Dealer 2
		softStrategies[8][2] = Decision.Stand;	//Dealer 3
		softStrategies[8][3] = Decision.Stand;	//Dealer 4
		softStrategies[8][4] = Decision.Stand;	//Dealer 5
		softStrategies[8][5] = Decision.Stand;	//Dealer 6	
		softStrategies[8][6] = Decision.Stand;	//Dealer 7
		softStrategies[8][7] = Decision.Stand;	//Dealer 8
		softStrategies[8][8] = Decision.Stand;	//Dealer 9
		softStrategies[8][9] = Decision.Stand;	//Dealer 10

	}
	
	private void populateDoubleStrategies(){
		
		//Double Aces
		doubleStrategies[0][0] = Decision.Split;	//Dealer Ace
		doubleStrategies[0][1] = Decision.Split;	//Dealer 2
		doubleStrategies[0][2] = Decision.Split;	//Dealer 3
		doubleStrategies[0][3] = Decision.Split;	//Dealer 4
		doubleStrategies[0][4] = Decision.Split;	//Dealer 5
		doubleStrategies[0][5] = Decision.Split;	//Dealer 6	
		doubleStrategies[0][6] = Decision.Split;	//Dealer 7
		doubleStrategies[0][7] = Decision.Split;	//Dealer 8
		doubleStrategies[0][8] = Decision.Split;	//Dealer 9
		doubleStrategies[0][9] = Decision.Split;	//Dealer 10

		//Double 2s
		doubleStrategies[1][0] = Decision.Hit;			//Dealer Ace
		doubleStrategies[1][1] = Decision.Split_Or_Hit;	//Dealer 2
		doubleStrategies[1][2] = Decision.Split_Or_Hit;	//Dealer 3
		doubleStrategies[1][3] = Decision.Split;		//Dealer 4
		doubleStrategies[1][4] = Decision.Split;		//Dealer 5
		doubleStrategies[1][5] = Decision.Split;		//Dealer 6	
		doubleStrategies[1][6] = Decision.Split;		//Dealer 7
		doubleStrategies[1][7] = Decision.Hit;			//Dealer 8
		doubleStrategies[1][8] = Decision.Hit;			//Dealer 9
		doubleStrategies[1][9] = Decision.Hit;			//Dealer 10

		//Double 3s
		doubleStrategies[2][0] = Decision.Hit;			//Dealer Ace
		doubleStrategies[2][1] = Decision.Split_Or_Hit;	//Dealer 2
		doubleStrategies[2][2] = Decision.Split_Or_Hit;	//Dealer 3
		doubleStrategies[2][3] = Decision.Split;		//Dealer 4
		doubleStrategies[2][4] = Decision.Split;		//Dealer 5
		doubleStrategies[2][5] = Decision.Split;		//Dealer 6	
		doubleStrategies[2][6] = Decision.Split;		//Dealer 7
		doubleStrategies[2][7] = Decision.Hit;			//Dealer 8
		doubleStrategies[2][8] = Decision.Hit;			//Dealer 9
		doubleStrategies[2][9] = Decision.Hit;			//Dealer 10

		//Double 4s
		doubleStrategies[3][0] = Decision.Hit;			//Dealer Ace
		doubleStrategies[3][1] = Decision.Hit;			//Dealer 2
		doubleStrategies[3][2] = Decision.Hit;			//Dealer 3
		doubleStrategies[3][3] = Decision.Hit;			//Dealer 4
		doubleStrategies[3][4] = Decision.Split_Or_Hit;	//Dealer 5
		doubleStrategies[3][5] = Decision.Split_Or_Hit;	//Dealer 6	
		doubleStrategies[3][6] = Decision.Hit;			//Dealer 7
		doubleStrategies[3][7] = Decision.Hit;			//Dealer 8
		doubleStrategies[3][8] = Decision.Hit;			//Dealer 9
		doubleStrategies[3][9] = Decision.Hit;			//Dealer 10

		//Double 5s
		doubleStrategies[4][0] = Decision.Hit;			//Dealer Ace
		doubleStrategies[4][1] = Decision.DoubleDown;	//Dealer 2
		doubleStrategies[4][2] = Decision.DoubleDown;	//Dealer 3
		doubleStrategies[4][3] = Decision.DoubleDown;	//Dealer 4
		doubleStrategies[4][4] = Decision.DoubleDown;	//Dealer 5
		doubleStrategies[4][5] = Decision.DoubleDown;	//Dealer 6	
		doubleStrategies[4][6] = Decision.DoubleDown;	//Dealer 7
		doubleStrategies[4][7] = Decision.DoubleDown;	//Dealer 8
		doubleStrategies[4][8] = Decision.DoubleDown;	//Dealer 9
		doubleStrategies[4][9] = Decision.Hit;			//Dealer 10

		//Double 6s
		doubleStrategies[5][0] = Decision.Hit;			//Dealer Ace
		doubleStrategies[5][1] = Decision.Split_Or_Hit;	//Dealer 2
		doubleStrategies[5][2] = Decision.Split;		//Dealer 3
		doubleStrategies[5][3] = Decision.Split;		//Dealer 4
		doubleStrategies[5][4] = Decision.Split;		//Dealer 5
		doubleStrategies[5][5] = Decision.Split;		//Dealer 6	
		doubleStrategies[5][6] = Decision.Hit;			//Dealer 7
		doubleStrategies[5][7] = Decision.Hit;			//Dealer 8
		doubleStrategies[5][8] = Decision.Hit;			//Dealer 9
		doubleStrategies[5][9] = Decision.Hit;			//Dealer 10

		//Double 7s
		doubleStrategies[6][0] = Decision.Hit;		//Dealer Ace
		doubleStrategies[6][1] = Decision.Split;	//Dealer 2
		doubleStrategies[6][2] = Decision.Split;	//Dealer 3
		doubleStrategies[6][3] = Decision.Split;	//Dealer 4
		doubleStrategies[6][4] = Decision.Split;	//Dealer 5
		doubleStrategies[6][5] = Decision.Split;	//Dealer 6	
		doubleStrategies[6][6] = Decision.Split;	//Dealer 7
		doubleStrategies[6][7] = Decision.Hit;		//Dealer 8
		doubleStrategies[6][8] = Decision.Hit;		//Dealer 9
		doubleStrategies[6][9] = Decision.Hit;		//Dealer 10

		//Double 8s
		doubleStrategies[7][0] = Decision.Split;	//Dealer Ace
		doubleStrategies[7][1] = Decision.Split;	//Dealer 2
		doubleStrategies[7][2] = Decision.Split;	//Dealer 3
		doubleStrategies[7][3] = Decision.Split;	//Dealer 4
		doubleStrategies[7][4] = Decision.Split;	//Dealer 5
		doubleStrategies[7][5] = Decision.Split;	//Dealer 6	
		doubleStrategies[7][6] = Decision.Split;	//Dealer 7
		doubleStrategies[7][7] = Decision.Split;	//Dealer 8
		doubleStrategies[7][8] = Decision.Split;	//Dealer 9
		doubleStrategies[7][9] = Decision.Split;	//Dealer 10

		//Double 9s
		doubleStrategies[8][0] = Decision.Stand;	//Dealer Ace
		doubleStrategies[8][1] = Decision.Split;	//Dealer 2
		doubleStrategies[8][2] = Decision.Split;	//Dealer 3
		doubleStrategies[8][3] = Decision.Split;	//Dealer 4
		doubleStrategies[8][4] = Decision.Split;	//Dealer 5
		doubleStrategies[8][5] = Decision.Split;	//Dealer 6	
		doubleStrategies[8][6] = Decision.Stand;	//Dealer 7
		doubleStrategies[8][7] = Decision.Split;	//Dealer 8
		doubleStrategies[8][8] = Decision.Split;	//Dealer 9
		doubleStrategies[8][9] = Decision.Stand;	//Dealer 10

		//Double 10s
		doubleStrategies[9][0] = Decision.Stand;	//Dealer Ace
		doubleStrategies[9][1] = Decision.Stand;	//Dealer 2
		doubleStrategies[9][2] = Decision.Stand;	//Dealer 3
		doubleStrategies[9][3] = Decision.Stand;	//Dealer 4
		doubleStrategies[9][4] = Decision.Stand;	//Dealer 5
		doubleStrategies[9][5] = Decision.Stand;	//Dealer 6	
		doubleStrategies[9][6] = Decision.Stand;	//Dealer 7
		doubleStrategies[9][7] = Decision.Stand;	//Dealer 8
		doubleStrategies[9][8] = Decision.Stand;	//Dealer 9
		doubleStrategies[9][9] = Decision.Stand;	//Dealer 10

	}
	
}
