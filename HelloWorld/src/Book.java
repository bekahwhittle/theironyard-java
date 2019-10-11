/**
 * Created by rdw1995 on 9/14/16.
 */
//practice & refresh before class

public class Book {
    String title;
    String author;
    String [] chapter;
    int yearPublished;
    boolean isClassic;
    static int minimumYearPublished = 2000;

    Book (String newTitle, String newAuthor, String [] newChapter, int newYearPublished, boolean newIsClassic){
        setTitle(newTitle);
        setAuthor(newAuthor);
        setChapter(newChapter);
        setYearPublished(newYearPublished);
        setIsClassic(newIsClassic);
    }

    String getTitle () {
        return title;
    }
    void setTitle (String newTitle) {
        title = newTitle;
    }
    String getAuthor () {
        return author;
    }
    void setAuthor (String newAuthor) {
        author = newAuthor;
    }
    String [] getChapter (){
        return chapter;
    }
    void setChapter (String[] newChapter){
        chapter = newChapter;
    }
    int getYearPublished (){
        return yearPublished;
    }
    void setYearPublished (int newYearPublished){
        if(newYearPublished < minimumYearPublished) {
            yearPublished = newYearPublished;
        }
    }
    boolean getIsClassic (){
        return true;
    }
    void setIsClassic (boolean newIsClassic){
        if(yearPublished <= 1900) {
            isClassic = newIsClassic;
        }
        else {
            isClassic = false;
        }
    }
}

