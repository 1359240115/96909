package com.yu.dao;

import com.yu.pojo.*;
import com.yu.util.DbPool;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        String sql = "select e_id,e_name,e_sex,e_age,e_requirement,e_minprice,e_maxprice,e_inputdate,e_birthday,e_phone from employer";
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
                employer.setBirthday(rs.getString(9));
                employer.setPhone(rs.getString(10));
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
    public Boolean addEmployer(Employer e){
        String sql = "insert into employer(c_id,e_name,e_contractid,e_maxprice,e_minprice,e_sex,e_age,e_nation,e_native,e_education,e_idcard,e_occupation,e_contractdate,e_phone,e_address,e_requirement,e_chargeman,e_inputdate,e_workspace,e_jtrs,e_fwnr,e_jtmj,e_ysxg,e_qita,e_home) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        Boolean rs = false;
        Connection con = DbPool.getConnection();
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,e.getC_id(),e.getName(),e.getHetonghao(),e.getMaxprice(),e.getMinprice(),e.getSex(),e.getAge(),e.getMingzu(),e.getJiguan(),e.getXueli(),e.getIdcard(),e.getZhiye(),e.getHetongqixian(),e.getPhone(),e.getAddress(),String.valueOf(e.getYaoqiu()),e.getJingbanren(),e.getInputdate(),e.getWorkspace(),e.getJtrs(),e.getFwnr(),e.getFwmj(),e.getYsxg(),e.getQita(),e.getHome());
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

    //已知ID查看的客户的具体信息
    public Employer seeEmployerByid(String id){
        String sql = "select * from employer where e_id=?";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Employer employer = new Employer();
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,id);
            if (rs!=null&rs.next()){
                employer.setName(rs.getString(3));
                employer.setSex(rs.getString(4));
                employer.setAge(rs.getString(5));
                employer.setMingzu(rs.getString(6));
                employer.setJiguan(rs.getString(7));
                employer.setXueli(rs.getString(8));
                employer.setIdcard(rs.getString(9));
                employer.setZhiye(rs.getString(10));
                employer.setHetonghao(rs.getString(11));
                employer.setHetongqixian(rs.getString(12));
                employer.setPhone(rs.getString(13));
                employer.setAddress(rs.getString(14));
                employer.setYaoqiu(rs.getString(15));
                employer.setJingbanren(rs.getString(16));
                employer.setInputdate(rs.getString(17));
                employer.setMaxprice(rs.getString(18));
                employer.setMinprice(rs.getString(19));
                employer.setBirthday(rs.getString(20));
                employer.setWorkspace(rs.getString(21));
                employer.setHome(rs.getString(22));
                employer.setJtrs(rs.getString(23));
                employer.setFwnr(rs.getString(24));
                employer.setFwmj(rs.getString(25));
                employer.setYsxg(rs.getString(26));
                employer.setQita(rs.getString(27));
            }
            return employer;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }

    //修改客户信息
    public boolean updateEmployerByhetonghao(Employer e){
        String sql = "update employer set e_maxprice=?,e_minprice=?,e_sex=?,e_age=?,e_nation=?,e_native=?,e_education=?,e_idcard=?,e_occupation=?,e_contractdate=?,e_phone=?,e_address=?,e_chargeman=?,e_inputdate=?,e_workspace=?,e_jtrs=?,e_fwnr=?,e_jtmj=?,e_ysxg=?,e_qita=?,e_home=? where e_contractid=?";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        Boolean rs =false;
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,e.getMaxprice(),e.getMinprice(),e.getSex(),e.getAge(),e.getMingzu(),e.getJiguan(),e.getXueli(),e.getIdcard(),e.getZhiye(),e.getHetongqixian(),e.getPhone(),e.getAddress(),e.getJingbanren(),e.getInputdate(),e.getWorkspace(),e.getJtrs(),e.getFwnr(),e.getFwmj(),e.getYsxg(),e.getQita(),e.getHome(),e.getHetonghao());
            con.commit();
            rs = true;
            return rs;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return rs;
    }

    //查找所有通知
    public List<NoticeMrtz> showAllNotice(){
        //格式化日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //取到今年的年份
        String dateStr = sdf.format(date).substring(0,4);
        List<NoticeMrtz> noticeList = new ArrayList<>();
        Ywgl_Dao ywglDao = new Ywgl_Dao();
        //取到所有客户的信息
        List<Employer> employerList = ywglDao.queryAllEmployer();
        //对客户信息进行迭代，把每个客户的生日通知存到通知集合中；
        for (int i = 0; i < employerList.size(); i++) {
            //拼接祝福语
            String context = "客户"+employerList.get(i).getName()+"("+employerList.get(i).getPhone()+")今天过生日，快送去最美的祝福吧！";
            NoticeMrtz notice = new NoticeMrtz();
            String birthday = null;
            if (employerList.get(i).getBirthday()!=null){
                notice.setContext(context);
                //拼接通知的日期
                birthday = dateStr +"-" + employerList.get(i).getBirthday().substring(5,10);
                notice.setNoticeTime(birthday);
            }else {
                break;
            }

            //date1表示当前的日期，用来和发出通知的日期做比较，如果到达当前的日期，则可以发出通知
            Date date1 = new Date();
            try {
               date1 = sdf.parse(birthday) ;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date1.getTime() < date.getTime()){
                noticeList.add(notice);
            }
        }
        //对已经发出的通知作排序
        for (int i = 0; i < noticeList.size()-1; i++) {
            for (int j = 1; j < noticeList.size(); j++) {
                NoticeMrtz notice = new NoticeMrtz();
                try {
                    Date date1 = sdf.parse(noticeList.get(i).getNoticeTime());
                    Date date2 = sdf.parse(noticeList.get(j).getNoticeTime());
                    if (date1.getTime()<date2.getTime()){
                        notice = noticeList.get(i);
                        noticeList.set(i,noticeList.get(j));
                        noticeList.set(j,notice);
                    }
                    noticeList.get(i).setId(i+1);
                    noticeList.get(j).setId(j+1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return noticeList;
    }

    //把通知写到数据库
    public void insertNotice(List<NoticeMrtz> noticeMrtzList){
        String sql = "insert into notice values(?,?,?)";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            for (int i = 0; i < noticeMrtzList.size(); i++) {
                dao.execUpdate(pst,noticeMrtzList.get(i).getId(),
                        noticeMrtzList.get(i).getContext(),
                        noticeMrtzList.get(i).getNoticeTime());
            }
            con.commit();
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
    }

    //查看所有的内部消息
    public List<MessageBean> queryAllMessage(String jieshouren){
        String sql = "select distinct m.*,c.fssj from message m,msgcontext c WHERE m.jieshouren =(SELECT name from account where userid=?) and m.msgid=c.msgid";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,jieshouren);
            List<MessageBean> messageList = new ArrayList<>();
            while (rs!=null&rs.next()){
                MessageBean message = new MessageBean();
                message.setId(Integer.parseInt(rs.getString(1)));
                message.setMid(rs.getInt(2));
                message.setTitle(rs.getString(3));
                message.setFasongren(rs.getString(4));
                message.setJieshouren(rs.getString(5));
                if (rs.getInt(6)==0){
                    message.setStatus("未读");
                }else {
                    message.setStatus("已读");
                }
                message.setFssj(rs.getString(7));
                messageList.add(message);
            }
            return messageList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }

    //查看单个消息内容
    public MessageBean queryMessageByMid(int mid){
        String sql = "select * from msgcontext where msgid = ?";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,mid);
            if (rs!=null&rs.next()){
                MessageBean message = new MessageBean();
                message.setTitle(rs.getString(2));
                message.setContext(rs.getString(3));
                message.setStatus("已读");
                String sql2 ="UPDATE message set msg_status=1 where msgid = ?";
                con.setAutoCommit(false);
                pst = con.prepareStatement(sql2);
                dao.execUpdate(pst,mid);
                con.commit();
                return message;
            }else {
                MessageBean message = new MessageBean();
                message.setStatus("已读");
                String sql2 ="UPDATE message set msg_status=1 where msgid = ?";
                con.setAutoCommit(false);
                pst = con.prepareStatement(sql2);
                dao.execUpdate(pst,mid);
                con.commit();
                return message;
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }

    //发送消息的方法
    public Boolean addMessage(MessageBean message){
        String sql ="INSERT INTO message(msgid,title,fasongren,jieshouren,msg_status) VALUES(?,?,?,?,0)";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        Boolean rs = false;
        Ywgl_Dao ywglDao = new Ywgl_Dao();
        message.setMid(ywglDao.queryMessageMaxmid());
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,message.getMid(),message.getTitle(),message.getFasongren(),message.getJieshouren());
            con.commit();
            rs = true;
            boolean b = ywglDao.addMsgContextBymsgid(message);
            if (b){
                return rs;
            }else {
                con.rollback();
            }
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
        return false;
    }

    //通过当前的msgid往msgcontext中写入内容的方法
    public boolean addMsgContextBymsgid(MessageBean message){
        String sql ="insert into msgcontext VALUES (?,?,?,?)";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        Boolean rs = false;
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(pst,message.getMid(),message.getTitle(),message.getContext(),message.getFssj());
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

    //获取一个当前最大的mid的方法
    public int queryMessageMaxmid(){
        String sql ="SELECT msgid from message ORDER BY msgid DESC";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,null);
            if (rs!=null&rs.next()){
                int msgid = rs.getInt(1)+1;
                return msgid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return 0;
    }

    //获取所有用户资料的方法
    public List<User> findUsers(){
        String sql = "select distinct a.accountid,a.userid,a.name,a.companyid,c.c_name from account a,company c where a.userid=c.c_account";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,null);
            List<User> userList = new ArrayList<>();
            while (rs!=null&rs.next()){
                User user = new User();
                user.setAccountid(rs.getInt(1));
                user.setUserid(rs.getInt(2));
                user.setName(rs.getString(3));
                user.setCompanyid(rs.getInt(4));
                user.setCompanyname(rs.getString(5));
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

    //通过公司名称动态获取公司下的所有工作号
    public List<String> findjsrBycname(String cname){
        String sql = "select name from account where companyid=(select c_id from company where c_name=?)";
        Connection con = DbPool.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            List<String> users = new ArrayList<>();
            pst = con.prepareStatement(sql);
            rs = dao.execQuery(pst,cname);
            while (rs!=null&rs.next()){
                String username =rs.getString(1);
                users.add(username);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResource(con,pst,rs);
        }
        return null;
    }
}