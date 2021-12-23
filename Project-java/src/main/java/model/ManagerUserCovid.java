package model;

import model.UserCovid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerUserCovid {
    List<UserCovid> listUserCovid = new ArrayList<>();

    public ManagerUserCovid() {
    }

    public void addUserCovid(UserCovid userCovid){
        listUserCovid.add(userCovid);
    }

    public void removeUserByID(String id){
        listUserCovid.removeIf(userCovid -> userCovid.getId().equals(id));
    }

    public List<UserCovid> getListUserCovid(){
        return listUserCovid;
    }

    public List<UserCovid> searchUserByName(String name){
        List<UserCovid> result = listUserCovid.stream().filter(userCovid -> userCovid.getName().equals(name)).collect(Collectors.toList());
        return result;
    }

    public UserCovid searchUserByID(String id){
        return listUserCovid.stream().filter(userCovid -> userCovid.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateUserCovidByState() {

    }

    public void updateUserCovidByHealthCenter(int idHealthCenter, String id) {
        UserCovid userCovid = searchUserByID(id);
        userCovid.setHealthCenter(idHealthCenter);
    }



}
