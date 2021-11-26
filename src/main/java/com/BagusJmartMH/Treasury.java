package com.BagusJmartMH;



public class Treasury
{
   public static final double COMMISSION_MULTIPLIER = 0.05;
   public static final double BOTTOM_PRICE = 20000.0;
   public static final double BOTTOM_FEE = 1000.0;

    private static double getDiscountedPrice(double price, double discount)
    {
        if(discount >= 100.0){
            return 100.0;
        }

        if(discount==100.0){
            return 0.0;
        }

        return(price-(price*(discount/100)));
    }

    public static double getAdminFee(double price, double discount)
    {
        double discountprice = getDiscountedPrice(price,discount);
        if(discountprice <= BOTTOM_PRICE){
            return BOTTOM_FEE;
        }
        else{
            discountprice = discountprice - (discountprice*COMMISSION_MULTIPLIER);
            return getDiscountedPrice(price,discount);
        }
    }

    public static double getAdjustedPrice(double price, double discount)
    {
        double discountprice = getDiscountedPrice(price,discount) + getAdminFee(price,discount);
        return discountprice;
    }

}
