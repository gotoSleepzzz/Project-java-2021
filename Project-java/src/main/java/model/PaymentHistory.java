package model;

import java.sql.Timestamp;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TRUNG
 */
public class PaymentHistory {
    private String userId;
    private float money;
    private Timestamp time;

    public PaymentHistory(String userId, float money, Timestamp time) {
        this.userId = userId;
        this.money = money;
        this.time = time;
    }

    public PaymentHistory() {
    }
    
    public String getUserId() {
        return userId;
    }

    public Float getMoney() {
        return money;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
}
