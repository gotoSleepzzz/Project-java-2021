package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserCovid {
    private String name;
    private String id;
    private Date dob;
    private String address;
    private String state;
    private String healthCenter;
    private List<String> peopleReached = new ArrayList<>();
    private List<String> history = new ArrayList<>();

    public UserCovid(String name, String id, Date dob, String address, String state, String healthCenter, String peopleReached) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.address = address;
        this.state = state;
        this.healthCenter = healthCenter;
        this.peopleReached.add(peopleReached);
    }
    
    public UserCovid(String name, String id, Date dob, String address, String state, String healthCenter, List<String> peopleReached, List<String> history) {

        this.name = name;
        this.id = id;
        this.dob = dob;
        this.address = address;
        this.state = state;
        this.healthCenter = healthCenter;
        this.peopleReached = peopleReached;
        this.history = history;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getHealthCenter() {
        return healthCenter;
    }

    public List<String> getPeopleReached() {
        return peopleReached;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setHealthCenter(String healthCenter) {
        this.healthCenter = healthCenter;
    }

    public void setPeopleReached(ArrayList<String> peopleReached) {
        this.peopleReached = peopleReached;
    }
    public void setPeopleReached(List<String> peopleReached) {
        this.peopleReached = peopleReached;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
    public void setHistory(ArrayList<String> history) {
        this.history = history;
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
                ", peopleReached=" + peopleReached +
                ", history=" + history +
                '}';
    }

}
