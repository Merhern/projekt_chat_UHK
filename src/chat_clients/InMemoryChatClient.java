package chat_clients;

import models.Message;

import java.util.ArrayList;
import java.util.List;

public class InMemoryChatClient implements ChatClient {

    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;

    public InMemoryChatClient(){

        loggedUsers = new ArrayList<>();

        messages = new ArrayList<>();


    }

    @Override
    public void send(String text) {

        messages.add(new Message(loggedUser, text));

    }

    @Override
    public void login(String username) {

        loggedUser = username;

        loggedUsers.add(username);

        System.out.println("new user: " + username);

    }

    @Override
    public void logout() {

        loggedUsers.remove(loggedUser);

        loggedUser = null;

        System.out.println("user logged out");

    }

    @Override
    public boolean isAuthorLogedin() {

        System.out.println("is authenticied");

        return loggedUser != null;
    }

    @Override
    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    @Override
    public List<Message> getMessage() {
        return messages;
    }
}
