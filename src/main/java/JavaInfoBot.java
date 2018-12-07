import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.UnsupportedEncodingException;

public class JavaInfoBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();

       String getAuthorAndSong  =update.getMessage().getText();

        String filterText = filterData(getAuthorAndSong);
        System.out.println(filterText);
        String filteredAuthorAndSong =  filterText.replaceAll("\\s+","%20");

    if (filteredAuthorAndSong.contains("/")){

        String segments[] = filteredAuthorAndSong.split("/");
        String document = segments[segments.length-1];

        Model model = new Model();
        try {
            message.setText(getBotUsername()+ "\n"+ GetLyrics.getLyrics(filteredAuthorAndSong)+"/"+document);
            message.setChatId(update.getMessage().getChatId());
        }catch (NullPointerException e){
            System.out.println("Song is not found sorry");
            message.setText(getBotUsername()+ "\n"+ "Song is not found sorry");
            message.setChatId(update.getMessage().getChatId());
        }


        try {
            execute(message);
        } catch (TelegramApiException e) {
           // e.printStackTrace();
        }
    }else{

        System.out.println("Not a song");
    }


    }
    public String filterData(String authorAndSongString){
        byte bytes[] = new byte[0];
        try {
            bytes = authorAndSongString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String value = null;
        try {
            value = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }

    public String getBotUsername() {
        return "JavaInfoBot";
    }

    public String getBotToken() {
        return "760718778:AAEkmNrupmDBfVU-ODJWrFDnByB1LPBjydc";
    }
}
