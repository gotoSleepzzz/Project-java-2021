package model;

import service.ManagerService;

import java.text.Collator;
import java.util.*;
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

    public List<UserCovid> sortUserCovidByNameIncrement(int previousTotal) {
        // sort with vietnamese
        // just get previsousTotal element

        Collator collate = Collator.getInstance(new Locale("vi"));
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getName, collate)).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserCovidByDobIncrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return ManagerService.getInstance().findAllUserCovid().stream().sorted(Comparator.comparing(UserCovid::getDob)).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserCovidByIdIncrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return ManagerService.getInstance().findAllUserCovid().stream().sorted(Comparator.comparing(UserCovid::getId)).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserCovidByStateIncrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getState)).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserByDebtIncrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getDebt)).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserCovidByNameDecrement(int previousTotal) {
        // sort with vietnamese
        Collator collate = Collator.getInstance(new Locale("vi"));
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getName, collate).reversed()).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserCovidByDobDecrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getDob).reversed()).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserCovidByIdDecrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getId).reversed()).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserCovidByStateDecrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getState).reversed()).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }

    public List<UserCovid> sortUserByDebtDecrement(int previousTotal) {
        var list = ManagerService.getInstance().findAllUserCovid();
        int size = list.size();
        return list.stream().sorted(Comparator.comparing(UserCovid::getDebt).reversed()).collect(Collectors.toList()).subList(0, Math.min(previousTotal, size));
    }
}
