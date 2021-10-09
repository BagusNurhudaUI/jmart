package BagusJmartMH;

import java.util.*;

public class Algorithm {
    private  Algorithm(){

    }
    public <T> int count(T[] array, T value){
        int cnt = 0;
        Predicate<T> pNilai= nilai1 -> (nilai1 == value);
        for (T t : array) {
            if (pNilai.predicate(t)) {
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(Iterable<T> iterable, T value){
        int cnt = 0;
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        for (T element : iterable) {
            if (pNilai.predicate(element)) {
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(Iterator<T> iterator, T value){
        int cnt = 0;
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pNilai.predicate(element))
            {
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(T[] array, Predicate<T> pred){
        int cnt = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(Iterable<T> iterable, Predicate<T> pred){
        int cnt = 0;
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(Iterator<T> iterator, Predicate<T> pred){
        int cnt = 0;
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                cnt++;
            }
        }
        return cnt;
    }

    public <T> boolean exists(T[] array, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        for (T t : array) {
            if (pNilai.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists (Iterable<T> iterable, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pNilai.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists (Iterator<T> iterator, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pNilai.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    public <T> T find(T[] array, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        for (T t : array) {
            if (pNilai.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public <T> T find(Iterable<T> iterable, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pNilai.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    public <T> T find(Iterator<T> iterator, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pNilai.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    public <T> T find(T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public <T> T find(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    public <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    public <T extends Comparable <? super T>> T max(T first, T second){
        if(first.compareTo(second) > 0)
        {
            return first;
        }
        return second;
    }

    public <T extends Comparable <? super T>> T min(T first, T second){
        if(first.compareTo(second) < 0)
        {
            return first;
        }
        return second;
    }

}
