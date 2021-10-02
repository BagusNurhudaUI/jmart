package BagusJmartMH;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Write a description of class Store here.
 *
 * @author (bagus nurhuda)
 * @version (modul 3)
 */
public class Store extends Recognizable implements FileParser
{
    public static final String REGEX_NAME = "^\\d{9,12}$";;
    public static final String REGEX_PHONE = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    public String name;
    public String address;
    public String phoneNumber;
    
    public Store(int accountId, String name, String address, String phoneNumber){
        super(accountId);
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store(Account account, String name, String address, String phoneNumber){
        super(account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public String toString() {
        return "name: " + this.name + 
                "\naddress: " + this.address + 
                "\nphoneNumber: " + this.phoneNumber;
    }

    
    public boolean validate(){
        Pattern patternNama = Pattern.compile(REGEX_NAME);
        Matcher matcherNama = patternNama.matcher(this.name);
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherPhone = patternPhone.matcher(this.phoneNumber);
        boolean matchName = matcherNama.find();
        boolean matchPhone = matcherPhone.find();
        
        if (matchName == true && matchPhone==true){
            return true;
        }else {
            return false;
        }
    }
}
