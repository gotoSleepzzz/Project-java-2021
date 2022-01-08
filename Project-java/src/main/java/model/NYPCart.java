package model;
public class NYPCart {
    private int id;
    private String name;
    private int soluong;
    private double price;
    private int expriredDate;

    public int getExpriredDate() {
        return expriredDate;
    }

    public NYPCart(int id, String name, int soluong, double price, int expriredDate) {
        this.id = id;
        this.name = name;
        this.soluong = soluong;
        this.price = price;
        this.expriredDate = expriredDate;
    }

    public void setExpriredDate(int expriredDate) {
        this.expriredDate = expriredDate;
    }

    public NYPCart(String name, int soluong, double price) {
        this.name = name;
        this.soluong = soluong;
        this.price = price;
    }

    public NYPCart(int id, String name, int soluong, double price) {
        this.id = id;
        this.name = name;
        this.soluong = soluong;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public NYPCart() {
    }
    
}
