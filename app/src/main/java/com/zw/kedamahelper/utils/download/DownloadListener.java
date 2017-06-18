package com.zw.kedamahelper.utils.download;

import java.io.IOException;

/**
 * Created by ZW on 2017/4/16.
 */

public interface DownloadListener {
    void onSuccess();
    void onFailed(IOException e);
    void onPause();
    void onCancel();
    void makeToast(String toast);
}
