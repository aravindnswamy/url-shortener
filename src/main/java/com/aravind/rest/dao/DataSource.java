package com.aravind.rest.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <Class Description>
 *
 * HikariConfig is the configuration class used to initialize a data source. I
 * t comes with four well-known, must-use parameters: username, password, jdbcUrl, and dataSourceClassName.
 *
 * Out of jdbcUrl and dataSourceClassName, we generally use one at a time.
 * However, when using this property with older drivers, we may need to set both properties.
 *
 * @author arvind.n
 */
@Component
public class DataSource {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${mysql.driver.cache.prep.stmt}")
    private String cachePrepStmts;

    @Value("${mysql.driver.prep.stmt.cache.size}")
    private String prepStmtCacheSize;

    @Value("${mysql.driver.prep.stmt.cache.sql.limit}")
    private String prepStmtCacheSqlLimit;

    private HikariConfig hikariConfig = new HikariConfig();
    private HikariDataSource hikariDataSource;

    @PostConstruct
    private void init() {
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("cachePrepStmts", cachePrepStmts);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", prepStmtCacheSqlLimit);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
