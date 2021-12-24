/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * @author DELL
 */
public class NYP {

    private int id;
    private String name;
    private int limit;
    private Date expriredDate;
    private double price;

    public NYP(int id, String name, int limit, Date expriredDate, double price) {
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.expriredDate = expriredDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public Date getExpriredDate() {
        return expriredDate;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setExpriredDate(Date expriredDate) {
        this.expriredDate = expriredDate;
    }

    void setPrice(double price) {
        this.price = price;
    }
}