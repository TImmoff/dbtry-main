
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Chat {
    Connection con = null;
    Statement stmt = null;

    public Chat() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/chat", "postgres", "vodolaz9");
            stmt = con.createStatement();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void updVer(int id, int vers) {
        try {
            stmt.executeQuery("INSERT INTO version(ver) VALUES (\'" + id + "\',\'" + vers + "\')");
        } catch (SQLException e) {
            System.out.println("e:" + e.getMessage());
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void showVer() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM version order by id limit 1");
            while (rs.next()) {

                System.out.println(rs.getMetaData().getColumnName(2) + "--- " + rs.getInt("ver"));
            }
        } catch (SQLException e) {
            System.out.println("e:" + e.getMessage());
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void sayName(String userName) {
        try {
            stmt.executeQuery("INSERT INTO messages( mess,us) VALUES ( 'writing......',\'" + userName + "\')");
        } catch (SQLException e) {
            System.out.println("e:" + e.getMessage());
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void sayMessage(String text) {
        try {
            stmt.executeQuery(
                    "update messages SET mess=\'" + text + "\' WHERE id=(select max(id) from messages) returning *;");
        } catch (SQLException e) {
            System.out.println("e:" + e.getMessage());
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void delete(String text) {
        try {
            stmt.executeQuery("delete from messages where mess=\'" + text + "\'");
        } catch (SQLException e) {
            System.out.println("e:" + e.getMessage());
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void showAll() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM messages");
            while (rs.next()) {

                System.out.println(rs.getMetaData().getColumnName(2) + "[" + rs.getString("id") + "]:"
                        + rs.getString("us") + "---" + rs.getString("mess"));
            }
        } catch (SQLException e) {
            System.out.println("e:" + e.getMessage());
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void showUsersMess(String userName) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM messages where us=\'" + userName + "\'");
            while (rs.next()) {

                System.out.println(rs.getString("us") + "---" + rs.getString("mess"));
            }
        } catch (SQLException e) {
            System.out.println("e:" + e.getMessage());
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void closeConn() throws SQLException {
        stmt.close();
        con.close();
    }
}
