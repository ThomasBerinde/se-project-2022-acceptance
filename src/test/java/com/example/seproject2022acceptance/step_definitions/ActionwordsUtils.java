package com.example.seproject2022acceptance.step_definitions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActionwordsUtils {

        String responseString;

        public JsonObject getResponseAsJson(Response response) throws Exception {
                if (response.body() == null) {
                        throw new Exception("Response body is null");
                }
                return JsonParser.parseString(response.body()
                                                      .string())
                                 .getAsJsonObject();
        }

        public void deleteAcceptanceAccounts(String BASE_URL,
                                             OkHttpClient httpClient) throws Exception {
                Request request = new Request.Builder().url(BASE_URL + "test/accounts")
                                                       .delete()
                                                       .build();
                try (Response response = httpClient.newCall(request)
                                                   .execute()) {
                        if (response.body() == null) {
                                throw new Exception("Response body is null");
                        }
                        responseString = response.body()
                                                 .string();
                }
                if (!responseString.equals("Successfully deleted accounts created by acceptance tests")) {
                        throw new Exception("Error deleting acceptance accounts");
                }
        }
}
