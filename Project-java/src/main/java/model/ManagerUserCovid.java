package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerUserCovid {
    List<UserCovid> listUserCovid = new ArrayList<>();


    public ManagerUserCovid() {
    }

    public void removeListUserCovid() {
        listUserCovid.clear();
    }

    public void addUserCovid(UserCovid userCovid) {
        listUserCovid.add(userCovid);
    }

    public void removeUserByID(String id) {
        listUserCovid.removeIf(userCovid -> userCovid.getId().equals(id));
    }

    public List<UserCovid> getListUserCovid() {
        return listUserCovid;
    }

    public List<UserCovid> searchUserByName(String name) {
        List<UserCovid> result = listUserCovid.stream().filter(userCovid -> userCovid.getName().equals(name)).collect(Collectors.toList());
        return result;
    }

    public UserCovid searchUserByID(String id) {
        return listUserCovid.stream().filter(userCovid -> userCovid.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateUserCovidByState(String state, String id) {
        // update
        UserCovid userCovid = searchUserByID(id);
        userCovid.setState(state);
    }

    public void updateUserCovidByHealthCenter(int idHealthCenter, String id) {
        UserCovid userCovid = searchUserByID(id);
        userCovid.setHealthCenter(idHealthCenter);
    }

    public List<UserCovid> sortUserCovidByNameIncrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getName)).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserCovidByDobIncrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getDob)).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserCovidByIdIncrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getId)).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserCovidByStateIncrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getState)).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserByDebtIncrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getDebt)).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserCovidByNameDecrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getName).reversed()).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserCovidByDobDecrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getDob).reversed()).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserCovidByIdDecrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getId).reversed()).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserCovidByStateDecrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getState).reversed()).collect(Collectors.toList());
    }

    public List<UserCovid> sortUserByDebtDecrement() {
        return listUserCovid.stream().sorted(Comparator.comparing(UserCovid::getDebt).reversed()).collect(Collectors.toList());
    }
}
