package com.serious.budgeat.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;
import com.serious.budgeat.R;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    static private final String CONTAINER_ID="GTM-PF85DB3";
    static private final Integer TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS=2000;
    static private final String screenName="Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("aaa", "ooo");

        TagManager tagManager = TagManager.getInstance(this);

        PendingResult<ContainerHolder> pending =
                tagManager.loadContainerPreferNonDefault(CONTAINER_ID,
                        R.raw.gtm_pf85db3);

        // The onResult method will be called as soon as one of the following happens:
//     1. a saved container is loaded
//     2. if there is no saved container, a network container is loaded
//     3. the 2-second timeout occurs
        pending.setResultCallback(new ResultCallback<ContainerHolder>() {
            @Override
            public void onResult(ContainerHolder containerHolder) {
                ContainerHolderSingleton.setContainerHolder(containerHolder);
                Container container = containerHolder.getContainer();

                if (!containerHolder.getStatus().isSuccess()) {
                    Log.e("CuteAnimals", "failure loading container");
                    displayErrorToUser(R.string.load_error);
                    return;
                }



                //ContainerLoadedCallback.registerCallbacksForContainer(container);
                //containerHolder.setContainerAvailableListener(new ContainerLoadedCallback());
                //startMainActivity();





            }
        }, TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS, TimeUnit.MILLISECONDS);

        Utils.pushOpenScreenEvent(this, screenName);

    }

    private void displayErrorToUser(int stringKey) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage(getResources().getString(stringKey));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        alertDialog.show();
    }

}
