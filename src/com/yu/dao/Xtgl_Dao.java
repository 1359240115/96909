package com.yu.dao;

import com.yu.pojo.Cxkfjl;
import com.yu.pojo.Czjl;
import com.yu.pojo.User;
import com.yu.util.DbPool;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Xtgl_Dao {

    BasicDao dao  = new BasicDao();

    //返回充值管理主页面用户的余额等记录的方法
    public List<User> showCzglMain(){
        String sql = "select c.c_name,a.accountid,a.userid,a.skcs,a.sfbz,a.zhye,a.bz from account a,company c where a.companyid = c.c_id";
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
                user.setUserid(rs.getInt(3));
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
            String sql = "insert into czjl(cid,jine,chargeman,czdate,bz,accid) VALUES(?,?,?,?,?,?)";
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




    //模糊查询公司账户余额及刷卡次数
    public List<User> queryYueByJs(String companyname,String accountid){
        String sql = "select c.c_name,a.accountid,a.userid,a.skcs,a.sfbz,a.zhye,a.bz from account a,company c where a.companyid = c.c_id";

        String cri = "";

        if (!companyname.isEmpty()){
            cri += "c.c_name like '%"+companyname+"%'";
        }

        if (!accountid.isEmpty()){
            if (!cri.isEmpty()){
                cri += " and a.userid = "+accountid;
            }else {
                cri += "a.userid =" +accountid;
            }
        }

        if (!cri.isEmpty()){
            sql += " and "+cri;
        }

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
                user.setUserid(rs.getInt(3));
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



    //查询扣费
    //该系统每个搜索功能都需要收费
    //搜索成功一次就扣一次费。

    //若要更改单次检索的收费标准
    //只需要更改代码中SQL里面的公式和xtglDao.lrkfjl(userid, 2)中的金额。
    //同时修改下方判断余额是否足够的方法中的收费标准。
    public boolean koufeiByJs(String userid){
        String sql = "UPDATE account set zhye=zhye-2 where userid=?";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        boolean result=false;
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,userid);
            Xtgl_Dao xtglDao = new Xtgl_Dao();
            boolean lrkfjl = xtglDao.lrkfjl(userid, 2);
            //在扣费之后判断是否正确写入记录，是则保存记录，否则rollback
            if (lrkfjl){
                result =true;
                con.commit();
            }else {
                con.rollback();
            }
            return result;
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
        return result;
    }

    //把扣费记录写到数据库
    //这个操作只能在扣费成功的基础上写，所以只能供内部扣费的方法调用。
    private boolean lrkfjl(String userid,int jine){
        String sql = "insert into cxkfjl(cname,userid,skdate,jine) VALUES ((SELECT c_name from company where c_account=?),?,?,?)";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        boolean rs = false;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String skdate = sdf.format(date);
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,userid,userid,skdate,jine);
            con.commit();
            rs =true;
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




    //查询扣费记录
    public List<Cxkfjl> queryAllKfjl(){
        String sql = "SELECT id,cname,userid,skdate,jine from cxkfjl";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst, null);
            List<Cxkfjl> kfjlList = new ArrayList<>();
            while (rs!=null&rs.next()){
                Cxkfjl kfjl = new Cxkfjl();
                kfjl.setId(rs.getInt(1));
                kfjl.setCompanyname(rs.getString(2));
                kfjl.setUserid(rs.getString(3));
                kfjl.setSkdate(rs.getString(4));
                kfjl.setJine(rs.getInt(5));
                kfjlList.add(kfjl);
            }
            return kfjlList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }



    //模糊查询扣费记录
    public List<Cxkfjl> jsKfjl(String companyname,String accountid,String mindate,String maxdate){
        String sql = "SELECT id,cname,userid,skdate,jine from cxkfjl ";

        String cri ="";

        if (!companyname.trim().isEmpty()){
            cri += "cname like '%"+companyname+"%'";
        }

        if (!accountid.trim().isEmpty()){
            if (!cri.trim().isEmpty()){
                cri += " and userid = "+accountid;
            }else {
                cri += " userid= "+accountid;
            }
        }

        if (!mindate.trim().isEmpty()) {
            if (!cri.trim().isEmpty()){
                cri += " and skdate> '"+mindate+"'";
            }else {
                cri += " skdate >'"+mindate+"'";
            }
        }

        if (!maxdate.trim().isEmpty()){
            if (!cri.trim().isEmpty()){
                cri += " and skdate> '"+maxdate+"'";
            }else {
                cri += " skdate >'"+mindate+"'";
            }
        }

        if (!cri.trim().isEmpty()){
            sql+=" where "+cri;
        }

        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst, null);
            List<Cxkfjl> kfjlList = new ArrayList<>();
            while (rs!=null&rs.next()){
                Cxkfjl kfjl = new Cxkfjl();
                kfjl.setId(rs.getInt(1));
                kfjl.setCompanyname(rs.getString(2));
                kfjl.setUserid(rs.getString(3));
                kfjl.setSkdate(rs.getString(4));
                kfjl.setJine(rs.getInt(5));
                kfjlList.add(kfjl);
            }
            return kfjlList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }



    //获取账户余额是否足够检索收费标准
    public boolean queryYue(String userid){
        String sql = "select zhye from account where userid=?";

        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs =null;
        boolean yn = false;
        double yue = -1;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,userid);
            if (rs!=null&rs.next()){
                yue = rs.getInt(1);
            }

            //此处是收费标准。
            if (yue>=2){
                yn =true;
            }
            return yn;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return yn;
    }


}
