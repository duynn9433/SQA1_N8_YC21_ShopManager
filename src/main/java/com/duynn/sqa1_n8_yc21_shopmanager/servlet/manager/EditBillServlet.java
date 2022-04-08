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
            System.out.println("ccccccccc");
            int id = Integer.parseInt(request.getParameter("id"));
            LocalDateTime paymentDate = LocalDateTime.parse(request.getParameter("paymentDate"));
            float saleOff = Float.parseFloat(request.getParameter("saleOff"));
            String note = request.getParameter("note");



            Bill bill = new Bill();
            bill.setId(id);
            //float payment;
            bill.setPaymentDate(paymentDate);
            bill.setSaleOff(saleOff);
            //payment = billDAO.payment(id)- billDAO.payment(id)*bill.getSaleOff();
            //long a =  (long) payment;
            //bill.setPaymentTotal(a);
            bill.setNote(note);

            System.out.println(bill);
            try {
                System.out.println("dddđ");
                boolean success = billDAO.editBill(bill);
                System.out.printf(success + "");
                url = "/manager/ManagementBillView.jsp";

                FileWriter fw = new FileWriter("dblog.txt",true);
                String log =""
                        + LocalDateTime.now()+": "
                        +action+""
                        + bill.getClass().getSimpleName()+""
                        +String.valueOf(bill.getId())+" "
                        +success+"\r\n";
                fw.write(log);
                fw.close();
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
