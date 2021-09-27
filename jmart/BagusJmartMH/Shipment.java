package BagusJmartMH;


/**
 * Write a description of class Shipment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shipment implements FileParser
{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt) {
                this.address = address;
                this.shipmentCost = shipmentCost;
                this.duration = duration;
                this.receipt = receipt;
    }
        
    public boolean read(String content) {
        return false;
    }
    
    static class Duration{
    public static Duration INSTANT = new Duration((byte) (1<<0));
    public static Duration SAME_DAY = new Duration((byte) (1<<1));
    public static Duration NEXT_DAY = new Duration((byte) (1<<2));
    public static Duration REGULER = new Duration((byte) (1<<3));
    public static Duration KARGO = new Duration((byte) (1<<4));
    private final byte bit;
    
        private Duration(byte bit){
        this.bit = bit;
    }
}

static class MultiDuration {
        public byte bit;

        public MultiDuration(Duration... args){
            byte kurir = args[0].bit;

            for(int i=1; i<args.length; i++){
                kurir = (byte) (kurir | args[i].bit);
            }

            bit = kurir;
        }

        public boolean isDuration(Duration reference){
            return ( (this.bit & reference.bit) == reference.bit );
        }
    }
            
}