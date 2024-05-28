package model.gui;

import model.chatClients.ChatClients;

import javax.swing.table.AbstractTableModel;

public class LoggedUsersTableModel extends AbstractTableModel {
    private ChatClients chatClient;
    public LoggedUsersTableModel(ChatClients chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public int getRowCount() {
        return chatClient.getLoggedUsers().size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return chatClient.getLoggedUsers().get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return "User";
    }
}
