package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
    public static String getUrl() 
    {
        return String.format
        (
            "jdbc:%s://%s:%s/%s",
            Config.get("DB_CONNECTION"),
            Config.get("DB_HOST"),
            Config.get("DB_PORT"),
            Config.get("DB_DATABASE")
        );
    }

    public static String getUser() 
    {
        return Config.get("DB_USERNAME");
    }

    public static String getPassword() 
    {
        return Config.get("DB_PASSWORD");
    }

    public static Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(getUrl(), getUser(), getPassword());
    }
}