package com.aravind.rest.validators;

import com.aravind.rest.dao.IDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Validator class for ensuring the clientId exists in the system or not.
 *
 * @author arvind.n
 */
@Component
public class ClientIdValidator {

    @Autowired
    private IDataStore dataStore;

    public void validate(String clientId) {
        dataStore.checkKeyExists(clientId);
    }
}
