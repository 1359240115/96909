package com.yu.dao;

import com.yu.pojo.User;
import com.yu.util.DbPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private DbPool dbPool = new DbPool();
    private BasicDao dao = new BasicDao();

    //检查登录用户的信息
    public boolean checkUser(User user){
        ResultSet rs = null;
        boolean result = false;
        PreparedStatement pst=null;
        Connection con = dbPool.getConnection();
        String sql = "SELECT count(*) from account where userid=? and password=?";
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,user.getUserid(),user.getPassword());
            int count = 0;
            if (rs!=null&&rs.next()){
                count = rs.getInt(1);
            }
            if (count == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return result;
    }
}

