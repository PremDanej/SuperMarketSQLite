package com.merp.my.sup.market.store.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

public class MyConstant {

    public static final String NAME_1 = "Banana";
    public static final String NAME_2 = "Burger";
    public static final String NAME_3 = "Carrot";
    public static final String NAME_4 = "Chilli";
    public static final String NAME_5 = "GrapeFruit";
    public static final String NAME_6 = "Pizza";

    public static final int CAT_1 = 1;
    public static final int CAT_2 = 3;
    public static final int CAT_3 = 3;
    public static final int CAT_4 = 2;
    public static final int CAT_5 = 1;
    public static final int CAT_6 = 3;

    public static final String PRICE_1 = "10";
    public static final String PRICE_2 = "20";
    public static final String PRICE_3 = "15";
    public static final String PRICE_4 = "18";
    public static final String PRICE_5 = "25";
    public static final String PRICE_6 = "40";

    public static final int STOCK_1 = 0;
    public static final int STOCK_2 = 1;
    public static final int STOCK_3 = 0;
    public static final int STOCK_4 = 1;
    public static final int STOCK_5 = 1;
    public static final int STOCK_6 = 1;
    public static byte[] getByte(Drawable d) {
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
}
