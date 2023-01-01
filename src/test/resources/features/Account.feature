Feature: Accounts

  Scenario: Create account success
    When a request to create an account with email="acceptance@gmail.com" is made
    Then an account with email="acceptance@gmail.com" is returned
    
  Scenario: Create account invalid fields
    When a request to create an account with email="acceptancegmail.com" is made
    Then an error containing the message="Validation failed for object='createAccountRequestDto'. Error count: 1" is returned