package model.DAO;

import model.UserCovid;

import java.util.List;

public interface ImplementUserCovid {
    void add(UserCovid userCovid);
    void update(UserCovid userCovid);
    void delete(String id);
    List<UserCovid> getAll();
    UserCovid getUserCovid(String id);
}
