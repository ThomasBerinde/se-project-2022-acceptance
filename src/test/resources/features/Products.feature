Feature: Products

  Scenario: Get product by id success
    Given a product with id="1" exists
    When a request with jwt header="QURNSU4=" to get a product by id="1" is made
    Then the product with id="1" is returned

  Scenario: Get product by id unauthorized
    Given a product with id="1" exists
    When a request with jwt header="VVNFUg==" to get a product by id="1" is made
    Then an error containing the message="Unauthorized, you don't have the required role to access this resource" is returned




