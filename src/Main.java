import chat_clients.ChatClient;
import chat_clients.InMemoryChatClient;
import models.gui.MainFrame;

public class Main {

    public static void main(String[] args){

        //System.out.println("hello world");

        MainFrame window = new MainFrame(600, 500);

    }

    public  static void test(){

        ChatClient client = new InMemoryChatClient();

        client.login("meh");

        client.send("hello");
        client.send("there");

        client.logout();

    }

}
