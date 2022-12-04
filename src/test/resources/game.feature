Feature: Cucumber Steps for Game
  Background: A game is started


    Scenario Outline: Players Hands at the start of the game
      Given Testing Start of Game
      When a pile is shuffled to <shuffle>
      And the pile is shuffled out
      Then the stock pile is <stock>
      And player 1 hand is <hand1>
      And player 2 hand is <hand2>
      And player 3 hand is <hand3>
      And player 4 hand is <hand4>
      Examples:
      |                               shuffle                               |   stock  |        hand1      |        hand2      |       hand3       |       hand4       |
      | "1D,2D,3D,4D,1H,2H,3H,4H,1C,2C,3C,4C,1S,2S,3S,4S,5D,5H,5C,5S,6H,7H" | "6H,7H," | "1D,1H,1C,1S,5D," | "2D,2H,2C,2S,5H," | "3D,3H,3C,3S,5C," | "4D,4H,4C,4S,5S," |

     Scenario Outline: One player has played thier last card
        Given Last Card
        When player 1 hand is empty
        And player 2 hand is currently <hand2>
        And player 3 hand is currently <hand3>
        And player 4 hand is currently <hand4>
        Then we end the game
        And player 1 score 0
        And player 2 score <score2>
        And player 3 score <score3>
        And player 4 score <score4>
       Examples:
         |  hand2  | score2 |           hand3           | score3 |      hand4  | score4 |
         | "8H,QC" |   60   | "1D,2D,3D,4D,5D,6D,7D,9D" |   37   | "KD,JC,10C" |   30   |
