package com.BagusJmartMH;



public class ProductRating
{
    private long total;
    private long count;
    
    public ProductRating(){
        this.total = total;
        this.count = count;
    }
    
    public void insert(int rating){
        this.total += rating;
        this.count++;
    }
    
    public double getAverage(){
        return ((double) total/count);
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
}
