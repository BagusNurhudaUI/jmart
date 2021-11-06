package BagusJmartMH;

/**
 * Write a description of class Product here.
 *
 * @author (bagus n)
 * @version (modul 2)
 */

public class Product extends Serializable
{
    public int accountid;
    double discount;
    double price;
    byte shipmentPlans;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public ProductCategory category;

    public Product(int accountid, double discount, double price, byte shipmentPlans, String name, int weight, boolean conditionUsed, ProductCategory category) {
        this.accountid = accountid;
        this.discount = discount;
        this.price = price;
        this.shipmentPlans = shipmentPlans;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.category = category;
    }

    public String toString(){
        return "Name: " + this.name + "\nWeight: " + this.weight +"\nconditionUsed: " + this.conditionUsed +"\npriceTag: " + this.price +"\ncategory: " + this.category +"\nweight: " + this.weight +"\naccount ID: " + this.accountid;
    }
}
