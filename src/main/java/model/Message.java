package model;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class Message {
    @Expose(serialize = true,deserialize = true)
    private String author;
    @Expose(serialize = true,deserialize = true)
    private String text;
    @Expose(serialize = true,deserialize = true)
    private LocalDateTime created;

    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 2;

    private static final String AUTHOR_SYSTEM = "System";

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        created = LocalDateTime.now();
    }
    public Message(int type, String userName) {
        this.author = AUTHOR_SYSTEM;
        this.created = LocalDateTime.now();
        if (type == USER_LOGGED_IN) {
            text = userName + " has joined the chat";
        }
        else if (type == USER_LOGGED_OUT) {
            text = userName + " has left the chat";
        }
    }
    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        if (author.toUpperCase().equals(AUTHOR_SYSTEM))
            return text + "\n";

        String s = author + " - " + created + "\n";
        s += text + "\n";
        return s;
    }
}
