import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class JavaInfoBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();

       String getAuthorAndSong  =update.getMessage().getText();
       String filteredAuthorAndSong =  getAuthorAndSong.replaceAll("\\s+","%20");

    if (filteredAuthorAndSong.contains("/")){

        String segments[] = filteredAuthorAndSong.split("/");
        String document = segments[segments.length-1];

        Model model = new Model();
        try {
            message.setText(getBotUsername()+ "\n"+ GetLyrics.getLyrics(filteredAuthorAndSong)+"/"+document);
            message.setChatId(update.getMessage().getChatId());
        }catch (NullPointerException e){
            System.out.println("Song is not found sorry");

        }


        try {
            execute(message);
        } catch (TelegramApiException e) {
           // e.printStackTrace();
        }
    }else{

        System.out.println("Nope");
    }


    }


    public String getBotUsername() {
        return "JavaInfoBot";
    }

    public String getBotToken() {
        return "760718778:AAEkmNrupmDBfVU-ODJWrFDnByB1LPBjydc";
    }
}
