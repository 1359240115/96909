package com.yu.dao;

import com.yu.pojo.Company;
import com.yu.pojo.Employer;
import com.yu.pojo.Worker;
import com.yu.util.DbPool;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ywgl_Dao {
    BasicDao dao  = new BasicDao();

    //业务管理中的检索工人信息的方法。
    public List<Worker> JiansuoWoker(Worker worker,int minage,int maxage){
        String sql = "SELECT * from worker ";
        String criteria = "";
        //拼接SQL语句
        if (!worker.getName().isEmpty()){//姓名
            criteria += " w_name like '%"+worker.getName()+"%'";
        }
        if (worker.getSex()!=null){//性别
            if (criteria.isEmpty()){
                criteria += "w_sex='"+worker.getSex()+"'";
            }else {
                criteria += "and w_sex='"+worker.getSex()+"'";
            }
        }
        if (worker.getXueli()!=null){//学历
            if (criteria.isEmpty()){
                criteria+= " w_xueli='"+worker.getXueli()+"'";
            }else {
                criteria+=" and w_xueli='"+worker.getXueli()+"'";
            }
        }
        if (worker.getStatus()!=null){//状态
            if (criteria.isEmpty()){
                criteria += " w_status='"+worker.getStatus()+"'";
            }else {
                criteria += "and w_status = '"+worker.getStatus()+"'";
            }
        }
        if (worker.getHunfou()!=null){//婚姻状态
            if (criteria.isEmpty()){
                criteria+= "w_marriage='"+worker.getHunfou()+"'";
            }else {
                criteria+="and w_marriage='"+worker.getHunfou()+"'";
            }
        }
        if (worker.getJineng()!=null){//技能
            String[] jineng = worker.getJineng();
            String jn = "w_skills like '%"+jineng[0]+"%'";
            if (jineng.length>1){
                for (int i = 1; i < jineng.length; i++) {
                    jn+= "and w_skills like '%"+jineng[i]+"%'";
                }
            }
            if (criteria.isEmpty()){
                criteria+= jn;
            }else {
                criteria+="and"+jn;
            }
        }
        //判断最终生成的SQL语句是否是空值
        if (!criteria.isEmpty()){
            sql+=" where w_age>"+minage+" and w_age< "+maxage+" and "+criteria;
        }else {
            sql+=" where w_age>"+minage+" and w_age< "+maxage;
        }
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection con = DbPool.getConnection();
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,null);
            List<Worker> workerList = new ArrayList<>();
            while (rs!=null&rs.next()){
                Worker rswoker = new Worker();
                rswoker.setW_id(rs.getInt(1));
                rswoker.setName(rs.getString(3));
                rswoker.setSex(rs.getString(4));
                rswoker.setAge(rs.getInt(7));
                rswoker.setType(rs.getString(10));
                rswoker.setStatus(rs.getString(16));
                rswoker.setInputtime(rs.getDate(19));
                rswoker.setC_id(rs.getInt(2));
                workerList.add(rswoker);
            }
            return workerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }

    //业务管理下的客户管理主页面
    public List<Employer> queryAllEmployer(){
        String sql = "select e_id,e_name,e_sex,e_age,e_requirement,e_minprice,e_maxprice,e_inputdate from employer";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection con = DbPool.getConnection();
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,null);
            List<Employer> employerList = new ArrayList<>();
            while (rs!=null&rs.next()){
                Employer employer = new Employer();
                employer.setId(rs.getString(1));
                employer.setName(rs.getString(2));
                employer.setSex(rs.getString(3));
                employer.setAge(rs.getString(4));
                employer.setYaoqiu(rs.getString(5));
                employer.setMinprice(rs.getString(6));
                employer.setMaxprice(rs.getString(7));
                employer.setInputdate(rs.getString(8));
                employer.setStatus(employerStatus(employer.getId()));
                employerList.add(employer);
            }
            return employerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }

    //返回雇主状态的方法
    //只需要在交易记录表里面查找是否有雇主的信息
    //返回一个“已雇佣”或“待雇佣”的字符串。
    private String employerStatus(String e_id){
        String sql = "select * from transaction where e_id=?";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String rsStr = "待雇佣";
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,e_id);
            if (rs!=null&rs.next()){
                rsStr = "已雇佣";
            }
            return rsStr;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return rsStr;
    }

   //业务管理下的客户管理中的模糊查询方法
    public List<Employer> queryEmployerByJS(Employer employer){
        String sql = "select e_id,e_name,e_sex,e_age,e_requirement,e_minprice,e_maxprice,e_inputdate from employer";
        String criteria = "";
        //拼接SQL语句
        if (!employer.getName().isEmpty()){//姓名
            criteria += " e_name like '%"+employer.getName()+"%'";
        }
        if (employer.getSex()!=null){//性别
            if (criteria.isEmpty()){
                criteria += "e_sex='"+employer.getSex()+"'";
            }else {
                criteria += "and e_sex='"+employer.getSex()+"'";
            }
        }
        if (employer.getPhone()!=null){
            if (criteria.trim().isEmpty()){
                criteria += "e_phone="+employer.getPhone().toString()+"";
            }else {
                criteria += "and e_phone="+employer.getPhone().toString()+"";
            }
        }
        if (employer.getYaoqiu()!=null){
            if (criteria.trim().isEmpty()){
                criteria += "e_requirement like '%"+employer.getYaoqiu()+"%'";
            }else {
                criteria += " and e_requirement like '%"+employer.getYaoqiu()+"%'";
            }
        }
        if (!criteria.isEmpty()){
            sql += " where "+criteria;
        }
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection con = DbPool.getConnection();
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,null);
            List<Employer> employerList = new ArrayList<>();
            while (rs!=null&rs.next()){
                Employer rsemployer = new Employer();
                rsemployer.setId(rs.getString(1));
                rsemployer.setName(rs.getString(2));
                rsemployer.setSex(rs.getString(3));
                rsemployer.setAge(rs.getString(4));
                rsemployer.setYaoqiu(rs.getString(5));
                rsemployer.setMinprice(rs.getString(6));
                rsemployer.setMaxprice(rs.getString(7));
                rsemployer.setInputdate(rs.getString(8));
                rsemployer.setStatus(employerStatus(rsemployer.getId()));
                //此处做判断，判断查询条件中的客户状态是否为空
                //1.如果不为空再做一个判断，判断结果集中的客户状态是否和条件中的状态一致
                //是则添加到list集合中，否则不添加。
                //2.如果为空，则直接添加到集合中
                if (employer.getStatus()!=null){
                    if (employer.getStatus().equals(rsemployer.getStatus())){
                        employerList.add(rsemployer);
                    }
                }else {
                    employerList.add(rsemployer);
                }

            }
            return employerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }

    //业务管理下的增加新客户
    public Boolean addEmployer(Employer e,String c_account){
        String sql = "insert into employer(c_id,e_name,e_contractid,e_maxprice,e_minprice,e_sex,e_age,e_nation,e_native,e_education,e_idcard,e_occupation,e_contractdate,e_phone,e_address,e_requirement,e_chargeman,e_inputdate,e_workspace,e_home,e_jtrs,e_fwnr,e_jtmj,e_ysxg,e_qita) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        Boolean rs = false;
        Connection con = DbPool.getConnection();
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,e.getC_id(),e.getName(),e.getHetonghao(),e.getMaxprice(),e.getMinprice(),e.getSex(),e.getAge(),e.getMingzu(),e.getJiguan(),e.getXueli(),e.getIdcard(),e.getZhiye(),e.getHetongqixian(),e.getPhone(),e.getAddress(),String.valueOf(e.getYaoqiu()),e.getJingbanren(),e.getInputdate(),e.getWorkspace(),e.getAddress(),e.getJtrs(),e.getFwnr(),e.getFwmj(),e.getYsxg(),e.getQita());
            con.commit();
            rs = true;
            return rs;
        } catch (SQLException e1) {
            try {
                con.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            e1.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,null);
        }
        return rs;
    }

}
