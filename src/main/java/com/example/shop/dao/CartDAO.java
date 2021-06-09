package com.example.shop.dao;

import com.example.shop.db.DBHelper;
import com.example.shop.model.Cart;
import com.example.shop.model.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    //加入购物车操作集,if 有用户选择了这个商品的数据。count++，else，插入这条选择数据
    public boolean update (Cart cart){  //更新count
    String sql = "UPDATE cartinfo SET `count` = count+? WHERE `userid` = ? and `goodsid` = ?";
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DBHelper.getConnection();
        if (conn == null)
            throw new Exception("CartDAO: 无法获得数据库连接!");

        int num = 1;
        ps = conn.prepareStatement(sql);
        ps.setString(num++, String.valueOf(cart.getCount()));
        ps.setString(num++, String.valueOf(cart.getUserid()));
        ps.setString(num++, String.valueOf(cart.getGoodsid()));


        if (ps.executeUpdate() <= 0) {
            throw new Exception("更新表CartInfo出错！");
        }
        conn.commit();
        return true;
    } catch (Exception e) {
        System.err.println("****更新表CartInfo出错！");
        e.printStackTrace();
        try {
            if (conn != null) conn.rollback();
        } catch (Exception ex) {
        }
        return false;
    } finally {
        DBHelper.close(conn);
        DBHelper.close(ps, null);
    }
}
    public boolean inset (Cart cart){ //插入count
    String sql = " INSERT INTO cartinfo(userid,goodsid,count) VALUES (?, ?, ?)";
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DBHelper.getConnection();
        if (conn == null)
            throw new Exception("CartDAO: 无法获得数据库连接!");

        int num = 1;
        ps = conn.prepareStatement(sql);
        ps.setString(num++, String.valueOf(cart.getUserid()));
        ps.setString(num++, String.valueOf(cart.getGoodsid()));
        ps.setString(num++, String.valueOf(cart.getCount()));

        if (ps.executeUpdate() <= 0) {
            throw new Exception("插入表CartInfo出错！");
        }
        conn.commit();
        return true;
    } catch (Exception e) {
        System.err.println("****插入表CartInfo出错！");
        e.printStackTrace();
        try {
            if (conn != null) conn.rollback();
        } catch (Exception ex) {
        }
        return false;
    } finally {
        DBHelper.close(conn);
        DBHelper.close(ps, null);
    }
}
    public boolean queryUserChoose (Cart cart){  //查询数据库中是否有商品id和userid对应的数据
    ResultSet rs = null;
    String sql = "select * from cartinfo WHERE `userid` = ? and `goodsid` = ?";
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DBHelper.getConnection();
        if (conn == null)
            throw new Exception("CartDAO: 无法获得数据库连接!");

        int num = 1;
        ps = conn.prepareStatement(sql);
        ps.setString(num++, String.valueOf(cart.getUserid()));
        ps.setString(num++, String.valueOf(cart.getGoodsid()));
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    } catch (Exception e) {
        System.err.println("****更新表CartInfo出错！");
        e.printStackTrace();
        try {
            if (conn != null) conn.rollback();
        } catch (Exception ex) {
        }
        return false;
    } finally {
        DBHelper.close(conn);
        DBHelper.close(ps, null);
    }

}
    //main action
    public boolean insetUserChoose (Cart cart){ //插入用户购物选择
    if (queryUserChoose(cart)) { //数据中是否有用户选择商品的记录
        update(cart);
    } else {
        inset(cart);
    }
    return true;

}
    //--------------------------------------------------------------------------

    //减少购物车操作集,if count<1，直接删除这一行，else count-1
    public int queryCount(Cart cart){
        String sql = " select count from cartinfo WHERE `userid` = ? and `goodsid` = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count=-1;
        try {
            conn = DBHelper.getConnection();
            if (conn == null)
                throw new SQLException("UserDAO: 无法获得数据库连接!");
            int num = 1;
            ps = conn.prepareStatement(sql);
            ps.setString(num++,String.valueOf(cart.getUserid()));
            ps.setString(num++,String.valueOf(cart.getGoodsid()));
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }

        } catch (Exception ex) {
            System.out.println("UserDAO SQLException: " + ex);
        } finally {
            DBHelper.close(conn);
            DBHelper.close(ps, rs);
        }
        return count;
    }
    private boolean decrease(Cart cart) {
        String sql="UPDATE cartinfo SET `count` = count-? WHERE `userid` = ? and `goodsid` = ?" ;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelper.getConnection();
            if (conn == null)
                throw new Exception("CartDAO: 无法获得数据库连接!");

            int num = 1;
            ps = conn.prepareStatement(sql);
            ps.setString(num++, String.valueOf(cart.getCount()));
            ps.setString(num++,String.valueOf(cart.getUserid()));
            ps.setString(num++, String.valueOf(cart.getGoodsid()));


            if (ps.executeUpdate() <= 0) {
                throw new Exception("更新表CartInfo出错！");
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            System.err.println("****更新表CartInfo出错！");
            e.printStackTrace();
            try {
                if (conn != null)  conn.rollback();
            } catch (Exception ex) { }
            return false;
        } finally {
            DBHelper.close(conn);
            DBHelper.close(ps,null);
        }
    }
    public boolean delUserChooseIfCountNull(Cart cart){
        String sql="delete from cartinfo where userid=? and goodsid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelper.getConnection();
            if (conn == null)
                throw new Exception("CartDAO: 无法获得数据库连接!");

            int num = 1;
            ps = conn.prepareStatement(sql);
            ps.setString(num++,String.valueOf(cart.getUserid()));
            ps.setString(num++, String.valueOf(cart.getGoodsid()));


            if (ps.executeUpdate() <= 0) {
                throw new Exception("删除表CartInfo出错！");
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            System.err.println("****删除表CartInfo出错！");
            e.printStackTrace();
            try {
                if (conn != null)  conn.rollback();
            } catch (Exception ex) { }
            return false;
        } finally {
            DBHelper.close(conn);
            DBHelper.close(ps,null);
        }
    }
    //main action
    public boolean decreaseUserChoose(Cart cart){ //减少用户购物选择数量
        if(queryCount(cart)>1){
            decrease(cart);
            return true;
        }else{
            delUserChooseIfCountNull(cart);
            return true;
        }
    }
    //--------------------------------------------------------------------------


    //直接改变购物数量
    public boolean updateCount(Cart cart){  //更新count
        int count= cart.getCount();;
        if(count>0) {
            String sql = "UPDATE cartinfo SET `count` = ? WHERE `userid` = ? and `goodsid` = ?";
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = DBHelper.getConnection();
                if (conn == null)
                    throw new Exception("CartDAO: 无法获得数据库连接!");

                int num = 1;
                ps = conn.prepareStatement(sql);
                ps.setString(num++, String.valueOf(cart.getCount()));
                ps.setString(num++, String.valueOf(cart.getUserid()));
                ps.setString(num++, String.valueOf(cart.getGoodsid()));


                if (ps.executeUpdate() <= 0) {
                    throw new Exception("更新表CartInfo出错！");
                }
                conn.commit();
                return true;
            } catch (Exception e) {
                System.err.println("****更新表CartInfo出错！");
                e.printStackTrace();
                try {
                    if (conn != null) conn.rollback();
                } catch (Exception ex) {
                }
                return false;
            } finally {
                DBHelper.close(conn);
                DBHelper.close(ps, null);
            }
        }else if(count==0){
            delUserChooseIfCountNull(cart);
            return true;
        }else{
            return false;
        }
    }

    //清空购物车
    public boolean delAllUserChooseByUserid(Cart cart){
        String sql="delete from cartinfo where userid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelper.getConnection();
            if (conn == null)
                throw new Exception("CartDAO: 无法获得数据库连接!");

            int num = 1;
            ps = conn.prepareStatement(sql);
            ps.setString(num++,String.valueOf(cart.getUserid()));
            if (ps.executeUpdate() <= 0) {
                throw new Exception("删除表CartInfo出错！");
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            System.err.println("****删除表CartInfo出错！");
            e.printStackTrace();
            try {
                if (conn != null)  conn.rollback();
            } catch (Exception ex) { }
            return false;
        } finally {
            DBHelper.close(conn);
            DBHelper.close(ps,null);
        }//清空购物车
    }

    //查询用户选择的所有商品
    public List<Cart> queryGoodsByUserid(int userid) { //根据用户名查询购物车所有商品
        List<Cart> allcart = new ArrayList<Cart>();
        String sql = "SELECT * FROM goodsinfo,cartinfo where goodsinfo.goodsid=cartinfo.goodsid and userid =?";
        Connection conn = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            conn = DBHelper.getConnection();
            if (conn == null)
                throw new SQLException("UserDAO: 无法获得数据库连接!");

            int num = 1;
            ps = conn.prepareStatement(sql);
            ps.setString(num++,String.valueOf(userid));
            rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setGoodsid(rs.getInt("goodsid"));
                cart.setImg(rs.getString("img"));
                cart.setTitle(rs.getString("title"));
                cart.setPrice(rs.getInt("price"));
                cart.setReserve(rs.getInt("reserve"));
                cart.setCount(rs.getInt("count"));
                allcart.add(cart);
            }
        } catch (Exception ex) {
            System.out.println("UserDAO SQLException: " + ex);
        } finally {
            DBHelper.close(conn);
            DBHelper.close(ps, rs);
        }
        return allcart;
    }

}
