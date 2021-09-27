package BagusJmartMH;


/**
 * Write a description of class Complaint here.
 *
 * @author (bagus n)
 * @version (modul 3)
 */
public class Complaint extends Recognizable implements FileParser
{
    public String date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = "test date";
        this.desc = desc;
    }
    
    public boolean read(String content){
        return false;
    }
}
