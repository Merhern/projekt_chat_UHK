package model.chatClients.fileOperations;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.LocalDatetimeDeserializer;
import model.LocalDatetimeSerializer;
import model.LocalDatetimeSerializer;
import model.Message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonChatFileOperations implements ChatFileOperations {
    private Gson gson;

    private static final String MESSAGES_FILE = "./messages.json";
    public JsonChatFileOperations() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting() // upraví formát zapsání do souboru
                .registerTypeAdapter(LocalDateTime.class, new LocalDatetimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDatetimeDeserializer())
                .create();
    }
    @Override
    public void writeMessages(List<Message> messages) {
        String jsonText = gson.toJson(messages);
        System.out.println(jsonText);

        try {
            FileWriter writer = new FileWriter(MESSAGES_FILE);
            writer.write(jsonText); //zapíše do souboru
            writer.flush(); // vyčistí
            writer.close(); // zavře
        } catch (IOException e) {
            e.printStackTrace(); //vypíše chybu do konzole
        }

    }

    @Override
    public List<Message> readMessages() {
        try {
            FileReader reader = new FileReader(MESSAGES_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader); // zajistí, aby se to četlo po řádcích, protože ta velikost bude huge
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.append(line);
            }
            reader.close();

            Type targetType = new TypeToken<ArrayList<Message>>(){}.getType();
            List<Message> messages = gson.fromJson(jsonText.toString(), targetType);

            return messages;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}


