package com.BagusJmartMH.controller;

import com.BagusJmartMH.*;
import com.BagusJmartMH.controller.AccountController;
import com.BagusJmartMH.controller.ProductController;
import com.BagusJmartMH.dbjson.JsonAutowired;
import com.BagusJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * merupakan method controoler untuk melakukan modifikasi pada payment json
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    @JsonAutowired(value = Payment.class, filepath = "C:/Users/bagus/Desktop/jmart/payment.json")
    public static JsonTable<Payment> paymentTable;

    public static final long DELIVERED_LIMIT_MS = 300000;
    public static final long ON_DELIVERY_LIMIT_MS = 300000;
    public static final long ON_PROGRESS_LIMIT_MS = 300000;
    public static final long WAITING_CONF_LIMIT_MS = 300000;
    public static ObjectPoolThread<Payment> poolThread;

    //Membuat thread dengan routine yang dijalankannya adalah timekeeper
    static
    {
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    /**
     * untuk mendapatkan data dari payment json berdasarkan id
     * @param buyerId
     * @return
     */
    @GetMapping("/getByAccountId")
    public List<Payment> getPaymentByAccountId(@RequestParam int buyerId){
        List<Payment> paymentList = new ArrayList<>();
        for(Payment p : paymentTable){
            if(p.buyerId == buyerId){
                paymentList.add(p);
            }
        }
        return paymentList;
    }

    /**
     * merupakan method untuk melakukan accept dari order
     * @param id
     * @return
     */
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

    /**
     * method untuk mendapatkan informasi json berdasarkan idnya
     * @param storeId
     * @return
     */
    @GetMapping("/getByStoreId")
    public List<Payment> getPaymentStoreid(@RequestParam int storeId){
        List<Payment> listP = new ArrayList<>();
        for (Payment p : paymentTable){
            if(p.storeId == storeId){
                listP.add(p);
            }
        }
        return listP;
    }

    /**
     * method untuk melakukan cancel pada order
     * @param id
     * @return
     */
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

    /**
     * merupakan method yang digunakan untuk melakukan order dan store ke json
     * @param buyerId
     * @param productId
     * @param productCount
     * @param shipmentAddress
     * @param shipmentPlan
     * @param storeId
     * @return
     */
    @PostMapping("/create")
    public Payment create (@RequestParam int buyerId,
                           @RequestParam int productId,
                           @RequestParam int productCount,
                           @RequestParam String shipmentAddress,
                           @RequestParam byte shipmentPlan,
                           @RequestParam int storeId){
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
            Payment payment = new Payment(buyerId, productId, productCount, shipment, storeId);
            double price = payment.getTotalPay(product);
            if(account.balance >= price){
                account.balance = account.balance - price;
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Payment berhasil dilakukan "));
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

    /**
     * merupakan method yang digunkaan untuk melakukan submit no resi berdasarkan id
     * @param id
     * @param receipt
     * @return
     */
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
                poolThread.add(payment);
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method yang digunakan untuk melakukan perhitungan waktu untuk mengubah informasi shipment
     * @param payment
     * @return
     */
    private static boolean timekeeper(Payment payment){
        Date timeNow = Calendar.getInstance().getTime();
        if(payment.history.size() != 0){
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                for(Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += getTotalPaymentPrice(payment);
                    }
                }
                return true;
            }
            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                for(Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += getTotalPaymentPrice(payment);
                    }
                }
                return true;
            }
            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "TERKIRIM"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "SELESAI"));
                for(Account a : AccountController.accountTable){
                    if(a.id == payment.storeId){
                        for(Product p : ProductController.productTable){
                            if(p.id == payment.productId){
                                a.store.balance += payment.productCount * p.price;
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method yang digunakan untuk melakukan perhitungan total harga
     * @param payment
     * @return
     */
    public static double getTotalPaymentPrice(Payment payment){
        for(Product p : ProductController.productTable){
            if(p.id == payment.productId){
                return payment.productCount * p.price;
            }
        }
        return 0.0;
    }

}
