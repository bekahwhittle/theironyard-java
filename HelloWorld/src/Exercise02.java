import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class Exercise02 {
    public static void main(String[] args) {
       Email [] emails = {
               new Email("alice@gmail.com", "MY CAT PUKED", "want pizza?", false),
               new Email("bob@gmail.com", "MY CAT PUKED", "want pizza?", false),
               new Email("alice@gmail.com", "MY CAT PUKED", "want pizza?", false),
               new Email("bob@gmail.com", "MY CAT PUKED", "want pizza?", false),
               new Email("alice@gmail.com", "MY CAT PUKED", "want pizza?", false),
               new Email("charlie@gmail.com", "MY CAT PUKED", "want pizza?", false),
        };
        HashMap <String, ArrayList<Email>> emailMap = new HashMap<>();

        for (Email m : emails) {
            ArrayList<Email> list =  emailMap.get(m.destination);
            if (list == null) {
                list = new ArrayList<>();
                emailMap.put(m.destination, list);
            }
            list.add(m);
        }
        System.out.println(emailMap);
    }
}
