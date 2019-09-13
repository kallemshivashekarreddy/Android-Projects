package com.caseior.WebServer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
TextView tv;
EditText et;
Button bt;
ListView lv;
ViewFlipper flip;
    int   []images;
List<pojoclassObj> items;
AdapterItem adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        et=findViewById(R.id.et);
        bt=findViewById(R.id.bt);
        lv=findViewById(R.id.lv);
        flip =findViewById(R.id.vflip);
        items=new Vector<pojoclassObj>();
        lv.setDivider(null);
   images= new int[]{android.R.drawable.ic_popup_sync, android.R.drawable.alert_light_frame};
        for(int img:images)
        {
            imgslide(img);
        }
        adapter=new AdapterItem(this,items);
        lv.setAdapter(adapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();
                adapter.notifyDataSetChanged();
                String s =et.getText().toString().trim();
                String url="http://api.geonames.org/wikipediaSearchJSON?formatted=true&q="+s+"&maxRows=10&username=srik784&style=full";
                if(s.length()!=0)
                {
                    JsonObjectRequest req=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jarr=response.getJSONArray("geonames");
                                for(int i=0;i<jarr.length();i++)
                                {
                                    JSONObject jobj=jarr.getJSONObject(i);
                                    pojoclassObj poj=new pojoclassObj();
                                    poj.setTitle(jobj.getString("title"));
                                    poj.setDescption(jobj.getString("summary"));
                                    poj.setImgUrl(jobj.getString("thumbnailImg"));
                                    items.add(poj);

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Volley.newRequestQueue(MainActivity.this).add(req);
                }
                else et.setError("Enter city");
            }
        });
    }
    public void imgslide(int i)
    {
        ImageView ims=new ImageView(this);
        ims.setBackgroundResource(i);
        flip.addView(ims);
        flip.setAutoStart(true);
        flip.setFlipInterval(3000);
        flip.setInAnimation(this,android.R.anim.slide_in_left);
        flip.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    public void onclick(View view) {
       Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();

    }
}
