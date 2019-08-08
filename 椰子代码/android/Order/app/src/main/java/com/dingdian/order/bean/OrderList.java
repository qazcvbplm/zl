package com.dingdian.order.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class OrderList {
    private String list;
    private ArrayList<OrderBean> orderList;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public ArrayList<OrderBean> getOrderList() {
        if (list != null) {
            Gson gson = new Gson();
            orderList = gson.fromJson(list, new TypeToken<ArrayList<OrderBean>>() {
            }.getType());
        }
        return orderList;
    }

    public void setOrderList(ArrayList<OrderBean> orderList) {
        this.orderList = orderList;
    }
}
