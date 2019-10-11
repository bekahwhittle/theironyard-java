import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by rdw1995 on 9/17/16.
 */
public class ATM {


    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<String, Customer> customers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        customers.put("Jim23", new Customer("Jim Davis", "Jim123", 100.0));
        customers.put("Asmith10", new Customer("Alice Smith", "Cows58", 300.0));
        customers.put("JD79", new Customer("David John", "Fruits&V", 150.0));

        System.out.println("Welcome to the ATM!");


        boolean keepRunning = true;
        while (keepRunning) {

            System.out.println("Would you like to: A) Log In or B) Create Account");
            String option = scanner.nextLine();

            if (option.equalsIgnoreCase("A")) {
                System.out.println("What is your username?");
                String username = scanner.nextLine();
                if (customers.containsKey(username)) {
                    System.out.printf("Welcome back %s!\n", username);
                    System.out.println("What is your password " + username + "?");
                    String realPassword = customers.get(username).password;
                    String password = scanner.nextLine();
                    if (password.equals(realPassword)) {

                        boolean isVerified = true;
                        while (isVerified) {

                            System.out.printf("What can I do for ya, %s?\n", customers.get(username).name, "!");
                            System.out.println("1. Option Menu 2. Delete Account 3. Log Out");
                            String decision = scanner.nextLine();

                            switch (decision) {
                                case ("1"):
                                    Customer customer1 = customers.get(username);
                                    customer1.chooseOption();
                                    break;
                                case ("2"):
                                    System.out.println("Are you sure . . . ? 1. Fine, I'll Stay! 2. YES GET ME OUT! ");
                                    String choose = scanner.nextLine();
                                    if (choose.equals("2")) {
                                        customers.remove(username);
                                    }
                                    break;
                                case ("3"):
                                    isVerified = false;
                                    break;
                                default:
                                    System.out.println("I'm sorry, please try again!");
                            }
                        }
                    }
                    else {
                        System.out.println("I didn't recognize that! Please try again or Create Account!");
                    }
                }
                else {
                    System.out.println("I didn't recognize that! Please try again or Create Account!");
                }
            }
            else if (option.equalsIgnoreCase("B")) {
                System.out.println("It looks like you're new!");
                Customer customer = new Customer();
                System.out.println("What would you like your username to be?");
                String newUsername = scanner.nextLine();
                customer.choosePassword();
                customer.chooseName();
                customers.put(newUsername, customer);
            }
            else {
                System.out.println("I'm sorry, I didn't recognize that! Please try again!");
            }
        }
    }
}


