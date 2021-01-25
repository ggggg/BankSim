package backDemo.gui.account;

import backDemo.gui.GuiManager;
import backDemo.users.User;
import io.github.ggggg.swingNavigator.interfaces.IRoute;

import javax.swing.*;

/**
 * Page to make a transaction.
 */
public class MakeTransaction implements IRoute {
    // the main panel.
    private JPanel panel;
    // allow deposits
    private JRadioButton depositButton;
    // allow withdraws
    private JRadioButton withdrawButton;
    // enter desired amount into said text field.
    private JTextField enterAmountTextField;
    // submit button
    private JButton submit;
    // output any errors
    private JLabel error;

    /**
     * @return the main panel
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Page to make a transaction.
     */
    public MakeTransaction() {
        // hide the error
        error.setVisible(false);
        // make the button clickable
        submit.addActionListener(e -> {
            // get the user
            User user = GuiManager.getInstance().getUser();
            // the value that the user inputs
            double value;
            try {
                // try formatting the value
                value = Double.parseDouble(enterAmountTextField.getText());
            } catch (NumberFormatException ex) {
                // display error
                error.setVisible(true);
                return;
            }
            // check if value is lower then 0
            if (value <= 0) {
                // display error
                error.setVisible(true);
                return;
            }
            // change account's balance
            if (depositButton.isSelected()) {
                user.setBalance(user.getBalance() + value);
            } else if (withdrawButton.isSelected()) {
                user.setBalance(user.getBalance() - value);
            } else return;
            // update the user object
            GuiManager.getInstance().updateUser(GuiManager.getInstance().getCore().getUserHandler().update(user));
            GuiManager.getInstance().navigate("home");
        });
    }

    /**
     * let people press enter to submit.
     */
    @Override
    public void onStarted() {
        SwingUtilities
                .getRootPane(submit)
                .setDefaultButton(submit);
    }


    /**
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "MakeTransaction{" +
                "panel=" + panel +
                ", depositButton=" + depositButton +
                ", withdrawButton=" + withdrawButton +
                ", enterAmountTextField=" + enterAmountTextField +
                ", submit=" + submit +
                ", error=" + error +
                '}';
    }
}
