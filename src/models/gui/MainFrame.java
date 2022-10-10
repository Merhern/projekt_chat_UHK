package models.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    JTextArea txtChat = new JTextArea();

    public MainFrame(int width, int height){

        super("CHAT_CLIENT");

        setSize(width, height);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initGui();
        setVisible(true);

    }

    private void initGui(){

        JPanel panelMain = new JPanel(new BorderLayout());

        panelMain.add(initLoginPanel(), BorderLayout.NORTH);

        panelMain.add(initMessagePanel(), BorderLayout.SOUTH);

        panelMain.add(initChatPanel(), BorderLayout.CENTER);

        add(panelMain);

    }

    private JPanel initLoginPanel(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Username: "));

        JTextField txtLogin = new JTextField( "",30);
        panel.add(txtLogin);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ButtonLogin Click" + txtLogin.getText());
            }
        });

        panel.add(btnLogin);

        return panel;

    }

    private JPanel initChatPanel(){

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        txtChat.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtChat);

        panel.add(scrollPane);
        panel.add(txtChat);

        /*for (int i = 0; i < 50; i++){

            txtChat.append("Message" + i);

        }*/

        return panel;

    }

    private JPanel initMessagePanel(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField txtMessage = new JTextField("", 50);
        panel.add(txtMessage);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ButtonSend Click " + txtMessage.getText());

                txtChat.append(txtMessage.getText() + "\n");
                txtMessage.setText("");
            }
        });

        panel.add(btnSend);


        return panel;

    }


}
