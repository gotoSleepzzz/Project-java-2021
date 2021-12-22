package service;

import model.ListUserCovid;
import model.UserCovid;
import utils.dbUtil;

import java.sql.SQLException;
import java.util.List;

public class UserService{
    dbUtil db;

    public UserService() {
        db = dbUtil.getDbUtil();
    }

    private String
            add = "INSERT INTO nguoi_lien_quan (ten, cmnd, namsinh, diachi, trangthai, idnoiquanly) VALUES (?, ?, ?, ?, ?, ?)";
    private String
            delete = "DELETE FROM nguoi_lien_quan WHERE cmnd = ?";
    private String
            getAll = "SELECT * FROM nguoi_lien_quan";
    private String
            updateUserCovidByState = "UPDATE nguoi_lien_quan SET trangthai = ? WHERE cmnd = ?";

    private String
            updateUserCovidByDebt = "UPDATE nguoi_lien_quan SET ghino = ? WHERE cmnd = ?";

    private String
            updateUserCovidByHealthCenter = "UPDATE nguoi_lien_quan SET idnoiquanly = ? WHERE cmnd = ?";

    private String
            getById = "SELECT * FROM nguoi_lien_quan WHERE cmnd = ?";
    private String
            getAllUser = "SELECT * FROM nguoi_lien_quan WHERE covid_id = ?";
    private String
            getByPage = "SELECT * FROM nguoi_lien_quan LIMIT ?, ?";
    ListUserCovid listUserCovid = new ListUserCovid();

    public void add(UserCovid userCovid) {
        Object[] params = {
                userCovid.getName(),
                userCovid.getId(),
                userCovid.getDob(),
                userCovid.getAddress(),
                userCovid.getState(),
                userCovid.getHealthCenter(),
        };

        try {
            db.executeUpdate(add, params);
            listUserCovid.addUserCovid(userCovid);
        } catch (Exception e) {
            System.out.println("Insert failed due some bug or id duplicated");
        }
    }

    public void update(UserCovid userCovid) {
        Object[] params = {
                userCovid.getState(),
                userCovid.getId(),
        };
        db.executeUpdate(updateUserCovidByState, params);
    }

    public void delete(String id) {
        Object[] params = {id};
        db.executeUpdate(delete, params);
    }


    public List<UserCovid> findAll() {
        Object[] params = {};
        var rs = db.executeQuery(getAll, params);
        try {
            while (rs.next()) {
                listUserCovid.addUserCovid(new UserCovid(rs.getString("ten"),
                        rs.getString("cmnd"),
                        Integer.parseInt(rs.getString("namsinh")),
                        rs.getString("diachi"),
                        rs.getString("trangthai"),
                        rs.getInt("idnoiquanly"),
                        rs.getString("ghino")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listUserCovid.getListUserCovid();
    }

    public UserCovid getUserCovidByID(String id) {
        Object[] params = {id};
        var rs = db.executeQuery(getById, params);
        try {
            if (rs.next()) {
                return new UserCovid(rs.getString("ten"),
                        rs.getString("cmnd"),
                        Integer.parseInt(rs.getString("namsinh")),
                        rs.getString("diachi"),
                        rs.getString("trangthai"),
                        rs.getInt("idnoiquanly"),
                        rs.getString("ghino")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
