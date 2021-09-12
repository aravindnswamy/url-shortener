package com.aravind.rest.dao;

import com.aravind.rest.models.Url;

/** An interface for abstracting the DataStore implementation.
 *
 * NOTE: In future, implement the same interface for connecting to any DB.
 *
 * @author arvind.n
 */
public interface IDataStore {

    /**
     * A method to add an entry to datastore.
     * @param key
     * @param value
     */
    void add(String clientId, String shortUrl, String longUrl);

    /**
     * A method to get an entry from datastore based on the key.
     * @param key
     *
     * TODO - Handle {@link NullPointerException}
     */
    void get(String key);

    Url get(String clientId, String longUrl);

    /**
     * A method to delete an entry from the datastore based on the key.
     * @param key
     */
    void delete(String key);

    /**
     * A method to check if the key is existing the data store or not.
     * @param key
     */
    public boolean checkKeyExists(String key);
}
