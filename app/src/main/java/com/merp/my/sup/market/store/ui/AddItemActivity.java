package com.merp.my.sup.market.store.ui;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.window.OnBackInvokedDispatcher;

import com.merp.my.sup.market.store.R;
import com.merp.my.sup.market.store.databinding.ActivityAddItemBinding;
import com.merp.my.sup.market.store.model.Product;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class AddItemActivity extends BaseActivity {

    private ActivityAddItemBinding binding;
    private boolean isStockAvailable = false;
    private Integer categorySelectedItem = Integer.MIN_VALUE;
    private Integer itemStockAvailable = Integer.MIN_VALUE;
    private Integer isEdit = Integer.MIN_VALUE;

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
        if (uri != null) {
            binding.imgProduct.setImageURI(uri);
        } else {
            showToast(this, "Please Select Image");
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onInit();

        binding.imgBack.setOnClickListener(view -> onGoToBack());
        binding.imgLogout.setOnClickListener(view -> onLogout(this));
        binding.imgProduct.setOnClickListener(view -> onImagePick());
        binding.imgStock.setOnClickListener(view -> onStockUpdate());
        binding.btnAddProduct.setOnClickListener(view -> onProductSave());

        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                overridePendingTransition(R.anim.anim_left_to_right,R.anim.anim_right_to_left);
            }
        });
    }

    private void onStockUpdate() {
        isStockAvailable = !isStockAvailable;
        itemStockAvailable = isStockAvailable ? 1 : 0;
        binding.imgStock.setImageDrawable(AppCompatResources.getDrawable(this, isStockAvailable ? R.drawable.ic_checked : R.drawable.ic_un_check));
    }

    private void onProductSave() {
        String name = Objects.requireNonNull(binding.edtName.getText()).toString();
        String price = Objects.requireNonNull(binding.edtPrice.getText()).toString();
        categorySelectedItem = binding.catSpinner.getSelectedItemPosition();
        if (isEdit > 0) {
            helper.updateProduct(new Product(isEdit, name, categorySelectedItem, price, imageViewToByte(binding.imgProduct), itemStockAvailable));
            showToast(this, getString(R.string.lbl_update_successfully));
        } else {
            helper.insertProduct(new Product(name, categorySelectedItem, price, imageViewToByte(binding.imgProduct), itemStockAvailable));
            showToast(this, getString(R.string.lbl_added_successfully));
        }
        setResult(Activity.RESULT_OK);
        finish();
        overridePendingTransition(R.anim.anim_left_to_right,R.anim.anim_right_to_left);
    }

    private void onImagePick() {
        // Launch the photo picker and let the user choose only images.
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    private void onGoToBack() {
        setResult(Activity.RESULT_CANCELED);
        finish();
        overridePendingTransition(R.anim.anim_left_to_right,R.anim.anim_right_to_left);
    }

    private void onInit() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorySpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.catSpinner.setAdapter(adapter);
        isEditable();
    }

    private void isEditable() {
        isEdit = getIntent().getIntExtra("IsEdit", 0);
        if (isEdit > 0) {
            binding.txtTitle.setText(R.string.lbl_update_item);
            Product product = helper.getProductById(isEdit);
            binding.edtName.setText(product.getName());
            binding.catSpinner.setSelection(product.getCategory());
            binding.edtPrice.setText(product.getPrice());
            binding.imgProduct.setImageBitmap(getImageBitmap(product.getImage()));
            itemStockAvailable = product.getIsStock();
            isStockAvailable = itemStockAvailable > 0;
            binding.imgStock.setImageDrawable(AppCompatResources.getDrawable(this, itemStockAvailable > 0 ? R.drawable.ic_checked : R.drawable.ic_un_check));
            binding.btnAddProduct.setText(R.string.lbl_update_item);
        } else {
            binding.txtTitle.setText(R.string.lbl_add_item);
            binding.btnAddProduct.setText(R.string.lbl_add_item);
        }

    }


    private byte[] imageViewToByte(ImageView avatar) {
        Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        return stream.toByteArray();
    }

    private Bitmap getImageBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}