package backDemo.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ido
 * The main GUI frame
 */
public class Gui extends JFrame {

    /**
     * config the frame
     */
    public Gui() {
        // set the title
        super("Bank");
        // close program when window closes
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        // maximize the window
        setExtendedState(MAXIMIZED_BOTH);
        // make it full screen by default
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        // display the frame
        pack();
        setVisible(true);
        setLayout(null);
        // allow resizing
        setResizable(true);
    }
}
