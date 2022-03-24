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
            try {
                List<Bill> list = new ArrayList<>(billDAO.searchBill(id));
                session.setAttribute("listBill", list);
                url = "/manager/ManagementBillView.jsp";
            } catch (Exception e){

            }
        }

        if(action.equals("edit")){
            System.out.println("bbbbb");
            String eid = request.getParameter("eid");
            LocalDateTime epaymentDate = LocalDateTime.parse(request.getParameter("epaymentDate"));
            float esaleOf = Float.parseFloat(request.getParameter("esaleOf"));
            String enote = request.getParameter("enote");

            session.setAttribute("id",eid);
            session.setAttribute("paymentDate",epaymentDate);
            session.setAttribute("saleOf",esaleOf);
            session.setAttribute("note",enote);

            url = "/manager/EditBillView.jsp";
        }

        if(action.equals("delete")){
//            System.out.println("delete");
//            String did = request.getParameter("did");
//            LocalDateTime dpaymentDate = LocalDateTime.parse(request.getParameter("dpaymentDate"));
//            float dsaleOf = Float.parseFloat(request.getParameter("dsaleOf"));
//            String dnote = request.getParameter("dnote");
//
//            Bill bill = new Bill();
//            bill.setId(Integer.parseInt(did));
//            bill.setPaymentDate(dpaymentDate);
//            bill.setSaleOf(dsaleOf);
//            bill.setNote(dnote);
//
//            boolean success = billDAO.deleteBill(bill);
//            System.out.printf(success + "");
//            url = "/manager/ManagementBillView.jsp";
        }

        context.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        ServletContext context = getServletContext();
        String url = "/manager/ManagementBillView.jsp";
        try {
            HttpSession session = request.getSession();
            Boolean res = new BillDAO().deleteBill(request.getParameter("id"));
            List<Bill> list = new ArrayList<>();
            session.setAttribute("listBill", list);
            url = "/manager/ManagementBillView.jsp";
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
