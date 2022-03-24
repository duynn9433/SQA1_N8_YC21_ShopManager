package com.duynn.sqa1_n8_yc21_shopmanager.servlet.client;

import com.duynn.sqa1_n8_yc21_shopmanager.DAO.ClientDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Set;

@WebServlet(name = "AddClientServlet", value = "/AddClientServlet")
public class AddClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "";
        action = request.getParameter("action");
        Client client = new Client();
        client.setName(new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        client.setPhoneNumber(new String(request.getParameter("phoneNumber").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        client.setAddress(new String(request.getParameter("address").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        client.setActive(true);

        if (action.equals("accept")) {
            String status = (String) request.getSession().getAttribute("status");
            if(status != null){
                if(status.equals("add_from_sell")){
                    try {
                        new ClientDAO().addClient(client);
                        Bill bill = (Bill) request.getSession().getAttribute("bill");
                        bill.setClient(client);
                        request.getSession().setAttribute("bill", bill);
                        request.getSession().setAttribute("addClientMsg","luu thanh cong");
                        getServletContext().getRequestDispatcher("/selling/SellingHome.jsp").forward(request, response);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                new ClientDAO().addClient(client);
                request.getSession().setAttribute("addClientMsg","luu thanh cong");
                getServletContext().getRequestDispatcher("/client/AddSuccess.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            try {
                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                Validator validator = validatorFactory.getValidator();
                Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
                if (!constraintViolations.isEmpty()) {
                    String errors = "<ul>";
                    for (ConstraintViolation<Client> constraintViolation : constraintViolations) {
                        errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                                + "</li>";
                    }
                    errors += "</ul>";
                    request.setAttribute("client", client);
                    request.setAttribute("errors", errors);
                    getServletContext().getRequestDispatcher("/client/AddClient.jsp").forward(request, response);
                } else {
                    request.setAttribute("client", client);
                    getServletContext().getRequestDispatcher("/client/AddSuccess.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("errors", e.getMessage());
                getServletContext().getRequestDispatcher("/client/AddClient.jsp").forward(request, response);
            }
        }
    }
}
