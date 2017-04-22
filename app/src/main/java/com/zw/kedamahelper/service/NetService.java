package com.zw.kedamahelper.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class NetService extends Service {
    public NetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new NetBinder();
    }

    public class NetBinder extends Binder{
        public NetService service = NetService.this;
    }
}
