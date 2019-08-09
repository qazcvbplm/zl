package com.dingdian.order.bean;

import com.google.gson.Gson;

public class MakeOrder {

    private String order;

    private OrderBean bean;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public OrderBean getBean() {
        if (order !=null &&order.length()> 0){
            Gson gson = new Gson();
            bean = gson.fromJson(order,OrderBean.class);
        }

        return bean;
    }

    public void setBean(OrderBean bean) {
        this.bean = bean;
    }
}
