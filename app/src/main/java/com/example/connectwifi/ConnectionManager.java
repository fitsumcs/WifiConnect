package com.example.connectwifi;


import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionErrorCode;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;

public class ConnectionManager {

    Context context;
    public  ConnectionManager(Context context)
    {
        this.context = context;
    }



    //the fun tha connect with the AP
    public void connectWithWAP(String ssid, String pasworrd) {

        enableWifi();

        WifiUtils.withContext(context)
                .connectWith(ssid, pasworrd)
                .setTimeout(40000)
                .onConnectionResult(new ConnectionSuccessListener() {
                    @Override
                    public void success() {
                        Log.e("Tag", "Success");
                    }

                    @Override
                    public void failed(@NonNull ConnectionErrorCode errorCode) {
                        Log.e("Tag", "Fail");

                    }
                })
                .start();
    }


    //Enable wifi if not enabled
    public void enableWifi() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
            Toast.makeText(context, "WIFI ENABLED", Toast.LENGTH_SHORT).show();
        }
    }







}
