package UI;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginWindow implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel welcomeMessage;
    JButton createAccount;
    JLabel usernameLabel;
    JTextField usernameText;
    JLabel passwordLabel;
    JPasswordField passwordCover;
    JButton enterButton;
    JLabel successMessage;

    LoginWindow() {
        frame = new JFrame("Welcome!");
        frame.setSize(500, 300);

        panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        welcomeMessage = new JLabel("Feeling Mood-ical? Login to begin!");
        welcomeMessage.setBounds(140, 10, 500, 100);
        panel.add(welcomeMessage);

        createAccount = new JButton("Or... Create an account");
        createAccount.setBounds(150, 75, 200, 25);
        createAccount.addActionListener(this);
        panel.add(createAccount);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(125, 110, 200, 25);
        panel.add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(190, 110, 200, 25);
        panel.add(usernameText);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(125, 130, 200, 25);
        panel.add(passwordLabel);

        passwordCover = new JPasswordField();
        passwordCover.setBounds(190, 130, 200, 25);
        panel.add(passwordCover);

        enterButton = new JButton("Login");
        enterButton.setBounds(150, 175, 200, 25);
        enterButton.addActionListener(this);
        panel.add(enterButton);

        successMessage = new JLabel("");
        successMessage.setBounds(125, 200, 200,25);
        panel.add(successMessage);
        successMessage.setText("");

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        String username = usernameText.getText();
        String password = passwordCover.getText();

        if (button.getSource() == createAccount) {
            new AccountCreationWindow();
        } else {
            RequestModels.UserAccountRequestModel requestModel =
                    new RequestModels.UserAccountRequestModel(username, password,
                            RequestModels.UserAccountRequestModel.createEmptyHistoryObject(),
                            RequestModels.UserAccountRequestModel.createEmptyFavouritesObject());
            try {
                if (Controllers.UserAccountController.returnUserExistence(requestModel)) {
                    successMessage.setText("Login Successful");
                } else {
                    successMessage.setText("User not found. Please create an account");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}

