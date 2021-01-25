package backDemo.gui;

import backDemo.Core;
import backDemo.gui.account.HomeScreen;
import backDemo.gui.account.MakeTransaction;
import backDemo.gui.login.LoginScreen;
import backDemo.gui.login.MainScreen;
import backDemo.gui.login.RegisterScreen;
import backDemo.users.User;
import io.github.ggggg.swingNavigator.Navigator;
import io.github.ggggg.swingNavigator.exceptions.RouteNotFoundException;

/**
 * @author Ido
 * Class to manage frontend operations
 */
public class GuiManager {
    // the main instance of this class
    private static GuiManager instance;
    // the logged in user
    private User user;
    // the core object
    private final Core core;
    // the main navigation object in charge of screen change
    private Navigator router;
    // the Gui frame
    private final Gui gui;

    /**
     * Init all the variables.
     *
     * @param core the core object of this program
     */
    public GuiManager(Core core) {
        instance = this;
        this.core = core;
        // start the frame
        gui = new Gui();
        // set up all the pages
        populateRouter();
        // go to the main page
        goHome();
    }

    /**
     * @return whether the user is logged in
     */
    public boolean isLoggedIn() {
        return user != null;
    }

    /**
     * @param user set the user object
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Set the user and go to the appropriate page
     *
     * @param user the new user object
     */
    public void login(User user) {
        setUser(user);
        goHome();
    }

    /**
     * Go to home page or user page (depending if the user is logged in or not)
     */
    public void goHome() {
        navigate(isLoggedIn() ? "home" : "main");
    }

    /**
     * update the user object and refresh current page
     *
     * @param user the new user
     */
    public void updateUser(User user) {
        setUser(user);
        refresh();
    }

    /**
     * @return the logged in user
     */
    public User getUser() {
        return user;
    }

    /**
     * Refresh current page
     */
    public void refresh() {
        navigate(router.getHistory().peek());
    }

    /**
     * Add all the pages to the router
     */
    public void populateRouter() {
        router = new Navigator(gui);
        // 404 page
        router.addRoute("404", PageNotFoundScreen.class);
        // main screen
        router.addRoute("main", MainScreen.class);
        // login screen
        router.addRoute("login", LoginScreen.class);
        // register screen
        router.addRoute("register", RegisterScreen.class);
        // home screen
        router.addRoute("home", HomeScreen.class);
        // make a transaction screen
        router.addRoute("transaction", MakeTransaction.class);
        // see the window title each time
        router.beforeEach((panel, args, route) -> {
            gui.setTitle("Bank - " + panel);
        });
    }

    /**
     * @return the navigation object
     */
    public Navigator getRouter() {
        return router;
    }

    /**
     * @param path the path to navigate to.
     */
    public void navigate(String path) {
        try {
            router.navigate(path);
        } catch (RouteNotFoundException e) {
            navigate("404");
        }
    }

    /**
     * @return get the code object
     */
    public Core getCore() {
        return core;
    }

    /**
     * @return the current instance
     */
    public static GuiManager getInstance() {
        return instance;
    }
}
