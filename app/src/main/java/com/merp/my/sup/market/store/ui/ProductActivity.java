package com.merp.my.sup.market.store.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.databinding.ActivityProductBinding;

public class ProductActivity extends BaseActivity {

    private ActivityProductBinding binding;
    private boolean isStockAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onInit();

        binding.imgStock.setOnClickListener(view ->{
            isStockAvailable = !isStockAvailable;
            binding.imgStock.setImageDrawable(getDrawable(isStockAvailable ? R.drawable.ic_checked : R.drawable.ic_un_check));
        });


        binding.edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.edtSearch.clearFocus();
                hideKeyboardFrom(this, v);
                return true;
            }
            return false;
        });

        // onLogout Click
        binding.imgLogout.setOnClickListener(view -> onLogout());

    }

    private void onLogout() {
        preference.setBoolean("isLogin",false);
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

    private void onInit() {
        String userName = "Welcome " + preference.getString("username", "User");
        binding.txtUserName.setText(userName);
    }
}