package com.example.dragonite.dragonite;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class ChartActivity extends AppCompatActivity {

    private TextView tv;
    ImageView mImageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ImageView mImageView = (ImageView) findViewById(R.id.chart);

        tv = (TextView) findViewById(R.id.stuff);
        System.out.println("got here");

        getChart();

    }


    private void getChart(){

        String url = "https://1.bp.blogspot.com/-U3mpIbLK6pY/VsQDBA3iVwI/AAAAAAAAPF4/LtQq9Kscquk/s1600/Pok%25C3%25A9mon-Type-Advantage-Chart-Fixes.jpg";
        mImageView = (ImageView) findViewById(R.id.chart);


// Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("There was a problem with loading the image");
                    }
                });
// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
}
