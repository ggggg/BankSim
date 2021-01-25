package backDemo;

import backDemo.database.DatabaseHandler;
import backDemo.exceptions.InstanceAlreadyRunningException;
import backDemo.gui.GuiManager;
import backDemo.interfaces.IDatabaseHandler;
import backDemo.users.UserHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Start the program
 */
public class BankDemoDriver {
    /**
     * @param args command line args
     * @throws InstanceAlreadyRunningException if the program already running
     * @throws IOException                     could not write needed files
     */
    public static void main(String[] args) throws InstanceAlreadyRunningException, IOException {
        // check if profiles folder+default profile picture exist
        if (!Files.exists(Paths.get("profiles\\default.png"))) {
            // check if the folder exists
            File directory = new File("profiles");
            if (!directory.exists()) {
                // if doesnt exist create it.
                directory.mkdir();
            }

            // get the default profile picture and copy it to a file
            try (InputStream in = new URL("https://www.iconfinder.com/icons/211677/download/png/512").openStream()) {
                Files.copy(in, Paths.get("profiles\\default.png"));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        // check if the database url exists
        if (!Files.exists(Paths.get("database_url"))) {
            System.err.println("Missing \"database_url\" file in root dir, please create it and input a valid mongodb connection string.");
            System.exit(1);
            return;
        }
        // try connect to the database
        IDatabaseHandler databaseHandler;
        try {
            databaseHandler = new DatabaseHandler(Files.readAllLines(Paths.get("database_url")).get(0));
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            // if connection url isnt valid print error
            System.err.println("Mongodb connection string isn't valid please enter a valid connection string and try again.");
            System.exit(1);
            return;
        }
        // start the program
        Core core = new Core(databaseHandler, new UserHandler(databaseHandler));
        // start the GUI
        GuiManager guiManager = new GuiManager(core);
    }
}
