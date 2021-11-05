package BagusJmartMH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;

public class Jmart
{
    class Country{
        public String name;
        public int population;
        public List<String> listOfStates;
    }
    public static void main(String args[])
    {
        String filepath = "C:/Users/bagus/Desktop/PrakOOP/jmart/src/city.json";
        Gson gson = new Gson();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Country input = gson.fromJson(br, Country.class);
            System.out.println("name: " + input.name);
            System.out.println("population: " + input.population);
            System.out.println("states:");
            input.listOfStates.forEach(state -> System.out.println(state));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
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
