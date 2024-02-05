package com.merp.my.sup.market.store.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.merp.my.sup.market.store.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // ======================> DATABASE <====================

    private static final String DATABASE    =   "SuperStore";
    private static final Integer VERSION    =   1;
    private static final String STORE_TABLE =   "tbl_product";

    // ======================> FIELD <====================

    private static final String PRODUCT_ID      =       "_id";
    private static final String PRODUCT_NAME    =       "Name";
    private static final String PRODUCT_CAT     =       "Category";
    private static final String PRODUCT_PRICE   =       "Price";
    private static final String PRODUCT_IMAGE   =       "Image";
    private static final String PRODUCT_STOCK   =       "isStock";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + STORE_TABLE + "(" +
                PRODUCT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PRODUCT_NAME + " TEXT NOT NULL," +
                PRODUCT_CAT + " INTEGER NOT NULL," +
                PRODUCT_PRICE + " TEXT NOT NULL," +
                PRODUCT_IMAGE + " BLOB NOT NULL," +
                PRODUCT_STOCK + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STORE_TABLE);
    }

    public void insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, product.getName());
        cv.put(PRODUCT_CAT, product.getCategory());
        cv.put(PRODUCT_PRICE, product.getPrice());
        cv.put(PRODUCT_IMAGE, product.getImage());
        cv.put(PRODUCT_STOCK, product.getIsStock());
        db.insert(STORE_TABLE, null, cv);
        db.close();
        cv.clear();
    }
    public void updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, product.getName());
        cv.put(PRODUCT_CAT, product.getCategory());
        cv.put(PRODUCT_PRICE, product.getPrice());
        cv.put(PRODUCT_IMAGE, product.getImage());
        cv.put(PRODUCT_STOCK, product.getIsStock());
        db.update(STORE_TABLE,  cv,PRODUCT_ID + " = ? ", new String[]{String.valueOf(product.getId())});
        db.close();
        cv.clear();
    }

    public void deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STORE_TABLE, PRODUCT_ID + " = ? ", new String[]{String.valueOf(id)});
        db.close();
    }

    @SuppressLint("Range")
    public List<Product> getAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Product> bookList = new ArrayList<>();
        Cursor cr = db.rawQuery("SELECT * FROM " + STORE_TABLE, null);
        if (cr.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(      cr.getInt(cr.getColumnIndex(PRODUCT_ID)));
                product.setName(    cr.getString(cr.getColumnIndex(PRODUCT_NAME)));
                product.setCategory(cr.getInt(cr.getColumnIndex(PRODUCT_CAT)));
                product.setPrice(   cr.getString(cr.getColumnIndex(PRODUCT_PRICE)));
                product.setImage(   cr.getBlob(cr.getColumnIndex(PRODUCT_IMAGE)));
                product.setIsStock( cr.getInt(cr.getColumnIndex(PRODUCT_STOCK)));
                bookList.add(product);
            } while (cr.moveToNext());
        }
        db.close();
        cr.close();
        return bookList;
    }

    @SuppressLint("Range")
    public Product getProductById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM " + STORE_TABLE + " WHERE " + PRODUCT_ID + " = ? ", new String[]{String.valueOf(id)});
        Product product = new Product();
        if (cr.moveToFirst()) {
            do {
                product.setId(      cr.getInt(cr.getColumnIndex(PRODUCT_ID)));
                product.setName(    cr.getString(cr.getColumnIndex(PRODUCT_NAME)));
                product.setCategory(cr.getInt(cr.getColumnIndex(PRODUCT_CAT)));
                product.setPrice(   cr.getString(cr.getColumnIndex(PRODUCT_PRICE)));
                product.setImage(   cr.getBlob(cr.getColumnIndex(PRODUCT_IMAGE)));
                product.setIsStock( cr.getInt(cr.getColumnIndex(PRODUCT_STOCK)));
            } while (cr.moveToNext());
        }
        db.close();
        cr.close();
        return product;
    }
}
