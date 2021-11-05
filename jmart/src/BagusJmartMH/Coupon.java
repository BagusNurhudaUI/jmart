package BagusJmartMH;

/**
 * Write a description of class Coupon here.
 *
 * @author (bagus n)
 * @version (modul 2)
 */
public class Coupon extends Recognizable
{
    public final String name ;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    double price = 10000;
    double discount = 10;

    public enum Type
    {
    DISCOUNT , REBATE
    }
    
    public Coupon(String name, int code, Type type, double cut, double minimum){

        used = false;
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }
    
    public boolean isUsed(){
        return used = false;
    }
    
    public boolean canApply (Treasury treasury){
        if (treasury.getAdjustedPrice(price, discount) >= minimum && used == false){
            return used = true;
        }else {
            return used = false;
        }
    }
    
    public double apply (Treasury treasury){
        used = true;
        
        if (type == type.DISCOUNT){
            return (100 - cut) / 100* treasury.getAdjustedPrice(price, discount);
        }
        else if (type == type.REBATE) {
            return treasury.getAdjustedPrice(price, discount) - price;
        }else{
            return 0.0;
        }
        
    }

}
