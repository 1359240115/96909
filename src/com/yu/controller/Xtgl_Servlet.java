package com.yu.controller;

import com.yu.pojo.Czjl;
import com.yu.pojo.User;
import com.yu.service.XtglService;
import com.yu.service.YwglService;
import com.yu.service.serviceImp.XtglServiceImp;
import com.yu.service.serviceImp.YwglServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/XtglSvl")
public class Xtgl_Servlet extends HttpServlet {

    YwglService ywservice = new YwglServiceImp();
    XtglService service = new XtglServiceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqType = request.getParameter("reqType");
        
        if (reqType.equals("CzglMain")){
            showCzglMain(request,response);
        }else if (reqType.equals("chongzhi")){
            accountRecharge(request,response);
        }
    }

    //给指定的账户充值，并将充值记录写入数据库
    private void accountRecharge(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Czjl czjl = new Czjl();
        //获取经办人，即当前登录的账户。
        List<User> allUser = ywservice.findAllUser();
        int userid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("user")).trim());
        String jbr ="";
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getUserid()==userid){
                jbr = allUser.get(i).getName();
            }
        }

        //获取当前时间，即充值时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String czsj = sdf.format(date);

        int accountid = Integer.parseInt(request.getParameter("accountid"));
        String czname = request.getParameter("czname");
        Integer jine = null;
        if (!request.getParameter("jine").trim().isEmpty()){
            jine = Integer.valueOf(request.getParameter("jine"));
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('请输入正确金额！');window.history.back()</script>");
        }

        String bz = request.getParameter("beizhu");

        //得到充值的账号属于哪个公司
        int cid = 1;
        for (int i = 0; i < allUser.size() ; i++) {
            if (allUser.get(i).getAccountid()==accountid){
                cid = allUser.get(i).getCompanyid();
            }
        }

        //将属性传到czjl中
        czjl.setInputdate(czsj);
        czjl.setChargeman(jbr);
        czjl.setCid(cid);
        czjl.setCzname(czname);
        czjl.setBeizhu(bz);
        if (jine>0){
            czjl.setJine(jine);
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('请输入正确金额！');window.history.back()</script>");
            return;
        }

        boolean b = service.accountRecharge(czjl, accountid);

        if (b){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('充值成功！');window.location.href='/96909/XtglSvl?reqType=CzglMain';</script>");
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('充值失败！');window.history.back()</script>");
        }
    }

    //访问充值主页面显示所有账号
    private void showCzglMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = new ArrayList<>();
        userList = service.showCzglMain();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("ny/xtgl/czgl.jsp").forward(request,response);
    }
}
