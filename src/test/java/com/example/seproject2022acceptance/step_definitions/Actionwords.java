package com.example.seproject2022acceptance.step_definitions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class Actionwords {

        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private final ActionwordsUtils utils = new ActionwordsUtils();
        private final String BASE_URL = "http://localhost:8080/";
        private final OkHttpClient httpClient = new OkHttpClient();
        private final String JWT_ADMIN = "QURNSU4=";
        private final String JWT_USER = "VVNFUg==";
        private JsonObject jsonResponse;

        @Before
        @After
        public void cleanTestEnv() {
                try {
                        utils.deleteAcceptanceAccounts(BASE_URL, httpClient);
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void aProductWithIdExists(String id) {
                Request request = new Request.Builder().url(BASE_URL + "products/" + id)
                                                       .get()
                                                       .addHeader("jwt", JWT_ADMIN)
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        jsonResponse = utils.getResponseAsJson(response);
                        if (!jsonResponse.get("id")
                                         .getAsString()
                                         .equals(id)) {
                                throw new RuntimeException(
                                        "There is no product with the given id, try a different one");
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void aRequestWithJwtHeaderToGetAProductByIdIsMade(String jwt,
                                                                 String id) {
                Request request = new Request.Builder().url(BASE_URL + "products/" + id)
                                                       .get()
                                                       .addHeader("jwt", jwt)
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        jsonResponse = utils.getResponseAsJson(response);
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void theProductWithIdIsReturned(String id) {
                JsonElement jsonId = jsonResponse.get("id");
                if (jsonId == null || !jsonId.getAsString()
                                             .equals(id)) {
                        fail();
                }
        }

        public void anErrorContainingTheMessageIsReturned(String message) {
                JsonElement jsonMessage = jsonResponse.get("message");
                if (jsonMessage == null || !jsonMessage.getAsString()
                                                       .equals(message)) {
                        fail();
                }
        }

        public void aRequestWithHeaderJwtToMakeAnOrderWithTheProductIdsIdIdAndIdIsMade(String jwt,
                                                                                       String id1,
                                                                                       String id2,
                                                                                       String id3) {
                String orderJson = String.format("{\"product_ids\": [%s, %s, %s]}", id1, id2, id3);
                RequestBody body = RequestBody.create(JSON, orderJson);
                Request request = new Request.Builder().url(BASE_URL + "orders")
                                                       .post(body)
                                                       .addHeader("jwt", jwt)
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        jsonResponse = utils.getResponseAsJson(response);
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void aResponseContainingTheMessageIsReturned(String message) {
                JsonElement jsonMessage = jsonResponse.get("message");
                if (jsonMessage == null || !jsonMessage.getAsString()
                                                       .equals(message)) {
                        fail();
                }
        }

        public void aProductWithIdDoesntExists(String id) {
                Request request = new Request.Builder().url(BASE_URL + "products/" + id)
                                                       .get()
                                                       .addHeader("jwt", JWT_ADMIN)
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        jsonResponse = utils.getResponseAsJson(response);
                        assertNull(jsonResponse.get("id"));
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void aRequestToCreateAnAccountWithEmailIsMade(String email) {
                String account = "{\n"
                        + "    \"email\": \"%s\",\n"
                        + "    \"first_name\": \"Account\",\n"
                        + "    \"last_name\": \"Test\",\n"
                        + "    \"phone\": \"0711111111\",\n"
                        + "    \"password\": \"test\",\n"
                        + "    \"address\": {\n"
                        + "        \"street\": \"Test Street\",\n"
                        + "        \"number\": \"1\",\n"
                        + "        \"city\": \"Test City\",\n"
                        + "        \"county\": \"Test County\",\n"
                        + "        \"country\": \"Test Country\",\n"
                        + "        \"post_code\": \"test-000111\"\n"
                        + "    }\n"
                        + "}";
                RequestBody body = RequestBody.create(JSON, String.format(account, email));
                Request request = new Request.Builder().url(BASE_URL + "accounts")
                                                       .post(body)
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        jsonResponse = utils.getResponseAsJson(response);
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void anAccountWithEmailIsReturned(String email) {
                JsonElement emailElement = jsonResponse.get("email");
                if (emailElement == null || !emailElement.getAsString()
                                                         .equals(email)) {
                        fail();
                }
        }

        public void anAccountWithEmailAndPasswordExists(String email,
                                                        String password) {
                String account = "{\n"
                        + "    \"email\": \"%s\",\n"
                        + "    \"first_name\": \"Account\",\n"
                        + "    \"last_name\": \"Acceptance\",\n"
                        + "    \"phone\": \"0711111111\",\n"
                        + "    \"password\": \"%s\",\n"
                        + "    \"address\": {\n"
                        + "        \"street\": \"Test Street\",\n"
                        + "        \"number\": \"1\",\n"
                        + "        \"city\": \"Test City\",\n"
                        + "        \"county\": \"Test County\",\n"
                        + "        \"country\": \"Test Country\",\n"
                        + "        \"post_code\": \"test-000111\"\n"
                        + "    }\n"
                        + "}";
                RequestBody body = RequestBody.create(JSON, String.format(account, email, password));
                Request request = new Request.Builder().url(BASE_URL + "accounts")
                                                       .post(body)
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        jsonResponse = utils.getResponseAsJson(response);
                        anAccountWithEmailIsReturned(email);
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void aRequestToLoginWithEmailAndPasswordIsMade(String email,
                                                              String password) {
                String loginJson = "{\n"
                        + "    \"email\": \"%s\",\n"
                        + "    \"password\": \"%s\"\n"
                        + "}";
                RequestBody body = RequestBody.create(JSON, String.format(loginJson, email, password));
                Request request = new Request.Builder().url(BASE_URL + "login")
                                                       .post(body)
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        jsonResponse = utils.getResponseAsJson(response);
                } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                }
        }

        public void aResponseContainingAJwtIsReturned(String jwt) {
                JsonElement jwtElement = jsonResponse.get("jwt");
                if (jwtElement == null || !jwtElement.getAsString()
                                                     .equals(jwt)) {
                        fail();
                }
        }

        public void anAccountWithEmailDoesntExist() {
                cleanTestEnv();
        }
}
