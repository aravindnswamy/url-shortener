package com.aravind.rest.dao;

import com.aravind.rest.exceptions.BusinessException;
import com.aravind.rest.models.Url;
import com.aravind.rest.utils.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A Class that acts as a in memory database.
 * This class internally uses {@link ConcurrentHashMap} for storing the data.
 *
 * @author arvind.n
 */
@Component
@Qualifier("InMemoryDataStore")
public class InMemoryDataStore implements IDataStore {

    Logger LOGGER = LoggerFactory.getLogger(InMemoryDataStore.class);

    private Map<String, String> dataStore = new ConcurrentHashMap();

    @Override
    public void add(String clientId, String shortUrl, String longUrl) {
        dataStore.put(clientId, shortUrl);
    }

    @Override
    public void get(String key) {
        dataStore.get(key);
    }

    @Override
    public Url get(String clientId, String longUrl) {
        return null;
    }

    @Override
    public void delete(String key) {
        dataStore.remove(key);
    }

    @Override
    public boolean checkKeyExists(String key) {
        try {
            return dataStore.containsKey(key);
        } catch(NullPointerException ex) {
            LOGGER.error("Given key doesn't exist in the datastore");
            throw new BusinessException(ErrorCode.ERR001, "Given key doesn't exist in the datastore", ex);
        }
    }
}
