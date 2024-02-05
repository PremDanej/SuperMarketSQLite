package com.merp.my.sup.market.store.model;

public class Product {

    private Integer id;
    private String name;
    private Integer category;
    private String price;
    private byte[] image;
    private Integer isStock; // 1 -> Available, 0 -> No Stock

    public Product() {
    }

    public Product(Integer id, String name, Integer category, String price, byte[] image, Integer isStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.isStock = isStock;
    }

    public Product(String name, Integer category, String price, byte[] image, Integer isStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.isStock = isStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getIsStock() {
        return isStock;
    }

    public void setIsStock(Integer isStock) {
        this.isStock = isStock;
    }
}
