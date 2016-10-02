package com.example.dragonite.dragonite.Storage.Tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import com.example.dragonite.dragonite.Model.Pokemon;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kathy on 14/09/2016.
 */
public class PokemonContract {
    public static final String TABLE_NAME = "pokemon";
    private final SQLiteOpenHelper dbHelper;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";

    //create query
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    PokemonEntry._ID + " INTEGER PRIMARY KEY," +
                    PokemonEntry.COLUMN_NAME_ID + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_WEIGHT + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_HEIGHT + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_IMAGE + BLOB_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_SPEED + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_SPECIAL_DEFENSE + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_SPECIAL_ATTACK + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_DEFENSE + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_ATTACK + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_HP + INT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_MOVES + TEXT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_TYPES + TEXT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_ABILITIES + TEXT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_EVOLUTION + TEXT_TYPE + COMMA_SEP +
                    PokemonEntry.COLUMN_NAME_LOCATION + TEXT_TYPE +
                    " )";

    //delete query
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    //abstract class containing values
    public abstract class PokemonEntry implements BaseColumns {
//        this.id = id;
//        this.name = name;
//        this.weight = weight;
//        this.mainIcon = mainIcon;
//        this.speed = speed;
//        this.special_defense = special_defense;
//        this.special_attack = special_attack;
//        this.defense = defense;
//        this.attack = attack;
//        this.hp = hp;

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_HEIGHT = "height";

        public static final String COLUMN_NAME_IMAGE = "icon";
        public static final String COLUMN_NAME_SPEED = "speed";
        public static final String COLUMN_NAME_SPECIAL_DEFENSE = "special_defense";
        public static final String COLUMN_NAME_SPECIAL_ATTACK = "special_attack";
        public static final String COLUMN_NAME_DEFENSE = "defense";
        public static final String COLUMN_NAME_ATTACK = "attack";
        public static final String COLUMN_NAME_HP = "hp";
        public static final String COLUMN_NAME_TYPES = "types";
        public static final String COLUMN_NAME_MOVES = "moves";
        public static final String COLUMN_NAME_ABILITIES = "abilities";
        public static final String COLUMN_NAME_EVOLUTION = "evolution";
        public static final String COLUMN_NAME_LOCATION = "location";



    }

    public PokemonContract(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    //insert values of pokemon into content value
    public long insert(Pokemon pokemon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PokemonEntry.COLUMN_NAME_ID, pokemon.getId());
        values.put(PokemonEntry.COLUMN_NAME_NAME, pokemon.getName());
        values.put(PokemonEntry.COLUMN_NAME_WEIGHT, pokemon.getWeight());
        values.put(PokemonEntry.COLUMN_NAME_HEIGHT, pokemon.getHeight());

        values.put(PokemonEntry.COLUMN_NAME_IMAGE, pokemon.getIcon());
        values.put(PokemonEntry.COLUMN_NAME_SPEED, pokemon.getSpeed());
        values.put(PokemonEntry.COLUMN_NAME_SPECIAL_DEFENSE, pokemon.getSpecial_defense());
        values.put(PokemonEntry.COLUMN_NAME_SPECIAL_ATTACK, pokemon.getSpecial_attack());
        values.put(PokemonEntry.COLUMN_NAME_DEFENSE, pokemon.getDefense());
        values.put(PokemonEntry.COLUMN_NAME_ATTACK, pokemon.getAttack());
        values.put(PokemonEntry.COLUMN_NAME_HP, pokemon.getHp());
        //byte[] img = getBitmapAsByteArray(pokemon.getIcon());
        //values.put(PokemonEntry.COLUMN_NAME_IMAGE); //img);
        values.put(PokemonEntry.COLUMN_NAME_TYPES, pokemon.getTypes());
        values.put(PokemonEntry.COLUMN_NAME_MOVES, pokemon.getMoves());
        values.put(PokemonEntry.COLUMN_NAME_ABILITIES, pokemon.getAbilities());
        values.put(PokemonEntry.COLUMN_NAME_EVOLUTION, pokemon.getEvolutionChain());
        values.put(PokemonEntry.COLUMN_NAME_LOCATION, pokemon.getLocations());


        long newRowId;
        newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        return newRowId;
    }

    //get all pokemons
    public List<Pokemon> getPokemons(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Columns to query
        String[] columns = {
                PokemonEntry._ID,
                PokemonEntry.COLUMN_NAME_ID,
                PokemonEntry.COLUMN_NAME_NAME,
                PokemonEntry.COLUMN_NAME_WEIGHT,
                PokemonEntry.COLUMN_NAME_HEIGHT,
                PokemonEntry.COLUMN_NAME_IMAGE,
                PokemonEntry.COLUMN_NAME_SPEED,
                PokemonEntry.COLUMN_NAME_SPECIAL_DEFENSE,
                PokemonEntry.COLUMN_NAME_SPECIAL_ATTACK,
                PokemonEntry.COLUMN_NAME_DEFENSE,
                PokemonEntry.COLUMN_NAME_ATTACK,
                PokemonEntry.COLUMN_NAME_HP,
                PokemonEntry.COLUMN_NAME_TYPES,
                PokemonEntry.COLUMN_NAME_MOVES,
                PokemonEntry.COLUMN_NAME_ABILITIES,
                PokemonEntry.COLUMN_NAME_EVOLUTION,
                PokemonEntry.COLUMN_NAME_LOCATION


        };

        //Sort order
        String sortOrder = PokemonEntry.COLUMN_NAME_ID;

        Cursor cur = db.query(
                TABLE_NAME,  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List<Pokemon> pokemons = new ArrayList<>();
//        PokemonEntry._ID,
//                PokemonEntry.COLUMN_NAME_ID,
//                PokemonEntry.COLUMN_NAME_NAME,
//                PokemonEntry.COLUMN_NAME_WEIGHT,
//                PokemonEntry.COLUMN_NAME_IMAGE,
//                PokemonEntry.COLUMN_NAME_SPEED,
//                PokemonEntry.COLUMN_NAME_SPECIAL_DEFENSE,
//                PokemonEntry.COLUMN_NAME_SPECIAL_ATTACK,
//                PokemonEntry.COLUMN_NAME_DEFENSE,
//                PokemonEntry.COLUMN_NAME_ATTACK,
//                PokemonEntry.COLUMN_NAME_HP

        // add your pokemon into db
        while (cur.moveToNext()){
            Pokemon mon = new Pokemon();
            mon.setId(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_ID)));
            mon.setName(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_NAME)));
            mon.setWeight(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_WEIGHT)));
            mon.setHeight(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_HEIGHT)));
            //byte[] img = cur.getBlob(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_IMAGE));
            mon.setIcon(cur.getBlob(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_IMAGE)));
            mon.setSpeed(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_SPEED)));
            mon.setSpecial_defense(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_SPECIAL_DEFENSE)));
            mon.setSpecial_attack(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_SPECIAL_ATTACK)));
            mon.setDefense(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_DEFENSE)));
            mon.setAttack(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_ATTACK)));
            mon.setHp(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_HP)));
            mon.setTypes(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_TYPES)));
            mon.setMoves(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_MOVES)));
            mon.setAbilities(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_ABILITIES)));
            mon.setEvolutionChain(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_EVOLUTION)));
            mon.setLocations(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_LOCATION)));

            //mon.setIcon(BitmapFactory.decodeByteArray(img, 0, img.length));

            pokemons.add(mon);
        }

        cur.close();
        db.close();
        return pokemons;
    }

    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //WHERE ID = ?
        String selection = PokemonEntry.COLUMN_NAME_ID + " = ?";

        //All the different IDs its equal to
        String[] selectionArgs = {String.valueOf(id)};

        db.delete(TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    //read values of one pokemon with id
    public Pokemon getPokemon(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PokemonEntry.COLUMN_NAME_ID + " = ?";

        String[] selectionArgs = {String.valueOf(id)};

        //Columns to query
        String[] columns = {
                PokemonEntry._ID,
                PokemonEntry.COLUMN_NAME_ID,
                PokemonEntry.COLUMN_NAME_NAME,
                PokemonEntry.COLUMN_NAME_WEIGHT,
                PokemonEntry.COLUMN_NAME_HEIGHT,
                PokemonEntry.COLUMN_NAME_IMAGE,
                PokemonEntry.COLUMN_NAME_SPEED,
                PokemonEntry.COLUMN_NAME_SPECIAL_DEFENSE,
                PokemonEntry.COLUMN_NAME_SPECIAL_ATTACK,
                PokemonEntry.COLUMN_NAME_DEFENSE,
                PokemonEntry.COLUMN_NAME_ATTACK,
                PokemonEntry.COLUMN_NAME_HP,
                PokemonEntry.COLUMN_NAME_TYPES,
                PokemonEntry.COLUMN_NAME_MOVES,
                PokemonEntry.COLUMN_NAME_ABILITIES,
                PokemonEntry.COLUMN_NAME_EVOLUTION,
                PokemonEntry.COLUMN_NAME_LOCATION

        };

        Cursor cur = db.query(
                TABLE_NAME,  // The table to query
                columns,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        Pokemon mon = null;

        if(cur.moveToNext()){
            mon = new Pokemon();
            mon.setId(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_ID)));
            mon.setName(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_NAME)));
            mon.setWeight(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_WEIGHT)));
            mon.setHeight(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_HEIGHT)));
            //byte[] img = cur.getBlob(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_IMAGE));
            mon.setIcon(cur.getBlob(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_IMAGE)));
//if((cur.getBlob(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_IMAGE))==null)){
//                System.out.println("it was null!! is this the problem?" + cur.getBlob(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_IMAGE)));
//
//            }
//            else{
//                System.out.println("its not null! is this the problem?" + cur.getBlob(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_IMAGE)));
//            }

            mon.setSpeed(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_SPEED)));
            mon.setSpecial_defense(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_SPECIAL_DEFENSE)));
            mon.setSpecial_attack(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_SPECIAL_ATTACK)));
            mon.setDefense(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_DEFENSE)));
            mon.setAttack(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_ATTACK)));
            mon.setHp(cur.getInt(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_HP)));
            //mon.setIcon(BitmapFactory.decodeByteArray(img, 0, img.length));
            mon.setTypes(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_TYPES)));
            mon.setMoves(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_MOVES)));
            mon.setAbilities(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_ABILITIES)));
            mon.setEvolutionChain(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_EVOLUTION)));
            mon.setLocations(cur.getString(cur.getColumnIndexOrThrow(PokemonEntry.COLUMN_NAME_LOCATION)));

        }
        cur.close();
        db.close();
        return mon;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}