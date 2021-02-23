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
import com.orm.TJingfei;
import com.util.DateUtils;


public class jingfei_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		if(type.endsWith("jingfeiAdd"))
		{
			jingfeiAdd(req, res);
		}
		if(type.endsWith("jingfeiMana"))
		{
			jingfeiMana(req, res);
		}
		if(type.endsWith("jingfeiDel"))
		{
			jingfeiDel(req, res);
		}
	}
	public void jingfeiAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String xiangmu_id=req.getParameter("xiangmu_id");
		String shijian=req.getParameter("shijian");
		String jine=req.getParameter("jine");
		
		String sql="insert into t_jingfei (xiangmu_id,jine,shijian) values(?,?,?)";
		Object[] params={xiangmu_id,jine,shijian};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "jingfei?type=jingfeiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	public void jingfeiDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_jingfei set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "jingfei?type=jingfeiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void jingfeiMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String sql="select ta.*,tb.mingcheng from t_jingfei ta,t_xiangmu tb " +
				   "where ta.xiangmu_id=tb.id and del='no'";
		List jingfeiList=new ArrayList();
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TJingfei jingfei=new TJingfei();
				
				jingfei.setId(rs.getInt("id"));
				jingfei.setXmmc(rs.getString("mingcheng"));
				jingfei.setShijian(rs.getString("shijian"));
				jingfei.setJine(rs.getString("jine"));
				
				jingfeiList.add(jingfei);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();			
		
		req.setAttribute("jingfeiList", jingfeiList);
		req.getRequestDispatcher("admin/jingfei/jingfeiMana.jsp").forward(req, res);
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
