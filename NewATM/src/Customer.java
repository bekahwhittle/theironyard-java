import java.util.Scanner;

/**
 * Created by rdw1995 on 9/17/16.
 */
public class Customer {

    public static Scanner scanner = new Scanner(System.in);

    String name;
    String password;
    Double balance = 100.0;

    Customer(String name, String password, Double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public Customer() {
    }

    void chooseName() {
        System.out.println("What is your first and last name?");
        name = scanner.nextLine();
    }

    void choosePassword() {
        System.out.println("What would you like your password to be?");
        password = scanner.nextLine();
    }

    void chooseOption() throws Exception {
        System.out.println("Like do you wanna A: Withdrawal B: Check balance C: Cancel");
        String option = ATM.scanner.nextLine();
        if (option.equalsIgnoreCase("A")) {
            System.out.println(name + ", how much you need brah?");
            String amount = ATM.scanner.nextLine();
            double numAmount = Double.valueOf(amount);
            if (numAmount > balance) {
                throw new Exception(name + " MAKE SOME FUCKING MORE THEN COME BACK.");
            }
            else {
                balance = balance - numAmount;
                System.out.println("here's your " + numAmount + " dollars "
                        + name + ", now go the fuck home!");
            }
        }
        else if (option.equalsIgnoreCase("B")) {
            System.out.println("Your balance is " + balance);
        }
        else if (option.equalsIgnoreCase("C")) {
            System.out.println("Why would you come all the way here then cancel?!! WHAT AN IDIOT!!");
        }
        else {
            throw new Exception(name + ", YOU IGNORANT SLUT");
        }
    }
}
