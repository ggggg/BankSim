package backDemo;

import backDemo.exceptions.InstanceAlreadyRunningException;
import backDemo.interfaces.IDatabaseHandler;
import backDemo.users.UserHandler;

public class Core {
    // the main program's instance
    private static Core instance;
    // the database
    private final IDatabaseHandler databaseHandler;
    // the user handler
    private final UserHandler userHandler;

    /**
     * starts the program
     * @param databaseHandler the database
     * @param userHandler the user handler
     * @throws InstanceAlreadyRunningException if the program is already running
     */
    public Core(IDatabaseHandler databaseHandler, UserHandler userHandler) throws InstanceAlreadyRunningException {
        // check if program is already running
        if (instance != null) {
            throw new InstanceAlreadyRunningException();
        }
        // set the variables
        instance = this;
        this.databaseHandler = databaseHandler;
        this.userHandler = userHandler;
    }

    /**
     * @return the database object
     */
    public IDatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    /**
     * @return the user handler
     */
    public UserHandler getUserHandler() {
        return userHandler;
    }
}
