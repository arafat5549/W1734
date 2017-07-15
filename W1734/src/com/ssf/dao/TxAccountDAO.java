package com.ssf.dao;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.DBUtils;

import com.ssf.model.TxAccount;

public class TxAccountDAO {

	//1.不要使用通配符 自己来解析列名（怎么让两种命名规范统一）
		private static final String COLUMN =
				" a.id, "+
				" a.name, "+
				" a.balance ";
		
		
		/**
		 * 根据id来查找
		 * @param id
		 * @return
		 */
		private TxAccount findById(int id){
			String sql ="SELECT "+COLUMN+" FROM tx_account a WHERE a.id=?";
			// SELECT * FROM mgr_department a WHERE a.id =1;
			return DBUtils.queryBean(sql, TxAccount.class, id);
		}
		
		/**
		 * 不使用事务
		 */
		private void transfer(int fromId,int toId,int count){
			String sqlMinus = "UPDATE tx_account SET balance=balance-"+count+" WHERE id=?";
			String sqlPlus  = "UPDATE tx_account SET balance=balance+"+count+" WHERE id=?";
			
			DBUtils.executeBean(sqlMinus, fromId);
			
			if(count>0) //自定义异常 自己抛出异常
				throw new RuntimeException("haha");
			
			DBUtils.executeBean(sqlPlus,toId);
		}
		/**
		 * 使用事务
		 * @param args
		 */
		private void transferTransaction(int fromId,int toId,int count){
			String sqlMinus = "UPDATE tx_account SET balance=balance-"+count+" WHERE id=?";
			String sqlPlus  = "UPDATE tx_account SET balance=balance+"+count+" WHERE id=?";
			Connection conn = null;//使用的是同一个连接
			try {
				conn = DBUtils.openConnection();
				conn.setAutoCommit(false);//关闭自动事务
				
				DBUtils.executeBean(conn,sqlMinus, fromId);
				if(count>0) //自定义异常 自己抛出异常
					throw new RuntimeException("开启事务的方式");
				DBUtils.executeBean(conn,sqlPlus,toId);
				
				conn.setAutoCommit(true);
				conn.commit();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void main(String[] args) {
			TxAccountDAO dao = new TxAccountDAO();
			TxAccount t1 = dao.findById(1);
			System.out.println(t1);
			
			dao.transferTransaction(2,1,100);
		}
}
