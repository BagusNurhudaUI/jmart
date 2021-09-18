package BagusJmartMH;



public class PriceTag
{
   public static final double COMMISSION_MULTIPLIER = 0.05;
   public static final double BOTTOM_PRICE = 20000.0;
   public static final double BOTTOM_FEE = 1000.0;
   double price;
   double discount;
   
   public PriceTag(double price){
       this.price = price;
       this.discount = 0.0f;
   }
   
   public PriceTag (double price, double discount){
       this.price = price;
       this.discount = discount; 
   }
   
   public double getAdjustedPrice(){
       
       return getDiscountedPrice()+ getAdminFee();
       
   }
   
   public double getAdminFee(){
       double afterDiscount = getDiscountedPrice();
       if (afterDiscount <= BOTTOM_PRICE){
           return BOTTOM_FEE;
       } else {
           afterDiscount = afterDiscount -(afterDiscount * COMMISSION_MULTIPLIER);
           return afterDiscount;
       }
       
   }
   
   private double getDiscountedPrice(){
       if (discount > 100.0){
           discount = 100.0;
       }
       if(discount == 100.0){
           return 0.0;
       } else{
           return (price -(price * discount));
       }
   }
    
}
