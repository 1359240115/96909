package com.yu.controller;

import com.yu.pojo.Worker;
import com.yu.service.YwglService;
import com.yu.service.serviceImp.YwglServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        }
    }

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
}
