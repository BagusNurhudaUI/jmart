package com.BagusJmartMH.controller;

import com.BagusJmartMH.Algorithm;
import com.BagusJmartMH.dbjson.JsonTable;
import com.BagusJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BasicGetController <T extends Serializable>{
    @GetMapping("/id")
    default T getById (@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }
    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    default List<T> getPage (@PathVariable int page, @PathVariable int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e->true);
    }
}
