Feature: Cucumber Steps for Player
  Background: A player exists

    Scenario Outline: Get And Set Scores
      Given a player has a <initScore>
      When they gain <points>
      Then a player score is <afterScore>
      Examples:
      |initScore|points|afterScore|
      |    0    |  75  |    75    |
      |    50   |  75  |   125    |