package com.yu.controller;

import com.yu.pojo.Employer;
import com.yu.pojo.Worker;
import com.yu.service.YwglService;
import com.yu.service.serviceImp.YwglServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
        }
    }


    //新增客户
    private void insertEmployer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employer employer = new Employer();
        employer.setSex(request.getParameter("sex"));
        employer.setAge(request.getIntHeader("age"));
        employer.setMingzu(request.getParameter("nation"));//民族
        employer.setJiguan( request.getParameter("native"));//籍贯
        employer.setXueli(request.getParameter("education"));//学历
        employer.setIdcard(request.getIntHeader("idcard"));
        employer.setWorkspace(request.getParameter("workspace"));//工作单位
        employer.setZhiye(request.getParameter("occupation"));//职业
        employer.setHetongqixian(Date.valueOf(request.getParameter("contractdate")));//合同期限
        employer.setPhone(Integer.valueOf(request.getParameter("telphone")));
        employer.setAddress( request.getParameter("address"));//服务地址
        employer.setJtrs(request.getIntHeader("jtrs"));//家庭人数
        employer.setFwnr(request.getParameter("fwnr"));
        employer.setFwmj(request.getIntHeader("fwmj"));//房屋面积
        employer.setYsxg(request.getParameter("ysxg"));//饮食习惯
        employer.setQita(request.getParameter("qita"));
        employer.setMinprice(request.getIntHeader("minprice"));
        employer.setMaxprice(request.getIntHeader("maxprice"));
        employer.setYaoqiu(request.getParameterValues("workertype").toString());//类型
        employer.setJingbanren((Integer) request.getSession().getAttribute("user"));//经办人
        if (!request.getParameter("chargeman").isEmpty()){
            Object user = request.getSession().getAttribute("user");
            if (user.equals("123")){

            }
        }
        employer.setInputdate(Date.valueOf(request.getParameter("inputtime")));//录入时间
        if (request.getParameter("e_name").isEmpty()){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('请正确填写姓名！');window.history.back()</script>");
        }else {
            employer.setName(request.getParameter("e_name"));
        }
        employer.setHetonghao(request.getParameter("contractid"));//合同号
        if (request.getParameter("contractid").isEmpty()){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>window.alert('请填写合同号！');window.history.back()</script>");
        }else {
            employer.setName(request.getParameter("e_name"));
        }
    }


    //模糊查询客户
    private void queryEmployerByJS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employer employer = new Employer();
        String telphone = request.getParameter("telphone");
        employer.setName(request.getParameter("searchName"));
        employer.setSex(request.getParameter("radio"));
        if (!"".equals(telphone)){
            employer.setPhone(Integer.parseInt(telphone));
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
}
