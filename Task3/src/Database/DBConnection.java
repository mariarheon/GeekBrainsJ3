package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/** Java 3. Lesson3. I/O
 *
 * @author Maria Mikhaleva
 * @version dated Apr 23 2018
 */

public class DBConnection {

    static final String DB_DRIVER = "org.postgresql.Driver";
    static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chat";
    static final String DB_USER = "postgres";
    static final String DB_PASSWORD = "student";
    private Connection dbConn;

    public DBConnection() {
        dbConn = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConn = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        exec("CREATE TABLE IF NOT EXISTS auth(id_login serial PRIMARY KEY, email varchar(200) NOT NULL, username varchar(50) NOT NULL, "
                + "password varchar(50) NOT NULL);");
    }

    public void exec(String s) {

        try {
            Statement st = dbConn.createStatement();
            st.execute(s);
            st.close();

        } catch (SQLException ex) {
            System.out.println("Error executing query");
        }

    }

    public boolean checkRes(String s) {
        ResultSet rs = null;
        try {
            Statement st = dbConn.createStatement();
            rs = st.executeQuery(s);
            if (rs.next())
              return true;
            rs.close();
            st.close();
            
        } catch (SQLException ex) {
            System.out.println("Error executing query");;
        }
        return false;
    }

}
