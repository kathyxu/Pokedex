package com.example.dragonite.dragonite;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kathy on 20/09/2016.
 */
public class getIcon {
//    public JSONParser jsonParser;
//
//    public getIcon(JSONParser jsonparser){
//        this.jsonParser = jsonparser;
//    }
//
//    public void getImage(String url) {
//         //Bitmap icon = null;
//        ImageView mImageView;
//        //mImageView = (ImageView) findViewById(R.id.myImage);
//
//
//// Retrieves an image specified by the URL, displays it in the UI.
//        ImageRequest request = new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        //mImageView.setImageBitmap(bitmap);
//                        jsonParser.itemGot(bitmap);
//                    }
//                }, 0, 0, null,
//                new Response.ErrorListener() {
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("debug", "image did not go and get");
//                    }
//                });
//// Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(this).addToRequestQueue(request);
//
//        //return icon;
//    }
//
//
}
