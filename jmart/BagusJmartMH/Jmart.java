package BagusJmartMH;


/**
 * Write a description of class Jmart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jmart
{
    public static void main (String args[])
    {
        
    }
    
    public static int getPromo()
    {
        return 0;
    }
    
    public static String getCustomer()
    {
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after)
    {
        if (before < after){
            return 0.0f;
        } 
        int potHarga = before - after ;
        float floatPotHarga = potHarga;
        float floatBefore = before;
        float percentage = floatPotHarga/floatBefore * 100;
        return percentage;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        float disc;
        if (discountPercentage > 100.0f){
            disc = 100.0f;
        }else{
            disc = discountPercentage;
        }
        float discPrice;
        discPrice = price - (disc * price /100);
        int intDiscPrice = (int) discPrice;
        return intDiscPrice;
        
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        float floatDiscountedPrice = discountedPrice;
        float originalPrice = (floatDiscountedPrice * 100)/(100 - discountPercentage) ;
        int intOriginalPrice = (int) originalPrice;
        return intOriginalPrice;
    }
    
    public static float getCommissionMultiplier()
    {
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price)
    {
        float newPrice = price * getCommissionMultiplier();
        int intNewPrice = (int) newPrice;
        price = price + intNewPrice;
        return price;
    }
    
    public static int getAdminFree(int price)
    {
        float floatPrice = price;
        float fee = floatPrice * getCommissionMultiplier();
        int lastFee = (int) fee;
        return lastFee;
    }
}
