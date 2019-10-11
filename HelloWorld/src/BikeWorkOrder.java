import javax.print.DocFlavor;

/**
 * Created by rdw1995 on 9/12/16.
 */
//displays options for online bike work order//

public class BikeWorkOrder {
    Bike bike;
    String[] problem = new String[6];
    int needBy;
    String[] adjustment = new String[4];
    static int maximumNeedBy = 31;


    BikeWorkOrder(Bike newBike, String [] newProblem, int newNeedBy, String [] newAdjustment){
        setBike(newBike);
        setProblem(newProblem);
        setNeedBy(newNeedBy);
        setAdjustment(newAdjustment);
    }
    Bike getBike() {
        return bike;
    }
    void setBike(Bike newBike) {
        bike = newBike;
    }
    String [] getProblem(){
        return problem;
    }
    void setProblem(String [] newProblem){
        problem = newProblem;
    }
    int getNeedBy() {
        return needBy;
    }
    void setNeedBy (int newNeedBy) {
        if (newNeedBy <= maximumNeedBy) {
            needBy = newNeedBy;
        }
    }
    String [] getAdjustment () {
        return adjustment;
    }
    void setAdjustment (String [] newAdjustment){
        adjustment = newAdjustment;
    }
}
