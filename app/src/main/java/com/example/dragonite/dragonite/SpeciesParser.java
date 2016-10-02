package com.example.dragonite.dragonite;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kathy on 30/09/2016.
 */
public class SpeciesParser {

    private Context mContext;

    public SpeciesParser(Context context){
        mContext = context;
    }

    public void speciesVolleyRequest(String url, final VolleyCallback callback) {

        System.out.println(url);
        RetryPolicy retryPolicy = new DefaultRetryPolicy(10000,2,1.0f);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String evolutionURL = null;
                        try {
                            evolutionURL = getUrl(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        callback.onSuccess(evolutionURL);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("hello");
                    }
                });
        jsObjRequest.setRetryPolicy(retryPolicy);

        MySingleton.getInstance(mContext).addToRequestQueue(jsObjRequest);

    }
    public interface VolleyCallback{
        void onSuccess(String bm);
    }

    public String getUrl(JSONObject json) throws JSONException {
        JSONObject wl = json.getJSONObject("evolution_chain");
        String url = wl.getString("url");

        return url;
    }
}
