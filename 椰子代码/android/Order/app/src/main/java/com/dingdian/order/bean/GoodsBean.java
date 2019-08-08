package com.dingdian.order.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsBean implements Serializable {

    /**
     * id : 716
     * productName : 荷包蛋
     * productImage : http://ops.sunwou.com/5c8f82de-1f17-4de6-8c74-9dc00c9884e4
     * discount : 1
     * boxPriceFlag : 0
     * productCategoryId : 111
     * shopId : 31
     * schoolId : 14
     * sale : 0
     * isShow : 1
     * isDelete : 0
     * attribute : [{"id":915,"name":"标准","price":2}]
     */

    private int id;
    private String productName;
    private String productImage;
    private double discount;
    private int boxPriceFlag;
    private int productCategoryId;
    private int shopId;
    private int schoolId;
    private int sale;
    private int isShow;
    private int isDelete;
    private ArrayList<GoodsAttr> attribute;
    private String stock;
    private int stockFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getBoxPriceFlag() {
        return boxPriceFlag;
    }

    public void setBoxPriceFlag(int boxPriceFlag) {
        this.boxPriceFlag = boxPriceFlag;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public List<GoodsAttr> getAttribute() {
        return attribute;
    }

    public void setAttribute(ArrayList<GoodsAttr> attribute) {
        this.attribute = attribute;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getStockFlag() {
        return stockFlag;
    }

    public void setStockFlag(int stockFlag) {
        this.stockFlag = stockFlag;
    }

    public boolean show(){
        return isShow == 1;
    }

    public double originPrice(){
        double result = 0;
        if (attribute != null && attribute.size() > 0){
            result = attribute.get(0).getPrice();
        }
        return result;
    }

    public double discountPrice(){
        return originPrice() * discount;
    }
}
