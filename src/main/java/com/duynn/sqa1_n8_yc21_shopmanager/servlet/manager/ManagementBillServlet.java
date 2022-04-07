package com.duynn.sqa1_n8_yc21_shopmanager.servlet.manager;

import com.duynn.sqa1_n8_yc21_shopmanager.DAO.BillDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.DAO.ClientDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManagementBillServlet", value = "/ManagementBillServlet")
public class ManagementBillServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ServletContext context = getServletContext();
        String url = "/manager/ManagementBillView.jsp";
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            session.invalidate();
            session = request.getSession();
        }
        BillDAO billDAO = new BillDAO();
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("search")) {
            System.out.println("aaaa");
            String id = request.getParameter("search_id");
            if(id!="") {
                try {
                    List<Bill> list = new ArrayList<>(billDAO.searchBill(id));
                    session.setAttribute("listBill", list);
                    url = "/manager/ManagementBillView.jsp";
                } catch (Exception e) {

                }
            } else {
                try {
                    List<Bill> list = new ArrayList<>(billDAO.allBill());
                    session.setAttribute("listBill", list);
                    url = "/manager/ManagementBillView.jsp";
                } catch (Exception e) {

                }
            }
        }

        if(action.equals("edit")){
            System.out.println("bbbbb");
            String eid = request.getParameter("eid");
            LocalDateTime epaymentDate = LocalDateTime.parse(request.getParameter("epaymentDate"));
            float esaleOff = Float.parseFloat(request.getParameter("esaleOff"));
            String enote = request.getParameter("enote");

            session.setAttribute("id",eid);
            session.setAttribute("paymentDate",epaymentDate);
            session.setAttribute("saleOff",esaleOff);
            session.setAttribute("note",enote);

            url = "/manager/EditBillView.jsp";
        }

        if(action.equals("delete")){


        }

        context.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String url = "/manager/ManagementBillView.jsp";
        try {
            HttpSession session = request.getSession();
            Boolean res = new BillDAO().deleteBill(request.getParameter("id"));
            List<Bill> list = new ArrayList<>();
            session.setAttribute("listBill", list);
            url = "/manager/ManagementBillView.jsp";

            FileWriter fw = new FileWriter("dblog.txt",true);
            String log =""
                    + LocalDateTime.now()+": "
                    +"delete"
                    + "bill"
                    +String.valueOf(request.getParameter("id"))
                    +res+"\r\n";
            fw.write(log);
            fw.close();

            context.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
