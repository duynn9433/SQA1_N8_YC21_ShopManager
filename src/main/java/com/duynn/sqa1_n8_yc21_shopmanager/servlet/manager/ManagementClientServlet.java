package com.duynn.sqa1_n8_yc21_shopmanager.servlet.manager;

import com.duynn.sqa1_n8_yc21_shopmanager.DAO.ClientDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.DAO.UserDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;
import com.duynn.sqa1_n8_yc21_shopmanager.model.User;
import com.duynn.sqa1_n8_yc21_shopmanager.servlet.user.CheckLoginServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ManagementClientServlet", value = "/ManagementClientServlet")
public class ManagementClientServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        String url = "/manager/ManagementClientView.jsp";
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            session.invalidate();
            session = request.getSession();
        }
        ClientDAO clientDAO = new ClientDAO();
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("search")) {
            String phoneNumber = request.getParameter("search_phone");
            try {
                List<Client> list = new ArrayList<>(clientDAO.searchClient(phoneNumber));
                session.setAttribute("listClient", list);
                url = "/manager/ManagementClientView.jsp";
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
