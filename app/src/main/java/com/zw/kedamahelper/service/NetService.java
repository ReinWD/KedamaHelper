package com.zw.kedamahelper.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.zw.kedamahelper.utils.http.Http;
import com.zw.kedamahelper.utils.http.OnRequestFinishedListener;

import okhttp3.Response;

public class NetService extends Service {

    Http mHttpClient ;
    public NetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new NetBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHttpClient = new Http(new NetListener());
    }

    public class NetBinder extends Binder{
        public NetService service = NetService.this;
    }

    private class NetListener implements OnRequestFinishedListener{
        @Override
        public void onResult(Response response) {

        }
    }
}
