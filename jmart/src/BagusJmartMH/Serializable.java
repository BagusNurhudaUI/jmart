package BagusJmartMH;


import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class Recognizable here.
 *
 * @author (Bagus Nurhuda)
 * @version (modul 3)
 */
public class Serializable implements Comparable<Serializable>
{
       public final int id;
       private static Map<Class<?>, Integer> mapCounter = new HashMap();


    protected Serializable(){
           Class getC = getClass();
           if(mapCounter.get(getC) == null){
               mapCounter.put(getC,0);
           }else {
               mapCounter.put(getC, mapCounter.get(getC) + 1);
           }
           this.id =mapCounter.get(getC);
    }


    public int compareTo(Serializable other) {
           if ( id == other.id){
               return 1;
           } else {
               return 0;
           }
    }

    public boolean equals (Object other){
           if (other instanceof Serializable){
           Serializable serializable = (Serializable) other;
               if(serializable.id == this.id){
                   return true;
               }else{
                   return false;
               }
           }
               return false;
           }
       
       public boolean equals (Serializable other){
           if(other.id == this.id){
            return true;   
        }else{
            return false;
        }
       }

    public static <T extends Serializable> int getClosingId (Class<T> clazz){
    return mapCounter.get(clazz);
    }

    public static <T extends Serializable> int setClosingId (Class<T> clazz, int id){
    return mapCounter.put(clazz,id);
    }

}