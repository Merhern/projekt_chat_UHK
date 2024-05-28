package model.chatClients;

import model.Message;

import java.awt.event.ActionListener;
import java.util.List;

public interface ChatClients {
    void sendMessage (String text);
    void login(String username);
    void logout();
    boolean isAuthenticated();
    List<String> getLoggedUsers();
    List<Message> getMessages();

    void addActionListenerLoggedUsersChanged(ActionListener toAdd);
    void addActionListenerMessagesChanged(ActionListener toAdd);
}
