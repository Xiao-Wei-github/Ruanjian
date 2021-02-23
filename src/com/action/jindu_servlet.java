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
import com.orm.TJindu;
import com.util.DateUtils;


public class jindu_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		if(type.endsWith("jinduAdd"))
		{
			jinduAdd(req, res);
		}
		if(type.endsWith("jinduMana"))
		{
			jinduMana(req, res);
		}
	}
	public void jinduAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String xiangmu_id=req.getParameter("xiangmu_id");
		String shijian=req.getParameter("shijian");
		String miaoshu=req.getParameter("miaoshu");
		String baifenbi=req.getParameter("baifenbi");
		
		String sql="insert into t_jindu (xiangmu_id,shijian,miaoshu,baifenbi) values(?,?,?,?)";
		Object[] params={xiangmu_id,shijian,miaoshu,baifenbi};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "²Ù×÷³É¹¦");
		req.setAttribute("path", "jindu?type=jinduMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void jinduMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String sql="select ta.*,tb.mingcheng from t_jindu ta,t_xiangmu tb " +
				   "where ta.xiangmu_id=tb.id and del='no'";
		List jinduList=new ArrayList();
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TJindu jindu=new TJindu();
				
				jindu.setId(rs.getInt("id"));
				jindu.setXmmc(rs.getString("mingcheng"));
				jindu.setShijian(rs.getString("shijian"));
				jindu.setMiaoshu(rs.getString("miaoshu"));
				jindu.setBaifenbi(rs.getString("baifenbi"));
				
				jinduList.add(jindu);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();			
		
		req.setAttribute("jinduList", jinduList);
		req.getRequestDispatcher("admin/jindu/jinduMana.jsp").forward(req, res);
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
