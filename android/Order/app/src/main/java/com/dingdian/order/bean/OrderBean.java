package com.dingdian.order.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBean implements Serializable {

    /**
     * addressDetail : 测试学校（用户勿选）,楼栋一,椰子校园生活哈哈201
     * addressName : 陈伟霆
     * addressPhone : 19999999999
     * appId : 1
     * boxPrice : 0
     * createTime : 2019-03-15 09:07:31.0
     * destination : 0
     * discountPrice : 0
     * endTime :
     * evaluateFlag : 0
     * floorId : 1
     * id : 201903150907312392153251225
     * openId : oWP5G42cNW2gghAZiqvfOFDQ--0A
     * payPrice : 0.1
     * payTime : 2019-03-15 09:07:40
     * payTimeLong : 1552612060100
     * payment : 微信支付
     * productPrice : 0.1
     * remark : 无
     * reseverTime : 09:27
     * schoolId : 9
     * schoolTopDownPrice : 0.5
     * sendAddCountPrice : 0
     * sendAddDistancePrice : 0
     * sendBasePrice : 0
     * sendGetFlag : 0
     * sendPrice : 0
     * senderId : 0
     * "senderName": "chen陈二傻",
     * "senderPhone": "18857818257",
     * shopAddress : 薰衣草庄园店
     * shopId : 10
     * shopImage : http://ops.sunwou.com/6744312a-078b-4bac-8b4e-6b43fcb0e344
     * shopName : 日式料理
     * shopPhone : 18888888888
     * status : 待接手
     * typ : 堂食订单
     * waterNumber : 0
     */

    private String addressDetail;
    private String addressName;
    private String addressPhone;
    private int appId;
    private double boxPrice;
    private String createTime;
    private int destination;
    private double discountPrice;
    private String endTime;
    private int evaluateFlag;
    private int floorId;
    private String id;
    private String openId;
    private double payPrice;
    private String payTime;
    private long payTimeLong;
    private String payment;
    private double productPrice;
    private String remark;
    private String reseverTime;
    private int schoolId;
    private double schoolTopDownPrice;
    private double sendAddCountPrice;
    private double sendAddDistancePrice;
    private double sendBasePrice;
    private int sendGetFlag;
    private double sendPrice;
    private int senderId;
    private String shopAddress;
    private int shopId;
    private String shopImage;
    private String shopName;
    private String senderName;
    private String senderPhone;
    private String shopPhone;
    private String status;
    private String typ;
    private int waterNumber;
    private ArrayList<Op> op;

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public double getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(double boxPrice) {
        this.boxPrice = boxPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(int evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public long getPayTimeLong() {
        return payTimeLong;
    }

    public void setPayTimeLong(long payTimeLong) {
        this.payTimeLong = payTimeLong;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReseverTime() {
        return reseverTime;
    }

    public void setReseverTime(String reseverTime) {
        this.reseverTime = reseverTime;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public double getSchoolTopDownPrice() {
        return schoolTopDownPrice;
    }

    public void setSchoolTopDownPrice(double schoolTopDownPrice) {
        this.schoolTopDownPrice = schoolTopDownPrice;
    }

    public double getSendAddCountPrice() {
        return sendAddCountPrice;
    }

    public void setSendAddCountPrice(double sendAddCountPrice) {
        this.sendAddCountPrice = sendAddCountPrice;
    }

    public double getSendAddDistancePrice() {
        return sendAddDistancePrice;
    }

    public void setSendAddDistancePrice(double sendAddDistancePrice) {
        this.sendAddDistancePrice = sendAddDistancePrice;
    }

    public double getSendBasePrice() {
        return sendBasePrice;
    }

    public void setSendBasePrice(double sendBasePrice) {
        this.sendBasePrice = sendBasePrice;
    }

    public int getSendGetFlag() {
        return sendGetFlag;
    }

    public void setSendGetFlag(int sendGetFlag) {
        this.sendGetFlag = sendGetFlag;
    }

    public double getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(double sendPrice) {
        this.sendPrice = sendPrice;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(int waterNumber) {
        this.waterNumber = waterNumber;
    }

    public ArrayList<Op> getOp() {
        return op;
    }

    public void setOp(ArrayList<Op> op) {
        this.op = op;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getOrderType(){
        String result = "(自取)";
        if (typ.contains("外卖")){
            result = "(外卖)";
        }else if (typ.contains("堂食")){
            result = "(堂食)";
        }
        return result;
    }

    public String isSendGet(){
        String result = "商家已接手";
        if (sendGetFlag == 1){
            result = "配送员已接手";
        }
        return result;
    }

    public boolean showSenderInfo(){
        boolean result = false;
        if (!TextUtils.isEmpty(senderName) || !TextUtils.isEmpty(senderPhone)){
            result = true;
        }
        return result;
    }

    public String formatAddressPhone() {
        String result = addressPhone;
        if (addressPhone!=null && addressPhone.length() >= 4) {
            result = addressPhone.substring(0, 3) + "****" + addressPhone.substring(addressPhone.length() - 4);
        }
        return result;

    }

    public String formatSenderPhone() {
        String result = senderPhone;
        if (senderPhone!=null && senderPhone.length() >= 4) {
            result = senderPhone.substring(0, 3) + "****" + senderPhone.substring(senderPhone.length() - 4);
        }
        return result;

    }
}
