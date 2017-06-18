package com.zw.kedamahelper.view.acticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zw.kedamahelper.R;
import com.zw.kedamahelper.controler.MainControler;

public class MainActivity extends AppCompatActivity {

    private MainControler mMainControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainControl = new MainControler(this);

    }

}
