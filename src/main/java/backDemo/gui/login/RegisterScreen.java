package backDemo.gui.login;

import backDemo.exceptions.UserExistsException;
import backDemo.gui.ClickableNavText;
import backDemo.gui.GuiManager;
import backDemo.users.AccountType;
import io.github.ggggg.swingNavigator.interfaces.IRoute;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

/**
 * @author Ido
 * Screen to allow a user to create an account.
 */
public class RegisterScreen implements IRoute {
    // the main panel
    private JPanel panel;
    // username field
    private JTextField usernameField;
    // password field
    private JPasswordField passwordField;
    // error text
    private JLabel error;
    // submit button
    private JButton registerButton;
    // the main label
    private JLabel registerText;
    // selection of account type
    private JComboBox<AccountType> accountTypeSelection;

    /**
     * @return the main panel of the page
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Screen to allow a user to create an account.
     */
    public RegisterScreen() {
        // hide the error
        error.setVisible(false);
        // get all the account types
        Arrays.stream(AccountType.values()).forEach(x -> accountTypeSelection.addItem(x));
        // make the text act as a link
        registerText.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerText.addMouseListener(new ClickableNavText("login"));
        // make the submit button clickable
        registerButton.addActionListener(e -> {
            // get entered info
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            AccountType type = (AccountType) accountTypeSelection.getSelectedItem();
            try {
                // create an account and log the user in
                GuiManager.getInstance().login(GuiManager.getInstance().getCore().getUserHandler().register(username, password, type));
            } catch (UserExistsException ex) {
                // if there is error display it.
                error.setVisible(true);
            }
        });
    }

    /**
     * Enable submitting by pressing enter.
     */
    @Override
    public void onStarted() {
        SwingUtilities
                .getRootPane(registerButton)
                .setDefaultButton(registerButton);
    }

    /**
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "RegisterScreen{" +
                "panel=" + panel +
                ", usernameField=" + usernameField +
                ", passwordField=" + passwordField +
                ", error=" + error +
                ", registerButton=" + registerButton +
                ", registerText=" + registerText +
                ", accountTypeSelection=" + accountTypeSelection +
                '}';
    }
}
