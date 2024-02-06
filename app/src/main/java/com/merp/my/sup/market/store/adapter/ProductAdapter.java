package com.merp.my.sup.market.store.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.merp.my.sup.market.store.databinding.ItemViewProductBinding;
import com.merp.my.sup.market.store.listener.ItemClickListener;
import com.merp.my.sup.market.store.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<Product> mainList;
    private List<Product> list;
    private final ItemClickListener listener;
    private final String[] stringArray;

    public ProductAdapter(ItemClickListener listener, String[] stringArray) {
        list = new ArrayList<>();
        mainList = new ArrayList<>();
        this.stringArray = stringArray;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemViewProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product item = list.get(position);
        holder.binding.txtPrice.setText(getPrice(item.getPrice()));
        holder.binding.imgProduct.setImageBitmap(getImageBitmap(item.getImage()));
        holder.binding.txtName.setText(item.getName());
        holder.binding.txtCat.setText(stringArray[item.getCategory()]);
        holder.binding.txtStock.setText(isStockAvailable(item.getIsStock()));
        holder.binding.imgEdit.setOnClickListener(view -> listener.onItemClickListener(item.getId(),"EDIT"));
        holder.binding.imgDel.setOnClickListener(view -> listener.onItemClickListener(item.getId(),"DEL"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUpdatedList(List<Product> allProducts) {
        list.clear();
        mainList.clear();
        list.addAll(allProducts);
        mainList.addAll(allProducts);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(String filter) {
        if (filter == null || filter.trim().equals("")) {
            list = new ArrayList<>();
            list.addAll(mainList);
        }
        else {
            list = new ArrayList<>();
            for(Product item : mainList){
                if(item.getName().contains(filter))
                    list.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemViewProductBinding binding;

        public MyViewHolder(@NonNull ItemViewProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    private Bitmap getImageBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private String getPrice(String price) {
        return "$ " + price;
    }

    private String isStockAvailable(Integer isStock) {
        return isStock > 0 ? "( In Stock )" : "( Out of Stock )";
    }

}
