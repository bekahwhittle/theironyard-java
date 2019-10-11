import java.util.Scanner;
import java.util.HashMap;



/**
 * Created by rdw1995 on 9/14/16.
 */
public class Customer {
    String name;
    Double balance = 100.0;

    Customer (String name, Double balance) {
         }

    public Customer() {
        }


    void chooseName() {
        System.out.println("WAASSUP? Before I show you the money give me your username:");
        String userName = ATM.scanner.nextLine();
        System.out.println("Aight " + userName + " what's your password?");
        String password = ATM.scanner.nextLine();
        System.out.println("Cool, bro, welcome back!");
//        System.out.println(userName + ", prove yo self and give me you password:");
//            if (){
//
//            }
//        String password = ATM.scanner.nextLine();
//            if (){
//
//            }
//        System.out.println("Cool cool, now we can get this party started brah!");

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

            }   else {
                balance = balance - numAmount;
                System.out.println("here's your "+ numAmount + " dollars "
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
