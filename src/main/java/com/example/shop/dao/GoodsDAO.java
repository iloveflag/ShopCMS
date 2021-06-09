package com.example.shop.dao;

import com.example.shop.db.DBHelper;
import com.example.shop.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
	//根据id查询商品详细信息
	public Goods queryGoodsByGoodsid(int goodsid) {
		Goods goodsDetail = new Goods();
		String sql = "SELECT * FROM goodsinfo where goodsid ="+goodsid;
		Connection conn = null;
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			if (conn == null)
				throw new SQLException("UserDAO: 无法获得数据库连接!");

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				goodsDetail.setGoodsid(rs.getInt("goodsid"));
				goodsDetail.setImg(rs.getString("img"));
				goodsDetail.setTitle(rs.getString("title"));
				goodsDetail.setPrice(rs.getInt("price"));
				goodsDetail.setReserve(rs.getInt("reserve"));
			}
		} catch (Exception ex) {
			System.out.println("UserDAO SQLException: " + ex);
		} finally {
			DBHelper.close(conn);
			DBHelper.close(ps, rs);
		}
		return goodsDetail;
	}

	//减少库存量
	public  boolean minusReserveByGoodsid(Goods goods,int count){
		String sql = "UPDATE goodsinfo SET `reserve` = reserve-? WHERE `goodsid` = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBHelper.getConnection();
			if (conn == null)
				throw new Exception("GoodsDAO: 无法获得数据库连接!");

			int num = 1;
			ps = conn.prepareStatement(sql);
			ps.setString(num++, String.valueOf(count));
			ps.setString(num++, String.valueOf(goods.getGoodsid()));


			if (ps.executeUpdate() <= 0) {
				throw new Exception("更新表GoodsInfo出错！");
			}
			conn.commit();
			return true;
		} catch (Exception e) {
			System.err.println("****更新表GoodsInfo出错！");
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


}
