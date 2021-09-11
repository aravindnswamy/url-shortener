package com.aravind.rest.services;

import com.aravind.rest.dao.IDataStore;
import com.aravind.rest.exceptions.BusinessException;
import com.aravind.rest.utils.ErrorCode;
import com.aravind.rest.validators.ClientIdValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A Service class that contains the endpoint to shorten a given URL.
 *
 * @author aravind.n
 */
@Service
public class UrlShorteningService {

    Logger LOGGER = LoggerFactory.getLogger(UrlShorteningService.class);

    @Autowired
    private IDataStore dataStore;

    @Autowired
    private ClientIdValidator clientIdValidator;

//    @Cacheable(value = "urlshortener", key = "#clientId")
    public String getShortenedUrl(String clientId, String longUrl) {
        //Hardcoding few entries.
        dataStore.add("1", "https://www.somedomain.com");
        dataStore.add("1", "https://www.somedomain1.com");
        dataStore.add("2", "https://www.somedomain.com");
        dataStore.add("2", "https://www.somedomain2.com");
        dataStore.add("3", "https://www.somedomain3.com");

        clientIdValidator.validate(clientId);
        String shortUrl = createShortUrl(clientId, longUrl);
        dataStore.add(clientId, shortUrl);
        return shortUrl;
    }

    private String createShortUrl(String clientId, String longUrl) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            String message = clientId + longUrl;
            byte[] shortUrl = messageDigest.digest(message.getBytes(StandardCharsets.UTF_8));
            return Base64Utils.encodeToString(shortUrl);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("Given algorithm doesn't exists");
            throw new BusinessException(ErrorCode.ERR001, "Given algorithm doesn't exists", ex);
        }
    }
}
