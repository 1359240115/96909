package com.yu.controller;


import com.yu.pojo.*;
import com.yu.service.YwglService;
import com.yu.service.serviceImp.YwglServiceImp;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/YwglSvl")
public class Ywgl_Servlet extends HttpServlet {
    YwglService service = new YwglServiceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqType = request.getParameter("reqType");
        if (reqType.equals("grxx_cx")){
            queryWorker(request,response);
        }else if (reqType.equals("khgl")){
            queryAllEmployer(request,response);
        }else if (reqType.equals("cxgzxx")){//模糊查询雇主信息
            queryEmployerByJS(request,response);
        }else if (reqType.equals("insert")){
            insertEmployer(request,response);
        }else if (reqType.equals("seeEmployer")){
            seeEmployerByid(request,response);
        }else if (reqType.equals("modEmployer1")){
            Employer employer = service.seeEmployerByid(request.getParameter("eid"));
            if (employer!=null){
                request.setAttribute("employer",employer);
                request.getRequestDispatcher("/ny/ywgl/gzxx_xg.jsp").forward(request,response);
            }
        }else if (reqType.equals("update")){
            updateEmployer(request,response);
        }else if (reqType.equals("notice")){
            showNotice(request,response);
        }else if (reqType.equals("messagelist")){
            showAllmassage(request,response);
        }else if (reqType.equals("showMessage")){
            showMessageBymid(request,response);
        }else if (reqType.equals("msgreply")){
            replyMessage(request,response);
        }else if (reqType.equals("jsxx")){
            queryMessageByjs(request,response);
        }else if (reqType.equals("addMessage")){
            gotoaddMessage(request,response);
        }else if (reqType.equals("findjsr")){
            findjsrBycname(request,response);
        }else if (reqType.equals("addMessageto")){
            addMessage(request,response);
        }
    }


    //发送消息
    private void addMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String msgtitle = request.getParameter("title");
        String context = request.getParameter("context");
        String jsr = request.getParameter("username");

        if (jsr==null||msgtitle.trim().isEmpty()||context.trim().isEmpty()){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('请正确填写各信息！');window.location.href='/96909/YwglSvl?reqType=addMessage';</script>");
        }
        List<User> allUser = service.findAllUser();
        String fsr = "";
        int userid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("user")).trim());
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getUserid()==userid){
                fsr = allUser.get(i).getName();
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fssj = sdf.format(date);

        MessageBean message = new MessageBean();
        message.setContext(context);
        message.setTitle(msgtitle);
        message.setJieshouren(jsr);
        message.setFasongren(fsr);
        message.setFssj(fssj);

        Boolean rs = service.addMessage(message);
        if (rs){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('发送成功！');window.location.href='/96909/YwglSvl?reqType=messagelist';</script>");
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('发送失败！');window.location.href='/96909/YwglSvl?reqType=addMessage';</script>");
        }
    }


    //通过公司名称找到属于该公司下的所有工作号
    private void findjsrBycname(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cname = request.getParameter("companyname");
        List<String> users = service.findjsrBycname(cname);
        response.setContentType("text/html;charset=UTF-8");
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        for (int i = 0; i < users.size(); i++) {
            JSONObject j = new JSONObject();
            try {
                j.put("name",users.get(i));
                array.put(i,j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            json.put("people",array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PrintWriter out=response.getWriter();
        out.println(json);
        out.close();
    }


    //发送内部消息的第一步方法
    private void gotoaddMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = service.findAllUser();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/ny/ywgl/message_add.jsp").forward(request,response);
    }

    //检索内部消息
    private void queryMessageByjs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jieshouren = String.valueOf(request.getSession().getAttribute("user"));
        String fsr = request.getParameter("fsr");
        String msgstatus = request.getParameter("status");
        //这是当前用户接收的所有信息集合
        List<MessageBean> messageBeans = service.messageList(jieshouren);
        //新建一个List用来存放检索出来的结果
        List<MessageBean> messageList = new ArrayList<>();
        
        //对messageBeans做迭代，找到符合检索要求的消息，存入messageList
        //首先判断发送人是否为空
        for (int i = 0; i < messageBeans.size(); i++) {
            if (!fsr.isEmpty()){
                if (messageBeans.get(i).getFasongren().equals(fsr) & messageBeans.get(i).getStatus().equals(msgstatus)){
                    messageList.add(messageBeans.get(i));
                }
            }else {
                if (messageBeans.get(i).getStatus().equals(msgstatus)){
                    messageList.add(messageBeans.get(i));
                }
            }
        }
        List<User> users = service.findAllUser();
        request.setAttribute("users",users);
        request.setAttribute("messageList",messageList);
        request.getRequestDispatcher("/ny/ywgl/message_list.jsp").forward(request,response);
    }

    //回复内部消息
    private void replyMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("msgtitle");
        String msgcontext = request.getParameter("msgcontext");
        String jsr = request.getParameter("jsr");
        List<User> allUser = service.findAllUser();
        String fsr = "";
        int userid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("user")).trim());
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getUserid()==userid){
                fsr = allUser.get(i).getName();
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fssj = sdf.format(date);

        MessageBean message = new MessageBean();
        message.setContext(msgcontext);
        message.setTitle(title);
        message.setJieshouren(jsr);
        message.setFasongren(fsr);
        message.setFssj(fssj);

        Boolean rs = service.addMessage(message);
        if (rs){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('发送成功！');window.location.href='/96909/YwglSvl?reqType=messagelist';</script>");
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('发送失败！');window.history.back()</script>");
        }
    }

    //查看某条具体的消息
    private void showMessageBymid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mid = Integer.parseInt(request.getParameter("mid"));
        MessageBean message = service.showMessageByMid(mid);
        request.setAttribute("message",message);
        request.getRequestDispatcher("/ny/ywgl/message_show.jsp").forward(request,response);
    }

    //查看当前用户接收到的所有的消息
    private void showAllmassage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jieshouren = String.valueOf(request.getSession().getAttribute("user"));
        List<MessageBean> messageList = service.messageList(jieshouren);
        List<User> users = service.findAllUser();
        request.setAttribute("users",users);
        request.setAttribute("messageList",messageList);
        request.getRequestDispatcher("/ny/ywgl/message_list.jsp").forward(request,response);
    }

    //查看每日通知，客户和工人的生日
    private void showNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NoticeMrtz> noticeList = new ArrayList<>();
        noticeList = service.showAllNotice();
        request.setAttribute("noticeList",noticeList);
        request.getRequestDispatcher("/ny/ywgl/mrtz.jsp").forward(request,response);
    }

    //修改客户信息
    private void updateEmployer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employer employer = new Employer();
        employer.setName(request.getParameter("e_name"));
        employer.setHetonghao(request.getParameter("contractid"));
        employer.setSex(request.getParameter("sex"));
        employer.setAge(request.getParameter("age"));
        employer.setMingzu(request.getParameter("nation"));//民族
        employer.setJiguan(request.getParameter("native"));//籍贯
        employer.setXueli(request.getParameter("education"));//学历
        employer.setIdcard(request.getParameter("idcard"));
        employer.setWorkspace(request.getParameter("workspace"));//工作单位
        employer.setZhiye(request.getParameter("occupation"));//职业
        employer.setHetongqixian(request.getParameter("contractdate"));//合同期
        employer.setPhone(request.getParameter("telphone"));
        employer.setHome(request.getParameter("hkszd"));//户口所在地
        employer.setAddress(request.getParameter("address"));//服务地址
        employer.setJtrs(request.getParameter("jtrs"));//家庭人数
        employer.setFwnr(request.getParameter("fwnr"));
        employer.setFwmj(request.getParameter("fwmj"));//房屋面积
        employer.setYsxg(request.getParameter("ysxg"));//饮食习惯
        employer.setQita(request.getParameter("qita"));
        employer.setMinprice(request.getParameter("minprice"));
        employer.setMaxprice(request.getParameter("maxprice"));
        employer.setJingbanren(request.getParameter("chargeman"));//经办人
        employer.setInputdate(request.getParameter("inputtime"));//录入时间

        boolean b = service.updateEmployerByhetonghao(employer);
        if (b){
            response.sendRedirect("YwglSvl?reqType=khgl");
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('修改失败！');window.history.back()</script>");
        }
    }

    //查看某个客户的具体信息
    private void seeEmployerByid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("eid");
        Employer employer = service.seeEmployerByid(id);
        if (employer!=null){
            request.setAttribute("employer",employer);
            request.getRequestDispatcher("/ny/ywgl/gzxx_ck.jsp").forward(request,response);
        }
    }


    //新增客户
    private void insertEmployer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employer employer = new Employer();
        String username = String.valueOf(request.getSession().getAttribute("user"));
        String c_id = String.valueOf(queryCompany(username));
        employer.setC_id(c_id);
        if (request.getParameter("e_name").isEmpty()){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('请正确填写姓名！');window.history.back()</script>");
            return;
        }else {
            employer.setName(request.getParameter("e_name"));
        }
        if (request.getParameter("contractid").isEmpty()){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('请填写合同号！');window.history.back()</script>");
            return;
        }else {
            employer.setHetonghao(request.getParameter("contractid"));//合同号
        }
        employer.setSex(request.getParameter("sex"));
        employer.setAge(request.getParameter("age"));
        employer.setMingzu(request.getParameter("nation"));//民族
        employer.setJiguan(request.getParameter("native"));//籍贯
        employer.setXueli(request.getParameter("education"));//学历
        employer.setIdcard(request.getParameter("idcard"));
        employer.setWorkspace(request.getParameter("workspace"));//工作单位
        employer.setZhiye(request.getParameter("occupation"));//职业
        employer.setHetongqixian(request.getParameter("contractdate"));//合同期
        employer.setPhone(request.getParameter("telphone"));
        employer.setAddress(request.getParameter("address"));//服务地址
        employer.setJtrs(request.getParameter("jtrs"));//家庭人数
        employer.setFwnr(request.getParameter("fwnr"));
        employer.setFwmj(request.getParameter("fwmj"));//房屋面积
        employer.setYsxg(request.getParameter("ysxg"));//饮食习惯
        employer.setQita(request.getParameter("qita"));
        employer.setMinprice(request.getParameter("minprice"));
        employer.setMaxprice(request.getParameter("maxprice"));
        employer.setJingbanren(request.getParameter("chargeman"));//经办人
        employer.setHome(request.getParameter("hkszd"));
        if (request.getParameterValues("workertype")!=null){
            String wType = "";
            for (int i = 0; i < request.getParameterValues("workertype").length; i++) {
                wType += request.getParameterValues("workertype")[i]+",";
            }
            if (!wType.trim().isEmpty()){
                employer.setYaoqiu(wType.substring(0,wType.length()-1));//类型
            }
        }else {
            employer.setYaoqiu("");
        }
        employer.setInputdate(request.getParameter("inputtime"));//录入时间


        boolean b = service.addEmployer(employer);
        if (b){
            response.sendRedirect("YwglSvl?reqType=khgl");
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('添加失败！');window.history.back()</script>");
        }
    }


    //模糊查询客户
    private void queryEmployerByJS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employer employer = new Employer();
        String telphone = request.getParameter("telphone");
        employer.setName(request.getParameter("searchName"));
        employer.setSex(request.getParameter("radio"));
        if (!"".equals(telphone)){
            employer.setPhone(telphone);
        }
        if (!request.getParameter("select").equals("请选择")){
            employer.setStatus(request.getParameter("select"));
        }
        if (!request.getParameter("select2").equals("请选择")){
            employer.setYaoqiu(request.getParameter("select2"));
        }
        List<Employer> employerList = service.queryEmployerByJS(employer);
        request.setAttribute("employers",employerList);
        request.getRequestDispatcher("/ny/ywgl/gzxx.jsp").forward(request,response);
    }


    //查找所有的客户
    private void queryAllEmployer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employer> employerList = new ArrayList<>();
        employerList = service.queryEmployer();
        request.setAttribute("employers",employerList);
        request.getRequestDispatcher("/ny/ywgl/gzxx.jsp").forward(request,response);
    }


    //业务管理下的查询所有工人
    private void queryWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("textarea2");
        String sex = request.getParameter("sex");
        int minage = Integer.parseInt(request.getParameter("minage"));
        int maxage = Integer.parseInt(request.getParameter("maxage"));
        if (!request.getParameter("textarea7").isEmpty()) {
            int longtime = Integer.parseInt(request.getParameter("textarea7"));//从业时长
        }
        String xueli = request.getParameter("radio");
        //String[] aihao = request.getParameterValues("checkbox");
        String[] yuyan = request.getParameterValues("checkbox1");
        String status = request.getParameter("status");
        String hyzt = request.getParameter("hyzt");//婚姻状态
        String zjzt = request.getParameter("checkbox2");//证件
        String[] grjn = request.getParameterValues("skill");
        Worker worker = new Worker();
        worker.setName(name);
        worker.setSex(sex);
        worker.setXueli(xueli);
        worker.setYuyan(yuyan);
        worker.setStatus(status);
        worker.setHunfou(hyzt);
        worker.setJineng(grjn);
        List<Worker> workerList = service.queryWokerByJS(worker, minage, maxage);
        if (workerList != null) {
            if (workerList.size() != 0) {
                request.setAttribute("workerList", workerList);
                request.getRequestDispatcher("/ny/ywgl/grxx_jsjg.jsp").forward(request, response);
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("<script>window.alert('无此类人员！');window.history.back()</script>");
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('无此类人员！');window.history.back()</script>");
        }
    }

    //判断当前的账户属于哪个公司
    private int queryCompany(String username){
        int c_id =0;
        switch (username){
            case "123": c_id=1;break;
            case "456": c_id=2;break;
            case "789": c_id=3;break;
        }
        return c_id;
    }
}
