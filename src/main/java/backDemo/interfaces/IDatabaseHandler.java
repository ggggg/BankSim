package backDemo.interfaces;

import com.mongodb.client.MongoCollection;

/**
 * @author Ido
 * Handle all database operations.
 */
public interface IDatabaseHandler {
    /**
     * Get a typed collection from the database.
     *
     * @param name name of the collection to be retrieved from the database.
     * @param type the type associated with said collection.
     * @param <T>  the type associated with said collection.
     * @return the collection.
     */
    <T> MongoCollection<T> getCollection(String name, Class<T> type);

    /**
     * Get a raw typed collection from the database.
     *
     * @param name name of the collection to be retrieved from the database.
     * @return collection.
     */
    MongoCollection<?> getCollection(String name);
}
