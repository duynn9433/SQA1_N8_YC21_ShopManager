package com.duynn.sqa1_n8_yc21_shopmanager.servlet.selling;

import com.duynn.sqa1_n8_yc21_shopmanager.DAO.BillDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.DAO.ClientDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.DAO.GoodsDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "SellServlet", value = "/SellServlet")
public class SellServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String url="/";
        String action = "";
        action = request.getParameter("action");
        if (action == null)
            action ="";
        if(action.equals("") || action==null){
            Bill bill = new Bill();
            bill.setUser((User) session.getAttribute("user"));
            session.setAttribute("bill",bill);
            url="/selling/SellingHome.jsp";

        }else if(action.equals("search_goods")){
            List<Goods> goodsList = new GoodsDAO().searchByName(request.getParameter("goodsname"));
            session.setAttribute("goodsList",goodsList);
            url="/selling/SellingHome.jsp";

        }else if(action.equals("add_client")){
            List<Goods> goodsList = new GoodsDAO().searchByName(request.getParameter("goodsname"));
            session.setAttribute("goodsList",goodsList);
            session.setAttribute("status","add_from_sell");
            url="/seller/AddClient.jsp";

        }else if(action.equals("confirm_bill")){
            session.removeAttribute("goodsList");
            session.removeAttribute("listClient");

            Bill bill = (Bill) session.getAttribute("bill");
            bill.setPaymentDate(LocalDateTime.now());

            url="/selling/Confirm.jsp";

        }
        context.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String url="/";
        String action = "";
        action = request.getParameter("action");
        if(action.equals("search_goods")){
            List<Goods> goodsList = new GoodsDAO().searchByName(request.getParameter("goodsname"));
            session.setAttribute("goodsList",goodsList);
            url="/selling/SellingHome.jsp";

        }else if(action.equals("add_goods")){
            Bill bill = (Bill) session.getAttribute("bill");
            List<Goods> goodsList = (List<Goods>) session.getAttribute("goodsList");
            int amount = Integer.parseInt(request.getParameter("amount"));

            Goods goods = goodsList.get(Integer.parseInt(request.getParameter("chooseIndex"))-1);
            BuyingGoods buyingGoods = new BuyingGoods();
            buyingGoods.setGoods(goods);
            buyingGoods.setAmount(amount);
            buyingGoods.setPricePerUnit(goods.getPricePerUnit());
            buyingGoods.setTotalPrice(amount*goods.getPricePerUnit());

            bill.addBuyingGoods(buyingGoods);
            bill.reCalPaymentTotal();

            url="/selling/SellingHome.jsp";
        }else if(action.equals("find_client")){
            try {
                List<Client> listClient = new ClientDAO().searchClient(request.getParameter("client_phone"));
                session.setAttribute("listClient",listClient);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            url="/selling/SellingHome.jsp";
        }else if(action.equals("choose_client")){
            List<Client> list = (List<Client>) session.getAttribute("listClient");
            Client client = list.get(Integer.parseInt(request.getParameter("chooseIndex"))-1);
            Bill bill = (Bill) session.getAttribute("bill");
            bill.setClient(client);

            System.out.println(bill.getClient());

            url="/selling/SellingHome.jsp";
        }else if(action.equals("save_bill")){
            Bill bill = (Bill) session.getAttribute("bill");
            bill.setPaid(true);
            new BillDAO().save(bill);
            request.getSession().setAttribute("confirmBillMsg", "Luu thanh cong");
            url="/selling/Confirm.jsp";
        }else if(action.equals("cancel_bill")){
            session.removeAttribute("bill");
            url="/seller/SellerHome.jsp";
        }
        context.getRequestDispatcher(url).forward(request, response);
    }
}
