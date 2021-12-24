package model;

public class UserCovid {
    private String name;
    private String id;
    private int dob;
    private String address;
    private String state;
    private int healthCenter;
    private String idReached;
    private double debt = 0;

    public UserCovid(String name, String id, int dob, String address, String state, int healthCenter, double debt) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.address = address;
        this.state = state;
        this.healthCenter = healthCenter;
        this.debt = debt;
    }


    public UserCovid(String name, String id, int dob, String address, String state, int healthCenter, String idReached) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.address = address;
        this.state = state;
        this.healthCenter = healthCenter;
        this.idReached = idReached;
    }

    public UserCovid(String name, String id, int dob, String address, String state, int healthCenter, String idReached, double debt) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.address = address;
        this.state = state;
        this.healthCenter = healthCenter;
        this.idReached = idReached;
        this.debt = debt;

    }





    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setHealthCenter(int healthCenter) {
        this.healthCenter = healthCenter;
    }

    public void setIdReached(String idReached) {
        this.idReached = idReached;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Integer getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public int getHealthCenter() {
        return healthCenter;
    }

    public String getIdReached() {
        return idReached;
    }

    public double getDebt() {
        return debt;
    }
    @Override
    public String toString() {
        return "UserCovid{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", healthCenter='" + healthCenter + '\'' +
                ", idReached='" + idReached + '\'' +
                ", debt=" + debt +
                '}';
    }


}
