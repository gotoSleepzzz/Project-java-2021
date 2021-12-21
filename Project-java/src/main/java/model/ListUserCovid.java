package model;

import model.UserCovid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUserCovid {
    List<UserCovid> listUserCovid = new ArrayList<>();

    public ListUserCovid() {
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

}
