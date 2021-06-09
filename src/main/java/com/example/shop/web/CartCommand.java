package com.example.shop.web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.example.shop.dao.CartDAO;

import com.example.shop.dao.GoodsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.shop.util.Command;
import com.example.shop.model.*;

public class CartCommand implements Command {
    private static Logger logger = LoggerFactory.getLogger(CartCommand.class);

    private static interface Views {
        public static final String SHOOPINGCAET_LIST = "/cart.do?cmd=querycart";
        public static final String RELOGIN = "/common/relogin.jsp";
        public static final String  ERROR ="/common/error.jsp";
        public static final String  noSuchPage ="/common/noSuchPage.jsp";
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置请求参数的字符编码
            request.setCharacterEncoding("UTF-8");
            String cmd = request.getParameter("cmd");
            if ("querycart".equals(cmd)) {
                return querycart(request, response);
            }else if("addchoose".equals(cmd)){
                return addchoose(request,response);
            }else if("decreasechoose".equals(cmd)){
                return decreasechoose(request,response);
            }else if("delchoose".equals(cmd)){
                return delchoose(request,response);
            }else if("delall".equals(cmd)){
                return delall(request,response);
            }else if("changecount".equals(cmd)){
                return changecount(request,response);
            }else{
                return Views.noSuchPage;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.err.println("CartCommand:" + ex);
            return Views.noSuchPage;
        }
    }

    private String querycart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            System.out.println(1);
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        CartDAO CartDao = new CartDAO();
        List<Cart> allCart = CartDao.queryGoodsByUserid(userid); //根据用户id多表查询
        if(allCart.size()==0) {
            //没有商品
            request.setAttribute("message", "<p style=\"text-align: center;font-size: 50px;\">购物车空空如也~</p>");
        }else {
            request.setAttribute("allcart", allCart);
        }
        return "/shoppingcart.jsp";

    }
    private String addchoose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        int count = Integer.parseInt(request.getParameter("count"));
        if(count<0){
            return Views.ERROR;
        }
        Cart cart = new Cart();
        cart.setGoodsid(goodsid);
        cart.setUserid(userid);
        cart.setCount(count);
        CartDAO cartDao = new CartDAO();
        if(cartDao.insetUserChoose(cart)) {
            return Views.SHOOPINGCAET_LIST;
        }else{
            return Views.ERROR;
        }


    }
    private String decreasechoose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        int count = Integer.parseInt(request.getParameter("count"));
        if(count<0){
            return Views.ERROR;
        }
        Cart cart = new Cart();
        cart.setGoodsid(goodsid);
        cart.setUserid(userid);
        cart.setCount(count);
        CartDAO cartDao=new CartDAO();
        if(cartDao.decreaseUserChoose(cart)) {
            return Views.SHOOPINGCAET_LIST;
        }else{
            return Views.ERROR;
        }
    }
    private String delall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        Cart cart = new Cart();
        cart.setUserid(userid);
        CartDAO cartDao=new CartDAO();
        if(cartDao.delAllUserChooseByUserid(cart)) {
            return Views.SHOOPINGCAET_LIST;
        }else{
            return Views.ERROR;
        }
    }
    private String delchoose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        Cart cart = new Cart();
        cart.setUserid(userid);
        cart.setGoodsid(goodsid);
        CartDAO cartDao=new CartDAO();
        if(cartDao.delUserChooseIfCountNull(cart)) {
            return Views.SHOOPINGCAET_LIST;
        }else {
            return Views.ERROR;
        }
    }
    private String changecount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Views.RELOGIN;
        }
        int userid = user.getUserid(); //获取登录的用户id
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        int count = Integer.parseInt(request.getParameter("count"));
        Cart cart = new Cart();
        cart.setGoodsid(goodsid);
        cart.setUserid(userid);
        cart.setCount(count);
        CartDAO cartDao = new CartDAO();
        if(cartDao.updateCount(cart)) {
            return Views.SHOOPINGCAET_LIST;
        }else{
            return Views.ERROR;
        }

    }

}
