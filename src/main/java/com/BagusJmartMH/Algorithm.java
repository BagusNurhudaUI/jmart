package com.BagusJmartMH;

import java.util.*;

public class Algorithm {
    private  Algorithm(){

    }

    public <T>List<T> collect (T[] array, T value){
        List<T> list = new ArrayList<T>();
        for (T element: array) {
            if(element.equals(value)) {
                list.add(element);
            }
        }
        return list;
    }

    public <T>List<T> collect (Iterable<T> iterable, T value){
        List<T> list = new ArrayList<T>();
        for (T element: iterable) {
            if(element.equals(value)) {
                list.add(element);
            }
        }
        return list;
    }

    public <T>List<T> collect (Iterator<T> iterator, T value){
        List<T> list = new ArrayList<T>();
        while(iterator.hasNext()) {
            T element = iterator.next();
            if(element.equals(value)) {
                list.add(element);
            }
        }
        return list;
    }

    public <T>List<T> collect (T[] array, Predicate<T> pred){
        List<T> list = new ArrayList<T>();
        for (T element: array) {
            if(pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public <T>List<T> collect (Iterable<T>iterable, Predicate<T> pred){
        List<T> list = new ArrayList<T>();
        for (T element: iterable) {
            if(pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public <T>List<T> collect (Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<T>();
        while(iterator.hasNext()) {
            T element = iterator.next();
            if(pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static<T> int count(T[] array, T value){
        int cnt = 0;
        Predicate<T> pNilai= nilai1 -> (nilai1 == value);
        for (T t : array) {
            if (pNilai.predicate(t)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static<T> int count(Iterable<T> iterable, T value){
        int cnt = 0;
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        for (T element : iterable) {
            if (pNilai.predicate(element)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static<T> int count(Iterator<T> iterator, T value){
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

    public static<T> int count(T[] array, Predicate<T> pred){
        int cnt = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static<T> int count(Iterable<T> iterable, Predicate<T> pred){
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

    public static<T> int count(Iterator<T> iterator, Predicate<T> pred){
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

    public static<T> boolean exists(T[] array, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        for (T t : array) {
            if (pNilai.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public static<T> boolean exists (Iterable<T> iterable, T value){
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

    public static<T> boolean exists (Iterator<T> iterator, T value){
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

    public static<T> boolean exists(T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public static<T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
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

    public static<T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
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

    public static<T> T find(T[] array, T value){
        Predicate<T> pNilai = nilai1 -> (nilai1 == value);
        for (T t : array) {
            if (pNilai.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public static<T> T find(Iterable<T> iterable, T value){
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

    public static<T> T find(Iterator<T> iterator, T value){
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

    public static<T> T find(T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public static<T> T find(Iterable<T> iterable, Predicate<T> pred){
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

    public static<T> T find(Iterator<T> iterator, Predicate<T> pred){
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

    public static<T extends Comparable<? super T>> T max(T first, T  second){
        if(first.compareTo(second) > 0){
            return first;
        } else {
            return second;
        }
    }

    public static<T extends Comparable<? super T>> T max(T[] array){
        T max = array[0];
        for (T t : array) {
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }
        return max;
    }

    public static<T extends Comparable<? super T>> T max(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(element.compareTo(max) > 0)
            {
                max = element;
            }
        }
        return max;
    }

    public static<T extends Comparable<? super T>> T max(Iterator<T> iterator){
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(element.compareTo(max) > 0)
            {
                max = element;
            }
        }
        return max;
    }

    public static<T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator){
        int thisCompare = comparator.compare(first, second);
        if(thisCompare > 0) {
            return first;
        }
        else {
            return second;
        }
    }

    public static<T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator){
        T max = array[0];
        for (T t : array) {
            int thisCompare = comparator.compare(t, max);
            if (thisCompare > 0) {
                max = t;
            }
        }
        return max;
    }

    public static<T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T max = iterator.next();
        while(iterator.hasNext()) {
            T element = iterator.next();
            int thisCompare = comparator.compare(element, max);
            if(thisCompare > 0) {
                max = element;
            }
        }
        return max;
    }

    public static<T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T max = iterator.next();
        while(iterator.hasNext()) {
            T element = iterator.next();
            int thisCompare = comparator.compare(element, max);
            if(thisCompare > 0) {
                max = element;
            }
        }
        return max;
    }

    public static<T extends Comparable<? super T>> T min(T  first, T  second){
        if(first.compareTo(second) < 0){
            return first;
        } else {
            return second;
        }
    }

    public static<T extends Comparable<? super T>> T min(T[] array){
        T min = null;
        for (T element : array) {
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) {
                min = element;
            }
        }
        return min;
    }

    public static<T extends Comparable<? super T>> T min(Iterable<T> iterable){
        T min = null;
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) min = element;
        }
        return min;
    }

    public static<T extends Comparable<? super T>> T min(Iterator<T> iterator){
        T min = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) min = element;
        }
        return min;
    }

    public static<T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator){
        int compare = comparator.compare(first, second);
        if (compare < 0) {
            return first;
        } else {
            return second;
        }
    }

    public static<T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator){
        T min = null;
        for (T element : array) {
            if (min == null) min = element;
            else {
                int thisCompare = comparator.compare(element, min);
                if (thisCompare < 0) min = element;
            }
        }
        return min;
    }

    public static<T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        T min = null;
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else {
                int thisCompare = comparator.compare(element, min);
                if (thisCompare < 0) min = element;
            }
        }
        return min;
    }

    public static<T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T min = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else {
                int thisCompare = comparator.compare(element, min);
                if (thisCompare < 0) min = element;
            }
        }
        return min;
    }

    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        for(T obj : array)
        {
            if(pred.predicate(obj))
            {
                newList.add(obj);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > newList.size())
        {
            paginatedList = newList.subList(startIndex, newList.size());
        }
        else
        {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                list.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }


    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                list.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

}
