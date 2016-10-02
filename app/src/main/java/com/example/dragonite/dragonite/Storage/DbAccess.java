package com.example.dragonite.dragonite.Storage;

import com.example.dragonite.dragonite.Model.Pokemon;

import java.util.List;

/**
 * Created by Kathy on 20/09/2016.
 */
public interface DbAccess {
    List<Pokemon> getAll();
//    void deleteAll();
//    void insertAll();
//    void updateAll();
}