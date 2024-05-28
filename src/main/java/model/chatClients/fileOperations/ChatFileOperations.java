package model.chatClients.fileOperations;

import model.Message;

import java.util.List;

public interface ChatFileOperations {
    void writeMessages(List<Message> messages);
    List<Message> readMessages();
}
