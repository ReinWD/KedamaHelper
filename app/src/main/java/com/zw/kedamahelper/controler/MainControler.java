package com.zw.kedamahelper.controler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.zw.kedamahelper.service.DownloadService;
import com.zw.kedamahelper.view.acticity.MainActivity;
import com.zw.kedamahelper.service.NetService;
import com.zw.kedamahelper.Value;

/**
 * Created by ZW on 2017/4/23.
 */

public class MainControler {
    private MainActivity mContext;
    private NetService mNetService;
    private DownloadService mDownloadService;
    public MainControler(Context context){
        this.mContext = (MainActivity) context;
    }

    public void startService(String serviceName){

        switch (serviceName){
            case Value.NET_SERVICE:
                Intent netIntent = new Intent(mContext, NetService.class);
                mContext.startService(netIntent);
                mContext.bindService(netIntent,new Connector(serviceName), Context.BIND_AUTO_CREATE);
                break;
            case Value.DOWNLOAD_SERVICE:
                Intent downloadIntent = new Intent(mContext, DownloadService.class);
                mContext.startService(downloadIntent);
                mContext.bindService(downloadIntent,new Connector(serviceName), Context.BIND_AUTO_CREATE);
                break;
        }
    }

    private class Connector implements ServiceConnection {
        private final String mServiceName;

        Connector(String serviceName) {
            this.mServiceName = serviceName;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            switch (mServiceName){
                case Value.NET_SERVICE:
                    mNetService = ((NetService.NetBinder) service).service;
                    break;
                case Value.DOWNLOAD_SERVICE:
                    mDownloadService = ((DownloadService.DownloadBinder) service).getService();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
