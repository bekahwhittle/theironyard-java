import java.util.ArrayList;

/**
 * Created by rdw1995 on 9/22/16.
 */
public class PeopleWrapper {
    ArrayList<Person> people;

    public PeopleWrapper() {
    }

    public PeopleWrapper(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
        // wraps array list of multiple objects into one singular object
    }
}
