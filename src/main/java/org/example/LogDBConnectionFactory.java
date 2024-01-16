package org.example;

import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class LogDBConnectionFactory {
    private static interface Singleton {

        final LogDBConnectionFactory INSTANCE = new LogDBConnectionFactory();
    }
    private final DataSource dataSource;

    private LogDBConnectionFactory() {

        Properties properties = new Properties();
        properties.setProperty("user", "user");
        properties.setProperty("password", "password"); // or get properties from some configuration file
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:oracle:thin:@localhost:1521:orcl", properties);

        PoolingDataSource ds = null;

        PoolableConnectionFactory poolableCF = new PoolableConnectionFactory(connectionFactory, null);
        poolableCF.setValidationQuery("select 1 from dual");
        GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool(poolableCF);
        poolableCF.setPool(connectionPool);
        this.dataSource = new PoolingDataSource(connectionPool);
    }

    public static Connection getConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}