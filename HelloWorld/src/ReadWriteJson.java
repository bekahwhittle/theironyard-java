import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rdw1995 on 9/21/16.
 */
public class ReadWriteJson {
    public static void main(String[] args) throws IOException {
//        Person p = new Person("Rebekah Whittle", 21, true);
//        File f = new File("person.json");

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Alice Smith", 30, true));
        people.add(new Person("Bob Jones", 30, true));
        people.add(new Person("Charlie Brown", 30, true));
        PeopleWrapper pw = new PeopleWrapper(people);

        File f = new File("people.json");

        // left hand gutter allows you to see  - click debug option
        // write json - serialize == take and make into string
        // create Json and parse into json

        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.deep(true).serialize(pw);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();

        // read json

        FileReader fr = new FileReader(f);
        int fileSize = (int) f.length();
        char[] contents = new char [fileSize];
        fr.read(contents, 0, fileSize);
        System.out.println(String.valueOf(contents));
        JsonParser parser = new JsonParser();
        Person p2 = parser.parse(contents,Person.class);
        System.out.println(p2);
    }
}
