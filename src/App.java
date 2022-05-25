import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner ott = new Scanner(System.in);
        String usname, mes, usn;
        Chat chat = new Chat();
        System.out.println("Next step? 0-Show ALl 1-name 2-add/correct message 3-delete message 4-search");
        while (ott.hasNextInt()) {
            int a = ott.nextInt();
            switch (a) {
                case (0): {
                    chat.showAll();
                    break;
                }
                case (1): {
                    ott.nextLine();
                    System.out.println("Your username:");
                    usname = ott.next();
                    System.out.println(usname);
                    chat.sayName(usname);
                    break;
                }
                case (2): {
                    ott.nextLine();
                    System.out.println("Write your Message:");
                    mes = ott.nextLine();
                    System.out.println(mes);
                    chat.sayMessage(mes);
                    break;
                }
                case (3): {
                    ott.nextLine();
                    System.out.println("Write message to delete");
                    mes = ott.nextLine();
                    System.out.println(mes);
                    chat.delete(mes);
                    break;
                }
                case (4): {
                    ott.nextLine();
                    System.out.println("Write which message you want to see-");
                    usn = ott.nextLine();
                    System.out.println(usn);
                    chat.showUsersMess(usn);
                    break;
                }
            }
        }

        /*
         * chat.showAll();
         */
        ott.close();
        chat.closeConn();
    }
}