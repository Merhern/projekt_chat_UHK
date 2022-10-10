package chat_clients;

import models.Message;

import java.util.List;

public interface ChatClient {

    void send(String text);

    void login(String username);

    void logout();

    boolean isAuthorLogedin();

    List<String> getLoggedUsers();

    List<Message> getMessage();

}
