package com.example.dragonite.dragonite.Storage;

import android.database.sqlite.SQLiteOpenHelper;

import com.example.dragonite.dragonite.Model.Pokemon;
import com.example.dragonite.dragonite.Storage.Tables.PokemonContract;

import java.util.List;

/**
 * Created by Kathy on 20/09/2016.
 */
public class PokemonAccess implements DbAccess {
    private final PokemonContract pokeBallsContract;

    public PokemonAccess(SQLiteOpenHelper sqLiteOpenHelper) {
        this.pokeBallsContract = new PokemonContract(sqLiteOpenHelper);
    }

    //Example of overriding interface
    @Override
    public List<Pokemon> getAll() {
        return pokeBallsContract.getPokemons();
    }

    //Example standard contract methods
    public void insertPokemon(Pokemon mon){
        pokeBallsContract.insert(mon);
    }

    public void deletePokemon(int id){
        pokeBallsContract.delete(id);
    }

    //Example of extra methods
    public void insertPokemons(List<Pokemon> pokemons){
        for(Pokemon mon : pokemons){
            pokeBallsContract.insert(mon);
        }
    }

    public void deletePokemons(int... ids){
        for(int id : ids){
            pokeBallsContract.delete(id);
        }
    }
    public Pokemon getPokemon(String number){
        return pokeBallsContract.getPokemon(Integer.parseInt(number));
    }

    public boolean hasMon(int id){
        return pokeBallsContract.getPokemon(id) != null;
    }
}
