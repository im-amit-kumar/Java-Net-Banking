package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.Account;
import com.providers.DAOConnectionProvider;
import com.services.DAOServices;

public class DAOServiceImpl implements DAOServices
{
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	public DAOServiceImpl()
	{
		con = DAOConnectionProvider.getDBConnection();
	}
	
	public int insertAccountData(Account acc) 
	{
		int updateCount=0;
		try 
		{
			stmt=con.prepareStatement("insert into Account values(?,?,?)");
			stmt.setInt(1, acc.getAccNo());
			stmt.setString(2, acc.getAccType());
			stmt.setFloat(3, acc.getAccBal());
			updateCount=stmt.executeUpdate();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return updateCount;
	}

	
	public Account getAccountDetails(int accNo) 
	{
		Account acc=null;
		try
		{
			stmt=con.prepareStatement("select * from Account where accNo=?");
			stmt.setInt(1, accNo);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
				int accNo1=rs.getInt(1);
				String accType=rs.getString(2);
				float accBal=rs.getFloat(3);
				acc=new Account(accNo1, accType, accBal);
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return acc;
	}
	
	public static void main (String[] args)
	{
		DAOServiceImpl dao = new DAOServiceImpl();
	//	Account a = dao.getAccountDetails(1001);
		//System.out.println(a);
	}
}
