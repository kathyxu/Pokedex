package com.example.dragonite.dragonite;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.dragonite.dragonite.Model.Pokemon;
import com.example.dragonite.dragonite.Storage.PokemonAccess;
import com.example.dragonite.dragonite.Storage.PokemonDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kathy on 14/09/2016.
 */
public class JSONParser extends AsyncTask{

    private Context mContext;
    public JSONParser (Context context){
        mContext = context;
    }

    PokemonDBHelper myDbHelper;
    PokemonAccess myDbAccess;

    Pokemon newPokemon = new Pokemon();

    public void onSuccess(final JSONObject response, VolleyCallback volleyCallback) throws JSONException {

        myDbHelper = new PokemonDBHelper(mContext);
        myDbAccess = new PokemonAccess(myDbHelper);


        Log.d("debug", "I got the json, We are in the JSONParser now. The response is: ");
        Log.d("debug", response.toString());


//for the forms section
        JSONArray firstArray = response.getJSONArray("forms");
        JSONObject forms = firstArray.getJSONObject(0);
        Log.d("debug", forms.toString());
        String myName = forms.getString("name");
        Log.d("debug", myName);
        newPokemon.setName(myName);

//weight
        String mWeight = response.getString("weight");
        Log.d("debug", mWeight);
        newPokemon.setWeight(Integer.parseInt(mWeight));

//height
        String mHeight = response.getString("height");
        Log.d("debug", "height is: " + mHeight);
        newPokemon.setHeight(Integer.parseInt(mHeight));

//id
        String mId = response.getString("id");
        Log.d("debug", mId);
        newPokemon.setId(Integer.parseInt(mId));


//for the abilities
        JSONArray abilitiesArray = response.getJSONArray("abilities");
        String abilitiesToAdd = "start";
        for (int i = 0; i < abilitiesArray.length(); i++) {
            Log.d("debug", abilitiesArray.toString());
            JSONObject abilities = abilitiesArray.getJSONObject(i);
            Log.d("debug", abilities.toString());
            //{"slot":3,"is_hidden":true,"ability":{"url":"http:\/\/pokeapi.co\/api\/v2\/ability\/34\/","name":"chlorophyll"}}

            JSONObject ability = abilities.getJSONObject("ability");
            Log.d("debug", ability.toString());
            String mattack = ability.getString("name");//attack is the ability!
            Log.d("debug", mattack);
            abilitiesToAdd = abilitiesToAdd + "," + mattack;
        }
        System.out.println("list of abilities " + abilitiesToAdd);
        newPokemon.setAbilities(abilitiesToAdd);


//stats
        JSONArray statsArray = response.getJSONArray("stats");

        for (int i = 0; i < statsArray.length(); i++) {
            JSONObject stats = statsArray.getJSONObject(i);
            //{"slot":3,"is_hidden":true,"ability":{"url":"http:\/\/pokeapi.co\/api\/v2\/ability\/34\/","name":"chlorophyll"}}
            JSONObject stat = stats.getJSONObject("stat");
            String statName = stat.getString("name");//attack is the ability!
            Log.d("debug", statName);

            String baseStat = stats.getString("base_stat");
            Log.d("debug", baseStat);

            if (statName.equals("hp")) {
                newPokemon.setHp(Integer.parseInt(baseStat));
            } else if (statName.equals("defense")) {
                newPokemon.setDefense(Integer.parseInt(baseStat));
            } else if (statName.equals("special-attack")) {
                newPokemon.setSpecial_attack(Integer.parseInt(baseStat));
                System.out.println("special attack for is " + baseStat);
                System.out.println("special attack for is " + newPokemon.getSpecial_attack());
            } else if (statName.equals("special-defense")) {
                newPokemon.setSpecial_defense(Integer.parseInt(baseStat));
            } else if (statName.equals("speed")) {
                newPokemon.setSpeed(Integer.parseInt(baseStat));
            } else if (statName.equals("attack")) {
                newPokemon.setAttack(Integer.parseInt(baseStat));
            }

        }


//for the moves
        JSONArray movesArray = response.getJSONArray("moves");
        String listOfMoves = "start";
        for (int i = 0; i < movesArray.length(); i++) {
            JSONObject moves = movesArray.getJSONObject(i);
            //{"slot":3,"is_hidden":true,"ability":{"url":"http:\/\/pokeapi.co\/api\/v2\/ability\/34\/","name":"chlorophyll"}}
            JSONObject move = moves.getJSONObject("move");
            String mMove = move.getString("name");//attack is the ability!

            listOfMoves = listOfMoves + "," + mMove;

        }
        System.out.println("list of moves " + listOfMoves);
        newPokemon.setMoves(listOfMoves);

        String back_default = response.getJSONObject("sprites").getString("back_default");
        Log.d("debug", back_default);
        String front_shiny = response.getJSONObject("sprites").getString("front_shiny");
        Log.d("debug", front_shiny);
        String back_shiny = response.getJSONObject("sprites").getString("back_shiny");
        Log.d("debug", back_shiny);

//types
        JSONArray typesArray = response.getJSONArray("types");
        String listOfTypes = "start";

        for (int i = 0; i < typesArray.length(); i++) {
            Log.d("debug", typesArray.toString());
            JSONObject types = typesArray.getJSONObject(i);
            Log.d("debug", types.toString());
            //{"slot":3,"is_hidden":true,"ability":{"url":"http:\/\/pokeapi.co\/api\/v2\/ability\/34\/","name":"chlorophyll"}}
            JSONObject type = types.getJSONObject("type");
            Log.d("debug", type.toString());
            String mType = type.getString("name");
            Log.d("debug", mType);

            listOfTypes = listOfTypes + "," + mType;
        }
        System.out.println("list of types " + listOfTypes);
        newPokemon.setTypes(listOfTypes);

//for locations
        System.out.println("one");
        String locationURL = "http://pokeapi.co" + response.getString("location_area_encounters");//   /api/v2/pokemon/1/encounters
        System.out.println("locationURL is " + locationURL);

        if(locationURL.equals("http://pokeapi.co/api/v2/pokemon/129/encounters")){
            newPokemon.setLocations("Network Error");
        }
        else{

            LocationsParser lp = new LocationsParser(mContext);
            lp.locationsVolleyRequest(locationURL, new LocationsParser.VolleyCallback() {
                @Override
                public void onSuccess(String bm) {
                    newPokemon.setLocations(bm);
                    System.out.println("the locations for " + newPokemon.getName() + " is " + bm);
                }
            });
        }


        String pokemonSpecies = response.getJSONObject("species").getString("url");
        SpeciesParser sp = new SpeciesParser(mContext);
        sp.speciesVolleyRequest(pokemonSpecies, new SpeciesParser.VolleyCallback() {
            @Override
            public void onSuccess(String bm) {
                EvolutionParser ep = new EvolutionParser(mContext);
                ep.getChain(bm, new EvolutionParser.VolleyCallback() {
                    @Override
                    public void onSuccess(String bm) {//bm is in the format: bulbasaur|ivysaur|venusaur
                        newPokemon.setEvolutionChain(bm);

                        //sprite images
                        String front_default = null;
                        try {
                            front_default = response.getJSONObject("sprites").getString("front_default");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("debug", front_default);

                        ImageParser im = new ImageParser(mContext);
                        im.imageVolleyRequest(front_default, new ImageParser.VolleyCallback() {
                            @Override
                            public void onSuccess(Bitmap response) {
                                if(response == null){
                                    System.out.println("null here! The image response is null!");
                                }
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                response.compress(Bitmap.CompressFormat.PNG,100,stream);
                                byte[] byteArray = stream.toByteArray();
                                newPokemon.setIcon(byteArray);
                                System.out.println("The length of "+newPokemon.getName() + "'s icon is " + newPokemon.getIcon().length);

                                myDbAccess.insertPokemon(newPokemon);

                                System.out.println("cool pokemon " + newPokemon.getName() + " was added with " + newPokemon.getIcon().length + " length and " + newPokemon.getAttack() + " as attack and " + newPokemon.getHeight() + " as height and " + newPokemon.getSpecial_attack() + newPokemon.getEvolutionChain() + " and is found at " + newPokemon.getLocations());

                            }

                        });
                    }
                });

            }
        });

        Log.d("debug", "*******************************************************************************************************************************");

        volleyCallback.onSuccess(newPokemon.getId());
    }


    public interface VolleyCallback{
        void onSuccess(int j) throws JSONException;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

//    public void imageRequestTwo(Bitmap bitmap) {
//        //mImageView.setImageBitmap(bitmap);
//        if(bitmap == null){
//            System.out.println("null here! 44");
//        }
//        System.out.println("*B*");
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
//        byte[] byteArray = stream.toByteArray();
//
//        if(byteArray == null){
//            System.out.println("null here! 55");
//        }
//        System.out.println("the name is " + newPokemon.getName());
//        newPokemon.setIcon(byteArray);
//        System.out.println("the byte array is " + newPokemon.getIcon());
//        System.out.println("the byte array size is  " + newPokemon.getIcon().length);
//
//
//        //System.out.println(byteArray);
//        //Decode from byte array to bitmap:
//        //Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
//        //mImageView.setImageBitmap(bitmap);
//    }
}
