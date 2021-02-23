package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.TXiangmu;


public class xiangmu_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		if(type.endsWith("xiangmuAdd"))
		{
			xiangmuAdd(req, res);
		}
		if(type.endsWith("xiangmuMana"))
		{
			xiangmuMana(req, res);
		}
		if(type.endsWith("xiangmuSele"))
		{
			xiangmuSele(req, res);
		}
		if(type.endsWith("xiangmuDel"))
		{
			xiangmuDel(req, res);
		}
	}
	
	public void xiangmuAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String mingcheng=req.getParameter("mingcheng");
		String shijian=req.getParameter("shijian");
		String fuzeren=req.getParameter("fuzeren");
		String del = "no";
		
		String sql="insert into t_xiangmu (mingcheng,shijian,fuzeren,del) values(?,?,?,?)";
		Object[] params={mingcheng,shijian,fuzeren,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "xiangmu?type=xiangmuMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void xiangmuDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_xiangmu set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "xiangmu?type=xiangmuMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void xiangmuMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String sql="select * from t_xiangmu where del='no'";
		req.setAttribute("xiangmuList", getxiangmuList(sql));
		req.getRequestDispatcher("admin/xiangmu/xiangmuMana.jsp").forward(req, res);
	}

	public void xiangmuSele(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String sql="select * from t_xiangmu where del='no'";
		req.setAttribute("xiangmuList", getxiangmuList(sql));
		req.getRequestDispatcher("admin/xiangmu/xiangmuSele.jsp").forward(req, res);
	}
	
	private List getxiangmuList(String sql){
		List xiangmuList=new ArrayList();
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TXiangmu xiangmu=new TXiangmu();
				
				xiangmu.setId(rs.getInt("id"));
				xiangmu.setMingcheng(rs.getString("mingcheng"));
				xiangmu.setShijian(rs.getString("shijian"));
				xiangmu.setFuzeren(rs.getString("fuzeren"));
				
				xiangmuList.add(xiangmu);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();		
		return xiangmuList;
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
