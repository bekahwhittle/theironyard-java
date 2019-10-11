/**
 * Created by rdw1995 on 9/12/16.
 */

//this is a class for what kind of dog you're interested in

public class Dog {
    boolean likesBig;
    boolean likesBarking;
    boolean likesShedding;
    String breed;

    Dog (boolean newLikesBig, boolean newLikesBarking, boolean newLikesShedding, String newBreed) {
        setLikesBig(newLikesBig);
        setLikesBarking(newLikesBarking);
        setLikesShedding(newLikesShedding);
        setBreed(newBreed);
    }

    boolean getLikesBig(){
        return true;
    }
    void setLikesBig (boolean newLikesBig){
        likesBig = newLikesBig;
    }
    boolean getLikesBarking(){
        return false;
    }
    void setLikesBarking (boolean newLikesBarking){
        likesBarking = newLikesBarking;
    }
    boolean isLikesShedding(){
        return false;
    }
    void setLikesShedding (boolean newLikesShedding) {
        likesShedding = newLikesShedding;
    }
    String getBreed () {
        return breed;
    }
    void setBreed (String newBreed){
        if(isValidBreed(newBreed)) {
            breed = newBreed;
        }
    }
    static boolean isValidBreed (String breed) { return breed.contains("Retriever");}
}
