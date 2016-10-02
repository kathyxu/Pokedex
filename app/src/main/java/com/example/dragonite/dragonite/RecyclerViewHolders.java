package com.example.dragonite.dragonite;

/**
 * Created by Kathy on 17/09/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragonite.dragonite.Model.Pokemon;

import java.util.List;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;
    public Pokemon pokemon;
    //public TextView number;
    //public Bitmap countryPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
        System.out.println("holder made");
    }

    @Override
    public void onClick(View view) {
        Context context = itemView.getContext();
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        Intent showPokemonIntent = new Intent(context, SecondActivity.class);

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(getAdapterPosition());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        String numberKey = String.valueOf(pokemon.getId());

        //String numberKey = String.valueOf(getAdapterPosition() + 1 );//TODOused to be getAdapterPosition()
        showPokemonIntent.putExtra("number", numberKey);
        context.startActivity(showPokemonIntent);
    }

    public int getPokeObject(List<Pokemon> list, int position){//return id of pokemon
            Pokemon selection = list.get(position);
            return selection.getId();
    }

}