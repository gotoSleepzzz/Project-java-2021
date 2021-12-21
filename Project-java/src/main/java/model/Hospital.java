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
public class Hospital {
    String ten;
    Integer sucChua;
    Integer SLHienTai;

    public Hospital(String ten, Integer sucChua, Integer SLHienTai) {
        this.ten = ten;
        this.sucChua = sucChua;
        this.SLHienTai = SLHienTai;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSucChua(Integer sucChua) {
        this.sucChua = sucChua;
    }

    public void setSLHienTai(Integer SLHienTai) {
        this.SLHienTai = SLHienTai;
    }

    public String getTen() {
        return ten;
    }

    public Integer getSucChua() {
        return sucChua;
    }

    public Integer getSLHienTai() {
        return SLHienTai;
    }
    
}
