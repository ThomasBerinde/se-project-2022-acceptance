Feature: Login

  Scenario: User login success
    Given an account with email="acceptance@gmail.com" and password="password" exists
    When a request to login with email="acceptance@gmail.com" and password="password" is made
    Then a response containing a jwt="VVNFUg==" is returned

  Scenario: Admin login success
    When a request to login with email="thomas.berinde.99@gmail.com" and password="berinde" is made
    Then a response containing a jwt="QURNSU4=" is returned

  Scenario: Login invalid email
    Given an account with email="acceptance@gmail.com" doesn't exist
    When a request to login with email="test@gmail.com" and password="password" is made
    Then an error containing the message="Invalid email" is returned


  Scenario: Login invalid password
    Given an account with email="acceptance@gmail.com" and password="password" exists
    When a request to login with email="acceptance@gmail.com" and password="whatever" is made
    Then an error containing the message="Invalid password" is returned
