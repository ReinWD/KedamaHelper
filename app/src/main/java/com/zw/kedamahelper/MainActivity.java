package com.zw.kedamahelper;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.zw.kedamahelper.service.NetService;

public class MainActivity extends AppCompatActivity {
    NetService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, NetService.class);
        startService(intent);
        bindService(intent,new Connector(),BIND_AUTO_CREATE);
    }

    class Connector implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((NetService.NetBinder) service).service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
