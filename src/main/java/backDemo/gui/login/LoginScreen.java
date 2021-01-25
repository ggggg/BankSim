package backDemo.gui.login;

import backDemo.exceptions.UserNotFoundException;
import backDemo.exceptions.WrongPasswordException;
import backDemo.gui.ClickableNavText;
import backDemo.gui.GuiManager;
import io.github.ggggg.swingNavigator.interfaces.IRoute;

import javax.swing.*;

/**
 * @author Ido
 * Screen to allow a user to log in.
 */
public class LoginScreen implements IRoute {
    // the main panel
    private JPanel panel;
    // the username field
    private JTextField usernameField;
    // the password field
    private JPasswordField passwordField;
    // the error text
    private JLabel error;
    // login  button
    private JButton loginButton;
    // register text
    private JLabel registerText;

    /**
     * @return the main panel of the page
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Init all the objects.
     */
    public LoginScreen() {
        // hide the error (only shown after invalid submission)
        error.setVisible(false);
        // make the text act like a link
        registerText.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerText.addMouseListener(new ClickableNavText("register"));
        // add action to login button
        loginButton.addActionListener(e -> {
            // get the info
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                // log in the user
                GuiManager.getInstance().login(
                        GuiManager.getInstance().getCore().getUserHandler().login(username, password));
            } catch (UserNotFoundException | WrongPasswordException userNotFoundException) {
                // show the error
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
                .getRootPane(loginButton)
                .setDefaultButton(loginButton);
    }


    /**
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "LoginScreen{" +
                "panel=" + panel +
                ", usernameField=" + usernameField +
                ", passwordField=" + passwordField +
                ", error=" + error +
                ", loginButton=" + loginButton +
                ", registerText=" + registerText +
                '}';
    }
}
