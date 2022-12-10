Feature: Test Cases

  Background:
   Given Open the Chrome tab
    And game is reset
    And all users are connected
  Scenario: Row 41
    Given The game has started
    And Player 0 hand is "3C,JC,"
    When Player 0 plays "3C"
    Then The next player is 1

  Scenario: Row 43
    Given The game has started
    And Player 0 hand is "1H,JC,"
    And Player 3 hand is "7H,QC,"
    When Player 0 plays "1H"
    Then The next player is 3
    And interface shows that we are going "down"
    When Player 3 plays "7H"
    Then The next player is 2

  Scenario: Row 44
    Given The game has started
    When Player 1 plays "QC"
    Then The next player is 3

  Scenario: Row 45
    Given The game has started
    When Player 4 plays "3C"
    Then The next player is 1

  Scenario: Row 47
    Given The game has started
    When Player 4 plays "1H"
    Then The next player is 3
    And interface switches direction

  Scenario: Row 48
    Given The game has started
    When Player 4 plays "QC"
    Then The next player is 2
