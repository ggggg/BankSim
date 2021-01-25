package backDemo.database;

import backDemo.interfaces.IDatabaseHandler;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author Ido
 * Handle all database operations.
 */
public class DatabaseHandler implements IDatabaseHandler {
    // the name of the database.
    private static final String databaseName = "main";
    // the mongodb client.
    private final MongoClient client;
    // the mongodb database.
    private final MongoDatabase database;

    /**
     * Handle all database operations.
     *
     * @param connectionString the connection string required to connect to the database server.
     */
    public DatabaseHandler(String connectionString) {
        // enable pojo
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        // build connection string and other settings.
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString)).codecRegistry(codecRegistry).build();
        // create the client.
        client = MongoClients.create(clientSettings);
        // get the database.
        database = client.getDatabase(databaseName);
    }

    /**
     * Get a typed collection from the database.
     *
     * @param name name of the collection to be retrieved from the database.
     * @param type the type associated with said collection.
     * @param <T>  the type associated with said collection.
     * @return the collection.
     */
    @Override
    public <T> MongoCollection<T> getCollection(String name, Class<T> type) {
        return database.getCollection(name, type);
    }

    /**
     * Get a raw typed collection from the database.
     *
     * @param name name of the collection to be retrieved from the database.
     * @return collection.
     */
    @Override
    public MongoCollection<?> getCollection(String name) {
        return database.getCollection(name);
    }


    /**
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "DatabaseHandler{" +
                "client=" + client +
                ", database=" + database +
                '}';
    }
}
