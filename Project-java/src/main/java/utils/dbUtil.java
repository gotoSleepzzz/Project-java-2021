package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbUtil {
    private static dbUtil db;
    private Connection conn;
    private PreparedStatement ps;
    private CallableStatement cs;
    private ResultSet rs;
    
    private dbUtil(){
        getConn();
    }
    
    public static dbUtil getDbUtil() {
        if (db == null) {
            db = new dbUtil();
        }
        return db;
    }
    
    public int executeUpdate(String sql) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean excuteProc(String sql, Object[] obj) {
        boolean result = false;
        if(getConn() == null){
            return result;
        }
        try{
            cs = conn.prepareCall(sql);
            for(int i=0;i<obj.length;i++){
                System.out.println(obj[i]);
                cs.setObject(i+1, obj[i]);
            }            
            result = cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(dbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ResultSet executeQuery(String sql) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql, Object[] obj) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean exeute(String sql) {
        if (getConn() == null) {
            return false;
        }
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
//			e.printStackTrace();
            return false;
        }
    }
   

    private Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(AppConstraints.JDBC_DRIVER);
                conn = DriverManager.getConnection(AppConstraints.JDBC_URL, AppConstraints.JDBC_USERNAME, AppConstraints.JDBC_PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver is not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cs != null){
                cs.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
