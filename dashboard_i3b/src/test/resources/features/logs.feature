Feature: The user should be able to see the logs related to its role in the application, but not other one's logs

  Scenario: Councilmen doesn't see other's logs
    Given the user navigates to /councilmen_raw
    And a message is produced with topic "councilmen"
    When the user waits 10 seconds
    Then there is a log of "councilmen" on the webpage
      But there is not a log of "councilStaff" on the webpage
