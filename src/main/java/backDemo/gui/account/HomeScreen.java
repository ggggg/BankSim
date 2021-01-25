package backDemo.gui.account;

import backDemo.gui.GuiManager;
import backDemo.users.User;
import backDemo.users.UserHandler;
import io.github.ggggg.swingNavigator.interfaces.IRoute;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * The home screen.
 */
public class HomeScreen implements IRoute {

    // welcome text
    private JLabel welcomeText;
    // the main panel that contains everything.
    private JPanel panel;
    private JButton profile;
    private JButton makeATransactionButton;
    private JButton logoutButton;

    public HomeScreen() {
        // get the user
        User user = GuiManager.getInstance().getUser();
        // check if the account is overdrawn
        boolean overDrawn = user.getBalance() < 0;
        // set the welcome message
        welcomeText.setText("<html>Welcome " + user.getUsername()
                + "!<br/>Your Balance is " + (overDrawn ? "<font color='red'>" : "") +
                new DecimalFormat("$###,###,###.##").format(user.getBalance()) +
                (overDrawn ? "</font>" : "") + "</html>");

        // style profile picture button
        profile.setOpaque(false);
        profile.setContentAreaFilled(false);
        profile.setBorderPainted(false);
        // get the profile picture
        ImageIcon icon = readPfp(user.getProfile() != null ? "profiles\\" + user.getProfile() : UserHandler.DEFAULT_PROFILE);
        // set the profile picture on the button
        profile.setIcon(icon == null ? readPfp(UserHandler.DEFAULT_PROFILE) : icon);
        // let user upload files.
        profile.addActionListener(event -> {
            // choose files
            JFileChooser chooser = new JFileChooser();
            // only allow images
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            // set title
            chooser.setDialogTitle("Choose profile picture:");
            // make sure user didn't cancel
            if (chooser.showOpenDialog(panel) != JFileChooser.APPROVE_OPTION) return;
            // set a random file name (to avoid duplicate file names)
            String extension = "";
            String fileName = chooser.getSelectedFile().getName();
            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i + 1);
            }
            String newFileName = UUID.randomUUID() + "." + extension;

            // copy the file to the root folder
            try {
                Files.copy(Paths.get(chooser.getSelectedFile().getPath()), Paths.get("profiles\\" + newFileName));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            // update the user object
            user.setProfile(newFileName);
            GuiManager.getInstance().updateUser(GuiManager.getInstance().getCore().getUserHandler().update(user));
        });
        // logout
        logoutButton.addActionListener(e -> GuiManager.getInstance().login(null));
        // make a transaction page
        makeATransactionButton.addActionListener(e -> GuiManager.getInstance().navigate("transaction"));
    }

    /**
     * read a picture from a path.
     *
     * @param path the path of the image to read
     * @return the image as an ImageIcon
     */
    private ImageIcon readPfp(String path) {
        BufferedImage buttonIcon;
        try {
            // red the image
            buttonIcon = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (buttonIcon == null) return null;
        // crop and resize the image.
        int length = Math.min(buttonIcon.getWidth(), buttonIcon.getHeight());
        return new ImageIcon(new ImageIcon(buttonIcon.getSubimage(0, 0, length, length)).getImage()
                .getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH));
    }

    /**
     * @return the main panel
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }


    /**
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "HomeScreen{" +
                "welcomeText=" + welcomeText +
                ", panel=" + panel +
                ", profile=" + profile +
                ", makeATransactionButton=" + makeATransactionButton +
                ", logoutButton=" + logoutButton +
                '}';
    }
}
