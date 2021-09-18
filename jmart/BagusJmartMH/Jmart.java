package BagusJmartMH;



public class Jmart
{
    public static void main (String args[])
    {
        
    }
    
    
    public static Product create(){
        PriceTag priceTag = new PriceTag(100000);
        Product product = new Product("Cardilas",1,false,priceTag,ProductCategory.TRAVEL);
        return product;
    }
}
