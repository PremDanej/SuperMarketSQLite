package com.merp.my.sup.market.store.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.adapter.ProductAdapter;
import com.merp.my.sup.market.store.databinding.ActivityProductBinding;
import com.merp.my.sup.market.store.listener.ItemClickListener;
import com.merp.my.sup.market.store.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends BaseActivity implements ItemClickListener {

    private ActivityProductBinding binding;
    private boolean isStockAvailable = false;
    private final List<Product> productList = new ArrayList<>();
    private ProductAdapter adapter;

    ActivityResultLauncher<Intent> startSomeActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            setStockProducts();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onInit();

        binding.imgStock.setOnClickListener(view -> onStockUpdate());

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                adapter.filterList(text);
            }
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
        binding.imgLogout.setOnClickListener(view -> onLogout(this));
        binding.btnAddProduct.setOnClickListener(view -> {
            startSomeActivityForResult.launch(new Intent(this, AddItemActivity.class));
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
        });

    }

    private void onStockUpdate() {
        isStockAvailable = !isStockAvailable;
        binding.imgStock.setImageDrawable(AppCompatResources.getDrawable(this, isStockAvailable ? R.drawable.ic_checked : R.drawable.ic_un_check));
        setStockProducts();
    }

    private void setStockProducts() {
        if(isStockAvailable){
            for(Product p : helper.getAllProducts()){
                if(p.getIsStock() > 0){
                    productList.add(p);
                }
            }
            adapter.setUpdatedList(productList);
            productList.clear();
        }else {
            adapter.setUpdatedList(helper.getAllProducts());
        }
    }


    private void onInit() {
        String userName = "Welcome " + preference.getString("username", "User");
        binding.txtUserName.setText(userName);
        adapter = new ProductAdapter( this, getResources().getStringArray(R.array.categorySpinner));
        binding.productRcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.productRcv.setAdapter(adapter);
        adapter.setUpdatedList(helper.getAllProducts());
    }

    @Override
    public void onItemClickListener(int productId, String tag) {
        if(tag.contains("EDIT")) {
            startSomeActivityForResult.launch(new Intent(this, AddItemActivity.class).putExtra("IsEdit", productId));
            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
        }
        if(tag.contains("DEL")) {
            helper.deleteProduct(productId);
            adapter.setUpdatedList(helper.getAllProducts());
        }
    }
}