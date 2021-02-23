package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.TChengguo;
import com.util.DateUtils;


public class chengguo_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		if(type.endsWith("chengguoAdd"))
		{
			chengguoAdd(req, res);
		}
		if(type.endsWith("chengguoMana"))
		{
			chengguoMana(req, res);
		}
	}
	public void chengguoAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String xiangmu_id=req.getParameter("xiangmu_id");
		String shijian=DateUtils.formatDate2Str(new Date(), "yyyy-MM-dd HH:mm:ss");
		String mingcheng=req.getParameter("mingcheng");
		String miaoshu=req.getParameter("miaoshu");
		String fujian=req.getParameter("fujian");
		String fujianYuanshiming=req.getParameter("fujianYuanshiming");
		
		String sql="insert into t_chengguo (xiangmu_id,mingcheng,miaoshu,shijian,fujian,fujianYuanshiming)" +
				   "values(?,?,?,?,?,?)";
		Object[] params={xiangmu_id,mingcheng,miaoshu,shijian,fujian,fujianYuanshiming};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "²Ù×÷³É¹¦");
		req.setAttribute("path", "chengguo?type=chengguoMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void chengguoMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String sql="select ta.*,tb.mingcheng xmmc from t_chengguo ta,t_xiangmu tb " +
				   "where ta.xiangmu_id=tb.id and del='no'";
		List chengguoList=new ArrayList();
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TChengguo chengguo=new TChengguo();
				
				chengguo.setId(rs.getInt("id"));
				chengguo.setXmmc(rs.getString("xmmc"));
				chengguo.setShijian(rs.getString("shijian"));
				chengguo.setMingcheng(rs.getString("mingcheng"));
				chengguo.setMiaoshu(rs.getString("miaoshu"));
				chengguo.setFujian(rs.getString("miaoshu"));
				chengguo.setFujianYuanshiming(rs.getString("fujianYuanshiming"));
				
				chengguoList.add(chengguo);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();			
		
		req.setAttribute("chengguoList", chengguoList);
		req.getRequestDispatcher("admin/chengguo/chengguoMana.jsp").forward(req, res);
	}

	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
