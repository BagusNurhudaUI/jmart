package BagusJmartMH;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Write a description of class Complaint here.
 *
 * @author (bagus n)
 * @version (modul 3)
 */
public class Complaint extends Recognizable implements FileParser
{
    public Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = new Date();
        this.desc = desc;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public String toSting(){
        SimpleDateFormat tanggal = new SimpleDateFormat("dd/MM/yyyy");
        return "Complaint{date=" + tanggal.format(date) + ", desc='" + desc + "pengiriman tidak tepat, kurir tersesat'}";
    }
}
