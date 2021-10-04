package BagusJmartMH;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

/**
 * Write a description of class Invoice here.
 *
 * @author (bagus n)
 * @version (modul 3 PT)
 */
public abstract class Invoice extends Recognizable implements FileParser
{
    public int buyerId;
    public int complaintId;
    public Date date;
    public ArrayList<Record> history = new ArrayList<Record>();
    public int productId;
    public Rating rating;
    public Status status;
    

    enum Rating {
        NONE, BAD, NEUTRAL, GOOD
    }

    enum Status {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED
    }
     protected Invoice(int id, int buyerId, int productId) {
        super(id);
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
        
    }
    
    @Override
    public boolean read(String content) {
        return false;
    }

    public abstract double getTotalPay();
    
    static class Record{
        public Date date;
        public String message;
        public Status status;
    }
    
}
