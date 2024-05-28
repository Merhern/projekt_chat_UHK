package model.chatClients.fileOperations;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import model.Message;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVChatFileOperations implements ChatFileOperations {

    private static final String MESSAGES_FILE = "./messages.csv";

    @Override
    public void writeMessages(List<Message> messages) {

        try {

            FileWriter outputFile = new FileWriter(MESSAGES_FILE);
            CSVWriter writer = new CSVWriter(outputFile);

            for (int i = 0; i < messages.size(); i++) {

                String message = messages.get(i).toString().replace("\n", ",");
                message = message.substring(0, message.length() -1);
                System.out.println(message);
                outputFile.append(message);
                System.out.println(message);
                outputFile.append("\n");
            }
            outputFile.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    public List<Message> readMessages() {
        try {

            FileReader output = new FileReader(MESSAGES_FILE);
            CSVReader reader = new CSVReader(output);

            List<Message> messages = new ArrayList<>();

            String[] next;
            while ((next = reader.readNext()) != null) {

                for (int i = 0; i < next.length - 1; i++) {

                    if (i % 2 == 0) {
                        String author = next[i];
                        String text = next[i + 1];
                        Message message = new Message(author, text);
                        messages.add(message);
                    }

                }

            }

            reader.close();
            return messages;

        } catch (IOException e) {

            e.printStackTrace();
            return new ArrayList<>();

        }

    }
}
