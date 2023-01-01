package com.example.seproject2022acceptance.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

        private Actionwords actionwords = new Actionwords();

        @Given("a product with id={string} exists")
        public void aProductWithIdExists(String id) {
                actionwords.aProductWithIdExists(id);
        }

        @When("a request with jwt header={string} to get a product by id={string} is made")
        public void aRequestWithJwtHeaderToGetAProductByIdIsMade(String jwt,
                                                                 String id) {
                actionwords.aRequestWithJwtHeaderToGetAProductByIdIsMade(jwt, id);
        }

        @Then("the product with id={string} is returned")
        public void theProductWithIdIsReturned(String id) {
                actionwords.theProductWithIdIsReturned(id);
        }

        @Then("an error containing the message={string} is returned")
        public void anErrorContainingTheMessageIsReturned(String message) {
                actionwords.anErrorContainingTheMessageIsReturned(message);
        }

        @When("a request with header jwt={string} to make an order with the product ids id={string}, id={string} and "
                + "id={string} is made")
        public void aRequestWithHeaderJwtToMakeAnOrderWithTheProductIdsIdIdAndIdIsMade(String jwt,
                                                                                       String id1,
                                                                                       String id2,
                                                                                       String id3) {
                actionwords.aRequestWithHeaderJwtToMakeAnOrderWithTheProductIdsIdIdAndIdIsMade(jwt, id1, id2, id3);
        }

        @Then("a response containing the message={string} is returned")
        public void aResponseContainingTheMessageIsReturned(String message) {
                actionwords.aResponseContainingTheMessageIsReturned(message);
        }

        @And("a product with id={string} doesn't exists")
        public void aProductWithIdDoesntExists(String id) {
                actionwords.aProductWithIdDoesntExists(id);
        }

        @When("a request to create an account with email={string} is made")
        public void aRequestToCreateAnAccountWithEmailIsMade(String email) {
                actionwords.aRequestToCreateAnAccountWithEmailIsMade(email);
        }

        @Then("an account with email={string} is returned")
        public void anAccountWithEmailIsReturned(String email) {
                actionwords.anAccountWithEmailIsReturned(email);
        }

        @Given("an account with email={string} and password={string} exists")
        public void anAccountWithEmailAndPasswordExists(String email,
                                                        String password) {
                actionwords.anAccountWithEmailAndPasswordExists(email, password);
        }

        @When("a request to login with email={string} and password={string} is made")
        public void aRequestToLoginWithEmailAndPasswordIsMade(String email,
                                                              String password) {
                actionwords.aRequestToLoginWithEmailAndPasswordIsMade(email, password);
        }

        @Then("a response containing a jwt={string} is returned")
        public void aResponseContainingAJwtIsReturned(String jwt) {
                actionwords.aResponseContainingAJwtIsReturned(jwt);
        }

        @Given("an account with email={string} doesn't exist")
        public void anAccountWithEmailDoesntExist(String email) {
                actionwords.anAccountWithEmailDoesntExist();
        }
}
