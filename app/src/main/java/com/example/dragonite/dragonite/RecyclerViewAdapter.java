package com.example.dragonite.dragonite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dragonite.dragonite.Model.Pokemon;
import com.example.dragonite.dragonite.R;
import com.example.dragonite.dragonite.RecyclerViewHolders;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Pokemon> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Pokemon> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.pokemon = itemList.get(position);

        holder.countryName.setText(itemList.get(position).getName().substring(0,1).toUpperCase() + itemList.get(position).getName().substring(1));
        //location[i].substring(0, 1).toUpperCase() + location[i].substring(1)
        byte[] byteArray = itemList.get(position).getIcon();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        holder.countryPhoto.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setFilter(List <Pokemon> filteredPokemon) {
        itemList = new ArrayList<>();
        itemList.addAll(filteredPokemon);
        notifyDataSetChanged();
    }

}