Feature: Test Cases

  Background:
   Given Open the Chrome tab
    And all users are connected
  Scenario: Row 41
    Given The game has started
    When Player 1 plays "3C"
    Then The next player is 2

  Scenario: Row 43
    Given The game has started
    When Player 1 plays "1H"
    Then The next player is 4
    And interface switches direction
    When Player 4 plays "7H"
    Then The next player is 3

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
