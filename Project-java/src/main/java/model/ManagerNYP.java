package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerNYP {

    List<NYP> list = new ArrayList<>();

    public ManagerNYP() {
    }

    public void removeAll() {
        list.clear();
    }

    public List<NYP> getList() {
        return list;
    }

    public void addNYP(NYP nyp) {
        list.add(nyp);
    }

    public void removeNYP(int id) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getId() == id) {
                list.remove(i);
            }
        }
    }

    public NYP searchNYP(String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    public boolean modifyNYP(NYP nyp) {
        var tmp = searchNYP(nyp.getName());
        if (tmp != null) {
            tmp.setName(nyp.getName());
            tmp.setExpriredDate(nyp.getExpriredDate());
            tmp.setPrice(nyp.getPrice());
            tmp.setLimit(nyp.getLimit());
            return true;
        }
        return false;
    }

    public List<NYP> sortNYPByDateIncrement() {
        return list.stream().sorted(Comparator.comparing(NYP::getExpriredDate)).collect(Collectors.toList());
    }

    public List<NYP> sortNYPByDateDecrement() {
        return  list.stream().sorted(Comparator.comparing(NYP::getExpriredDate).reversed()).collect(Collectors.toList());
    }

    public List<NYP> sortNYPByPriceIncrement() {
        return list.stream().sorted(Comparator.comparing(NYP::getPrice)).collect(Collectors.toList());
    }

    public List<NYP> sortNYPByPriceDecrement() {
        return list.stream().sorted(Comparator.comparing(NYP::getPrice).reversed()).collect(Collectors.toList());
    }

    public List<NYP> sortNYPByLimitIncrement() {
        return list.stream().sorted(Comparator.comparing(NYP::getLimit)).collect(Collectors.toList());
    }

    public List<NYP> sortNYPByLimitDecrement() {
        return list.stream().sorted(Comparator.comparing(NYP::getLimit).reversed()).collect(Collectors.toList());
    }

    public List<NYP> sortNYPByNameIncrement() {
        return list.stream().sorted(Comparator.comparing(NYP::getName)).collect(Collectors.toList());
    }

    public List<NYP> sortNYPByNameDescement() {
        return list.stream().sorted(Comparator.comparing(NYP::getName).reversed()).collect(Collectors.toList());
    }


}
