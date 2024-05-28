package model.chatClients;

import model.Message;
import model.chatClients.fileOperations.ChatFileOperations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FileChatClient implements ChatClients { // zkopirovano z InMemoryChatClient
    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;

    private List<ActionListener> listenersLoggedUsersChanged = new ArrayList<>();
    private List<ActionListener> listenersMessagesChanged = new ArrayList<>();

    ChatFileOperations chatFileOperations;

    public FileChatClient(ChatFileOperations chatFileOperations) {
        loggedUsers = new ArrayList<>();
        this.chatFileOperations = chatFileOperations;
        messages = chatFileOperations.readMessages();
    }

    @Override
    public void sendMessage(String text) {
        messages.add(new Message(loggedUser, text));
        System.out.println("new message: " + messages);
        chatFileOperations.writeMessages(messages);
        raiseEventMessagesChanged();
    }

    @Override
    public void login(String username) {
        loggedUser = username;
        loggedUsers.add(loggedUser);
        addSystemMessage(Message.USER_LOGGED_IN, username);
        System.out.println("user logged in" + username);
        raiseEventLoggedUsersChanged();
    }

    @Override
    public void logout() {
        addSystemMessage(Message.USER_LOGGED_OUT, loggedUser);
        loggedUsers.remove(loggedUser);
        loggedUser = null;
        System.out.println("user logged out");
        raiseEventLoggedUsersChanged();
    }

    @Override
    public boolean isAuthenticated() {
        System.out.println("is authenticated: " + (loggedUser != null));
        return loggedUser != null;
    }

    @Override
    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addActionListenerLoggedUsersChanged(ActionListener toAdd) {
        listenersLoggedUsersChanged.add(toAdd);
    }

    @Override
    public void addActionListenerMessagesChanged(ActionListener toAdd) {
        listenersMessagesChanged.add(toAdd);
    }

    private void raiseEventLoggedUsersChanged() {
        for (ActionListener al :
                listenersLoggedUsersChanged) {
            al.actionPerformed(
                    new ActionEvent(
                            this,
                            1,
                            "usersChanged")
            );
        }
    }

    private void raiseEventMessagesChanged() {
        for (ActionListener al :
                listenersMessagesChanged) {
            al.actionPerformed(
                    new ActionEvent(
                            this,
                            1,
                            "messagesChanged")
            );
        }
    }

    private void addSystemMessage(int type, String author) {
        messages.add(new Message(type, author));
        chatFileOperations.writeMessages(messages);
        raiseEventMessagesChanged();
    }
}
