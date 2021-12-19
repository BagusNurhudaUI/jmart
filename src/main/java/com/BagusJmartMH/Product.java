package com.BagusJmartMH;

import com.BagusJmartMH.dbjson.Serializable;

/**
 * merupakan class yang digunakan untuk inisialisasi objek yang akan digunakan dalam product
 *
 * @author (bagus n)
 * @version (modul 2)
 */

public class Product extends Serializable
{
    public int accountId;
    double discount;
    public double price;
    byte shipmentPlans;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public ProductCategory category;

    /**
     * merupakan inisialisasi construktor pada product
     * @param accountId
     * @param discount
     * @param price
     * @param shipmentPlans
     * @param name
     * @param weight
     * @param conditionUsed
     * @param category
     */
    public Product(int accountId, double discount, double price, byte shipmentPlans, String name, int weight, boolean conditionUsed, ProductCategory category) {
        this.accountId = accountId;
        this.discount = discount;
        this.price = price;
        this.shipmentPlans = shipmentPlans;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.category = category;
    }

    public String toString(){
        return "Name: " + this.name + "\nWeight: " + this.weight +"\nconditionUsed: " + this.conditionUsed +"\npriceTag: " + this.price +"\ncategory: " + this.category +"\nweight: " + this.weight +"\naccount ID: " + this.accountId;
    }
}
