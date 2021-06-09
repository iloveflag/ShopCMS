package com.example.shop.web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.example.shop.dao.GoodsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.shop.util.Command;
import com.example.shop.model.*;

public class GoodsCommand implements Command {
	private static Logger logger = LoggerFactory.getLogger(GoodsCommand.class);
	
	public static final String FILE_ROOT = "E:/temp/test";
	
	private static interface Views {        
        public static final String SHOOPINGCAET_LIST = "/shoppingcart.jsp";
        public static final String ERROR = "/common/relogin.jsp";
		public static final String  DETAIL_LIST ="/detail.jsp";
		public static final String  noSuchPage ="/common/noSuchPage.jsp";
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置请求参数的字符编码
			request.setCharacterEncoding("UTF-8");
			String cmd = request.getParameter("cmd");
			if("queryid".equals(cmd)){
				return queryid(request,response);
			}else {
				return Views.noSuchPage;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			System.err.println("GoodsCommand:" + ex);
			return Views.noSuchPage;
		}
	}
	
	/**
     * 处理文件查询请求
     */
    private String queryid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodsid = Integer.parseInt(request.getParameter("goodsid"));
		GoodsDAO goodsDao = new GoodsDAO();
		Goods goodsDetail = goodsDao.queryGoodsByGoodsid(goodsid);
		if(goodsDetail.getTitle()!=null) {
			request.setAttribute("goodsDetail", goodsDetail);
			return Views.DETAIL_LIST;
		}else{
			return Views.noSuchPage;
		}
    }
}
