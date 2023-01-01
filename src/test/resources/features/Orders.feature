Feature: Orders

  Scenario: Create order success
    Given a product with id="1" exists
    And a product with id="2" exists
    And a product with id="3" exists
    When a request with header jwt="VVNFUg==" to make an order with the product ids id="1", id="2" and id="3" is made
    Then a response containing the message="Successfully created order" is returned

  Scenario: Create order product not found
    Given a product with id="1" exists
    And a product with id="2" exists
    And a product with id="1000" doesn't exists
    When a request with header jwt="VVNFUg==" to make an order with the product ids id="1", id="2" and id="1000" is made
    Then an error containing the message="Product with id=1000 doesn't exists" is returned