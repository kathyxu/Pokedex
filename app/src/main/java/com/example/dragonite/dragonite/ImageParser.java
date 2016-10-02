package com.example.dragonite.dragonite;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.dragonite.dragonite.Model.Pokemon;
import com.example.dragonite.dragonite.Storage.PokemonAccess;
import com.example.dragonite.dragonite.Storage.PokemonDBHelper;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Kathy on 24/09/2016.
 */
public class ImageParser {
    byte[] image = null;

    private Context mContext;

    public ImageParser(Context context){
        mContext = context;
    }

    public void imageVolleyRequest(String imageUrl, final VolleyCallback callback) {

        System.out.println(imageUrl);
        RetryPolicy retryPolicy = new DefaultRetryPolicy(10000,2,1.0f);
        ImageRequest imageRequest = new ImageRequest(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        System.out.println("in image response");
                        //imageRequestTwo(bitmap);
                        try {
                            callback.onSuccess(bitmap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //mImageView.setImageResource(R.drawable.image_load_error);
                    }
                }
        );
        imageRequest.setRetryPolicy(retryPolicy);
        MySingleton.getInstance(mContext).addToRequestQueue(imageRequest);//wtf

//        if(image == null) {
//            System.out.println("the image is now null");
//        }
        //return image;


    }
    public interface VolleyCallback{
        void onSuccess(Bitmap bm) throws JSONException;
    }

    public byte[] itemGot(Bitmap bitmap){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();

//        for(int i = 0; i < byteArray.length; i++) {
//            System.out.println("this pokemon has image array: " + i);
//        }

        //newPokemon.setIcon(byteArray);
        //myDbAccess.insertPokemon(newPokemon);
        return byteArray;
    }

}
