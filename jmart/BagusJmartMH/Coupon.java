package BagusJmartMH;

public class Coupon
{
    public final String name ;
    public int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    
         public enum Type
    {
    DISCOUNT , REBATE
    }
    
    public Coupon (String name ,int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    public boolean isUsed(){
        return used = false;
    }
    
    public boolean canApply (PriceTag priceTag){
        if (priceTag.getAdjustedPrice() >= minimum && used == false){
            return used = true;
        }else {
            return used = false;
        }
    }
    
    public double apply (PriceTag priceTag){
        used = true;
        
        if (type == type.DISCOUNT){
            return (100 - cut) / 100*priceTag.getAdjustedPrice();
        }
        else if (type == type.REBATE) {
            return priceTag.getAdjustedPrice() -priceTag.price;
        }else{
            return 0.0;
        }
        
    }
    
}
