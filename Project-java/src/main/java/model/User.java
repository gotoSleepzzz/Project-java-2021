/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author TRUNG
 */
public class User {
    String name;
    String cmnd;
    int bornYear;
    String Address;
    String status;
    int idhospital;
    float debt;
    public User(String name, String cmnd, int bornYear, String Address, String status, int idhospital, float debt) {
        this.name = name;
        this.cmnd = cmnd;
        this.bornYear = bornYear;
        this.Address = Address;
        this.status = status;
        this.idhospital = idhospital;
        this.debt = debt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdhospital(int idhospital) {
        this.idhospital = idhospital;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public String getName() {
        return name;
    }

    public String getCmnd() {
        return cmnd;
    }

    public Integer getBornYear() {
        return bornYear;
    }

    public String getAddress() {
        return Address;
    }

    public String getStatus() {
        return status;
    }

    public int getIdhospital() {
        return idhospital;
    }

    public float getDebt() {
        return debt;
    }
    
}
