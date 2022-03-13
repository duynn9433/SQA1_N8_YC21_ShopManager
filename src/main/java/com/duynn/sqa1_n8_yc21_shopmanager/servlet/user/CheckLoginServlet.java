package com.duynn.sqa1_n8_yc21_shopmanager.servlet.user;

import com.duynn.sqa1_n8_yc21_shopmanager.DAO.UserDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CheckLoginServlet", value = "/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
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
        String url = "/index.jsp";
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            session.invalidate();
            session = request.getSession();
        }
        UserDAO userDAO = new UserDAO();
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("checkLogin")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            boolean check = false;
            try {
                check = userDAO.checkLogin(user);
            } catch (SQLException ex) {
                Logger.getLogger(CheckLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (check) {
                session.setAttribute("user", user);
                String position = user.getPosition();
                System.out.println("position " + position);
                switch (position) {
                    case "manager":
                        url = "/manager/ManagerHome.jsp";
                        break;
                    case "seller":
                        url = "/seller/SellerHome.jsp";
                        break;
                    default:
                        break;
                }
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
