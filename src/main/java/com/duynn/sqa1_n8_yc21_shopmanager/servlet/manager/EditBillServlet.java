package com.duynn.sqa1_n8_yc21_shopmanager.servlet.manager;


import com.duynn.sqa1_n8_yc21_shopmanager.DAO.BillDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.DAO.BuyingGoodsDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.DAO.ClientDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.DAO.GoodsDAO;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Goods;
import org.checkerframework.checker.units.qual.C;

import javax.print.attribute.standard.MediaSizeName;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditBillServlet", value = "/EditBillServlet")
public class EditBillServlet extends HttpServlet {



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        String url = "/manager/EditBillView.jsp";
        String error = "";
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            session.invalidate();
            session = request.getSession();
        }
        //
        BillDAO billDAO = new BillDAO();
        BuyingGoodsDAO buyingGoodsDAO = new BuyingGoodsDAO();
        ClientDAO clientDAO = new ClientDAO();
        Bill bill = billDAO.searchBillid(getBillID());

        //reset lai thong tin trang.
        processRequestreset(request,response);



        String action = request.getParameter("action");
        if(action.equals("search_goods")){
            System.out.println("search_goods");
            List<Goods> goodsList = new GoodsDAO().searchByName(request.getParameter("goodsname"));
            if(goodsList.size()<=0){
                error += "Không tìm thấy sản phẩm nào;";
            }

            request.getSession().setAttribute("error",error);
            session.setAttribute("goodsList",goodsList);

            url = "/manager/EditBillView.jsp";

        }

        if(action.equals("add_goods")){
            System.out.println("update_goods");
            url = "/manager/EditBillView.jsp";

        }



        if(action.equals("find_client")){
            System.out.println("search_client");
            try{
                String phone = request.getParameter("client_phone");
                List<Client> clients = clientDAO.searchClient(phone);
                if(clients.size()<=0){
                    error += "Không tìm thấy khách hàng nào;";
                }
                session.setAttribute("clientlist",clients);
            }catch (Exception e){}
        }
        if(action.equals("choose_client")){
            System.out.println("update_client");

            request.getParameter("chooseIndex");
            try {
                List<Client> list = (List<Client>) session.getAttribute("clientlist");
                int i =Integer.parseInt(request.getParameter("chooseIndex"));
                bill.setClient(list.get(i));
            }catch (Exception e){
                error += "Chưa chọn khách hàng;";
            }
            url = "/manager/EditBillView.jsp";
        }
        if(action.equals("update_bill")){
            System.out.println("update_bill");
            url = "/manager/EditBillView.jsp";


        }
        context.getRequestDispatcher(url).forward(request, response);



    }
    private String getBillID (){
        return ManagementBillServlet.BillID;
    }
    protected void processRequestreset(HttpServletRequest request, HttpServletResponse response) {
        BillDAO billDAO = new BillDAO();
        BuyingGoodsDAO buyingGoodsDAO = new BuyingGoodsDAO();
        ClientDAO clientDAO = new ClientDAO();
        GoodsDAO goodsDAO = new GoodsDAO();
        // load lai id
        Bill bill = billDAO.searchBillid(getBillID());
        request.setAttribute("id",bill.getId());
        request.setAttribute("paymentDate",bill.getPaymentDate());
        request.setAttribute("saleOff",bill.getSaleOff());
        request.setAttribute("note",bill.getNote());

        // load sdt-ten-diachi
        Client client =bill.getClient();
        request.setAttribute("client_phone",client.getPhoneNumber());
        request.setAttribute("client_name",client.getName());
        request.setAttribute("client_adress",client.getAddress());
        //buyingoods
        ArrayList<BuyingGoods> buyingGoodsList = bill.getBuyingGoodsList();
        request.setAttribute("buyiggoodslist",buyingGoodsList);


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
