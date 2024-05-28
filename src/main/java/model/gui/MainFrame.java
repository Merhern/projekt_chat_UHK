package model.gui;

import model.Message;
import model.chatClients.ChatClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private ChatClients chatClient;
    JTextArea txtChat;
    JTextField txtInputMessage;
    public MainFrame(int width, int height, ChatClients chatClient) {
        super("PRO2 2022 ChatClient");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.chatClient = chatClient;

        initGui();
        setVisible(true);
    }

    private void initGui() { //pripravi nam komponenty
        JPanel panelMain = new JPanel(new BorderLayout()); // neco jako div, neni videt ale davame tam neco

        panelMain.add(initLoginPanel(), BorderLayout.NORTH);
        panelMain.add(initChatPanel(), BorderLayout.CENTER);
        panelMain.add(initLoggedUsersPanel(), BorderLayout.EAST);
        panelMain.add(initMessagePanel(), BorderLayout.SOUTH);
        add(panelMain);
    }

    private JPanel initLoginPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // komponenty se řadí za sebe zleva

        panel.add(new JLabel("Username"));
        JTextField txtInputUsername = new JTextField("",30);
        panel.add(txtInputUsername);
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = txtInputUsername.getText();
                System.out.println("button login clicked - " + txtInputUsername.getText());

                if(chatClient.isAuthenticated()){
                    // LOGIN
                    chatClient.logout();
                    btnLogin.setText("Login");
                    txtInputUsername.setEditable(true);
                    txtChat.setEnabled(false);
                    txtInputMessage.setEnabled(false);
                }
                else {
                    // LOGOUT
                    if (userName.length()<1) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Enter your username",
                                "Error",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                    chatClient.login(userName);
                    btnLogin.setText("Logout");
                    txtInputUsername.setEditable(false);
                    txtChat.setEnabled(true);
                    txtInputMessage.setEnabled(true);
                }

            }
        });
        panel.add(btnLogin);


        return panel;
    }

    private JPanel initChatPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        txtChat = new JTextArea(); // je v MainFrame, aby to bylo pristupne pro initMessagePanel
        txtChat.setEditable(false); // at se tam neda psat
        txtChat.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(txtChat);

        //panel.add(txtChat);
        panel.add(scrollPane);

        /*
        for (int i = 0; i < 50; i++) {
            txtChat.append("Message " + i + "\n");
        }
        */

        chatClient.addActionListenerMessagesChanged(e -> {
            refreshMessages();
        });

        return panel;
    }

    private JPanel initLoggedUsersPanel() {
        JPanel panel = new JPanel();

        /*Object[][] data = new Object[][]{
                {"0,0", "0,1"},
                {"1,0", "1,1"},
                {"aaa", "bbb"},
        };
        String[] colNames = new String[]{"Col1", "Col2"};

        JTable tblLoggedUsers = new JTable(data, colNames); */

        JTable tblLoggedUsers = new JTable();
        LoggedUsersTableModel loggedUsersTableModel = new LoggedUsersTableModel(chatClient);
        tblLoggedUsers.setModel(loggedUsersTableModel);

        JScrollPane scrollPane = new JScrollPane(tblLoggedUsers);
        scrollPane.setPreferredSize(new Dimension(250,500)); //bez tohohle by to bylo přes půlku okna
        panel.add(scrollPane);

        chatClient.addActionListenerLoggedUsersChanged(e -> {
            loggedUsersTableModel.fireTableDataChanged();
        });

        return panel;
    }

    private JPanel initMessagePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtInputMessage = new JTextField("",50);
        txtInputMessage.setEnabled(false);

        panel.add(txtInputMessage);
        JButton btnSendMessage = new JButton("Send");
        btnSendMessage.addActionListener(e -> {
            String msgText = txtInputMessage.getText();
            System.out.println("btn send clicked - " + msgText);
            //txtChat.append(txtInputMessage.getText() + "\n");
            if(msgText.length() == 0)
                return;
            if (!chatClient.isAuthenticated())
                return;
            chatClient.sendMessage(msgText);
            txtInputMessage.setText("");    // vymaze to z radku po odeslani
            //refreshMessages();
        });
        panel.add(btnSendMessage);

        return panel;
    }

    private void refreshMessages() {
        if(!chatClient.isAuthenticated())
            return;

        txtChat.setText("");
        for (Message msg : chatClient.getMessages()) {
            txtChat.append(msg.toString());
            txtChat.append("\n");
        }
    }
}
