package com.merp.my.sup.market.store.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.db.DatabaseHelper;
import com.merp.my.sup.market.store.model.Product;
import com.merp.my.sup.market.store.utils.MySharedPreference;

public class BaseActivity extends AppCompatActivity {

    public MySharedPreference preference;
    public DatabaseHelper helper;
    public static final String TAG = "===========> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new MySharedPreference(this);
        helper = new DatabaseHelper(this);

    }

    public void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onLogout(Context context) {
        preference.setBoolean("isLogin", false);
        startActivity(new Intent(context, LoginActivity.class));
        finishAffinity();
        overridePendingTransition(R.anim.anim_left_to_right,R.anim.anim_right_to_left);
    }
}