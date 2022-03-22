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
import java.sql.Array;
import java.sql.Date;
import java.time.LocalDateTime;

@WebServlet(name = "EditBillServlet", value = "/EditBillServlet")
public class EditBillServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        String url = "/manager/ManagementBillView.jsp";
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            session.invalidate();
            session = request.getSession();
        }
        BillDAO billDAO = new BillDAO();
        String action = request.getParameter("action");
        if (action.equals("edit")) {

            int id = Integer.parseInt(request.getParameter("id"));
            LocalDateTime paymentDate = LocalDateTime.parse(request.getParameter("paymentDate"));
            long paymentTotal  = Long.parseLong(request.getParameter("paymentTotal"));
            String paymentType = request.getParameter("paymentType");
            float saleOf = Float.parseFloat(request.getParameter("saleOf"));
            String note = request.getParameter("note");
            Boolean isPaid= Boolean.valueOf(request.getParameter("isPaid"));
            Boolean isActive = Boolean.valueOf(request.getParameter("isActive"));
            //Array BuyingGoods = request.getParameter("")


            Bill bill = new Bill();
            bill.setId(id);
//            bill.setPaymentDate(paymentDate);
            bill.setPaymentTotal(paymentTotal);
            bill.setSaleOf(saleOf);
            bill.setNote(note);
            bill.setPaid(isPaid);
            bill.setActive(isActive);

            System.out.println(bill);
            try {
                boolean success = billDAO.editBill(bill);
                System.out.printf(success + "");
                url = "/manager/ManagementBillView.jsp";
            } catch (Exception e){

            }
        }
        context.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
