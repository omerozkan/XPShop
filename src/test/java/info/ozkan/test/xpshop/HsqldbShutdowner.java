package info.ozkan.test.xpshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HsqldbShutdowner {

    /**
     * HSQL Veritabanını sonlandırır. Maven için gereklidir.
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String url = "jdbc:hsqldb:hsql://" + args[0];
            String sql = "SHUTDOWN";
            Connection connection = DriverManager.getConnection(url, "sa", "");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
