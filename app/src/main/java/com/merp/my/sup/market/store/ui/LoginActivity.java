package com.merp.my.sup.market.store.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private static final String TAG = "===========> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edtPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.edtPassword.clearFocus();
                hideKeyboardFrom(this, v);
                return true;
            }
            return false;
        });

        binding.btnLogin.setOnClickListener(view -> {
            if (!binding.edtUserName.getText().toString().isEmpty() && !binding.edtPassword.getText().toString().isEmpty()) {
                Log.d(TAG, " Value Got it");
            } else {
                Log.e(TAG, " Value is NULL");
            }
            binding.edtUserName.clearFocus();
            binding.edtPassword.clearFocus();

        });


    }

    private boolean textIsEmpty(TextInputEditText editText) {
        return editText.getText().toString().isEmpty();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}