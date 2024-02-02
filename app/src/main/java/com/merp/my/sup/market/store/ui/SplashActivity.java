package com.merp.my.sup.market.store.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.databinding.ActivitySplashBinding;

import java.util.Objects;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivitySplashBinding.inflate(getLayoutInflater()).getRoot());

        new Handler((Looper.myLooper())).postDelayed(() ->{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        },2000L);

    }
}