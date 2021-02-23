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
import com.orm.TZiyuan;
import com.util.DateUtils;


public class ziyuan_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		if(type.endsWith("ziyuanAdd"))
		{
			ziyuanAdd(req, res);
		}
		if(type.endsWith("ziyuanMana"))
		{
			ziyuanMana(req, res);
		}
	}
	public void ziyuanAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String xiangmu_id=req.getParameter("xiangmu_id");
		String shijian=req.getParameter("shijian");
		String mingcheng=req.getParameter("mingcheng");
		String miaoshu=req.getParameter("miaoshu");
		
		String sql="insert into t_ziyuan (xiangmu_id,mingcheng,miaoshu,shijian) values(?,?,?,?)";
		Object[] params={xiangmu_id,mingcheng,miaoshu,shijian};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "²Ù×÷³É¹¦");
		req.setAttribute("path", "ziyuan?type=ziyuanMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void ziyuanMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String sql="select ta.*,tb.mingcheng xmmc from t_ziyuan ta,t_xiangmu tb " +
				   "where ta.xiangmu_id=tb.id and del='no'";
		List ziyuanList=new ArrayList();
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TZiyuan ziyuan=new TZiyuan();
				
				ziyuan.setId(rs.getInt("id"));
				ziyuan.setXmmc(rs.getString("xmmc"));
				ziyuan.setShijian(rs.getString("shijian"));
				ziyuan.setMingcheng(rs.getString("mingcheng"));
				ziyuan.setMiaoshu(rs.getString("miaoshu"));
				
				ziyuanList.add(ziyuan);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();			
		
		req.setAttribute("ziyuanList", ziyuanList);
		req.getRequestDispatcher("admin/ziyuan/ziyuanMana.jsp").forward(req, res);
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
