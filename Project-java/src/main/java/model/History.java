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
public class History {
    String user;
    String action;
    String table;
    String msg;
    Date time;

    public History(String user, String msg, Date time) {
        this.user = user;
        this.msg = msg;
        this.time = time;
    }

    public History(String user, String action, String table, String msg, Date time) {
        this.user = user;
        this.action = action;
        this.table = table;
        this.msg = msg;
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

    public String getTable() {
        return table;
    }

    public String getMsg() {
        return msg;
    }

    public Date getTime() {
        return time;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
