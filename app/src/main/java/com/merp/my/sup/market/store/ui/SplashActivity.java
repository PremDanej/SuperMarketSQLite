package com.merp.my.sup.market.store.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.databinding.ActivitySplashBinding;
import com.merp.my.sup.market.store.utils.MyConstant;

import java.util.Objects;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivitySplashBinding.inflate(getLayoutInflater()).getRoot());

        new Handler((Looper.myLooper())).postDelayed(() -> {
            startActivity(new Intent(this,
                    preference.getBoolean(MyConstant.IS_LOGIN, false) ? ProductActivity.class : LoginActivity.class));
            finish();
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
        }, 1000L);

    }
}