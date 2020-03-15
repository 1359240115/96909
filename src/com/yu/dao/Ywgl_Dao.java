package com.yu.dao;

import com.yu.pojo.Worker;
import com.yu.util.DbPool;

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
}
