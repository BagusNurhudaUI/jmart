package BagusJmartMH;


/**
 * Write a description of class Recognizable here.
 *
 * @author (Bagus Nurhuda)
 * @version (modul 3)
 */
public class Recognizable implements Comparable<Recognizable>
{
       public final int id;
       
       protected Recognizable (int id){
           this.id = id;
       }

    @Override
    public int compareTo(Recognizable other) {
           if ( id == other.id){
               return 1;
           } else {
               return 0;
           }
    }

    public boolean equals (Object other){
           if (other instanceof Recognizable){
           Recognizable recognizable = (Recognizable) other;
               if(recognizable.id == this.id){
                   return true;
               }else{
                   return false;
               }
           }
               return false;
           }
       
       public boolean equals (Recognizable other){
           if(other.id == this.id){
            return true;   
        }else{
            return false;
        }
       }

    public static <T extends Recognizable> int getClosingId (Class<T> clazz){
    return 0;
    }

    public static <T extends Recognizable> int setClosingId (Class<T> clazz, int id){
    return 0;
    }

}
