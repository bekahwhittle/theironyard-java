/**
 * Created by rdw1995 on 9/20/16.
 */
public class Character {
    String name;
    int health;
    int damage;

    public void battle(Character enemy) {
        System.out.printf("Shit, it's %s!\n", enemy.name);

        while (this.health > 0 && enemy.health > 0) {
            this.health -= enemy.damage;
            enemy.health -= this.damage;
            System.out.printf("%s's health: %s\n", this.name, this.health);
            System.out.printf("%s's health: %s\n", enemy.name, enemy.health);
        }

        if (this.health <= 0) {
            System.out.printf("%s is nighty nighty if ya know what I mean\n", this.name);
        }
        if (enemy.health <= 0) {
            System.out.printf("%s is gonna sleep forevs\n", enemy.name);
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
}
