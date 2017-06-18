package com.zw.kedamahelper.view.acticity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zw.kedamahelper.R;

public class IntroActivity extends AppCompatActivity {
    private static final int DURITION_MIN = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        LoadTask task = new LoadTask();
        task.execute();
    }

    private class LoadTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            long startTime = System.currentTimeMillis();
            long loadTime = System.currentTimeMillis()-startTime;
            if (loadTime<DURITION_MIN){
                try {
                    Thread.sleep(DURITION_MIN-loadTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
}
