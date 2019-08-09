package com.dingdian.order.bean;

import java.util.ArrayList;

public class CompleteList {
    private int total;
    private ArrayList<OrderBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<OrderBean> getList() {
        return list;
    }

    public void setList(ArrayList<OrderBean> list) {
        this.list = list;
    }
}
