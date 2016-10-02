package com.example.dragonite.dragonite;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kathy on 29/09/2016.
 */
public class EvolutionParser extends AsyncTask {

    private Context mContext;

    public EvolutionParser(Context context){
        mContext = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }


    public void getChain(String url, final VolleyCallback callback){
        System.out.println(url);
        RetryPolicy retryPolicy = new DefaultRetryPolicy(20000,2,1.0f);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String evolutionURL = null;
                        try {
                            evolutionURL = getEvolutionString(response);
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

    public String getEvolutionString (JSONObject response) throws JSONException {

        JSONObject chain = response.getJSONObject("chain");

        System.out.println(chain);

        JSONObject species = chain.getJSONObject("species");
        String name = species.getString("name");
        System.out.println(name);//bulbasaur

        JSONArray asdf = chain.getJSONArray("evolves_to");
        //System.out.println(asdf);
        String myName = "";
        String jj = "";
        if(asdf != null) {
            JSONObject forms = asdf.getJSONObject(0);
            System.out.println(forms);
            JSONObject species1 = forms.getJSONObject("species");

            myName = species1.getString("name");
            System.out.println(myName);//ivysaur


            JSONArray fdsa = forms.getJSONArray("evolves_to");
            jj = "";

            if (fdsa.length() != 0) {
                System.out.println(fdsa);
                JSONObject aa = fdsa.getJSONObject(0);
                System.out.println(aa);

                JSONObject spe = aa.getJSONObject("species");
                jj = spe.getString("name");

                System.out.println(jj);//venusaur
            }
        }




        String evolutions = "start" + "," + name + "," + myName + "," + jj;
        return evolutions;

    }
}
