package com.dingdian.order.bean;

public class ShopBean {

    /**
     * id : 24
     * schoolId : 14
     * shopName : 港式小铁板（免配送费）
     * shopPhone : 13606544201
     * shopImage : http://ops.sunwou.com/c8fa4b34-63b4-42b1-8951-f2fc6ef51b55
     * shopCategoryId : 10
     * openFlag : 1
     * sendModelFlag : 1
     * getModelFlag : 1
     * score : 5
     * startPrice : 10
     * boxPrice : 1
     * sendPrice : 0
     * sendTime : 40
     * topTitle : 全场免配送费！！！
     * shopLoginName : gsxtb
     * shopLoginPassWord : gsxtb004
     * shopAddress : 杭电信工三楼美食城
     * rate : 0.04
     * lat : 30.296648
     * lng : 119.831291
     * vipDiscountFlag : 0
     * sendPriceAddByCountFlag : 0
     * sendPriceAdd : 0
     * isDelete : 0
     * sort : 1552495770000
     */

    private int id;
    private int schoolId;
    private String shopName;
    private String shopPhone;
    private String shopImage;
    private int shopCategoryId;
    private int openFlag;
    private int sendModelFlag;
    private int getModelFlag;
    private int score;
    private String startPrice;
    private String boxPrice;
    private String sendPrice;
    private String sendTime;
    private String topTitle;
    private String shopLoginName;
    private String shopLoginPassWord;
    private String shopAddress;
    private double rate;
    private String lat;
    private String lng;
    private int vipDiscountFlag;
    private int sendPriceAddByCountFlag;
    private String sendPriceAdd;
    private int isDelete;
    private long sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
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

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public int getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(int shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public int getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(int openFlag) {
        this.openFlag = openFlag;
    }

    public int getSendModelFlag() {
        return sendModelFlag;
    }

    public void setSendModelFlag(int sendModelFlag) {
        this.sendModelFlag = sendModelFlag;
    }

    public int getGetModelFlag() {
        return getModelFlag;
    }

    public void setGetModelFlag(int getModelFlag) {
        this.getModelFlag = getModelFlag;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(String boxPrice) {
        this.boxPrice = boxPrice;
    }

    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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

    public int getVipDiscountFlag() {
        return vipDiscountFlag;
    }

    public void setVipDiscountFlag(int vipDiscountFlag) {
        this.vipDiscountFlag = vipDiscountFlag;
    }

    public int getSendPriceAddByCountFlag() {
        return sendPriceAddByCountFlag;
    }

    public void setSendPriceAddByCountFlag(int sendPriceAddByCountFlag) {
        this.sendPriceAddByCountFlag = sendPriceAddByCountFlag;
    }

    public String getSendPriceAdd() {
        return sendPriceAdd;
    }

    public void setSendPriceAdd(String sendPriceAdd) {
        this.sendPriceAdd = sendPriceAdd;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public String status(){
        if (openFlag==1){
            return "营业状态：营业中";
        }else {
            return "营业状态：休息中";
        }
    }

    public String formatShopPhone() {
        String result = shopPhone;
        if (shopPhone!=null && shopPhone.length() >= 4) {
            result = shopPhone.substring(0, 3) + "****" + shopPhone.substring(shopPhone.length() - 4);
        }
        return result;

    }
}
