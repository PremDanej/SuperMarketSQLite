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
        if(preference.getInt(MyConstant.LOGIN_FIRST_TIME, 0) == 0) {
            helper.insertProduct(new Product(MyConstant.NAME_1, MyConstant.CAT_1,MyConstant.PRICE_1,MyConstant.getByte(getDrawable(R.drawable.img_banana)),MyConstant.STOCK_1));
            helper.insertProduct(new Product(MyConstant.NAME_2, MyConstant.CAT_2,MyConstant.PRICE_2,MyConstant.getByte(getDrawable(R.drawable.img_burger)),MyConstant.STOCK_2));
            helper.insertProduct(new Product(MyConstant.NAME_3, MyConstant.CAT_3,MyConstant.PRICE_3,MyConstant.getByte(getDrawable(R.drawable.img_carrots)),MyConstant.STOCK_3));
            helper.insertProduct(new Product(MyConstant.NAME_4, MyConstant.CAT_4,MyConstant.PRICE_4,MyConstant.getByte(getDrawable(R.drawable.img_chili)),MyConstant.STOCK_4));
            helper.insertProduct(new Product(MyConstant.NAME_5, MyConstant.CAT_5,MyConstant.PRICE_5,MyConstant.getByte(getDrawable(R.drawable.img_grapefruit)),MyConstant.STOCK_5));
            helper.insertProduct(new Product(MyConstant.NAME_6, MyConstant.CAT_6,MyConstant.PRICE_6,MyConstant.getByte(getDrawable(R.drawable.img_pizza)),MyConstant.STOCK_6));
            preference.setInt(MyConstant.LOGIN_FIRST_TIME, 1);
        }
    }

    private void loginValidate() {
        boolean isAllFieldCheck = checkAllFields();
        if (isAllFieldCheck) {
            String username = binding.edtUserName.getText().toString();
            String password = binding.edtPassword.getText().toString();
            preference.setString(MyConstant.USERNAME, username);
            preference.setString(MyConstant.PASSWORD, password);
            preference.setBoolean(MyConstant.IS_LOGIN, true);
            startActivity(new Intent(this, ProductActivity.class));
            showToast(this,getString(R.string.lbl_login_successful));
            finish();
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
        }
    }

    private boolean checkAllFields() {
        if(binding.edtUserName.length() == 0){
            binding.edtUserName.requestFocus();
            binding.edtUserName.setError(getString(R.string.lbl_this_field_is_required));
            return false;
        }
        if(binding.edtPassword.length() == 0){
            binding.edtPassword.requestFocus();
            binding.edtPassword.setError(getString(R.string.lbl_this_field_is_required));
            return false;
        }
        return true;
    }
}