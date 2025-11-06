package org.example.user_system.model.repository;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.user_system.model.entity.UserAccount;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserAccountRepository {
  private final String API_URL;
  private final String API_KEY;
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final ObjectMapper objectMapper = new ObjectMapper();

  public UserAccountRepository() {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    API_URL = dotenv.get("SUPABASE_URL");
    API_KEY = dotenv.get("SUPABASE_KEY");
  }

  public void createUser(UserAccount userAccount) throws IOException, InterruptedException {
    /*
    -H "apikey: SUPABASE_KEY" \
    -H "Authorization: Bearer SUPABASE_KEY" \
    -H "Content-Type: application/json" \
    -H "Prefer: return=minimal" \
     */
    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(API_URL + "/rest/v1/USER_ACCOUNT"))
        .headers("apikey", API_KEY, "Authorization", "Bearer " + API_KEY, "Content-Type", "application/json", "Prefer", "return=minimal")
        .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(userAccount)))
        .build();

    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.statusCode());
    if (response.body().isBlank()) {
      System.out.println(response.body());
    }
  }
}
