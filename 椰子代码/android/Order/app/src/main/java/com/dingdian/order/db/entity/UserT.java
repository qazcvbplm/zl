package com.dingdian.order.db.entity;

import java.math.BigDecimal;

public class UserT extends User {

    private BigDecimal txAmount;



    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }
}
