/**
 * Created by rdw1995 on 9/12/16.
 */

//a document showing a woman going through sorority recruitment

public class SororityPreference {
    String name;
    boolean isLegacy;
    String highSchool;
    int age;
    static int minimumAge = 19;

    SororityPreference (String newName, boolean newIsLegacy, String newHighSchool, int newAge){
        setName(newName);
        setIsLegacy(newIsLegacy);
        setHighSchool(newHighSchool);
        setAge(newAge);
    }
    String getName(){
        return name;
    }
    void setName (String newName){
        name = newName;
    }
    boolean getIsLegacy(){
        return false;
    }
    void setIsLegacy(boolean newIsLegacy){
        isLegacy = newIsLegacy;
    }
    String getHighSchool(){
        return highSchool;
    }
    void setHighSchool (String newHighSchool){
        highSchool = newHighSchool;
    }
    int getAge(){
        return age;
    }
    void setAge (int newAge){
        if(newAge > minimumAge) {
            age = newAge;
        }
    }
}
