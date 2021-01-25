package backDemo.users;

import backDemo.exceptions.UserExistsException;
import backDemo.exceptions.UserNotFoundException;
import backDemo.exceptions.WrongPasswordException;
import backDemo.interfaces.IDatabaseHandler;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

/**
 * Handle user based database operations.
 */
public class UserHandler {
    // default profile location.
    public static String DEFAULT_PROFILE = "profiles\\default.png";
    // the user collection.
    private final MongoCollection<User> collection;

    /**
     * Handle user based database operations.
     *
     * @param db the database that is used to retrieve users.
     */
    public UserHandler(IDatabaseHandler db) {
        // get the collection
        assert db != null;
        collection = Objects.requireNonNull(db.getCollection("users", User.class), "User collection not found!");
    }

    /**
     * @return the users collection.
     */
    public MongoCollection<User> getCollection() {
        return collection;
    }

    /**
     * @param username the username to search for.
     * @return the user object, null if no user found.
     */
    public User getUser(String username) {
        return collection.find(eq("username", username)).first();
    }

    /**
     * Hash a password.
     *
     * @param password the password to be hashed.
     * @return the hashed password.
     */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check if a password is correct.
     *
     * @param password the password to be checked.
     * @param hash     the hashed password.
     * @return weather the passwords are equal.
     */
    private boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    /**
     * Login a user.
     *
     * @param username the user to be logged in.
     * @param password the password of said user.
     * @return the user object (assuming successful login).
     * @throws UserNotFoundException  in case no user is found.
     * @throws WrongPasswordException in case user is found but password is incorrect.
     */
    public User login(String username, String password) throws UserNotFoundException, WrongPasswordException {
        final User user = getUser(username);
        if (user == null) throw new UserNotFoundException();
        if (!checkPassword(password, user.getPassword())) throw new WrongPasswordException();
        return user;
    }

    /**
     * Create a new user.
     *
     * @param username the username of the new user.
     * @param password the new password of the new user.
     * @param type     the type of the account.
     * @return the new user object.
     * @throws UserExistsException in case of duplicate user.
     */
    public User register(String username, String password, AccountType type) throws UserExistsException {
        // check nothing is null
        assert username != null;
        assert password != null;
        assert type != null;

        // check if user exists
        if (getUser(username) != null) throw new UserExistsException();

        // create new user
        User user = new User();
        user.setPassword(hashPassword(password));
        user.setUsername(username);
        user.setAccountType(type);
        user.setBalance(0);
        // insert to db and get the newly created id
        user.setId(Objects.requireNonNull(collection.insertOne(user).getInsertedId()).asObjectId().getValue());

        // return new user
        return user;
    }

    /**
     * Save the user to the database.
     *
     * @param user user
     * @return the new user object
     */
    public User update(User user) {
        return collection.findOneAndReplace(eq("username", user.getUsername()),
                user, new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER));
    }
}
