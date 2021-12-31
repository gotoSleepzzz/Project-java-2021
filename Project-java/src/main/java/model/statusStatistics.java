/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author TRUNG
 */
public class statusStatistics {
    String status;
    int quantity;
    Date time;

    public statusStatistics(String status, int quantity, Date time) {
        this.status = status;
        this.quantity = quantity;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getTime() {
        return time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
}
