package com.aravind.rest.services;

import com.aravind.rest.dao.IDataStore;
import com.aravind.rest.exceptions.BusinessException;
import com.aravind.rest.models.Url;
import com.aravind.rest.response.object.UrlShortenResponse;
import com.aravind.rest.utils.ErrorCode;
import com.aravind.rest.validators.ClientIdValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("MySqlDataStore")
    private IDataStore dataStore;

    @Autowired
    private ClientIdValidator clientIdValidator;

//    @Cacheable(value = "urlshortener", key = "#clientId")
    public UrlShortenResponse getShortenedUrl(String clientId, String longUrl) {
        clientIdValidator.validate(clientId);
        // Before shortening the given URL, check if the shortened URL for the given clientId exists in the DataStore.
        Url url = dataStore.get(clientId, longUrl);
        if (url == null) {
            String shortUrl = createShortUrl(clientId, longUrl);
            dataStore.add(clientId, shortUrl, longUrl);
            return buildUrlShortenResponse(clientId, longUrl, shortUrl);
        }
        return buildUrlShortenResponse(clientId, longUrl, url.getShortUrl());
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

    private UrlShortenResponse buildUrlShortenResponse(String clientId, String longUrl, String shortUrl) {
        UrlShortenResponse urlShortenResponse = new UrlShortenResponse();
        urlShortenResponse.setShortUrl(shortUrl);
        urlShortenResponse.setLongUrl(longUrl);
        urlShortenResponse.setClientId(clientId);
        return urlShortenResponse;
    }
}
