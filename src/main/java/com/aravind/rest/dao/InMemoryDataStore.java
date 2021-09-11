package com.aravind.rest.dao;

import com.aravind.rest.exceptions.BusinessException;
import com.aravind.rest.utils.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class InMemoryDataStore implements IDataStore {

    Logger LOGGER = LoggerFactory.getLogger(InMemoryDataStore.class);

    private Map<String, String> dataStore = new ConcurrentHashMap();

    public void add(String key, String value) {
        dataStore.put(key, value);
    }

    public void get(String key) {
        dataStore.get(key);
    }

    public void delete(String key) {
        dataStore.remove(key);
    }

    public boolean checkKeyExists(String key) {
        try {
            return dataStore.containsKey(key);
        } catch(NullPointerException ex) {
            LOGGER.error("Given key doesn't exist in the datastore");
            throw new BusinessException(ErrorCode.ERR001, "Given key doesn't exist in the datastore", ex);
        }
    }
}
