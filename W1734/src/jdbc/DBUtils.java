package jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.SysStudent;
import com.ssf.model.SysUser;

/**
 * 数据库JDBC连接工具类
 * 
 * 都是java.sql.*这个包底下的
 * @author wyy
 *
 */
public class DBUtils {
    //1.驱动类
	private static String driverClass = "com.mysql.jdbc.Driver";
	//2.url路径
	private static String url=
	"jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=UTF-8";
			//"jdbc://localhost:3306?mytest";
	//3.用户名
	private static String userName = "root";
	//4.密码
	private static String password = "123456";
	
	/**
	 * 1.获取数据库连接
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection openConnection() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		
		//1.加载驱动类到JVM 
		Class.forName(driverClass);
		//2.
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}
	/**
	 * 
	 * @param conn
	 * @param sql
	 * @throws SQLException 
	 */
	public static List<SysUser> query(Connection conn,String sql) throws SQLException{
		
		List<SysUser> list= new ArrayList<>();
		Statement stmt = conn.createStatement();
		//查询 应该返回结果集
		ResultSet rs =  stmt.executeQuery(sql);
		//遍历结果集 - 游标，迭代器
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			System.out.println(id+","+name);
			SysUser user = new SysUser();
			user.setId(id);
			user.setName(name);
			list.add(user);
		}
		return list;
	}
	//如何编写一套通用的JDBC连接
	//泛型，反射
	//1.提取出对象User，Bean对象（）  引入分层的概念 ，JavaBean位于实体entity或者model层
	public static <T> List<T> queryBean(Connection conn,String sql,Class<T> t) throws SQLException, InstantiationException, IllegalAccessException{
		List<T> list = new ArrayList<>();
		
		Statement stmt = conn.createStatement();
		//查询 应该返回结果集
		ResultSet rs =  stmt.executeQuery(sql);
		//结果集可以获取 元数据 MetaData
		ResultSetMetaData rsmd = rs.getMetaData();
		
		//这张表总有有多少列
		int columns = rsmd.getColumnCount();
		while(rs.next()){
			T obj = t.newInstance();
			for (int i = 0; i < columns; i++) {
				 String columnName = rsmd.getColumnName(i+1);//数据库都是从1开始，0代表连接成功
				 Object value = rs.getObject(columnName);
				 //首字母大写
				 columnName = columnName.substring(0,1).toUpperCase() +  columnName.substring(1);
										 
				 //System.out.println(columnName+","+value);
				 
				 invokeMethodByName(obj,"set"+columnName,new Object[]{value});
			}
			list.add(obj);
		}
		
		return list;
	}
	 
	//反射reflect # 类的所有部分反射为对象  # 运行时起效的东西
	static void reflectDemo() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		//获取类的模型 有几种方式
		//1.
		Class c1 = SysUser.class;
		//2.class.forName
		Class c2 = Class.forName("com.ssf.model.SysUser");
		//3.
		SysUser u = new SysUser();
		Class c3 = u.getClass(); 
		
		//获取类的所有定义 # 
		//属性
		Field  fs [] = c1.getFields();//所有的public属性
		for (int i = 0; i < fs.length; i++) {
			System.out.println(fs[i]);
		}
		System.out.println("所有定义属性---");
		Field  fs2 [] = c1.getDeclaredFields();
		for (int i = 0; i < fs2.length; i++) {
			System.out.println(fs2[i]);
		}
		System.out.println("所有Public方法---");
		//方法
		Object obj = c1.newInstance();
		Method ms[] = c1.getMethods();
		for (int i = 0; i < ms.length; i++) {
			System.out.println(ms[i]);
			if(ms[i].getName().equals("setId")){
				//ms[i].invoke(obj, new Object[]{new Integer(1)});
			}
		}
		//System.out.println(obj);
		
		
		invokeMethodByName(
				obj,"setId",
				new Object[]{new Integer(1111)}
		);
		System.out.println("obj="+obj);
	}
	
	//不正向new出来 如何将属性注入对象中
	//获取setter方法来注入,final Class<?>... parameterTypes
	/**
	 * 利用反射注入属性
	 * @param obj
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) 
	{
		Method method = null;
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method m : methods) {
			// System.out.println("哈哈："+m.getName());
			if (m.getName().equals(methodName)) {
				method = m;
				break;
			}
		}
		
		System.out.println("获得："+method);
		
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void invokeMethod(Class t,String methodName,Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException{
//		Method method = t.getDeclaredMethod(methodName, parameterTypes);
//		//makeAccessible(method);
//		System.out.println("获得："+method);
//		
//	}
	
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			Connection conn = openConnection();
			String sql = "SELECT * FROM sys_student";
//			//query(conn, sql);
			List<SysStudent> list= queryBean(conn, sql,SysStudent.class);
			System.out.println(list);
			
			
			//reflectDemo();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
