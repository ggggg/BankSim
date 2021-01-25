package backDemo.users;

import org.bson.types.ObjectId;

import java.util.Objects;

/**
 * @author Ido
 * The user object as stored in the database
 */
public class User {
    // the id of the user
    private ObjectId id;
    // the username of the user
    private String username;
    // the account type
    private AccountType accountType;
    // the user's password
    private String password;
    // the profile picture path
    private String profile;
    // the user's balance
    private double balance;

    /**
     * @return the account type
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @return the user's id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the user's profile picture path
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @return the user's balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param accountType the new account type
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * @param id the user's new id
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * @param username the user's new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password new user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param balance the new user's balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @param profile the new profile picture's path
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", accountType=" + accountType +
                '}';
    }

    /**
     * check if an object is equal to this user
     * @param o the object
     * @return is the user equal to the object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0 &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                accountType == user.accountType &&
                Objects.equals(password, user.password) &&
                Objects.equals(profile, user.profile);
    }

    /**
     * @return hash code representation of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, accountType, password, profile, balance);
    }
}
