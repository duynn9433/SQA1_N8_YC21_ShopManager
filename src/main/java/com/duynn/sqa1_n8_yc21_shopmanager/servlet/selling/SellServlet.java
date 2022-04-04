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
        String error = "";
        if (action == null)
            action ="";
        if(action.equals("") || action==null){
            Bill bill = new Bill();
            bill.setUser((User) session.getAttribute("user"));
            session.setAttribute("bill",bill);
            url="/selling/SellingHome.jsp";

        }else if(action.equals("search_goods")){
            List<Goods> goodsList = new GoodsDAO().searchByName(request.getParameter("goodsname"));
            if(goodsList.size()<=0){
                error += "\nKhông tìm thấy sản phẩm nào";
            }

            request.getSession().setAttribute("error",error);
            session.setAttribute("goodsList",goodsList);
            url="/selling/SellingHome.jsp";

        }else if(action.equals("add_client")){
            List<Goods> goodsList = new GoodsDAO().searchByName(request.getParameter("goodsname"));
            session.setAttribute("goodsList",goodsList);
            session.setAttribute("status","add_from_sell");
            url="/client/AddClient.jsp";

        }else if(action.equals("confirm_bill")){
            url="/selling/Confirm.jsp";
            session.removeAttribute("goodsList");
            session.removeAttribute("listClient");


            Bill bill = (Bill) session.getAttribute("bill");
            try {
                float saleOff = Float.parseFloat(request.getParameter("sale_off"));
                if(saleOff>1 || saleOff<0){
                    error += "\nGiảm giá không hợp lệ (chỉ trong khoảng 0-1)";
                    url="/selling/SellingHome.jsp";
                }
                bill.setSaleOff(saleOff);
            }catch (NumberFormatException e){
                bill.setSaleOff(0);
                error += "\nSale off không hợp lệ";
                url="/selling/SellingHome.jsp";
            }

            bill.setPaymentDate(LocalDateTime.now());
            request.getSession().setAttribute("error",error);

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
        String error = "";
        if(action.equals("search_goods")){
            List<Goods> goodsList = new GoodsDAO().searchByName(request.getParameter("goodsname"));
            if(goodsList.size()<=0){
                error += "\nKhông tìm thấy sản phẩm nào";
            }
            request.getSession().setAttribute("error",error);
            session.setAttribute("goodsList",goodsList);
            url="/selling/SellingHome.jsp";

        }
        else if(action.equals("add_goods")){
            Bill bill = (Bill) session.getAttribute("bill");
            List<Goods> goodsList = (List<Goods>) session.getAttribute("goodsList");
            try {
                int amount = Integer.parseInt(request.getParameter("amount"));

                Goods goods = goodsList.get(Integer.parseInt(request.getParameter("chooseIndex"))-1);
                BuyingGoods buyingGoods = new BuyingGoods();
                buyingGoods.setGoods(goods);
                buyingGoods.setAmount(amount);
                buyingGoods.setPricePerUnit(goods.getPricePerUnit());
                buyingGoods.setTotalPrice(amount*goods.getPricePerUnit());

                bill.addBuyingGoods(buyingGoods);
                bill.reCalPaymentTotal();
            }catch (NullPointerException e){
                error += "\nChưa chọn sản phẩm";
            }catch (NumberFormatException e){
                error += "\nSố lượng không hợp lệ";
            }
            request.getSession().setAttribute("error",error);
            url="/selling/SellingHome.jsp";
        }
        else if(action.equals("find_client")){
            try {
                List<Client> listClient = new ClientDAO().searchClient(request.getParameter("client_phone"));
                if(listClient.size()<=0){
                    error += "\nKhông tìm thấy khách hàng nào";
                }

                session.setAttribute("listClient",listClient);
            } catch (SQLException e) {
                e.printStackTrace();
                error+= "\nLỗi kết nối cơ sở dữ liệu";
            }
            request.getSession().setAttribute("error",error);
            url="/selling/SellingHome.jsp";
        }
        else if(action.equals("choose_client")){
            List<Client> list = (List<Client>) session.getAttribute("listClient");
            try {
                Client client = list.get(Integer.parseInt(request.getParameter("chooseIndex"))-1);
                Bill bill = (Bill) session.getAttribute("bill");
                bill.setClient(client);

                System.out.println(bill.getClient());
            }catch (NumberFormatException e){
                error += "\nChưa chọn khách hàng";
            }
            request.getSession().setAttribute("error",error);

            url="/selling/SellingHome.jsp";
        }
        else if(action.equals("save_bill")){
            Bill bill = (Bill) session.getAttribute("bill");
            bill.setPaid(true);
            new BillDAO().save(bill);
            session.removeAttribute("bill");
            request.getSession().setAttribute("confirmBillMsg", "Lưu thành công");
            url="/seller/SellerHome.jsp";
        }
        else if(action.equals("cancel_bill")){
            session.removeAttribute("bill");
            url="/seller/SellerHome.jsp";
        }
        else if(action.equals("update_goods")){
            Bill bill = (Bill) session.getAttribute("bill");
            //update hang
            try {
                int amount = Integer.parseInt(request.getParameter("amount"));
                int index = Integer.parseInt(request.getParameter("index")) -1;
                if(amount <= 0) {
                    bill.getBuyingGoodsList().remove(index);
                }else {
                    bill.getBuyingGoodsList().get(index).setAmount(amount);
                }

                //update tong tien bill
                bill.reCalPaymentTotal();

                session.setAttribute("bill",bill);
            }catch (NumberFormatException e){
                error += "\nSố lượng không hợp lệ";
            }
            request.getSession().setAttribute("error",error);
            url="/selling/SellingHome.jsp";
        }
        else if(action.equals("remove_goods")){
            Bill bill = (Bill) session.getAttribute("bill");
            try {
                int index = Integer.parseInt(request.getParameter("index")) -1 ;
                bill.getBuyingGoodsList().remove(index);
                bill.reCalPaymentTotal();

                session.setAttribute("bill",bill);
            } catch (NumberFormatException e) {
                error += "\nChưa chọn sản phẩm";
            }
            request.getSession().setAttribute("error",error);
            url="/selling/SellingHome.jsp";

        }
        context.getRequestDispatcher(url).forward(request, response);
    }
}
