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
public class ManagerHistory {
    int id;
    String nguoiQL;
    String userId;
    String oldStatus;
    String newStatus;
    int idOldHospital;
    int idNewHospital;
    Date time;

    public ManagerHistory(int id, String nguoiQL, String userId, String oldStatus, String newStatus, int idOldHospital, int idNewHospital, Date time) {
        this.id = id;
        this.nguoiQL = nguoiQL;
        this.userId = userId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.idOldHospital = idOldHospital;
        this.idNewHospital = idNewHospital;
        this.time = time;
    }

    public ManagerHistory() {
    }

    public Integer getId() {
        return id;
    }

    public String getNguoiQL() {
        return nguoiQL;
    }

    public String getUserId() {
        return userId;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public Integer getIdOldHospital() {
        return idOldHospital;
    }

    public Integer getIdNewHospital() {
        return idNewHospital;
    }

    public Date getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNguoiQL(String nguoiQL) {
        this.nguoiQL = nguoiQL;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public void setIdOldHospital(int idOldHospital) {
        this.idOldHospital = idOldHospital;
    }

    public void setIdNewHospital(int idNewHospital) {
        this.idNewHospital = idNewHospital;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
}
