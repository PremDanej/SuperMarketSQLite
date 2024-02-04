package com.merp.my.sup.market.store.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

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

        binding.btnLogin.setOnClickListener(view -> loginValidate());


    }

    private void loginValidate() {
        if (!binding.edtUserName.getText().toString().isEmpty() && !binding.edtPassword.getText().toString().isEmpty()) {
            String username = binding.edtUserName.getText().toString();
            String password = binding.edtPassword.getText().toString();
            preference.setString("username", username);
            preference.setString("password", password);
            preference.setBoolean("isLogin", true);
            showToast(this,"Login Successful");
            startActivity(new Intent(this, ProductActivity.class));
            finish();
        } else {
            showToast(this,"Value can't be empty");
        }
        binding.edtUserName.clearFocus();
        binding.edtPassword.clearFocus();
    }


}