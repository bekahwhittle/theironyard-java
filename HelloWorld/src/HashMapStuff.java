import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by rdw1995 on 9/16/16.
 */

//public class HashMapStuff {
//    static HashMap<String, Person> users = new HashMap<>();
//
//    public static void main(String[] args) {
//        users.put("Alice", new Person("Alice", 30, true));
//
//
//    }
public class HashMapStuff {
    static HashMap<String, Person> users = new HashMap<>();

    public static void main(String[] args) {
        users.put("Alice1", new Person("Alice", 30, true));
        users.put("Bob2", new Person("Bob", 40, true));

        Scanner scanner = new Scanner(System.in);

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("Enter username:");
            String userName = scanner.nextLine();

            if (!users.containsKey(userName)) {
                Person p = new Person(userName, 20, true);
                users.put(userName, p);
            }

            Person person = users.get(userName);

            System.out.println("Logged in as: " + userName);
        }
    }
}