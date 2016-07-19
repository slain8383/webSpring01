package com.han.webApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCP {
	
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	public DBCP(){}
	
	//DB���� �� connection ����
	public Connection getConnection(){
		Connection con = null;
		try{
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)ctx.lookup("jdbc/myoracle");
			con = ds.getConnection();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return con;
	}
	//DB�ݱ�
	public void dbClose(){
		try{
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		}catch(Exception e){
			System.out.println("DB�ݱ� ���� \n"+e.getMessage());
		}
	}

}
