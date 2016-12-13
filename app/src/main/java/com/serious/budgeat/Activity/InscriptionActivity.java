package com.serious.budgeat.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.serious.budgeat.JsonFactory.JsonParserFactory;
import com.serious.budgeat.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        ButterKnife.bind(this);
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JsonParserFactory());

        AndroidNetworking.get("http://10.32.1.129:3000/yolo")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("TAG_LOCATIONS", response.getJSONObject(0).getString("firstName"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("TAG_LOCATIONS", error.toString());
                    }
                });
    }

    @OnClick(R.id.vaporisation)
    void inscription(View view) {
        Log.d("OOK", "LANCER");
        AndroidNetworking.post("https://budgeat.stan.sh/user/new")
                .addBodyParameter("name", "Captain Yolo")
                .addBodyParameter("email", "captain@yolo")
                .addBodyParameter("password", "captainleplusbeau")
                .addBodyParameter("ecole_id", "1")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("OOK", "CA FONCTIONNE");
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("TAG_LOCATIONS", error.toString());
                    }
                });

    }

}
