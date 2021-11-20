package com.BagusJmartMH;
import com.BagusJmartMH.dbjson.Serializable;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Write a description of class Account here.
 *
 * @author (bagus nurhuda)
 * @version (modul 3)
 */
public class Account extends Serializable
{

    public String name;
    public String email;
    public String password;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    public double balance;
    public Store store;
     
    public Account( String name, String email, String password, double balance)
    {

        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public boolean validate(){
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherEmail = patternEmail.matcher(this.email);
        Pattern patternPass = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherPass = patternPass.matcher(this.password);
        boolean matchEmail = matcherEmail.find();
        boolean matchPass = matcherPass.find();
        
        if (matchEmail == true && matchPass==true){
            return true;
        }else {
            return false;
        }
    }
}
