package com.zw.kedamahelper.utils.download;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zw.kedamahelper.utils.http.Http;
import com.zw.kedamahelper.utils.http.OnRequestFinishedListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by ZW on 2017/4/14.
 */

public class DownloadTask extends AsyncTask<Map<String, String>, Integer, Boolean> {
    private static final String TAG = "DownloadTask";

    private DownloadListener mListener;
    private boolean isPaused = false;
    private boolean isCanceled = false;
    private final String mDownloadUrl;
    private final String mFilePath;
    private final String mFileName;
    private File mData;

    public DownloadTask(DownloadListener mListener, String downloadUrl, String storagePath, String fileName) {
        this.mListener = mListener;
        this.mDownloadUrl = downloadUrl;
        this.mFilePath = storagePath;
        this.mFileName = fileName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mData = new File(mFilePath, mFileName);
        if (!mData.exists()) {
            new File(mData.getPath()).mkdirs();
            try {
                mData.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Boolean doInBackground(Map<String, String>... params) {
        try {
            return startDownload(params[0], mListener);
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: download failed", e);
            return false;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    private boolean startDownload(Map<String, String> params, final DownloadListener listener) throws IOException {
        final boolean[] issuccess = {false};
        Http http = new Http(new OnRequestFinishedListener() {
            @Override
            public void onResult(Response result) {
                if (result == null) {
                    issuccess[0] = false;
                }
                try {
                    download(new RandomAccessFile(mData, "rw"), result);
                    issuccess[0] = true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });return issuccess[0];
    }

    private void download(RandomAccessFile target, Response response) throws IOException {
        long targetLength = response.body().contentLength();//文件大小
        long currentLength = target.length();
        InputStream inputStream = response.body().byteStream();

        //连接失败
        if (targetLength == 0) {
            mListener.onFailed(new IOException());
        }
        if (currentLength == targetLength) {
            //读取数据
            int readBuffer;
            while (((readBuffer = inputStream.read())) != -1) {

                if (isPaused) {
                    mListener.onPause();//暂停
                    return;
                }
                if (isCanceled) {
                    mListener.onCancel();//取消
                    return;
                }
                target.write(readBuffer);
                currentLength++;
            }
        }
        mListener.onSuccess();//完成
    }
}
