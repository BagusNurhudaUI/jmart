package com.BagusJmartMH.controller;

import com.BagusJmartMH.Algorithm;
import com.BagusJmartMH.dbjson.JsonTable;
import com.BagusJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * merupakan method untuk melakukan modifikasi dari class algorithm
 * @param <T>
 */
@RestController
public interface BasicGetController <T extends Serializable>{
    /**
     * Method untuk mengambil suatu objek dari list yang berkaitan dengan tipe objek berdasarkan id-nya
     * @param id id dari suatu objek yang ingin dicari atau diambil
     * @return objek yang ingin dicari atau diambil berdasarkan id, dan null jika tidak ditemukan
     */
    @GetMapping("/id")
    default T getById ( int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    public abstract JsonTable<T> getJsonTable();

    /**
     * Method untuk melakukan paginasi pada collection berdasarkan index page dan ukuran page yang diberikan
     * @param page index page
     * @param pageSize ukuran page (jumlah elemen yang dapat dikandung dalam 1 page)
     * @return list yang berisikan elemen-elemen pada index page dengan ukuran pageSize
     */
    @GetMapping("/page")
    default List<T> getPage(int page, int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, (e) -> true);
    }

}
