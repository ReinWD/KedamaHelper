package com.zw.kedamahelper.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zw.kedamahelper.R;
import com.zw.kedamahelper.utils.download.DownloadListener;
import com.zw.kedamahelper.utils.download.DownloadTask;
import com.zw.kedamahelper.view.acticity.MainActivity;

import java.io.IOException;

/**
 * Created by ZW on 2017/4/14.
 */

public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";

    private MainActivity mContext;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService(String name, Context context) {
        super(name);
        this.mContext = (MainActivity) context;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        int res = super.onStartCommand(intent, flags, startId);
        Notification.Builder builder = new Notification.Builder(mContext);
        Intent foregroundIntent = new Intent(this, MainActivity.class);

        builder.setContentIntent(PendingIntent.getActivity(this, 0, foregroundIntent, 0)).
                setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)).
                setContentTitle("下拉列表中的Title").
                setSmallIcon(R.mipmap.ic_launcher). // 设置状态栏内的小图标
                setContentText("wow").
                setWhen(System.currentTimeMillis());
        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND;
        startForeground(19980419, notification);
        return res;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new DownloadBinder();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }

    public void startDownload(DownloadCallback callback, String url, String fileName) {
        DownloadTask download = new DownloadTask(callback, url, mContext.getFilesDir().getPath(), fileName);
        download.execute();
    }

    public class DownloadBinder extends Binder {
        DownloadBinder() {
        }

        public DownloadService getService() {
            return DownloadService.this;
        }
    }

    public interface DownloadCallback extends DownloadListener {
        void makeToast(final String toast);
    }
}
