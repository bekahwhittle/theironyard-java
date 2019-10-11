import java.io.*;
import java.util.Scanner;

/**
 * Created by rdw1995 on 9/21/16.
 */
public class ReadWriteFile {
    public static void main(String[] args) {
        File f = new File("namex.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write("Alice\n");
            fw.append("Bob\n");
            fw.append("Charlie\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read line by line
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()){
                String name = scanner.nextLine();
                System.out.println(name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //read entire file
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char [fileSize];
            fr.read(contents, 0, fileSize);
            System.out.println(String.valueOf(contents));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
