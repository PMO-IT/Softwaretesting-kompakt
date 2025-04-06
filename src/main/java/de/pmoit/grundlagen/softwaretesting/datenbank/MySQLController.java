package de.pmoit.grundlagen.softwaretesting.datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySQLController {

    private Connection connection;

    public MySQLController(String username, String password, String server, int port, String datenbank) throws SQLException {
        String connectionUrl = "jdbc:mysql://" + server + ":" + port + "/" + datenbank;
        connection = DriverManager.getConnection(connectionUrl, username, password);
    }

    public ResultSet select(String selectSql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(selectSql);
        return stmt.executeQuery();
    }

    public int executeSql(String sql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeUpdate(sql);
    }
}
