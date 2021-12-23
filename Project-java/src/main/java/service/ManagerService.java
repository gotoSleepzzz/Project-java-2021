package service;

import model.ManagerUserCovid;
import model.UserCovid;
import utils.dbUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ManagerService {

    private static ManagerService single_instance;
    private String nameManager = null;
    dbUtil db;
    ManagerUserCovid managerUserCovid = new ManagerUserCovid();
    HashMap<Integer, String> healthCenter = new HashMap<>();


    public ManagerService() {
        db = dbUtil.getDbUtil();
        MapUserCovidToHealthCenter();
    }

    public static ManagerService getInstance() {
        if (single_instance == null)
            single_instance = new ManagerService();
        return single_instance;
    }



    private String
            addUserCovid = "{Call proc_themnguoi (?, ?, ?, ?, ?, ?, ?, ?)}"; // _ten, _cmnd,_namsinh,_diachi,_trangthai,_noiquanly,_nguonlay,_quanly

    private String
            addNYP = "{call proc_themnhuyeupham (?, ?, ?, ?)}";


    private String
            deleteUserCovid = "DELETE FROM nguoi_lien_quan WHERE cmnd = ?";
    private String
            getAllUserCovid = "SELECT * FROM nguoi_lien_quan";

    private String
            updateUserCovidByDebt = "UPDATE nguoi_lien_quan SET ghino = ? WHERE cmnd = ?";

    private String
            updateUserCovidByState = "{call proc_chuyentrangthai(?,?,?)}"; // _doituong, _trangthaimoi, _quanly

    private String
            updateUserCovidByHealthCenter = "UPDATE nguoi_lien_quan SET idnoiquanly = ? WHERE cmnd = ?";

    private String
            getUserById = "SELECT * FROM nguoi_lien_quan WHERE cmnd = ?";

    private String
            getByPage = "SELECT * FROM nguoi_lien_quan LIMIT ?, ?";

    private String
            getAllHealthCenter = "SELECT * FROM noi_quan_ly";




    public boolean addUserCovid(UserCovid userCovid) {
        Object[] params = {
                userCovid.getName(),
                userCovid.getId(),
                userCovid.getDob(),
                userCovid.getAddress(),
                userCovid.getState(),
                userCovid.getHealthCenter(),
                userCovid.getIdReached(),
                this.nameManager
        };

        try {
            if (db.excuteProc(addUserCovid, params)) {
                managerUserCovid.addUserCovid(userCovid);
                return true;
            }

        } catch (Exception e) {
            System.out.println("Insert failed due some bug or id duplicated");
        }
        return false;
    }

    public boolean updateUserCovidByState(UserCovid userCovid) {
        Object[] params = {
                userCovid.getState(),
                userCovid.getId(),
        };

        return db.excuteProc(updateUserCovidByState, params);
    }

     public boolean updateUserCovidByHealthCenter(int healthCenter, String id) {
        Object[] params = {
                healthCenter,
                id,
        };
        if (db.excuteProc(updateUserCovidByHealthCenter, params)) {
            managerUserCovid.updateUserCovidByHealthCenter(healthCenter, id);
        }
         System.out.println("Error when change health center");
        return false;
    }

    public List<UserCovid> findAllUserCovid() {
        Object[] params = {};
        var rs = db.executeQuery(getAllUserCovid, params);
        try {
            while (rs.next()) {
                managerUserCovid.addUserCovid(new UserCovid(rs.getString("ten"),
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
        return managerUserCovid.getListUserCovid();
    }

    public UserCovid findOneUserCovid(String id) {
        Object[] params = {id};
        var rs = db.executeQuery(getUserById, params);
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

    public void MapUserCovidToHealthCenter() {
        Object[] params = {};
        var rs = db.executeQuery(getAllHealthCenter, params);
        try {
            if (rs.next()) {
                healthCenter.put(rs.getInt("id"), rs.getString("ten"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getHealthCenterName(int id) {
        return healthCenter.get(id);
    }

    public void setNameManager(String userName) {
        this.nameManager = userName;
    }
}
