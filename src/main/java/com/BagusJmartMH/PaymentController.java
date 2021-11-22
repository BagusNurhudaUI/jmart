package com.BagusJmartMH;

import com.BagusJmartMH.Account;
import com.BagusJmartMH.ObjectPoolThread;
import com.BagusJmartMH.Payment;
import com.BagusJmartMH.Shipment;
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
    return true;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel (int id){
    return true;
    }

    @PostMapping("/create")
    public Payment create (@RequestParam int buyerId,@RequestParam int productId,@RequestParam int productCount,@RequestParam String shipmentAddress,@RequestParam byte shipmentPlan){
        Shipment shipment = new Shipment(shipmentAddress, 1000, shipmentPlan, "");
        Payment payment = new Payment(buyerId, productId, productCount, shipment);

        paymentTable.add(payment);
        return payment;
    }

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(int id,String receipt){
    return true;
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
