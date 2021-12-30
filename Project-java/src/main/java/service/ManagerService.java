package service;

import model.*;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import utils.dbUtil;

import java.sql.SQLException;
import java.util.*;

public class ManagerService {

    dbUtil db;
    private static ManagerService single_instance;
    private String nameManager = null;
    ManagerUserCovid managerUserCovid = new ManagerUserCovid();
    HashMap<Integer, String> healthCenter = new HashMap<>();
    DirectedGraph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    List<UserCovid> userCovidList;
    ManagerNYP managerNYP = new ManagerNYP();

    public ManagerService() {

        db = dbUtil.getDbUtil();
        MapUserCovidToHealthCenter();
        userCovidList = findAllUserCovid();
        buildGraph();

    }

    public static ManagerService getInstance() {
        if (single_instance == null)
            single_instance = new ManagerService();
        return single_instance;
    }


    private String
            addUserCovid = "{Call proc_themnguoi (?, ?, ?, ?, ?, ?, ?, ?)}"; // _ten, _cmnd,_namsinh,_diachi,_trangthai,_noiquanly,_nguonlay,_quanly

    private String
            addNYP = "{call proc_themnhuyeupham (?, ?, ?, ?, ?)}"; // _ten, _muchan, _hsd, _gia, _quanly

    private String
            updateUserCovidByState = "{call proc_chuyentrangthai(?,?,?)}"; // _doituong, _trangthaimoi, _quanly

    private String
            getAllUserCovid = "SELECT * FROM nguoi_lien_quan";

    private String
            updateUserCovidByHealthCenterProc = "{call proc_chuyennoidieutri(?,?,?)}";

    private String
            getUserById = "SELECT * FROM nguoi_lien_quan WHERE cmnd = ?";

    private String
            getByPage = "SELECT * FROM nguoi_lien_quan LIMIT ?, ?";

    private String
            getAllHealthCenter = "SELECT * FROM noi_quan_ly";

    private String
            removeNYP = "{call proc_XoaNhuYeuPham(?, ?)}"; // id, quanly

    private String
            getAllNYP = "Select * from nhu_pham";

    private String
            getOneNYP = "select * from nhu_pham where id = ?";

    private String
            updateNYP = "{call proc_CapNhatNhuYeuPham(?, ?, ?, ?, ?, ?)}"; // id, ten, muchan,hsd,gia,quanly

    // find nyp in range of price
    private String
            filterNYPByPrice = "SELECT * FROM nhu_pham WHERE gia BETWEEN ? AND ?";

    private String
            updateStateUserCovid = "{call proc_chuyentrangthai(?,?,?)}";

    private String
            getHistoryChangeState = "select * from chuyen_trang_thai where cmnd = ?";

    private String
            countHistroyChangeState = "select count(*) from chuyen_trang_thai where cmnd = ?";

    private String
            addHistoryChangeState = "insert into chuyen_trang_thai values(?,?,?,?,?,?)";


    public List<NYP> filterNYP(int min, int max) {
        Object[] params = {min, max};
        var rs = db.executeQuery(filterNYPByPrice, params);
        List<NYP> nypList = new ArrayList<>();
        while (true) {
            try {
                if (!rs.next()) break;
                nypList.add(new NYP(rs.getInt("id"),
                        rs.getString("ten"),
                        rs.getInt("muchan"),
                        rs.getInt("hsd"),
                        rs.getDouble("gia")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nypList;
    }


    public boolean updateNYP(NYP nyp) {
        Object[] params = {nyp.getId(), nyp.getName(), nyp.getLimit(), nyp.getExpriredDate(), nyp.getPrice(), this.nameManager};
        return db.excuteProc(updateNYP, params);
    }


    public NYP findOneNYPById(int id) {
        Object[] params = {id};
        var rs = db.executeQuery(getOneNYP, params);
        try {
            if (rs.next()) {
                return new NYP(rs.getInt("id"),
                        rs.getString("ten"),
                        rs.getInt("muchan"),
                        rs.getInt("hsd"),
                        rs.getDouble("gia"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Object[][] getHistoryChangeState(String id) {
        Object[] params;
        params = new Object[]{id};
        var rs = db.executeQuery(countHistroyChangeState, params);
        if (rs == null) return null;

        int count = 0;

        try {
            count = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] result = new Object[count][5];

        rs = db.executeQuery(getHistoryChangeState, params);
        int i = 0;
        while (true) {
            try {
                if (!rs.next()) break;
                result[i][3] = rs.getString(2);
                result[i][4] = rs.getString(3);
                result[i][1] = rs.getString(4);
                result[i][2] = rs.getString(5);
                result[i][0] = i;
                // 4 5 2 3

                ++i;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }




    public List<NYP> findAllNYP() {

        Object[] params = {};
        var rs = db.executeQuery(getAllNYP, params);
        try {
            managerNYP.removeAll();
            while (rs.next()) {
                managerNYP.addNYP(new NYP(rs.getInt("id"),
                        rs.getString("ten"),
                        rs.getInt("muchan"),
                        rs.getInt("hsd"),
                        rs.getDouble("gia")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return managerNYP.getList();
    }


    public boolean addNYP(NYP nyp) {
        Object[] params = {
                nyp.getName(),
                nyp.getLimit(),
                nyp.getExpriredDate(),
                nyp.getPrice(),
                this.nameManager
        };

        try {
            db.excuteProc(addNYP, params);
            findAllNYP();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean removeNYP(int id) {

        Object[] params = {
                id,
                this.nameManager
        };
        try {
            managerNYP.removeNYP(id);
            db.excuteProc(removeNYP, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


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


    public String changeState(int state) {
        return state == 1 ? "F1" : state == 2 ? "F2" : state == 3 ? "F3" : state == 4 ? "F4" : "F0";
    }

    public boolean updateUserCovidByState(String id, String state, String currentState) {
        Object[] params = {
                id,
                state,
                this.nameManager
        };

        if (state.equals("OK")) {
            try {
                db.excuteProc(updateUserCovidByState, params);
                Object [] params2 = {
                        id,
                        "OK",
                        currentState,
                        null,
                        null,
                        this.nameManager
                };
                db.executeUpdate(addHistoryChangeState, params2);
            } catch (Exception e) {
                return false;
            }
            return true;
        }


        if (currentState.equals("F3")) {
            UserCovid currentUser = findOneUserCovid(id);
            params[0] = currentUser.getIdReached();
            params[1] = "F1";
            try {
                db.excuteProc(updateUserCovidByState, params);
                Object [] params2 = {
                        id,
                        "F1",
                        "F2",
                        null,
                        null,
                        this.nameManager
                };
                db.executeUpdate(addHistoryChangeState, params2);


            } catch (Exception e) {
                return false;
            }

            params[0] = id;
            params[1] = "F0";
            try {
                db.excuteProc(updateUserCovidByState, params);
                Object [] params2 = {
                        id,
                        "F0",
                        currentState,
                        null,
                        null,
                        this.nameManager
                };
                db.executeUpdate(addHistoryChangeState, params2);

            } catch (Exception e) {
                return false;
            }
            traversalAndUpdate(currentUser.getIdReached(), 2, id);
            traversalAndUpdate(id, 1, id);

        } else {
            params[0] = id;
            params[1] = "F0";
            try {
                db.excuteProc(updateUserCovidByState, params);
                Object[] params2 = {
                        id,
                        "F0",
                        currentState,
                        null,
                        null,
                        this.nameManager
                };
                db.executeUpdate(addHistoryChangeState, params2);
            } catch (Exception e) {
                return false;
            }
            traversalAndUpdate(id, 1, "");
        }

        return true;
    }


    void traversalAndUpdate(String first, int count, String id) {

        // iterater through the first layer node using breadth first search
        Queue<String> queue = new LinkedList<>();
        queue.add(first);

        int layer = count;
        while (!queue.isEmpty()) {
            String node = queue.poll();

            // get al the vertex that is connected to the current node
            Set<DefaultEdge> set = directedGraph.outgoingEdgesOf(node);
            for (DefaultEdge edge : set) {

                String next = directedGraph.getEdgeTarget(edge);
                if ((next.equals(id))) {
                    continue;
                }
                String newState = changeState(count);
                queue.add(next);
                Object[] param = {
                        newState,
                        this.nameManager
                };
                try {

                    Object [] params = {
                            id,
                            newState,
                            findOneUserCovid(id).getState(),
                            null,
                            null,
                            this.nameManager
                    };


                    db.executeUpdate(addHistoryChangeState, params);
                    db.excuteProc(updateUserCovidByState, param);
                } catch (Exception e) {
                    System.out.println("Update failed");
                }
            }
            count++;
        }
    }


    public boolean updateUserCovidByHealthCenter(int healthCenter, String id) {
        Object[] params = {
                id,
                healthCenter,
                this.nameManager
        };
        try {

            Object params2 [] = {
                    id,
                    null,
                    null,
                    getHealthCenterName(healthCenter),
                    getHealthCenterName(findOneUserCovid(id).getHealthCenter()),
                    this.nameManager
            };
            db.executeUpdate(addHistoryChangeState, params2);
            db.excuteProc(updateUserCovidByHealthCenterProc, params);
            managerUserCovid.updateUserCovidByHealthCenter(healthCenter, id);

        } catch (Exception e) {
            System.out.println("Error when change health center");
        }
        return false;
    }

    public List<UserCovid> findAllUserCovid() {
        Object[] params = {};
        var rs = db.executeQuery(getAllUserCovid, params);
        try {
            managerUserCovid.removeListUserCovid();
            while (rs.next()) {
                managerUserCovid.addUserCovid(new UserCovid(rs.getString("ten"),
                        rs.getString("cmnd"),
                        Integer.parseInt(rs.getString("namsinh")),
                        rs.getString("diachi"),
                        rs.getString("trangthai"),
                        rs.getInt("idnoiquanly"),
                        rs.getString("nguonlay"),
                        rs.getDouble("ghino")
                ));
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
                        rs.getString("nguonlay"),
                        rs.getDouble("ghino"));
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
            while (rs.next()) {
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


    public List<UserCovid> getByPage(int page, int size) {
        Object[] params = {page, size};
        var rs = db.executeQuery(getByPage, params);
        try {
            while (rs.next()) {
                managerUserCovid.addUserCovid(new UserCovid(rs.getString("ten"),
                        rs.getString("cmnd"),
                        Integer.parseInt(rs.getString("namsinh")),
                        rs.getString("diachi"),
                        rs.getString("trangthai"),
                        rs.getInt("idnoiquanly"),
                        rs.getString("nguonlay"),
                        rs.getDouble("ghino")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return managerUserCovid.getListUserCovid();
    }


    public int mapHealthCenterToId(String name) {
        int res = -1;
        for (Map.Entry<Integer, String> entry : healthCenter.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return res;
    }


    public void buildGraph() {

        for (UserCovid userCovid : userCovidList) {
            String id = userCovid.getId();
            directedGraph.addVertex(id);
            String idReached = userCovid.getIdReached();
            if (idReached != null) {
                if (!directedGraph.containsVertex(idReached)) {
                    directedGraph.addVertex(idReached);
                }
                directedGraph.addEdge(idReached, id);
            }
        }

    }


    public List<String> getListHealtCenter() {
        return new ArrayList<>(healthCenter.values());
    }

    public List<UserCovid> sortByNameIncrement() {
        return managerUserCovid.sortUserCovidByNameIncrement();
    }

    public List<UserCovid> sortByDobIncrement() {
        return managerUserCovid.sortUserCovidByDobIncrement();
    }

    public List<UserCovid> sortByDebtIncrement() {
        return managerUserCovid.sortUserByDebtIncrement();
    }

    public List<UserCovid> sortByStatusIncrement() {
        return managerUserCovid.sortUserCovidByStateIncrement();
    }

    public List<UserCovid> sortByIdIncrement() {
        return managerUserCovid.sortUserCovidByIdIncrement();
    }

    public List<UserCovid> sortByNameDecrement() {
        return managerUserCovid.sortUserCovidByNameDecrement();
    }

    public List<UserCovid> sortByDobDecrement() {
        return managerUserCovid.sortUserCovidByDobDecrement();
    }

    public List<UserCovid> sortByDebtDecrement() {
        return managerUserCovid.sortUserByDebtDecrement();
    }

    public List<UserCovid> sortByStatusDecrement() {
        return managerUserCovid.sortUserCovidByStateDecrement();
    }

    public List<UserCovid> sortByIdDecrement() {
        return managerUserCovid.sortUserCovidByIdDecrement();
    }


    public List<NYP> sortNYPByDateIncrement() {
        return managerNYP.sortNYPByDateIncrement();
    }

    public List<NYP> sortNYPByDateDecrement() {
        return managerNYP.sortNYPByDateDecrement();
    }

    public List<NYP> sortNYPByPriceIncrement() {
        return managerNYP.sortNYPByPriceIncrement();
    }

    public List<NYP> sortNYPByPriceDecrement() {
        return managerNYP.sortNYPByPriceDecrement();
    }

    public List<NYP> sortNYPByLimitIncrement() {
        return managerNYP.sortNYPByLimitIncrement();
    }

    public List<NYP> sortNYPByLimitDecrement() {
        return managerNYP.sortNYPByLimitDecrement();
    }


    public NYP getNYPByName(String name) {
        var list = findAllNYP();
        for (NYP nyp : list) {
            if (nyp.getName().equals(name)) {
                return nyp;
            }
        }
        return null;
    }

    public NYP getNYPByName(List<NYP> list, String name) {
        for (NYP nyp : list) {
            if (nyp.getName().equals(name)) {
                return nyp;
            }
        }
        return null;
    }

    public List<UserCovid> findAllRelative(String id) {
        List<UserCovid> list = new ArrayList<>();
        Set<DefaultEdge> listId = directedGraph.outgoingEdgesOf(id);
        for (DefaultEdge edge : listId) {
            list.add(findOneUserCovid(directedGraph.getEdgeTarget(edge)));
        }
        return list;
    }
}
