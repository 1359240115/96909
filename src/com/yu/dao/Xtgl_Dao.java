package com.yu.dao;

import com.yu.pojo.Czjl;
import com.yu.pojo.User;
import com.yu.util.DbPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Xtgl_Dao {

    BasicDao dao  = new BasicDao();

    //返回充值管理主页面用户的余额等记录的方法
    public List<User> showCzglMain(){
        String sql = "select c.c_name,a.accountid,a.name,a.skcs,a.sfbz,a.zhye,a.bz from account a,company c where a.companyid = c.c_id";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,null);
            List<User> userList = new ArrayList<>();
            while (rs!=null&rs.next()){
                User user = new User();
                user.setCompanyname(rs.getString(1));
                user.setAccountid(rs.getInt(2));
                user.setName(rs.getString(3));
                if (!String.valueOf(rs.getInt(4)).isEmpty()){
                    user.setSkcs(rs.getInt(4));
                }else{
                    user.setSkcs(0);
                }
                if (!String.valueOf(rs.getInt(5)).isEmpty()){
                    user.setSfbz(rs.getInt(5));
                }else {
                    user.setSkcs(0);
                }
                if (!String.valueOf(rs.getInt(6)).isEmpty()){
                    user.setYue(rs.getInt(6));
                }else {
                    user.setYue(0);
                }
                user.setBz(rs.getString(7));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }

    //给账户充值的第一步，先给账户充值，若写入成功，则跳到第二步
    //若写入失败，则rollback
    //此方法不能在外部调用，只能由第二步方法来调用
    private boolean zhanghuchongzhi(int czje,int accountid){
        String sql = "UPDATE account SET zhye=zhye+? where accountid=?";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        Boolean rs = false;
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,czje,accountid);
            con.commit();
            rs = true;
            return rs;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,null);
        }
        return rs;
    }

    //给账户充值的第二步，将充值记录录入数据库
    public boolean accountRecharge(Czjl czjl,int accountid){
        Xtgl_Dao xtgl = new Xtgl_Dao();
        boolean zhcg = xtgl.zhanghuchongzhi(czjl.getJine(), accountid);
        //首先判断第一步的金额充值是否成功，如果成功则添加充值记录，不成功则不添加
        if (zhcg){
            String sql = "insert into czjl(cid,jine,chargeman,czdate,bz,accname) VALUES(?,?,?,?,?,?)";
            Connection con = DbPool.getConnection();
            PreparedStatement pst = null;
            boolean rs = false;
            try {
                con.setAutoCommit(false);
                pst = con.prepareStatement(sql);
                dao.execUpdate(pst,czjl.getCid(),czjl.getJine(),czjl.getChargeman(),czjl.getInputdate(),czjl.getBeizhu(),czjl.getCzname());
                con.commit();
                rs = true;
                return rs;
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                dao.releaseResource(con,pst,null);
            }
            return rs;
        }else {
            return false;
        }
    }




}
