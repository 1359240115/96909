package com.yu.controller;

import com.yu.service.YwglService;
import com.yu.service.serviceImp.YwglServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        }
    }

    private void queryWorker(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("textarea2");
        String sex = request.getParameter("sex");
        int minage = Integer.parseInt(request.getParameter("minage"));
        int maxage = Integer.parseInt(request.getParameter("maxage"));
        if (!request.getParameter("textarea7").isEmpty()){
            int longtime = Integer.parseInt(request.getParameter("textarea7"));//从业时长
        }
        String xueli = request.getParameter("radio");
        String[] aihao = request.getParameterValues("checkbox");
        System.out.println(name+" "+sex+" "+aihao);
    }
}
