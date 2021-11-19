package BagusJmartMH;

public class PhoneTopUp extends Invoice{
    public String phoneNumber;
    public Status status;

    public PhoneTopUp (int buyerID,int productId, String phoneNumber){
        super(buyerID,productId);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public double getTotalPay (Product product){
        return product.price;
    }
}
