package com.BagusJmartMH;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class Shipment here.
 *
 * @author (bagus n)
 * @version (modul 3)
 */
public class Shipment
{
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat ("EEE MMMM dd yyyy");
    public static Plan INSTANT = new Plan((byte)1);
    public static Plan SAME_DAY = new Plan((byte)(1 << 1));
    public static Plan NEXT_DAY = new Plan((byte)(1 << 2));
    public static Plan REGULER = new Plan((byte)(1 << 3));
    public static Plan KARGO = new Plan((byte)(1 << 4));
    public String address;
    public int cost;
    public byte plan;
    public String receipt;


    static class Plan {
        public final byte bit;
        private Plan(byte bit) {
            this.bit = bit;
        }
    }

    public Shipment(String address, int cost, byte plan, String receipt) {
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }


    public String getEstimatedArrival (Date reference){
        Calendar cal = Calendar.getInstance();
        cal.setTime(reference);
        if (plan ==NEXT_DAY.bit){
            cal.add(Calendar.DATE, 1);
        }
        else if (plan == REGULER.bit){
            cal.add(Calendar.DATE, 2);
        }
        else if (plan == KARGO.bit){
            cal.add(Calendar.DATE, 5);
        }
        return ESTIMATION_FORMAT.format(cal.getTime());
    }

    public boolean isDuration(Plan reference)
    {
        return (this.plan & reference.bit) == reference.bit;
    }

    public static boolean isDuration(byte object, Plan reference)
    {
        return (object & reference.bit) == reference.bit;
    }
            
}
