package com.duynn.sqa1_n8_yc21_shopmanager.servlet.manager;

import com.duynn.sqa1_n8_yc21_shopmanager.DAO.ClientDAO;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditClientServlet", value = "/EditClientServlet")
public class EditClientServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
        if (action.equals("edit")) {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");

            Client client = new Client();
            client.setID(id);
            client.setName(name);
            client.setPhoneNumber(phoneNumber);
            client.setAddress(address);

            System.out.println(client);
            try {
                boolean success = clientDAO.editClient(client);
                System.out.printf(success + "");
                url = "/manager/ManagementClientView.jsp";

                // dblog.txt ở trong folder tomcat\bin
                FileWriter fw = new FileWriter("dblog.txt", true);
                String log = ""
                        + LocalDateTime.now() + ": "
                        + action + " "
                        + client.getClass().getSimpleName() + " "
                        + String.valueOf(client.getID()) + " "
                        + success + "\r\n";
                fw.write(log);
                fw.close();
            } catch (Exception e) {

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
