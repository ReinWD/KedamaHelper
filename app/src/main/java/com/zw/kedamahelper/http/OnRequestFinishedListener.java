package com.zw.kedamahelper.http;

import android.support.annotation.Nullable;

import java.io.InputStream;

/**
 * Created by ZW on 2017/3/9.
 */

public interface OnRequestFinishedListener {
    /**
     *
     * @param resultStream result from your request
     */
    @Nullable
    void onResult(InputStream resultStream);
}
