package backDemo.gui.login;

import backDemo.gui.GuiManager;
import io.github.ggggg.swingNavigator.interfaces.IRoute;

import javax.swing.*;

/**
 * @author Ido
 * The welcome screen.
 */
public class MainScreen implements IRoute {
    // the main panel
    private JPanel mainPanel;
    // login button
    private JButton loginButton;
    // the register button
    private JButton registerButton;

    /**
     * @return the main panel of the page
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    /**
     * set up navigation button.
     */
    public MainScreen() {
        // login button
        loginButton.addActionListener((e) -> {
            GuiManager.getInstance().navigate("login");
        });
        // register button
        registerButton.addActionListener((e) -> {
            GuiManager.getInstance().navigate("register");
        });
    }
}
