package com.BagusJmartMH;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Write a description of class Complaint here.
 *
 * @author (bagus n)
 * @version (modul 3)
 */
public class Complaint
{
    public Date date;
    public String desc;
    
    public Complaint(String desc){

        this.date = new Date();
        this.desc = desc;
    }
    
    public String toSting(){
        SimpleDateFormat tanggal = new SimpleDateFormat("dd/MM/yyyy");
        return "Complaint{date=" + tanggal.format(date) + ", desc='" + desc + "pengiriman tidak tepat, kurir tersesat'}";
    }
}
