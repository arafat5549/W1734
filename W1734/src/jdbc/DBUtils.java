package jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	/**
	 * 
	 * 利用Statement编写通用查询
	 * 
	 * //如何编写一套通用的JDBC连接
	   //泛型，反射
	   //1.提取出对象User，Bean对象,  引入分层的概念 ，JavaBean位于实体entity或者model层
	    * 
	    Connection    ： 连接
	    DriverManager ： 驱动管理
	    Statement     ： 段落，为了一个连接能处理多个sql
	    ResultSet     ： 结果集 ，游标 数据库游标都是从1开始，0代表连接成功
	 * 
	 * @param conn   连接
	 * @param sql    sql语句
	 * @param t      泛型类对象
	 * @return
	 */
	public static <T> List<T> queryBeanStatment(Connection conn,String sql,Class<T> t) throws SQLException, InstantiationException, IllegalAccessException{
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
				 //System.out.println(columnName+","+value);x
				 invokeMethodByName(obj,"set"+columnName,new Object[]{value});
			}
			list.add(obj);
		}
		return list;
	}
	/**
	 * Statement通用修改
	 * @throws SQLException 
	 */
	public static boolean executeBeanStatment(Connection conn,String sql) throws SQLException{
		Statement stmt = conn.createStatement();
		return stmt.execute(sql);
	}
	
	/**
	 * 
	 */
	public static <T> T queryBean(String sql,Class<T> t,Object ...objs)
	{
		List<T> list = listBean(sql, t, objs);
		return list.size()>0 ? list.get(0) : null;
	}
	
	public static <T> List<T> listBean(String sql,Class<T> t,Object ...objs){
		Connection conn = null;
		List<T> list = new ArrayList<>();
		try {
			conn = openConnection();
			list =  listBean(conn, sql, t, objs);
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * PreparedStatement
	 * 
	 * Object ...objs 可变参数列表，一定要放在参数列表的最后
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> List<T> listBean(Connection conn,String sql,Class<T> t,Object ...objs) throws SQLException, InstantiationException, IllegalAccessException{
		List<T> list = new ArrayList<>();
		
		//1.获取PreparedStatement
		PreparedStatement ptmt  = conn.prepareStatement(sql);
		int idx = 0;
		for (Object object : objs) {
			ptmt.setObject(++idx,object);
		}
		//
		ResultSet rs =  ptmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while(rs.next()){
			T obj = t.newInstance();
			for (int i = 0; i < columns; i++) {
				 //String columnName = rsmd.getColumnName(i+1);//数据库都是从1开始，0代表连接成功
				String columnLabel = rsmd.getColumnLabel(i+1);
				Object value = rs.getObject(columnLabel);
				columnLabel = columnLabel.substring(0,1).toUpperCase() +  columnLabel.substring(1);					 
				 invokeMethodByName(obj,"set"+columnLabel,new Object[]{value});
			}
			list.add(obj);
		}
		
		return list;
	}
	
	public static boolean executeBean(Connection conn,String sql,Object ...objs) throws SQLException{
		PreparedStatement ptmt  = conn.prepareStatement(sql);
		int idx = 0;
		for (Object object : objs) {
			ptmt.setObject(++idx,object);
		}
		boolean flag = ptmt.execute();
		//conn.close();
		return  flag;
	}
	
	public static boolean executeBean(Connection conn,String sql,List<Object> params) throws SQLException{
		PreparedStatement ptmt  = conn.prepareStatement(sql);
		int idx = 0;
		for (Object object : params) {
			ptmt.setObject(++idx,object);
		}
		boolean flag = ptmt.execute();
		//conn.close();
		return  flag;
	}
	
	/**
	 * 批处理
	 * @throws SQLException 
	 */
//	public static boolean batchExecute(Connection conn,String sql,Object ...objs) throws SQLException{
//		PreparedStatement ptmt  = conn.prepareStatement(sql);
//		int idx = 0;
//		for (Object object : objs) {
//			ptmt.setObject(++idx,object);
//		}
//		ptmt.addBatch();
//	}
	
	
	/**
	 * 数据库批处理 Batch
	 * 插入1万条数据
	 * @throws SQLException 
	 */
	
	private static void insertBatch0(Connection conn) throws SQLException{
		long begin = System.currentTimeMillis();
		String sql = "INSERT INTO sys_student(name) VALUES(?) ";
		int max = 10000;
		for (int i = 0; i < max; i++) {
			executeBean(conn, sql, "Hello"+i);
		}
		System.out.println("insertBatch0运行时间:"+(System.currentTimeMillis()-begin));
		
	}
	/**
	 * 关闭自动事务#影响最大的部分 
	 */
	private static void insertBatch1(Connection conn) throws SQLException{
		conn.setAutoCommit(false);
		
		long begin = System.currentTimeMillis();
		String sql = "INSERT INTO sys_student(name) VALUES(?) ";
		int max = 10000;
		for (int i = 0; i < max; i++) {
			executeBean(conn, sql, "Hello"+i);
		}
		conn.commit();//需要手动提交
		System.out.println("insertBatch1运行时间:"+(System.currentTimeMillis()-begin));
	}
	/**
	 * 批处理方式
	 * 
	 * 
	 */
	private static void insertBatch2(Connection conn) throws SQLException{
		conn.setAutoCommit(false);
		long begin = System.currentTimeMillis();
		
		String sql = "INSERT INTO sys_student(name) VALUES(?) ";
		PreparedStatement ptmt  = conn.prepareStatement(sql);
		int max = 10000;
		for (int i = 0; i < max; i++) {
			ptmt.setObject(1,"Hello"+i);
			ptmt.addBatch();
		}
		ptmt.executeBatch();
		
		conn.commit();//需要手动提交
		System.out.println("insertBatch1运行时间:"+(System.currentTimeMillis()-begin));
	}
	/**
	 * INSERT可以拼接成
	 * 一条SQL来运行
	 * INSERT INTO sys_lesson VALUES
		(1,'数学'),
		(2,'英语'),
		(3,'语文'),
		(4,'物理'),
		(5,'化学');
	 */
	private static void insertBatch3(Connection conn) throws SQLException{
		conn.setAutoCommit(false);
		long begin = System.currentTimeMillis();
		
		String sql = "INSERT INTO sys_student(name) VALUES ";
		int max = 10000;
		List<Object> params = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			//ptmt.setObject(1,"Hello"+i);
			//ptmt.addBatch();
			if(i==max-1)
			  sql += "(?)";
			else
			 sql += "(?),";
			params.add("Hello"+i);
		}
		executeBean(conn, sql, params);
		
		conn.commit();//需要手动提交
		System.out.println("insertBatch3运行时间:"+(System.currentTimeMillis()-begin));
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
		
		//System.out.println("获得："+method);
		
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
//			//SQL注入问题
//			String username = " 1=1' or '1=1 ";
//			String sql = "SELECT * FROM sys_student WHERE name='"+username+"'";
//			System.out.println(sql);
//			List<SysStudent> list= queryBeanStatment(conn, sql,SysStudent.class);
//			System.out.println(list);
//			//? 占位符
//			String sql2 ="SELECT * FROM sys_student WHERE name=?";
//			List<SysStudent> list2= queryBean(conn, sql2,SysStudent.class,username);
//			System.out.println(list2);
			
//			sql = "INSERT INTO sys_student(name) VALUES('哇哈哈')";
//			executeBean(conn, sql);
			
			//reflectDemo();
			
			
			//批处理
			insertBatch3(conn);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
