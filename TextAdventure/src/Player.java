import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rdw1995 on 9/14/16.
 */
public class Player extends Character {

    String weapon;
    String location;

    ArrayList<String> items = new ArrayList<>();

    public Player () {
        this.health = 20;
        this.damage = 20;
    }

    void chooseName(){
        System.out.println("What is your name?");

        name = Game.customLine();
        System.out.printf("Welcome, %s\n", name);
    }

    void chooseWeapon() {
        System.out.println("Do you want a sword or mace?");
        weapon = Game.customLine();

        if (weapon.equalsIgnoreCase("sword")) {
            System.out.println("Here is your sword!");
        } else if (weapon.equalsIgnoreCase("mace")) {
            System.out.println("Here is your mace!");
        } else {
//            throw new Exception("Invalid weapon fucker!");
            System.out.println("Weapon not found.");
            chooseWeapon();
        }
    }
        void chooseLocation() {

        System.out.println("Would you like to go to the tunnel or forest?");
        location = Game.customLine();

        if (location.equalsIgnoreCase("tunnel")) {
            System.out.println("Entering tunnel. . . ");
        }
        else if (location.equalsIgnoreCase("forest")) {
            System.out.printf("Entering forest. . .");
        }
        else{
//            throw new Exception("Invalid location brah!");
            System.out.println("brah you trippin");
            chooseLocation();
        }
    }

    void findItem(String item) {
        System.out.println("You found a " + item + "! Press y to pick up.");
        String answer = Game.customLine();
            if (answer.equalsIgnoreCase("y")){
                items.add(item);
                System.out.println("Look at you - ya did it!!");
            }
    }

    public String getWeapon() {
        return weapon;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (weapon != null ? !weapon.equals(player.weapon) : player.weapon != null) return false;
        if (location != null ? !location.equals(player.location) : player.location != null) return false;
        return items != null ? items.equals(player.items) : player.items == null;

    }

    @Override
    public int hashCode() {
        int result = weapon != null ? weapon.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
