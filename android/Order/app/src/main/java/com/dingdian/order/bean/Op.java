package com.dingdian.order.bean;

import java.io.Serializable;

public class Op implements Serializable {

    /**
     * attributeName : 标准
     * attributePrice : 0.1
     * id : 35784
     * orderId : 201903150907312392153251225
     * productCount : 1
     * productDiscount : 1
     * productId : 3
     * productImage : http://ops.sunwou.com/b902efe6-67c4-4c45-a83a-1f8a29581b7c
     * productName : 鸡蛋炒番茄鸡蛋炒番茄鸡蛋炒番茄鸡蛋炒番茄
     * totalPrice : 0.1
     */

    private String attributeName;
    private double attributePrice;
    private int id;
    private String orderId;
    private int productCount;
    private double productDiscount;
    private int productId;
    private String productImage;
    private String productName;
    private double totalPrice;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public double getAttributePrice() {
        return attributePrice;
    }

    public void setAttributePrice(double attributePrice) {
        this.attributePrice = attributePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
