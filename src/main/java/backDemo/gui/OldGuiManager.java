//package backDemo.gui;
//
//import backDemo.Core;
//import backDemo.gui.account.HomeScreen;
//import backDemo.gui.account.MakeTransaction;
//import backDemo.gui.login.LoginScreen;
//import backDemo.gui.login.MainScreen;
//import backDemo.gui.login.RegisterScreen;
//import backDemo.interfaces.GuiPanel;
//import backDemo.users.User;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.HashMap;
//
//public class OldGuiManager {
//    private static OldGuiManager instance;
//    private User user;
//    private final Core core;
//    private HashMap<String, Class<? extends GuiPanel>> router;
//    private String curPath;
//    private final Gui gui;
//
//    public OldGuiManager(Core core) {
//        instance = this;
//        this.core = core;
//        gui = new Gui();
//        populateRouter();
//        goHome();
//    }
//
//    public boolean isLoggedIn() {
//        return user != null;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void login(User user) {
//        setUser(user);
//        populateRouter();
//        goHome();
//    }
//
//    public void goHome() {
//        navigate(isLoggedIn() ? "home" : "main");
//    }
//
//    public void updateUser(User user) {
//        setUser(user);
//        refresh();
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void refresh() {
//        navigate(curPath);
//    }
//
//    public void populateRouter() {
//        router = new HashMap<>();
//        router.put("404", PageNotFoundScreen.class);
//        if (!isLoggedIn()) {
//            router.put("main", MainScreen.class);
//            router.put("login", LoginScreen.class);
//            router.put("register", RegisterScreen.class);
//        } else {
//            router.put("home", HomeScreen.class);
//            router.put("transaction", MakeTransaction.class);
//        }
//    }
//
//    public void navigate(String path) {
//        curPath = path;
//        if (gui.getContentPane() != null)
//            gui.getContentPane().setVisible(false);
//        GuiPanel panelInstance;
//        try {
//            panelInstance = router.get(path).newInstance();
//            JPanel panel = panelInstance.getPanel();
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            panel.setPreferredSize(screenSize);
//            gui.setContentPane(panel);
//        } catch (NullPointerException | InstantiationException | IllegalAccessException e) {
//            navigate("404");
//            return;
//        }
//        gui.getContentPane().setVisible(true);
//        gui.pack();
//        panelInstance.onStarted();
//    }
//
//    public static OldGuiManager getInstance() {
//        return instance;
//    }
//
//    public Core getCore() {
//        return core;
//    }
//}
