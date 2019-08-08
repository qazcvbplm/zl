package com.dingdian.order.bean;

public class ShopStatisticsResult {

    /**
     * totalCount : do
     * totalSuccessCount : 0
     * totalPrice : 0
     * selfGet : 0
     * boxPrice : 0
     * sendPrice : 0
     */

    private int totalCount;
    private int totalSuccessCount;
    private double totalPrice;
    private double selfGet;
    private double boxPrice;
    private double sendPrice;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalSuccessCount() {
        return totalSuccessCount;
    }

    public void setTotalSuccessCount(int totalSuccessCount) {
        this.totalSuccessCount = totalSuccessCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getSelfGet() {
        return selfGet;
    }

    public void setSelfGet(double selfGet) {
        this.selfGet = selfGet;
    }

    public double getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(double boxPrice) {
        this.boxPrice = boxPrice;
    }

    public double getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(double sendPrice) {
        this.sendPrice = sendPrice;
    }
}
