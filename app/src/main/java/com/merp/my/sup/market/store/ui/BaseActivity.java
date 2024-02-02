package com.merp.my.sup.market.store.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.merp.my.sup.market.store.utils.MySharedPreference;
import java.util.Objects;

public class BaseActivity extends AppCompatActivity {

    public MySharedPreference preference;
    public static final String TAG = "===========> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new MySharedPreference(this);
    }
}