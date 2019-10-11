/**
 * Created by rdw1995 on 9/12/16.
 */

//a class about your health that could be apart of the Medical Form
public class Health {
    int weight;
    String height;
    String [] disability = new String[4];
    boolean isSmoker;
    static int maximumWeight = 255;

    Health (int newWeight, String newHeight, String [] newDisability, boolean newIsSmoker) {
        setWeight(newWeight);
        setHeight(newHeight);
        setDisability(newDisability);
        setIsSmoker(newIsSmoker);
    }

    int getWeight() {
        return weight;
    }
    void setWeight(int newWeight){
        if(newWeight < maximumWeight){
            weight = newWeight;
        }
    }
    String getHeight () {
        return height;
    }
    void setHeight (String newHeight) {
        height = newHeight;
    }
    String [] getDisability (){
        return disability;
    }
    void setDisability (String [] newDisability){
        disability = newDisability;
    }
    boolean getIsSmoker () {
        return false;
    }
    void setIsSmoker (boolean newIsSmoker){
        isSmoker = newIsSmoker;
    }
}
