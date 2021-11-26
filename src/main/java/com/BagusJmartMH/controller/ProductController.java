package com.BagusJmartMH.controller;

import com.BagusJmartMH.Algorithm;
import com.BagusJmartMH.Product;
import com.BagusJmartMH.ProductCategory;
import com.BagusJmartMH.controller.AccountController;
import com.BagusJmartMH.controller.BasicGetController;
import com.BagusJmartMH.dbjson.JsonAutowired;
import com.BagusJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>
{
    @JsonAutowired(value = Product.class,filepath = "C:/Users/bagus/Desktop/jmart/coupon.json")
    public static JsonTable<Product> productTable;

    @Override
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    @GetMapping("/{id}/store")
    @ResponseBody
    List<Product> getProductByStore
            (
                    @RequestParam int id,
                    @RequestParam int page,
                    @RequestParam int pageSize
            )
    {
        return Algorithm.paginate(productTable, page, pageSize, pred->pred.accountId == id);
    }

    @PostMapping("/create")
    @ResponseBody
    Product create
            (
                    @RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam int weight,
                    @RequestParam boolean conditionUsed,
                    @RequestParam double price,
                    @RequestParam double discount,
                    @RequestParam ProductCategory category,
                    @RequestParam byte shipmentPlans
            )
    {
        for(Product each : productTable) {
            if (each.accountId == accountId){
                Product product =  new Product(accountId, discount, price, shipmentPlans,  name,  weight, conditionUsed,category);
                productTable.add(product);
                return product;
            }
        }
        return null;
    }

    @GetMapping("/getFiltered")
    @ResponseBody
    List<Product> getProductByFilter
            (
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "0") int pageSize,
                    @RequestParam(defaultValue = "0") int accountId,
                    @RequestParam(defaultValue = "") String search,
                    @RequestParam(defaultValue = "0.0") int minPrice,
                    @RequestParam(defaultValue = "0.0") int maxPrice,
                    @RequestParam(defaultValue = "") ProductCategory category
            )
    {
        List<Product> tempProduct = null;
        for (Product each : productTable) {
            if (each.accountId == accountId)
                if (each.name.contains(search))
                    if (minPrice <= each.price)
                        if (maxPrice >= each.price)
                            if (each.category == category)
                                tempProduct.add(each);
        }
        return tempProduct;
    }
}