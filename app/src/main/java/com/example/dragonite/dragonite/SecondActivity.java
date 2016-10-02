package com.example.dragonite.dragonite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.dragonite.dragonite.Model.Pokemon;
import com.example.dragonite.dragonite.Storage.PokemonAccess;
import com.example.dragonite.dragonite.Storage.PokemonDBHelper;

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kathy on 24/09/2016.
 */
public class SecondActivity extends AppCompatActivity{
    private ImageView image;
    private TextView name;
    private TextView id;
    private TextView height;
    private TextView weight;
    private TextView hp;
    private TextView attack;
    private TextView defense;
    private TextView speed;
    private TextView special_attack;
    private TextView special_defense;
    private ProgressBar hpProgress;
    private ProgressBar speedProgress;
    private ProgressBar attackProgress;
    private ProgressBar defenseProgress;
    private ProgressBar specialAtkProgress;
    private ProgressBar specialDefProgress;

    private TextView types;
    private TextView moves;
    private TextView abilities;

    private TableLayout movesTable;

    private TextView movesOne;
    private TextView movesTwo;
    private TextView movesThree;

    private ImageView evolutionOne;
    private ImageView evolutionTwo;
    private ImageView evolutionThree;

    private TextView evolutionOneLabel;
    private TextView evolutionTwoLabel;
    private TextView evolutionThreeLabel;

    private TextView locationOne;
    private TextView locationTwo;
    private TextView locationThree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        fillFields();
    }

    private void fillFields() {
        PokemonDBHelper myDbHelper = new PokemonDBHelper(this);
        PokemonAccess myDbAccess = new PokemonAccess(myDbHelper);
        Pokemon detailPokemon = myDbAccess.getPokemon(getIntent().getStringExtra("number"));

//        number = (TextView) findViewById(R.id.number);
//        number.setText(detailPokemon.getNumber());

        name = (TextView) findViewById(R.id.name);
        name.setText(detailPokemon.getName().substring(0, 1).toUpperCase() + detailPokemon.getName().substring(1));
        //name.setText("hello!");


        byte[] byteArray = detailPokemon.getIcon();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image = (ImageView) findViewById(R.id.pokemonImage);
        image.setImageBitmap(bmp);

        id = (TextView) findViewById(R.id.id);
        id.setText("ID: " + detailPokemon.getId());

        System.out.println("the height before setting text is " + detailPokemon.getHeight());
        height = (TextView) findViewById(R.id.height);
        Float f = Float.valueOf(detailPokemon.getHeight());
        String db = String.valueOf(f / 10);
        height.setText("Height: " + db + "m");

        weight = (TextView) findViewById(R.id.weight);
        Float w = Float.valueOf(detailPokemon.getWeight());
        String wb = String.valueOf(w / 10);
        weight.setText("Weight: " + wb + "kg");


        hp = (TextView) findViewById(R.id.hp);
        hp.setText("HP:                         " + detailPokemon.getHp());

        hpProgress = (ProgressBar) findViewById(R.id.hpProgressBar);
        hpProgress.setProgress(detailPokemon.getHp());

        attack = (TextView) findViewById(R.id.attack);
        attack.setText("ATTACK:               " + detailPokemon.getAttack());

        attackProgress = (ProgressBar) findViewById(R.id.attackProgressBar);
        attackProgress.setProgress(detailPokemon.getAttack());

        defense = (TextView) findViewById(R.id.defense);
        defense.setText("DEFENSE:             " + detailPokemon.getDefense());

        defenseProgress = (ProgressBar) findViewById(R.id.defenseProgressBar);
        defenseProgress.setProgress(detailPokemon.getDefense());

        speed = (TextView) findViewById(R.id.speed);
        speed.setText("SPEED:                  " + detailPokemon.getSpeed());

        speedProgress = (ProgressBar) findViewById(R.id.speedProgressBar);
        speedProgress.setProgress(detailPokemon.getSpeed());

        special_attack = (TextView) findViewById(R.id.special_attack);
        special_attack.setText("SP ATTACK:         " + detailPokemon.getSpecial_attack());

        specialAtkProgress = (ProgressBar) findViewById(R.id.specialAtkProgressBar);
        specialAtkProgress.setProgress(detailPokemon.getSpecial_attack());

        special_defense = (TextView) findViewById(R.id.special_defense);
        special_defense.setText("SP DEFENSE:       " + detailPokemon.getSpecial_defense());

        specialDefProgress = (ProgressBar) findViewById(R.id.specialDefProgressBar);
        specialDefProgress.setProgress(detailPokemon.getSpecial_defense());

        types = (TextView) findViewById(R.id.types);
        String[] type = detailPokemon.getTypes().split(",");
        String displayTypes = "";
        System.out.println("displayTypes is " + displayTypes);
        for (int i = 1; i < type.length; i++) {
            String capital = type[i].toUpperCase();
            displayTypes = displayTypes + "\n" + capital;
        }
        types.setText(displayTypes);
        //types.setText("Types: \n" + detailPokemon.getTypes());

//        moves = (TextView) findViewById(R.id.moves);
//        moves.setText("Moves: \n" + detailPokemon.getMoves());

        //moves = (TextView) findViewById(R.id.moves);
        String[] move = detailPokemon.getMoves().split(",");//contains all moves in arraylist
        movesOne = (TextView) findViewById(R.id.movesOne);
        movesTwo = (TextView) findViewById(R.id.movesTwo);
        movesThree = (TextView) findViewById(R.id.movesThree);

        String moveOne = "";
        String moveTwo = "";
        String moveThree = "";

        for (int i = 1; i < move.length; i++) {
            if (i % 3 == 0) {
                moveThree = moveThree + "\n" + (move[i].substring(0, 1).toUpperCase() + move[i].substring(1));
            } else if (i % 3 == 1) {
                moveOne = moveOne + "\n" + (move[i].substring(0, 1).toUpperCase() + move[i].substring(1));
            } else {
                moveTwo = moveTwo + "\n" + (move[i].substring(0, 1).toUpperCase() + move[i].substring(1));
            }
        }
        movesOne.setText(moveOne);
        movesTwo.setText(moveTwo);
        movesThree.setText(moveTwo);


        System.out.println("hello the locations for this pokemon are: " + detailPokemon.getLocations());
        locationOne = (TextView) findViewById(R.id.locOne);
        locationTwo = (TextView) findViewById(R.id.locTwo);
        locationThree = (TextView) findViewById(R.id.locThree);

        if (detailPokemon.getLocations() != null) {
            String[] location = detailPokemon.getLocations().split(",");//contains all moves in arraylist

            String locationsOne = "";
            String locationsTwo = "";
            String locationsThree = "";

            if (location.length != 1) {
                //
                for (int i = 1; i < location.length; i++) {
                    if (i % 3 == 0) {
                        locationsThree = locationsThree + "\n" + (location[i].substring(0, 1).toUpperCase() + location[i].substring(1));
                    } else if (i % 3 == 1) {
                        locationsOne = locationsOne + "\n" + (location[i].substring(0, 1).toUpperCase() + location[i].substring(1));
                    } else {
                        locationsTwo = locationsTwo + "\n" + (location[i].substring(0, 1).toUpperCase() + location[i].substring(1));
                    }
                }
                locationOne.setText(locationsOne);
                locationTwo.setText(locationsTwo);
                locationThree.setText(locationsThree);
            } else {
                locationOne.setText("Not found anywhere");
            }

        } else {
            locationOne.setText("Not found anywhere");

        }


//        String displayMoves="";
//        //String displayMoves = move[1].substring(0, 1).toUpperCase() + move[1].substring(1);
//        System.out.println("displayMoves is "+ displayMoves);
//        for (int i = 1 ; i<move.length; i++){
//            String capital = move[i].substring(0, 1).toUpperCase() + move[i].substring(1);
//            displayMoves = displayMoves + "\n" + capital;
//        }
//        moves.setText("Moves: \n" + displayMoves);


//        movesTable = (TableLayout) findViewById(R.id.table);
//        //ArrayList<String> moves = new ArrayList<>();
//        //String[] move = detailPokemon.getMoves().split(",");
////        for (int i = 0 ; i < move.length; i+=4){
//        int k = 0 ;
//        while(k<move.length){
//            TableRow row = new TableRow(this);
//            String hi = move[k];
////            String hi2 = move [k+1];
////            String hi3 = move[k+2];
////            String hi4 = move[k+3];
//
//            TextView tv = new TextView(this);
//            tv.setText(hi);
////            TextView tv2 = new TextView(this);
////            tv2.setText(hi2);
////            TextView tv3 = new TextView(this);
////            tv3.setText(hi3);
////            TextView tv4 = new TextView(this);
////            tv4.setText(hi4);
//
//            row.addView(tv);
////            row.addView(tv2);
////            row.addView(tv3);
////            row.addView(tv4);
//
//            movesTable.addView(row);
//          k+=4;
//        }


        abilities = (TextView) findViewById(R.id.abilities);
        String[] ability = detailPokemon.getAbilities().split(",");
        String displayAbility = "";
        System.out.println("displayMoves is " + displayAbility);
        for (int i = 1; i < ability.length; i++) {
            String capital = ability[i].substring(0, 1).toUpperCase() + ability[i].substring(1);
            displayAbility = displayAbility + "\n" + capital;

        }
        abilities.setText(displayAbility);

//evolutions
        evolutionOne = (ImageView) findViewById(R.id.evolutionOne);
        evolutionTwo = (ImageView) findViewById(R.id.evolutionTwo);
        evolutionThree = (ImageView) findViewById(R.id.evolutionThree);

        evolutionOneLabel = (TextView) findViewById(R.id.evolutionLabelOne);
        evolutionTwoLabel = (TextView) findViewById(R.id.evolutionLabelTwo);
        evolutionThreeLabel = (TextView) findViewById(R.id.evolutionLabelThree);

        String evolutionChain = detailPokemon.getEvolutionChain();
        System.out.println("ldkldk" + evolutionChain);
if(evolutionChain==null) {
    System.out.println("evolution chain is null you head. ...");
}
        else{
        String[] evolutions = evolutionChain.split(",");
        //System.out.println("one of the evolutions is : " + evolutions[1] + " and the length is " + evolutionChain.length());

        List<Pokemon> pokemonList = myDbAccess.getAll();

        if (evolutions.length == 2) {
            String baby = evolutions[1];
            System.out.println(baby);

            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println("sdf   " + pokemonList.get(i).getName());

                if ((pokemonList.get(i).getName()).equals(baby)) {
                    byte[] apple = pokemonList.get(i).getIcon();
                    Bitmap pear = BitmapFactory.decodeByteArray(apple, 0, apple.length);
                    evolutionOne.setImageBitmap(pear);
                    System.out.println("set the evolution One");
                    evolutionOneLabel.setText(pokemonList.get(i).getName());
                }
            }
        } else if (evolutions.length == 3) {
            String baby = evolutions[1];
            System.out.println(baby);
            String child = evolutions[2];
            System.out.println(child);

            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println("sdf   " + pokemonList.get(i).getName());

                if ((pokemonList.get(i).getName()).equals(baby)) {
                    byte[] apple = pokemonList.get(i).getIcon();
                    Bitmap pear = BitmapFactory.decodeByteArray(apple, 0, apple.length);
                    evolutionOne.setImageBitmap(pear);
                    System.out.println("set the evolution One");
                    evolutionOneLabel.setText(pokemonList.get(i).getName());
                }
                if ((pokemonList.get(i).getName()).equals(child)) {
                    byte[] apple = pokemonList.get(i).getIcon();
                    Bitmap pear = BitmapFactory.decodeByteArray(apple, 0, apple.length);
                    evolutionTwo.setImageBitmap(pear);
                    System.out.println("set the evolution 2");
                    evolutionTwoLabel.setText(pokemonList.get(i).getName());

                }

            }
        } else if (evolutions.length == 4) {
            String baby = evolutions[1];
            System.out.println(baby);
            String child = evolutions[2];
            System.out.println(child);
            String adult = evolutions[3];
            System.out.println(adult);

            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println("sdf   " + pokemonList.get(i).getName());

                if ((pokemonList.get(i).getName()).equals(baby)) {
                    byte[] apple = pokemonList.get(i).getIcon();
                    Bitmap pear = BitmapFactory.decodeByteArray(apple, 0, apple.length);
                    evolutionOne.setImageBitmap(pear);
                    System.out.println("set the evolution One");
                    evolutionOneLabel.setText(pokemonList.get(i).getName());
                }
                if ((pokemonList.get(i).getName()).equals(child)) {
                    byte[] apple = pokemonList.get(i).getIcon();
                    Bitmap pear = BitmapFactory.decodeByteArray(apple, 0, apple.length);
                    evolutionTwo.setImageBitmap(pear);
                    System.out.println("set the evolution 2");
                    evolutionTwoLabel.setText(pokemonList.get(i).getName());

                }
                if ((pokemonList.get(i).getName()).equals(adult)) {
                    byte[] apple = pokemonList.get(i).getIcon();
                    Bitmap pear = BitmapFactory.decodeByteArray(apple, 0, apple.length);
                    evolutionThree.setImageBitmap(pear);
                    System.out.println("set the evolution 3");
                    evolutionThreeLabel.setText(pokemonList.get(i).getName());

                }

            }
        } else {

        }

    }
        System.out.println("sldkfj");

//        for(int i = 0; i < pokemonList.size(); i++) {
//            System.out.println("sdf   " + pokemonList.get(i).getName());
//
//            if((pokemonList.get(i).getName()).equals(baby)){
//                byte[] apple = pokemonList.get(i).getIcon();
//                    Bitmap pear = BitmapFactory.decodeByteArray(apple,0,apple.length);
//                    evolutionOne.setImageBitmap(pear);
//                    System.out.println("set the evolution One");
//                    evolutionOneLabel.setText(pokemonList.get(i).getName());
//            }
//            if((pokemonList.get(i).getName()).equals(child)){
//                byte[] apple = pokemonList.get(i).getIcon();
//                Bitmap pear = BitmapFactory.decodeByteArray(apple,0,apple.length);
//                evolutionTwo.setImageBitmap(pear);
//                System.out.println("set the evolution 2");
//                evolutionTwoLabel.setText(pokemonList.get(i).getName());
//
//            }
//            if((pokemonList.get(i).getName()).equals(adult)){
//                byte[] apple = pokemonList.get(i).getIcon();
//                Bitmap pear = BitmapFactory.decodeByteArray(apple,0,apple.length);
//                evolutionThree.setImageBitmap(pear);
//                System.out.println("set the evolution 3");
//                evolutionThreeLabel.setText(pokemonList.get(i).getName());
//
//            }
//
//        }

//        for(Pokemon p: pokemonList){
//                if(p.getName().equals(baby)){
//                    byte[] apple = p.getIcon();
//                    Bitmap pear = BitmapFactory.decodeByteArray(byteArray,0,apple.length);
//                    evolutionOne.setImageBitmap(pear);
//                    System.out.println("set the evolution One");
//                }
//
//        }
//        for(Pokemon p: pokemonList) {
//            if (p.getName().equals(child)) {
//                byte[] apple = p.getIcon();
//                Bitmap pear = BitmapFactory.decodeByteArray(byteArray, 0, apple.length);
//                evolutionTwo.setImageBitmap(pear);
//                System.out.println("set the evolution Two");
//            }
//        }
//        for(Pokemon p: pokemonList) {
//            if (p.getName().equals(adult)) {
//                byte[] apple = p.getIcon();
//                Bitmap pear = BitmapFactory.decodeByteArray(byteArray, 0, apple.length);
//                evolutionThree.setImageBitmap(pear);
//                System.out.println("set the evolution Three");
//            }
//        }





//        abilities = (TextView) findViewById(R.id.abilities);
//        abilities.setText("Abilities: \n" + detailPokemon.getAbilities());

        System.out.println("in the second activity");
        System.out.println(detailPokemon.getSpecial_attack());
        System.out.println(detailPokemon.getName());
        System.out.println(detailPokemon.getWeight());
        System.out.println(detailPokemon.getHeight());//TODO didnt work
        System.out.println(detailPokemon.getHp());


    }
}
