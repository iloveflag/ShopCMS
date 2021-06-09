package com.example.shop.dao;

import com.example.shop.model.User;
import com.example.shop.db.DBHelper;

import java.sql.*;
import java.util.*;

public class UserDAO {
	/**
	 * 查询用户
	 * @param username 用户登录帐号
	 * @param password  登录密码
	 */
	public User queryUser(String username, String password) {
		String sql = "SELECT * FROM userinfo WHERE username = ?";
		if (password != null) {
			sql += " AND password=?";
		}
		Connection conn = null;
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			if (conn == null)
				throw new SQLException("UserDAO: 无法获得数据库连接!");

			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			if (password != null) {
				ps.setString(2, password);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		} catch (Exception ex) {
			System.out.println("UserDAO SQLException: " + ex);
		} finally {
			DBHelper.close(conn);
			DBHelper.close(ps, rs);
		}
		return null;
	}

	/**
	 * 新增用户
	 * @param user 用户对象
	 */
	public boolean insert(User user) {
		String sql = " INSERT INTO userinfo(username,password) VALUES (?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBHelper.getConnection();
			if (conn == null)
				throw new Exception("UserDAO: 无法获得数据库连接!");

			int num = 1;
			ps = conn.prepareStatement(sql);
			ps.setString(num++, user.getUsername());
			ps.setString(num++, user.getPassword());
			if (ps.executeUpdate() <= 0) {
				throw new Exception("插入表UserInfo出错！");
			}
			conn.commit();
			return true;
		} catch (Exception e) {
			System.err.println("****插入表UserInfo出错！");
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
}
