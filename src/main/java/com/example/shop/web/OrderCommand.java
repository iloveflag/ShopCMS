package com.example.shop.web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.example.shop.dao.CartDAO;

import com.example.shop.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.shop.util.Command;
import com.example.shop.model.*;

import static com.example.shop.dao.OrderDao.ifHasReserve;

public class OrderCommand implements Command {
    private static Logger logger = LoggerFactory.getLogger(CartCommand.class);

    private static interface Views {
        public static final String SHOOPINGCAET_LIST = "/cart.do?cmd=querycart";
        public static final String RELOGIN = "/common/relogin.jsp";
        public static final String  ERROR ="/common/error.jsp";
        public static final String  NORESERVE ="/common/noreserve.jsp";
        public static final String  noSuchPage ="/common/noSuchPage.jsp";
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置请求参数的字符编码
            request.setCharacterEncoding("UTF-8");
            String cmd = request.getParameter("cmd");
            if ("query".equals(cmd)) {
                return query(request, response);
            }else if("byok".equals(cmd)) {
                return byok(request,response);
            }else{
                return Views.noSuchPage;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.err.println("CartCommand:" + ex);
            return Views.noSuchPage;
        }
    }

    private String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        CartDAO CartDao = new CartDAO();
        List<Cart> allCart = CartDao.queryGoodsByUserid(userid); //根据用户id多表查询
        if(allCart.size()==0){
            //没有商品
            return Views.SHOOPINGCAET_LIST;
        }else if(ifHasReserve(allCart)!=""){
            //没有库存
            request.setAttribute("message",ifHasReserve(allCart));
            return Views.NORESERVE;
        }else{
            request.setAttribute("allcart",allCart);
            return "/order.jsp";
        }
    }
    private String byok(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            System.out.println(1);
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        Cart cart = new Cart();
        cart.setUserid(userid);
        CartDAO CartDao = new CartDAO();
        OrderDao orderDao=new OrderDao();
        List<Cart> allCart = CartDao.queryGoodsByUserid(userid);
        if(CartDao.queryGoodsByUserid(userid)!=null && ifHasReserve(allCart)=="") { //有商品选择 //有库存
            orderDao.minusReserve(allCart);
            CartDao.delAllUserChooseByUserid(cart);  //清空购物车
            request.setAttribute("byok", 1);
            return "/order.jsp";
        }else{
            return Views.ERROR;
        }
    }
}
