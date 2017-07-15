package jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 1.导入连接池的jar c3p0 mchange
 * 2.编写配置文件 要放在class路径
 * 3.从DataSource数据源获取连接
 * @author wyy
 *
 */
public class DBConnectionPoolUtils {
	
	//数据源
	private  static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	
	private DBConnectionPoolUtils(){}
	private static DBConnectionPoolUtils instance = null;
	public  static DBConnectionPoolUtils getInstance(){
		if(instance == null){
			instance = new  DBConnectionPoolUtils();
			
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mytest");
			dataSource.setUser("root");
			dataSource.setPassword("123456");
			try {
				dataSource.setDriverClass("com.mysql.jdbc.Driver");
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
	 		
			dataSource.setAcquireIncrement(5) ;
			dataSource.setInitialPoolSize(20) ;
			dataSource.setMinPoolSize(2) ;
			dataSource.setMaxPoolSize(50) ;

		}
		return instance;
	}
	
	/**
	 * 1.获取数据库连接
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Connection openConnection(){
		Connection conn = null;
		try {
			conn =  dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	
	
	public static void main(String[] args) {
		Connection conn = DBConnectionPoolUtils.getInstance().openConnection();
		System.out.println(conn);
	}
}
