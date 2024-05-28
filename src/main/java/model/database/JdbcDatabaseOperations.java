package model.database;

import com.google.gson.reflect.TypeToken;
import model.Message;
import org.apache.http.entity.StringEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations {

    private final Connection connection;

    public JdbcDatabaseOperations(String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url);
    }


    @Override
    public void addMessage(Message message) {
        try{
            String sql =
                    "INSERT INTO ChatMessages (author, text, created)"
            + "VALUES ("
                    + "'" + message.getAuthor() + "'"
                    + "'" + message.getText() + "'"
                    + "'" + Timestamp.valueOf(message.getCreated()) + "'"
            + ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //dodělat + .csv
    @Override
    public List<Message> getMessages() {
        try {

            List<Message> messages = new ArrayList<>();

            String sql = "SELECT author, text, created FROM ChatMessages";
            Statement statement = connection.createStatement();
            ResultSet rst = statement.executeQuery(sql);

            while (rst.next()) {

                Message message = new Message(rst.getString("author"), rst.getString("text"));
                messages.add(message);

            }

            return messages;

    } catch (Exception e) {
        e.printStackTrace();
    }
        return new ArrayList<>();
    }
}
