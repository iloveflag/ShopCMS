package com.example.shop.dao;

import com.example.shop.db.DBHelper;
import com.example.shop.model.Cart;
import com.example.shop.model.Goods;
import com.example.shop.dao.GoodsDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public static String  ifHasReserve(List<Cart> allCart) { //库存比对
        String message="";
        for (Cart cart : allCart) {
            GoodsDAO goodsDAO=new GoodsDAO();
            goodsDAO.queryGoodsByGoodsid(cart.getGoodsid());
            int reserve=goodsDAO.queryGoodsByGoodsid(cart.getGoodsid()).getReserve();
            if(cart.getCount()>reserve){
                message=message+cart.getTitle()+"库存不足,当前库存为:"+reserve+"</br>";
            }
        }
        return message;
    }
    public boolean minusReserve(List<Cart> allCart) {
        for (Cart cart : allCart) {
            int count = cart.getCount();
            Goods goods = new Goods();
            goods.setGoodsid(cart.getGoodsid());
            if(new GoodsDAO().minusReserveByGoodsid(goods,count)){
                continue;
            }else{
                return false;
            }

        }
        return true;
    }
}
