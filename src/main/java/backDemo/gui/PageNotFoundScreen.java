package backDemo.gui;

import io.github.ggggg.swingNavigator.interfaces.IRoute;

import javax.swing.*;

/**
 * A page that shows when the requested page isn't found.
 */
public class PageNotFoundScreen implements IRoute {
    // the main panel object
    private JPanel panel;

    /**
     * @return get the main panel.
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }
}
