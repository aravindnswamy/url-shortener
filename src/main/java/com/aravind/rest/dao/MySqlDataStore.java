package com.aravind.rest.dao;

import com.aravind.rest.models.Url;
import com.aravind.rest.services.UrlShorteningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * A Class that defines a datastore for MySQL DB.
 *
 * @author arvind.n
 */
@Repository
@Qualifier("MySqlDataStore")
public class MySqlDataStore implements IDataStore {

    Logger LOGGER = LoggerFactory.getLogger(MySqlDataStore.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void add(String clientId, String shortUrl, String longUrl) {
        String INSERT_QUERY = "INSERT INTO URL(CLIENT_ID, LONG_URL, SHORT_URL, CREATE_DATETIME) VALUES(?, ?, ?, ?)";
        LocalDateTime dateTime = ZonedDateTime.now().toLocalDateTime();
        LOGGER.info(INSERT_QUERY);
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
            pst.setString(1, clientId);
            pst.setString(2, longUrl);
            pst.setString(3, shortUrl);
            pst.setString(4, dateTime.toString());
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void get(String clientId) {
        String GET_QUERY = "SELECT * FROM URL WHERE CLIENT_ID = ? AND LONG_URL = ?";
//        try {
//            Connection con = dataSource.getConnection();
//            PreparedStatement pst = con.prepareStatement(GET_QUERY);
//            pst.setString(1, clientId);
//            pst.setString(2, longUrl);
//            pst.setString(3, shortUrl);
//            pst.setString(4, dateTime.toString());
//            LOGGER.info(GET_QUERY);
//            pst.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    public Url get(String clientId, String longUrl) {
        String GET_QUERY = "SELECT * FROM URL WHERE CLIENT_ID = ? AND LONG_URL = ?";
        Url url = null;
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(GET_QUERY);
            pst.setString(1, clientId);
            pst.setString(2, longUrl);
            LOGGER.info(GET_QUERY);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet != null && resultSet.next()) {
                url = new Url();
                url.setId(resultSet.getString("ID"));
                url.setShortUrl(resultSet.getString("SHORT_URL"));
                url.setLongUrl(longUrl);
                url.setClientId(clientId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return url;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public boolean checkKeyExists(String key) {
        return false;
    }
}
