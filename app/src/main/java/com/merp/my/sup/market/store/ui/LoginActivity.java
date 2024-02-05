package com.merp.my.sup.market.store.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.databinding.ActivityLoginBinding;
import com.merp.my.sup.market.store.model.Product;
import com.merp.my.sup.market.store.utils.MyConstant;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onInit();

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

    @SuppressLint("UseCompatLoadingForDrawables")
    private void onInit() {
        if(preference.getInt("loginFirstTime", 0) == 0) {
            helper.insertProduct(new Product(MyConstant.NAME_1, MyConstant.CAT_1,MyConstant.PRICE_1,MyConstant.getByte(getDrawable(R.drawable.img_banana)),MyConstant.STOCK_1));
            helper.insertProduct(new Product(MyConstant.NAME_2, MyConstant.CAT_2,MyConstant.PRICE_2,MyConstant.getByte(getDrawable(R.drawable.img_burger)),MyConstant.STOCK_2));
            helper.insertProduct(new Product(MyConstant.NAME_3, MyConstant.CAT_3,MyConstant.PRICE_3,MyConstant.getByte(getDrawable(R.drawable.img_carrots)),MyConstant.STOCK_3));
            helper.insertProduct(new Product(MyConstant.NAME_4, MyConstant.CAT_4,MyConstant.PRICE_4,MyConstant.getByte(getDrawable(R.drawable.img_chili)),MyConstant.STOCK_4));
            helper.insertProduct(new Product(MyConstant.NAME_5, MyConstant.CAT_5,MyConstant.PRICE_5,MyConstant.getByte(getDrawable(R.drawable.img_grapefruit)),MyConstant.STOCK_5));
            helper.insertProduct(new Product(MyConstant.NAME_6, MyConstant.CAT_6,MyConstant.PRICE_6,MyConstant.getByte(getDrawable(R.drawable.img_pizza)),MyConstant.STOCK_6));
            preference.setInt("loginFirstTime", 1);
        }
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
        overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
    }


}