package BagusJmartMH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.*;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Jmart
{

    public static void main(String[] args) {
        // String filepath = "C:/Users/bagus/Desktop/PrakOOP/jmart/src/GoldenSample/randomProductList.json";
//        String filepath = "C:/Users/bagus/Desktop/PrakOOP/jmar/src/GoldenSample/tesJson.json";
        try {
            String filepath = "C:/Users/bagus/Desktop/PrakOOP/jmart/src/GoldenSample/tesJson.json";

            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.add(new Account("name", "email", "password",1000 ));
            tableAccount.writeJson();

            tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.forEach(account -> System.out.println(account.toString()));
        }
            catch(Throwable t)
            {
            t.printStackTrace();
        }

    }
//            List<Product>list = read("C:/Users/bagus/Desktop/PrakOOP/jmart/src/GoldenSample/randomProductList.json");
//
//            System.out.println("filtered by account id");
//            List<Product> filteredAccount = filterByAccountId(list, 3,0, 10);
//            filteredAccount.forEach(product -> System.out.println(product.name));
//
//            System.out.println("filtered by category");
//            List<Product> filteredCategory = filterByCategory(list,ProductCategory.FNB);
//            filteredCategory.forEach(product -> System.out.println(product.name));
//
//            System.out.println("filtered by price");
//            List<Product>filtered = filterByPrice(list, 0.0, 20000.0);
//            filtered.forEach(product -> System.out.println(product.price));
//
//            System.out.println("filtered by name");
//            List<Product> filteredName = filterByName(list, "gtx", 1, 5);
//            filteredName.forEach(product -> System.out.println(product.name));

//        }
//        catch (Throwable t){
//            t.printStackTrace();
//        }
//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//        System.out .println("account id:" + new Account(null, null, null, -1).id);
//
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
//    }
//    public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize){
//        Predicate<Product> predicate = i -> (i.accountId == accountId);
//        return paginate(list, page, pageSize, predicate);
//
//    }
//    public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
//        List<Product> newList = new ArrayList<Product>();
//        for(Product p : list)
//        {
//            if(p.category == category)
//            {
//                newList.add(p);
//            }
//        }
//        return newList;
//    }
//    public static List<Product> filterByName (List<Product> list, String search, int page, int pageSize){
//        Predicate<Product> predicate = a -> (a.name.toLowerCase().contains(search.toLowerCase()));
//        return paginate(list, page, pageSize, predicate);
//    }
//    public static List<Product> filterByPrice (List<Product> list,double minPrice, double maxPrice){
//        List<Product> newList = new ArrayList<Product>();
//        if(minPrice != 0.0 && maxPrice != 0.0)
//        {
//            for(Product p : list)
//            {
//                double productPrice = p.price;
//                if(productPrice > minPrice && productPrice < maxPrice)
//                {
//                    newList.add(p);
//                }
//            }
//        }
//        else if(minPrice == 0.0)
//        {
//            for(Product p : list)
//            {
//                double productPrice = p.price;
//                if(productPrice < maxPrice)
//                {
//                    newList.add(p);
//                }
//            }
//        }
//        else if(maxPrice == 0.0)
//        {
//            for(Product p : list)
//            {
//                double productPrice = p.price;
//                if(productPrice > minPrice)
//                {
//                    newList.add(p);
//                }
//            }
//        }
//        return newList;
//    }
//
//    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred){
//        List<Product> newList = new ArrayList<>();
//        for(Product p : list)
//        {
//            if(pred.predicate(p))
//            {
//                newList.add(p);
//            }
//        }
//        List<Product> paginatedList = new ArrayList<>();
//        int startIndex = page * pageSize;
//        for(int i = startIndex; i < startIndex + pageSize; i++)
//        {
//            paginatedList.add(newList.get(i));
//        }
//        return paginatedList;
//    }
//
//   public static List<Product> read(String filepath) throws FileNotFoundException
//   {
//       Gson gson = new Gson();
//       JsonReader jsonReader = new JsonReader(new FileReader(filepath));
//       Product[] products = gson.fromJson(jsonReader, Product[].class);
//       List<Product> list = new ArrayList<>();
//       Collections.addAll(list, products);
//       return list;
//   }




//    class Country{
//        public String name;
//        public int population;
//        public List<String> listOfStates;
//    }
//    public static void main(String args[])
//    {
//        String filepath = "C:/Users/bagus/Desktop/PrakOOP/jmart/src/city.json";
//        Gson gson = new Gson();
//        try
//        {
//            BufferedReader br = new BufferedReader(new FileReader(filepath));
//            Country input = gson.fromJson(br, Country.class);
//            System.out.println("name: " + input.name);
//            System.out.println("population: " + input.population);
//            System.out.println("states:");
//            input.listOfStates.forEach(state -> System.out.println(state));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    // test comment buat push

    /*public static Product createProduct(){
        PriceTag priceTag = new PriceTag(100000);
        Product product = new Product("Cardilas",1,false,priceTag,ProductCategory.TRAVEL);
        return product;
    }

    public Coupon createCoupun(){
        Coupon coupon = new Coupon("idulfitri",1,Coupon.Type.DISCOUNT,20.0,10.0);
       return coupon;
    }

    public ShipmentDuration createShipmentDuration(){
         return new ShipmentDuration(ShipmentDuration.REGULER, ShipmentDuration.KARGO);
    }*/
}
