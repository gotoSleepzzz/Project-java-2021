package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public void sortNYPByDateIncrement() {
        list.sort(Comparator.comparing(NYP::getExpriredDate));
    }

    public void sortNYPByDateDescement() {
        list.sort(Comparator.comparing(NYP::getExpriredDate).reversed());
    }

    public void sortNYPByPriceIncrement() {
        list.sort(Comparator.comparing(NYP::getPrice));
    }

    public void sortNYPByPriceDescement() {
        list.sort(Comparator.comparing(NYP::getPrice).reversed());
    }

    public void sortNYPByLimitIncrement() {
        list.sort(Comparator.comparing(NYP::getLimit));
    }

    public void sortNYPByLimitDescement() {
        list.sort(Comparator.comparing(NYP::getLimit).reversed());
    }

    public void sortNYPByNameIncrement() {
        list.sort(Comparator.comparing(NYP::getName));
    }

    public void sortNYPByNameDescement() {
        list.sort(Comparator.comparing(NYP::getName).reversed());
    }


}
