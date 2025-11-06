package org.example.user_system.model.repository;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.user_system.model.entity.UserAccount;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

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

  public void createUser(UserAccount userAccount) {
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

    HttpResponse<String> response = null;
    try {
      response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println(response.statusCode());
    if (response.body().isBlank()) {
      System.out.println(response.body());
    }
  }

  /*
  -H "apikey: SUPABASE_KEY" \
  -H "Authorization: Bearer SUPABASE_KEY" \
  -H "Range: 0-9" \
  -d "select=*" \
   */
  public UserAccount findByUsername(String username) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL + "/rest/v1/USER_ACCOUNT?select=*&username=eq." + username))
        .headers("apikey", API_KEY, "Authorization", "Bearer " + API_KEY, "Range", "0-9")
        .GET()
        .build();

    try {
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(response.statusCode());
      if (response.body().isBlank()) throw new RuntimeException();
      return objectMapper.readValue(
          response.body(), // 문자열 형태로 전달받은 데이터
          new TypeReference<List<UserAccount>>() {
          } // UserAccount로 파싱하여서 리스트 형태로 받고...
      ).get(0); // 1개만 있다고 가정
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
