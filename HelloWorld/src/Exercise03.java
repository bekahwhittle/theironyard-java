import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Created by rdw1995 on 9/21/16.
 */
public class Exercise03 {
    public static void main(String[] args) {
        String []names = {"alice", "bob", "charlie", "daniel", "andrew", "alex"};
        ArrayList<String> namesArr = new ArrayList<>(Arrays.asList(names));

    // find a way to loop through and remove 'a's'

        for (String allNames : names){
           // String firstLetter = String.valueOf(allNames.charAt(0));
            if (allNames.charAt(0) == 'a'){
                namesArr.remove(allNames);
            }
        }
        System.out.println(namesArr);

    // run through all names and if "a" remove it?
        namesArr = new ArrayList<>(Arrays.asList(names));
        for (int n = namesArr.size() - 1; n >= 0; n --){
            if (namesArr.get(n).charAt(0) == 'a') {
                namesArr.remove(n);
            }
        }
        System.out.println(namesArr);

     // iterator
        namesArr = new ArrayList<>(Arrays.asList(names));
        Iterator<String> iter = namesArr.iterator();
        while (iter.hasNext()) {
            String name = iter.next();
            if (name.charAt(0) == 'a') {
                iter.remove();
            }
        }
     // anonymous function
     // stream
        namesArr = new ArrayList<>(Arrays.asList(names));
        namesArr = namesArr.stream().filter((name) -> {
            return !name.startsWith("a");
        }).collect(Collectors.toCollection(ArrayList:: new)); // -- convert into a new arraylist
    }
}
