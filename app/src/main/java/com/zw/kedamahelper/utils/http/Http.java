package com.zw.kedamahelper.utils.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZW on 2017/3/9.
 */

public class Http {

    private final String TAG = "Http";

    public Http(OnRequestFinishedListener listener) {
        this.mListener = listener;
    }

    private OnRequestFinishedListener mListener;
    private OkHttpClient httpClient;

    public void requestData(String url) {
        this.requestData(url, null);
    }

    public void requestData(String url, Map<String, String> params) {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        }
        if (params != null) {
            url = makeUrl(url, params);
        }

        Request request = new Request.Builder()
                .get()
                .url(url).build();

        makeCall(request);
    }

    private String makeUrl(String url, @NonNull Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            builder.append(entry.getKey()).
                    append("=").
                    append(entry.getValue()).
                    append("&");
        }
        builder.deleteCharAt(builder.length());
        url = builder.toString();
        return url;
    }

    private void makeCall(Request request) {
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mListener.onResult(null);
                Log.e(TAG, "on requestData():", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mListener.onResult(response);
            }
        });
    }

//    @Nullable
//    private String toMD5(String raw){
//        try{
//            byte[] md5Byte = MessageDigest.getInstance("MD5").digest(raw.getBytes());
//            if (md5Byte != null) {
//                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                int temp;
//                for (byte aMd5Byte : md5Byte) {
//                    temp = aMd5Byte;
//                    os.write(temp);
//                }
//                return os.toString();
//            }else {return null;}
//
//        } catch (NoSuchAlgorithmException e) {
//            Log.e(TAG, "toMD5: ", e);
//            e.printStackTrace();
//            return null;
//        }
//}

}
