import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by rdw1995 on 9/14/16.
 */
public class ATM {

    public static HashMap<String, Customer> customers = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        customers.put("CollegeKid12", new Customer("Svedka", 60.90));
        customers.put("efBatman201", new Customer("Spidey", 80.20));
        customers.put("SteveHolt78", new Customer("Steve", 40.50));

//        Customer customer1 = customers.get("CollegeKid12");
//        System.out.println(customer1.name);

        System.out.println("You rich brah? Find out here at this ATM!");

        Customer customer = new Customer();

        boolean keepRunning = true;
        while (keepRunning) {
//            customer.chooseName();
            System.out.println("WAASSUP? Before I show you the money give me your username:");
            String userName = ATM.scanner.nextLine();
                if(!customers.containsKey(userName)) {
                    Customer customer1 = new Customer(userName, 100.0);
            }
//            Customer customer1 = customers.get(userName);
//            System.out.println(customer1.name);
            customer.chooseOption();
                System.out.println("Want to see if you still a broke ass? A: sure whatevs B: Nah brah");
                String answer = customLine();
                    if (answer.equalsIgnoreCase("B")) {
                        keepRunning = false;
                    }

        }


        System.out.println("BYE FELICIA!!");
    }

    static String customLine() {
        String line = scanner.nextLine();
        return line;
    }
}
