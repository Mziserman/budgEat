package com.serious.budgeat.Reseau;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by thomas on 13/12/16.
 */

public class Reseau extends Application {

    private static Reseau sharedInstance;
    private RequestQueue mVolleyRequestQueue;
    private ImageLoader mVolleyImageLoader;

    public static Reseau getIstance() {
        return sharedInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Reseau.sharedInstance = this;
        //Création de la queue
        mVolleyRequestQueue = Volley.newRequestQueue(getApplicationContext());

    }

    public RequestQueue getRequestQueue() {
        return mVolleyRequestQueue;
    }

}
