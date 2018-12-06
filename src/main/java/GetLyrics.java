import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

public class GetLyrics {

    private GetLyrics() {
        throw new IllegalStateException("Utility class");
    }
   public static String getLyrics(String authorName) {

       Model model = new Model();
       URL url = null;
       try {
           url = new URL("https://api.lyrics.ovh/v1/"+authorName);
       } catch (MalformedURLException e) {

           Logger.getLogger(""+e);
       }

       Scanner sc = null;
       try {
           sc = new Scanner((InputStream) url.getContent());
       } catch (IOException e) {

           Logger.getLogger(""+e);

       }

       StringBuilder result = new StringBuilder("");


           while (sc.hasNext()){

               result.append(sc.nextLine());

           }


      JSONObject jsonObject = new JSONObject(result.toString());
      model.setLyrics(jsonObject.getString("lyrics"));


       return "Lyrics "+"\n\n\n"+model.getLyrics();
   }
}
