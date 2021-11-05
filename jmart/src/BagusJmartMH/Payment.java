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
    
     public Payment( int buyerId, int productId, int productCount, Shipment shipment){
        super( buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    @Override
    public double getTotalPay() {

         return 0;
    }
}
