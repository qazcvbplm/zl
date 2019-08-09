package com.dingdian.order.bean;

import java.util.ArrayList;

public class ShopList {

    private int total;
    private ArrayList<ShopBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ShopBean> getList() {
        return list;
    }

    public void setList(ArrayList<ShopBean> list) {
        this.list = list;
    }
}
