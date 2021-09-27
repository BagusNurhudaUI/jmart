package BagusJmartMH;


/**
 * Write a description of class Complaint here.
 *
 * @author (bagus n)
 * @version (modul 3)
 */
public class Complaint extends Recognizable implements FileParser
{
    public int paymentId;
    public String desc;

    public Complaint(int id, Payment payment, String desc) {
        super(id);
        this.paymentId = payment.id;
        this.desc = desc;
    }

    public Complaint(int id, int buyerId, int storeId, int paymentId, String desc) {
        super(id);
        this.paymentId = paymentId;
        this.desc = desc;
    }

    public boolean validate() {
        return false;
    }

    public Transaction perform() {
        return null;
    }

    public boolean read(String content){
        return false;
    }
}
