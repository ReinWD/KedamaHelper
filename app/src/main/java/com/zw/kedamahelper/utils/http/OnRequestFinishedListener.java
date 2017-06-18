package com.zw.kedamahelper.utils.http;

import android.support.annotation.Nullable;

import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by ZW on 2017/3/9.
 */

public interface OnRequestFinishedListener {
    /**
     *
     * @param response response from your request
     */
    void onResult(Response response);
}
