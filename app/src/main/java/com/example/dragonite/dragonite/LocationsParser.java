package com.example.dragonite.dragonite;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kathy on 1/10/2016.
 */
public class LocationsParser {
    private Context mContext;

    public LocationsParser(Context context){
        mContext = context;
    }

        public void locationsVolleyRequest(String url, final VolleyCallback callback) {
            System.out.println("two. Locations VolleyRequest is called after getting the url. We are going to call this url now");

            System.out.println(url);
            RetryPolicy retryPolicy = new DefaultRetryPolicy(20000,2,1.0f);
            StringRequest locationStringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("The locations response came back! We are in location response");

                            String locations = null;
                            try {
                                locations = getLocation(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            callback.onSuccess(locations);


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("The location volley request didnt return anything. That didn't work!");
                    callback.onSuccess("Not found anywhere");

                }
            });
            locationStringRequest.setRetryPolicy(retryPolicy);

            MySingleton.getInstance(mContext).addToRequestQueue(locationStringRequest);

        }


        public interface VolleyCallback{
            void onSuccess(String bm);
        }

        public String getLocation(String json) throws JSONException {
            String allLocations = "start";
            System.out.println("five");

            JSONArray locationArray = new JSONArray(json);

            if(locationArray != null) {
                for (int i = 0; i < locationArray.length(); i++) {
                    JSONObject locationObject = (JSONObject) locationArray.get(i);
                    JSONObject locations = locationObject.getJSONObject("location_area");
                    String location = locations.getString("name");

                    allLocations = allLocations + "," + location;
                }
            }
            else{
                allLocations = "Not found anywhere";
            }
            return allLocations;

        }

}
