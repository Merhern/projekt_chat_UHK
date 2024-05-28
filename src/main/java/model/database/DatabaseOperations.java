package model.database;

import model.Message;

import java.util.List;

public interface DatabaseOperations {
    void addMessage(Message message);
    List<Message> getMessages();
}
