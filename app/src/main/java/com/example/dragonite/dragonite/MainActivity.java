package com.example.dragonite.dragonite;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dragonite.dragonite.Model.Pokemon;
import com.example.dragonite.dragonite.Storage.PokemonAccess;
import com.example.dragonite.dragonite.Storage.PokemonDBHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Spinner spinner;

    private RecyclerViewAdapter adapter;

    PokemonDBHelper myDbHelper;
    PokemonAccess myDbAccess;

    private RecyclerView rView;

    Button errorRun;
    TextView mTxtDisplay;

    List<Pokemon> pokemonToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.chartButton);

        errorRun = (Button) findViewById(R.id.errorRun);
        errorRun.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent showChart = new Intent(context, ChartActivity.class);
                context.startActivity(showChart);
            }
        });
        myDbHelper = new PokemonDBHelper(this);
        myDbAccess = new PokemonAccess(myDbHelper);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 4);

        rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(gridLayoutManager);

        boolean databaseExists = databaseExists();
        if (databaseExists==true){//if there is a database, load the display using the database
            pokemonToSearch=myDbAccess.getAll();
            System.out.println("setdisplay called!");
            setDisplay(pokemonToSearch);
            setSpinner();
        }
        else{//if there is no database, go get the info from poke api using the getPokemon() method
            pokemonToSearch=myDbAccess.getAll();
            getPokemon();
        }

    }

    private boolean databaseExists(){
        boolean isExists = false;
        if(myDbAccess.getAll().size()>0){
            isExists=true;
        }
        return isExists;
    }


    private void setSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner);

        List<Pokemon> pok = myDbAccess.getAll();

        List<String> types = new ArrayList<>();
        types.add("Select type...");
        types.add("FAIRY");
        types.add("STEEL");
        types.add("DARK");
        types.add("DRAGON");
        types.add("GHOST");
        types.add("ROCK");
        types.add("BUG");
        types.add("PSYCHIC");
        types.add("FLYING");
        types.add("GROUND");
        types.add("POISON");
        types.add("FIGHT");
        types.add("GRASS");
        types.add("ICE");
        types.add("ELECTR");
        types.add("WATER");
        types.add("FIRE");
        types.add("NORMAL");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, types);
        //android.R.layout.simple_spinner_item
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        System.out.println("The spinner is ready");

        //is pokemontosearch null?
        if(pok.size() == 0){
            System.out.println("There are no pokemon in the database to pass into the filter method. Please check the database");
        }
        else{
            System.out.println("Getting the list of Pokemon to pass into the filter method for filter types: How big is this list? The size of pokemon to search is " + pok.size());
        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(" you picked: " + parent.getItemAtPosition(position).toString());
                String selection = parent.getItemAtPosition(position).toString();

                if(parent.getItemAtPosition(position).toString().equals("Select type...")){
                    System.out.println("RESETTING TO DEFAULT FILTER");
                    pokemonToSearch = myDbAccess.getAll();
                    setDisplay(pokemonToSearch);

                }
                else {
                    List<Pokemon>pok = myDbAccess.getAll();
                    final List<Pokemon> filteredList = filterTypes(pok, selection);//returns list of pokemon that fit the selection criteria
                    adapter.setFilter(filteredList);
                    //setDisplay(filteredList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    int urlNumber = 1;
    private void getPokemon() {
        mTxtDisplay = (TextView) findViewById(R.id.txtDisplay);

        String url = "http://pokeapi.co/api/v2/pokemon/" + urlNumber + "/";
        RetryPolicy retryPolicy = new DefaultRetryPolicy(40000,2,1.0f);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mTxtDisplay.setText("Please wait \n\n Installing application. \n This process can take up to 25 minutes. \n\n\n\n\n Hello Trainer! \n\n My name is Mewdex and I am here to equip you with the tools you need to know your pokemon counters to be effective in battle! Use the Pokemon Counters Chart to see what Pokemon types counter each other and use the filter options to search for those Pokemon. \n See you at the Gym, Trainer!");
                        try {
                            JSONObject json = new JSONObject(String.valueOf(response));
                            Log.d("debug", "im in the try block");
                            JSONParser jsonParser = new JSONParser(MainActivity.this);
                            jsonParser.onSuccess(json, new JSONParser.VolleyCallback() {
                                @Override
                                public void onSuccess(int j) throws JSONException {
                                    urlNumber ++;
                                    System.out.println("url number is "+ urlNumber);
                                    System.out.println("there are "+ myDbAccess.getAll().size()+ " items in the database now");
                                    System.out.println("the callback pokemon number was " + j);
                                    //if(urlNumber > 8){
                                    if(j==150){
                                        System.out.println("made it to the 150th JSON callback");
                                        pokemonToSearch = myDbAccess.getAll();

                                        setDisplay(pokemonToSearch);
                                        setSpinner();

                                        return;
                                    }
                                    getPokemon();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        urlNumber ++;
                        if(urlNumber>150) {
                            System.out.println("made it to number 150 in the URL call");
                            pokemonToSearch = myDbAccess.getAll();

                            setDisplay(pokemonToSearch);
                            setSpinner();

                            return;
                        }
                        getPokemon();

//                        System.out.println("hello");
//                        mTxtDisplay.setText("There was a problem with the network. Do you want to run anyway? You may have incomplete set of pokemon");
//                        //retry
//                        //button run anyway
//                        errorRun.setVisibility(View.VISIBLE);
//                        errorRun.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                setDisplay(pokemonToSearch);
//                                setSpinner();
//                            }
//                        });


                    }
                });
        jsObjRequest.setRetryPolicy(retryPolicy);

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }


    public void getEvol(JSONObject response) throws JSONException {

        String pokemonSpecies = response.getJSONObject("species").getString("url");
        SpeciesParser sp = new SpeciesParser(this);
        sp.speciesVolleyRequest(pokemonSpecies, new SpeciesParser.VolleyCallback() {
            @Override
            public void onSuccess(String bm) {
                EvolutionParser ep = new EvolutionParser(MainActivity.this);
                ep.getChain(bm, new EvolutionParser.VolleyCallback() {
                    @Override
                    public void onSuccess(String bm) {
                        String[] arrayOfEvolutions = bm.split("|");

                        List<Pokemon>allPokemons = myDbAccess.getAll();

                        for(Pokemon p : allPokemons){
                            for(int i = 0; i<arrayOfEvolutions.length; i++){
                                if(p.getName().equals(arrayOfEvolutions[i])){
                                    p.setEvolutionChain(bm);
                                }

                            }
                        }

                    }
                });

            }
        });


    }



    private List<Pokemon> getAllItemList(){

        List<Pokemon> allItems = myDbAccess.getAll();

        return allItems;
    }

    private void setDisplay(List<Pokemon> hello){
        mTxtDisplay = (TextView) findViewById(R.id.txtDisplay);
        mTxtDisplay.setText("");
        errorRun.setVisibility(View.INVISIBLE);//dont want the error button to show with menu

        System.out.println("The display is set for the following pokemon: ");
        for (Pokemon p : hello) {
            System.out.println("- " + p.getName());
        }

        List<Pokemon> rowListItem = getAllItemList();
        System.out.println("oooooooooooooooooooooooooooooooooooooo");
        //check for any null icons - or icons that were not received
        for (Pokemon p: rowListItem){
            if(p.getIcon()== null){
                System.out.println(p.getName() + "icon is null");
            }
        }
        System.out.println("YOU CALLED SET DISPLAY");

        adapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);//here your arraylist of pokemon
        rView.setAdapter(adapter);
        System.out.println("SET DISPLAY WORKED!");


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        final MenuItem item = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        adapter.setFilter(pokemonToSearch);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        //setSpinner();//hmm
                        return true;
                    }
                });
        return true;
    }


    public boolean onQueryTextChange(String searchText) {
        final List<Pokemon>filteredList = filter(pokemonToSearch, searchText);
        adapter.setFilter(filteredList);
        return true;
    }


    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<Pokemon> filter(List<Pokemon>originalList, String searchText) {
        final List<Pokemon> filteredList = new ArrayList<>();
        for (Pokemon pokemon:originalList) {
            final String text = pokemon.getName().toLowerCase();
            if (text.contains(searchText)) {
                filteredList.add(pokemon);
            }
        }
        return filteredList;
    }
    private List<Pokemon> filterTypes (List<Pokemon>originalList, String selection) {
        final List<Pokemon> filteredList = new ArrayList<>();
        System.out.println("How big is the filteredList right now. Check if zero: " + filteredList.size());

        for (Pokemon pokemon:originalList) {
            System.out.println("Database has these pokemons: " + pokemon.getName());

            if ((pokemon.getTypes().toUpperCase()).contains(selection) == true) {
                filteredList.add(pokemon);
            }
        }
        System.out.println("You clicked a filter. This is the filtered list: " + filteredList.size());

        return filteredList;
    }

}
