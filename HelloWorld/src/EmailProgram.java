import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class EmailProgram {
    public static void main(String[] args) {
        HashMap<String, User> users = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name:");
            String name = scanner.nextLine();

            User user = users.get(name);
            if (user == null) {
                user = new User();
                user.name = name;
                users.put(name, user);
            }

            System.out.println("1. New Email");
            System.out.println("2. List Emails");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.println("Type Email Destination");
                String dest = scanner.nextLine();
                System.out.println("Type body");
                String body = scanner.nextLine();
                Email e = new Email(dest, "", body, false);
                user.emails.add(e);
            }
            else if (option.equals("2")) {
                for (Email e : user.emails){
                    System.out.println(e.destination + " " + e.body);
                }
            }
            else {
                System.out.println("NO, absoloutley not.");
            }
        }
    }
}
