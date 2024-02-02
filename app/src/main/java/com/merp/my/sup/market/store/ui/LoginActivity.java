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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

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
                String username = binding.edtUserName.getText().toString();
                String password = binding.edtPassword.getText().toString();
                preference.setString("username", username);
                preference.setString("password", password);
            } else {
                Toast.makeText(this, "Value can't be empty", Toast.LENGTH_SHORT).show();
            }
            binding.edtUserName.clearFocus();
            binding.edtPassword.clearFocus();
        });


    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}