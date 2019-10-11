import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

/**
 * Created by rdw1995 on 9/12/16.
 */
public class Person {
    String name;
    int age;
    boolean isAlive;
    static int minimumAge = 18;

    Person () {

    }

    Person(String newName, int newAge, boolean newIsAlive) {
        setName(newName);
        setAge(newAge);
        setIsAlive(newIsAlive);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isAlive=" + isAlive +
                '}';
    }
}