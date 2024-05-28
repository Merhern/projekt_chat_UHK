import model.chatClients.ChatClients;
import model.chatClients.FileChatClient;
import model.chatClients.InMemoryChatClient;
import model.chatClients.api.ApiChatClient;
import model.chatClients.fileOperations.CSVChatFileOperations;
import model.chatClients.fileOperations.ChatFileOperations;
import model.chatClients.fileOperations.JsonChatFileOperations;
import model.database.DatabaseOperations;
import model.database.DbInitializer;
import model.database.JdbcDatabaseOperations;
import model.gui.MainFrame;

import java.awt.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String databaseDriver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseUrl = "jdbc:derby:ChatClient_skB";

        //DbInitializer dbInitializer = new DbInitializer(databaseDriver, databaseUrl);
        //dbInitializer.init();

        //ChatFileOperations chatFileOperations = new CSVChatFileOperations();
        ChatClients chatClient = new ApiChatClient();

        MainFrame window = new MainFrame(800,600, chatClient);

        //test();

    }

    private static void test() {
        ChatClients client = new InMemoryChatClient();

        client.login("krejzlm");

        client.sendMessage("Message 1");
        client.sendMessage("Message 2");

        client.logout();
    }
}