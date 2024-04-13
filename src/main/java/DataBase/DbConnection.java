package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String DB_URL_ENV = "DB_URL"; // Environment variable for the database URL
    private static final String DB_USER_ENV = "DB_USER"; // Environment variable for the database username
    private static final String DB_PASSWORD_ENV = "DB_PASSWORD"; // Environment variable for the database password

    /**
     * Returns a Connection object to the database.
     * @return Connection to the database.
     * @throws SQLException if a database access error occurs or the url is null.
     */
    public static Connection getConnection() throws SQLException {
        String dbUrl = System.getenv(DB_URL_ENV);
        String dbUser = System.getenv(DB_USER_ENV);
        String dbPassword = System.getenv(DB_PASSWORD_ENV);

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            throw new IllegalStateException("Database connection environment variables are not properly set.");
        }

        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
