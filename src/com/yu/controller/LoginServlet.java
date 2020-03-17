package com.yu.controller;

import com.yu.dao.LoginDao;
import com.yu.pojo.User;
import com.yu.service.LoginCheckService;
import com.yu.service.serviceImp.LoginCheckImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginSvl")
public class LoginServlet extends HttpServlet {
    LoginCheckService service = new LoginCheckImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqType = request.getParameter("reqType");
        if (reqType.equals("login")){
            CheckLogin(request,response);
        }
    }

    private void CheckLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        User user = new User();
        user.setUserid(Integer.valueOf(username));
        user.setPassword(password);
        boolean rs = service.CheckUser(user);
        if (rs){
            request.getSession().setAttribute("user",username);
            request.getRequestDispatcher("ny/index.html").forward(request,response);
        }else {
            request.setAttribute("info","账号或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }
}
