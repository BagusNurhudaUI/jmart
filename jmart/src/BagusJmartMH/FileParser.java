package BagusJmartMH;


/**
 * Write a description of interface FileParser here.
 *
 * @author (Bagus N)
 * @version (modul 3)
 */
public interface FileParser
{
    public boolean read (String content);

    default Object write(){
        return null;
    }
    
    public static Object newInstance (String content){
        return null;
    }
}
