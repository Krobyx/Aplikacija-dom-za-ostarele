import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQL {

    private Connection connection;
    private String url = "jdbc:postgresql://balarama.db.elephantsql.com/qtojddgr";
    private String username = "qtojddgr";
    private String password = "yWDsDW9lZmTbbYzVNIv-EGX6tgks7BEb";

    public PostgreSQL() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
