package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Date;

public class Main {
  public static void main(String[] args) throws IOException {
    String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
    
    String bodyText = Request.Get(url).execute().returnContent().asString();

    Gson mapper = new Gson();
    Player[] players = mapper.fromJson(bodyText, Player[].class);

    Date dNow = new Date();
    
    System.out.println("Players from FIN " + dNow + "\n");
    for (Player player : players) {
      if (player.getNationality().equals("FIN"))
        System.out.println(player);
    }   
  }
}