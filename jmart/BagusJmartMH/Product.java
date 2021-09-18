package BagusJmartMH;



public class Product
{
    private static int idCounter = 0;
    public int id;
    public String name;
    public int weight;
    public boolean conditionUsed;
    
    public PriceTag priceTag;
    public ProductCategory category ;
    public ProductRating rating = new ProductRating();
    
    public Product(String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category){
        id = idCounter++;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        id = idCounter++;
    }
}
