package com.ssf.dao;

import java.util.ArrayList;
import java.util.List;

import jdbc.DBUtils;

import com.ssf.model.MgrDepartment;
import com.ssf.model.MgrEmployee;

/**
 * DAO(DataAccessobject 数据获取对象)：从数据库里面读取数据出来
 * 
 * DAO（Data Access Object）模式就是写一个类，把访问数据库的代码封装起来。
 * DAO在数据库与业务逻辑（Service）之间。
	1实体域，即操作的对象，例如我们操作的表是user表，那么就需要先写一个User类；
	2DAO模式需要先提供一个DAO接口；
	3然后再提供一个DAO接口的实现类；
	4再编写一个DAO工厂，Service通过工厂来获取DAO实现。
 * @author wyy
 *
 */
public class MgrDepartmentDAO {

	//1.不要使用通配符 自己来解析列名（怎么让两种命名规范统一）
	private static final String COLUMN =
			" a.id, "+
			" a.name, "+
			" a.parent_id AS 'parentId' ";
	/**
	 * 根据id来查找
	 * @param id
	 * @return
	 */
	private MgrDepartment findById(int id){
		String sql ="SELECT "+COLUMN+" FROM mgr_department a WHERE a.id=?";
		// SELECT * FROM mgr_department a WHERE a.id =1;
		return DBUtils.queryBean(sql, MgrDepartment.class, id);
	}
	//
	private List<MgrDepartment> getChildDept(int deptId){
		String sql 
		="SELECT "+COLUMN+" FROM mgr_department a "+
				"JOIN mgr_department d2 "+
				"ON a.parent_id = d2.id "+
				"WHERE d2.id = ?";
		return DBUtils.listBean(sql, MgrDepartment.class, deptId);
	}
	//如何做递归
	private void getChildDepts(List<MgrDepartment> list,int deptId){
		List<MgrDepartment> depts = getChildDept(deptId);
		for(MgrDepartment dept:depts){
			list.add(dept);
			getChildDepts(list,dept.getId());
		}
	}
	
	
	public static void main(String[] args) {
		MgrDepartmentDAO dao = new MgrDepartmentDAO();
		//MgrDepartment dept = dao.getChildDept(11);
		//System.out.println(dept);
		int deptId = 1;
		int empId = 1;
		List<MgrDepartment> list = new ArrayList<>();
		dao.getChildDepts(list,deptId);
		list.add(dao.findById(deptId));
		
		System.out.println(list);
		
		for (MgrDepartment d : list) {
			MgrEmployee emp = null;//新写个员工的DAO来获取员工对象
			if(emp.getDepartmentId() == d.getId()){
				System.out.println("员工属于这个部门");
				return;
			}
		}
	}
}
