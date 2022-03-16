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
            String paymentDate = request.getParameter("paymentDate");
            long paymentTotal  = Long.parseLong(request.getParameter("paymentTotal"));
            String paymentType = request.getParameter("paymentType");
            float saleOf = Float.parseFloat(request.getParameter("saleOf"));
            String note = request.getParameter("note");

            Bill bill = new Bill();
            bill.setId(id);
            bill.setPaymentDate(paymentDate);
            bill.setPaymentTotal(paymentTotal);
            bill.setPaymentType(paymentType);
            bill.setSaleOf(saleOf);
            bill.setNote(note);

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