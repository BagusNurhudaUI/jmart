package BagusJmartMH;
import java.util.ArrayList;

/**
 * Write a description of class Filter here.
 *
 * @author (Bagus N)
 * @version (modul 4)
 */
public class Filter
{   
    
     public static ArrayList<PriceTag> filterPriceTag(PriceTag[] list, double value, boolean less) 
    {
        ArrayList<PriceTag> priceTags = new ArrayList<>();
        for (PriceTag harga : list) {
            if (less && harga.getAdjustedPrice() < value || !less && harga.getAdjustedPrice() >= value)
            {
                priceTags.add(harga);
            }
        }
        return priceTags;
    }

    public static void filterProductRating(ArrayList<ProductRating> list, double value, boolean less) 
    {
        for (int i = 0; i < list.size(); ++i) 
        {
            final ProductRating harga = list.get(i);
            if (less && harga.getAverage() < value || !less && harga.getAverage() >= value)
            {
                list.remove(i);
            }
        }
    }

}
