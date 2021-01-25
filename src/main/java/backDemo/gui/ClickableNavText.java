package backDemo.gui;

import java.awt.event.MouseAdapter;

/**
 * Make the text clickable (navigation)
 */
public class ClickableNavText extends MouseAdapter {
    private final String path;

    /**
     * navigate to path
     * @param evt default param
     */
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        GuiManager.getInstance().navigate(path);
    }

    /**
     * @param path the path that when text is clicked navigate to.
     */
    public ClickableNavText(String path){
        this.path = path;
    }
}