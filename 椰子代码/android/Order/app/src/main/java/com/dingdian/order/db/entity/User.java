package com.dingdian.order.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.math.BigDecimal;

@Entity(nameInDb = "tb_user")
public class User {

    /**
     * boxPrice : 0
     * getModelFlag : 1
     * id : 10
     * isDelete : 0
     * lat : 39.97158
     * lng : 116.614688
     * openFlag : 1
     * rate : 0.05
     * schoolId : 9
     * score : 4
     * sendModelFlag : 1
     * sendPrice : 1
     * sendPriceAdd : 0
     * sendPriceAddByCountFlag : 0
     * sendTime : 45
     * shopAddress : 薰衣草庄园店
     * shopCategoryId : 3
     * shopImage : http://ops.sunwou.com/6744312a-078b-4bac-8b4e-6b43fcb0e344
     * shopLoginName : rsll2
     * shopLoginPassWord : 123123
     * shopName : 日式料理
     * shopPhone : 18888888888
     * sort : 1544977375917
     * startPrice : 0
     * topTitle : 新开的店铺，优惠多多
     * vipDiscountFlag : 0
     */

    @Id(autoincrement = true)
    private long _id;
    private String boxPrice;
    private int getModelFlag;
    private int tsModelFlag;
    private int id;
    private int isDelete;
    private String lat;
    private String lng;
    private int openFlag;
    private double rate;
    private int schoolId;
    private int score;
    private int sendModelFlag;
    private String sendPrice;
    private String sendPriceAdd;
    private String sendPriceAddByCountFlag;
    private String sendTime;
    private String shopAddress;
    private int shopCategoryId;
    private String shopImage;
    private String shopLoginName;
    private String shopLoginPassWord;
    private String shopName;
    private String shopPhone;
    private long sort;
    private String startPrice;
    private String topTitle;
    private int vipDiscountFlag;
    private String token;

    @Generated(hash = 593179954)
    public User(long _id, String boxPrice, int getModelFlag, int tsModelFlag,
            int id, int isDelete, String lat, String lng, int openFlag, double rate,
            int schoolId, int score, int sendModelFlag, String sendPrice,
            String sendPriceAdd, String sendPriceAddByCountFlag, String sendTime,
            String shopAddress, int shopCategoryId, String shopImage,
            String shopLoginName, String shopLoginPassWord, String shopName,
            String shopPhone, long sort, String startPrice, String topTitle,
            int vipDiscountFlag, String token) {
        this._id = _id;
        this.boxPrice = boxPrice;
        this.getModelFlag = getModelFlag;
        this.tsModelFlag = tsModelFlag;
        this.id = id;
        this.isDelete = isDelete;
        this.lat = lat;
        this.lng = lng;
        this.openFlag = openFlag;
        this.rate = rate;
        this.schoolId = schoolId;
        this.score = score;
        this.sendModelFlag = sendModelFlag;
        this.sendPrice = sendPrice;
        this.sendPriceAdd = sendPriceAdd;
        this.sendPriceAddByCountFlag = sendPriceAddByCountFlag;
        this.sendTime = sendTime;
        this.shopAddress = shopAddress;
        this.shopCategoryId = shopCategoryId;
        this.shopImage = shopImage;
        this.shopLoginName = shopLoginName;
        this.shopLoginPassWord = shopLoginPassWord;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.sort = sort;
        this.startPrice = startPrice;
        this.topTitle = topTitle;
        this.vipDiscountFlag = vipDiscountFlag;
        this.token = token;
    }

    @Generated(hash = 586692638)
    public User() {
    }



    public String getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(String boxPrice) {
        this.boxPrice = boxPrice;
    }

    public int getGetModelFlag() {
        return getModelFlag;
    }

    public void setGetModelFlag(int getModelFlag) {
        this.getModelFlag = getModelFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(int openFlag) {
        this.openFlag = openFlag;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSendModelFlag() {
        return sendModelFlag;
    }

    public void setSendModelFlag(int sendModelFlag) {
        this.sendModelFlag = sendModelFlag;
    }

    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getSendPriceAdd() {
        return sendPriceAdd;
    }

    public void setSendPriceAdd(String sendPriceAdd) {
        this.sendPriceAdd = sendPriceAdd;
    }

    public String getSendPriceAddByCountFlag() {
        return sendPriceAddByCountFlag;
    }

    public void setSendPriceAddByCountFlag(String sendPriceAddByCountFlag) {
        this.sendPriceAddByCountFlag = sendPriceAddByCountFlag;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(int shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopLoginName() {
        return shopLoginName;
    }

    public void setShopLoginName(String shopLoginName) {
        this.shopLoginName = shopLoginName;
    }

    public String getShopLoginPassWord() {
        return shopLoginPassWord;
    }

    public void setShopLoginPassWord(String shopLoginPassWord) {
        this.shopLoginPassWord = shopLoginPassWord;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
    }

    public int getVipDiscountFlag() {
        return vipDiscountFlag;
    }

    public void setVipDiscountFlag(int vipDiscountFlag) {
        this.vipDiscountFlag = vipDiscountFlag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getTsModelFlag() {
        return tsModelFlag;
    }

    public void setTsModelFlag(int tsModelFlag) {
        this.tsModelFlag = tsModelFlag;
    }
}
