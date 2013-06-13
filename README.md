BlackJack
=========

#About
This is a project I started to look into the basic strategy and probabilities of the classic casino game BlackJack (21). It simulates a player (or players) playing blackjack against a dealer with the goal of maximizing his/her probability of winning using a basic strategy table found online (http://www.online-casinos.com/blackjack/blackjack_chart.asp).

#Rules and usage
The user of this program has the ability to change the following house rules:
* Number of decks in the shoe
* The ability to double down after a split
* The minimum bet
* The maximum number of hands (if multiple splits occur)
* The blackjack payout ratio (usually 1.5)

When a game object is created, its start method can be called to begin a simulation. The simulation will continue until the number of shoes (passed in as a parameter) have been cycled through, with each shoe containing the number of decks specified in the house rules. During the simulation, stats for each player will be kept on how how often they win, lose and push. Each players' cash will also be tracked along with the peak amount of cash they reach (i.e. how much they could have ever made if they got out on top). 

Each hand, all players except for the dealer use the Strategy class to determine which action they should take (hit, stand, double or split) to maximize his/her probability of winning.

#Findings & problems
As with all projects, after completing it there were a few things I learned about how I could do it better next time. First, populating the strategy tables was very tedious (although sublime text helped greatly thanks to its great bulk editing capabilities). Instead of using 2d arrays of the Decision Enum class which made it hard to visualize the tables, I should have used a 2d array of Strings that represented the decisions and then converted the String into the corresponding Decision Enum. While my way worked, using Strings would have been easier to decipher visually for someone else reading my code.

#Future & contact
In the future I might expand on this project by looking into other card-counting strategies such as Hi-Lo to investigate the effectiveness of card-counting. In the meantime please contact me with an questions, comments or advice at sam.donohue@wustl.edu.