package com.csms.dal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    protected Connection connection;
    private static final Logger logger = LogManager.getLogger();

    public DBContext() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "database=CSMS;"
                    + "user=sa;"
                    + "password=0912217817;"
                    + "encrypt=false;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error("Error at DBContext", e);
        }
    }
}