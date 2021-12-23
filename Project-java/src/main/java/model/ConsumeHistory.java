/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author TRUNG
 */
public class ConsumeHistory {
    int id;
    String UserId;
    int NeccessaryId;
    int quantity;
    float total;
    Date time;

    public ConsumeHistory(int id, String UserId, int NeccessaryId, int quantity, float total, Date time) {
        this.id = id;
        this.UserId = UserId;
        this.NeccessaryId = NeccessaryId;
        this.quantity = quantity;
        this.total = total;
        this.time = time;
    }

    public ConsumeHistory(int NeccessaryId, int quantity, float total, Date time) {
        this.NeccessaryId = NeccessaryId;
        this.quantity = quantity;
        this.total = total;
        this.time = time;
    }
    
    public ConsumeHistory(){
        this.id = -1;
        this.UserId = null;
        this.NeccessaryId = -1;
        this.quantity = -1;
        this.total = -1;
        this.time = null;
    }
    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return UserId;
    }

    public Integer getNeccessaryId() {
        return NeccessaryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getTotal() {
        return total;
    }

    public Date getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public void setNeccessaryId(int NeccessaryId) {
        this.NeccessaryId = NeccessaryId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
}
