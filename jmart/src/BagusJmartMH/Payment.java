package BagusJmartMH;


/**
 * Write a description of class Payment here.
 *
 * @author (bagus nurhuda)
 * @version (modul 3)
 */
public class Payment extends Invoice
{
    public Shipment shipment;
    public int productCount;
    
     public Payment(int id, int buyerId, int productId, int productCount, Shipment shipment){
        super(id, buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    @Override
    public double getTotalPay() {

         return 0;
    }
}
