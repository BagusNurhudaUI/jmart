package com.BagusJmartMH;

import com.BagusJmartMH.controller.AccountController;
import com.BagusJmartMH.controller.ProductController;
import com.BagusJmartMH.dbjson.JsonAutowired;
import com.BagusJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class PaymentController {
    @JsonAutowired(value = Account.class, filepath = "C:/Users/bagus/Desktop/jmart/account.json")
    public static final long DELIVERED_LIMIT_MS =1;
    public static final long ON_DELIVERY_LIMIT_MS =2;
    public static final long ON_PROGRESS_LIMIT_MS =3;
    public static final long WAITING_CONF_LIMIT_MS =4;
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("/{id}/accept")
    public boolean accept (int id){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment Accepted");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel (int id){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Payment Cancelled");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/create")
    public Payment create (@RequestParam int buyerId,@RequestParam int productId,@RequestParam int productCount,@RequestParam String shipmentAddress,@RequestParam byte shipmentPlan){
        Account account = null;
        Product product = null;
        for(Account a : AccountController.accountTable){
            if(a.id == buyerId){
                account = a;
            }
        }

        for(Product p : ProductController.productTable){
            if(p.id == productId){
                product = p;
            }
        }
        if(account != null && product != null){
            Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
            Payment payment = new Payment(buyerId, productId, productCount, shipment);
            double price = payment.getTotalPay(product);
            if(account.balance >= price){
                account.balance = account.balance - price;
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Payment paid and waiting for the confirmation"));
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(int id,String receipt){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.ON_PROGRESS && (!receipt.isBlank())){
                payment.shipment.receipt = receipt;
                Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "Payment Submitted");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }


    private boolean timekeeper(Payment payment){
        Payment.Record record = payment.history.get(payment.history.size() - 1);
        long elapsed = Math.abs(record.date.getTime() - (new Date()).getTime());

        if (record.status == Invoice.Status.WAITING_CONFIRMATION && elapsed > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Waiting"));
            return true;
        } else if (record.status == Invoice.Status.ON_PROGRESS && elapsed > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Progress"));
            return true;
        } else if (record.status == Invoice.Status.ON_DELIVERY && elapsed > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Delivery"));
            return false;
        } else if (record.status == Invoice.Status.DELIVERED && elapsed > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Finish"));
            return true;
        }
        return false;
    }


}
