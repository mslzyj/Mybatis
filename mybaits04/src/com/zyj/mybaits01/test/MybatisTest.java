package com.zyj.mybaits01.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.zyj.mybaits01.bean.Department;
import com.zyj.mybaits01.bean.Employee;
import com.zyj.mybaits01.dao.DepartmentMapper;
import com.zyj.mybaits01.dao.EmployeeMapper;
import com.zyj.mybaits01.dao.EmployeeMapperAnnotation;
import com.zyj.mybaits01.dao.EmployeeMapperDynamicSQL;
import com.zyj.mybaits01.dao.EmployeeMapperplus;

/**
 * 1���ӿ�ʽ��� ԭ���� Dao ====> DaoImpl mybatis�� Mapper ====> xxMapper.xml
 * 
 * 2��SqlSession��������ݿ��һ�λỰ���������رգ�
 * 3��SqlSession��connectionһ�������Ƿ��̰߳�ȫ��ÿ��ʹ�ö�Ӧ��ȥ��ȡ�µĶ���
 * 4��mapper�ӿ�û��ʵ���࣬����mybatis��Ϊ����ӿ�����һ��������� �����ӿں�xml���а󶨣� EmployeeMapper
 * empMapper = sqlSession.getMapper(EmployeeMapper.class); 5��������Ҫ�������ļ���
 * mybatis��ȫ�������ļ����������ݿ����ӳ���Ϣ�������������Ϣ��...ϵͳ���л�����Ϣ�����Բ��������ļ��ķ�ʽ�� ֱ�ӽ���Ҫ�õ��Ķ���new�������ɡ�
 * sqlӳ���ļ���������ÿһ��sql����ӳ����Ϣ�� ��sql��ȡ������ ����Ҫ��sqlӳ���ļ���
 * 
 * 
 * @author ZYJ
 *
 */
public class MybatisTest {

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	/**
	 * 1������xml�����ļ���ȫ�������ļ�������һ��SqlSessionFactory���� ������ԴһЩ���л�����Ϣ
	 * 2��sqlӳ���ļ���������ÿһ��sql���Լ�sql�ķ�װ����ȡ� 3����sqlӳ���ļ�ע����ȫ�������ļ��� 4��д���룺
	 * 1��������ȫ�������ļ��õ�SqlSessionFactory��
	 * 2����ʹ��sqlSession��������ȡ��sqlSession����ʹ������ִ����ɾ�Ĳ�
	 * һ��sqlSession���Ǵ�������ݿ��һ�λỰ������ر�
	 * 3����ʹ��sql��Ψһ��־������MyBatisִ���ĸ�sql��sql���Ǳ�����sqlӳ���ļ��еġ�
	 * 
	 * @throws IOException
	 */

	@Test
	public void test01() throws IOException {
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�������
			// ��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
			EmployeeMapper mapper = (EmployeeMapper) openSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpById(1);
			System.out.println(mapper.getClass());
			System.out.println(employee);
		} finally {
			openSession.close();
		}

	}

	@Test
	public void test02() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
			Employee empById = mapper.getEmpById(1);
			System.out.println(empById);
		} finally {
			openSession.close();
		}
	}
	/**
	 * @throws IOException 
	 * ������ɾ��
	 * 1��mybatis������ɾ��ֱ�Ӷ����������ͷ���ֵ
	 * 		Integer��Long��Boolean��void
	 * 2��������Ҫ�ֶ��ύ����
	 * 		sqlSessionFactory.openSession();===���ֶ��ύ
	 * 		sqlSessionFactory.openSession(true);===���Զ��ύ
	 * @throws IOException 
	 */
	@Test
	public void test03() throws IOException{
	  SqlSessionFactory sessionFactory =  getSqlSessionFactory();
	  //1.��ȡSqlSession�����Զ��ύ����
	  SqlSession  openSession=sessionFactory.openSession();
	  //�Զ��ύ��   SqlSession  openSession=sessionFactory.openSession(true);
	  try{
		  EmployeeMapper mapper =  openSession.getMapper(EmployeeMapper.class);
	      Employee employee = new Employee(null,"ddd","dd@qq.com","0", null);
		  //�������
	      mapper.addEmp(employee);
	      System.out.println(employee.getId());
	       //mapper.updateEmp(employee);
	      
	     // mapper.deleteEmpById(4);
		  //�ֶ��ύ����
		  openSession.commit();
	  }finally{
		  openSession.close();
	  }
	}

	@Test
	public void test04() throws IOException{
	  SqlSessionFactory sessionFactory =  getSqlSessionFactory();
	  //1.��ȡSqlSession�����Զ��ύ����
	  SqlSession  openSession=sessionFactory.openSession();
	  //�Զ��ύ��   SqlSession  openSession=sessionFactory.openSession(true);
	  try{
		  EmployeeMapper mapper =  openSession.getMapper(EmployeeMapper.class);
	     
		 // Employee employee = mapper.getEmpByIdAndLastName(1, "aa");
		  
		/*  Map<String, Object> map = new HashMap<>();
		  map.put("id", 1);
		  map.put("lastName", "aa");
		  Employee employee = mapper.getEmpByMap(map);
		  System.out.println(employee);*/
		  
		  /*List<Employee> like = mapper.getEmpsByLastNameLike("a%");
		  for (Employee employee:like){
			  System.out.println(employee);
		  }*/
		  /*Map<String, Object> map = mapper.getEmpByIdReturnMap(2);
		  System.out.println(map);
*/
		  Map<Integer, Employee> map =  mapper.getEmpByLastNameLikeReturnMap("a%");
		  System.out.println(map);
		  }finally{
		  openSession.close();
	  }
	}
	
	@Test
	public void test05() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperplus mapper = openSession.getMapper(EmployeeMapperplus.class);
			/*Employee empById = mapper.getEmpById(1);
			System.out.println(empById);*/
			
			/*Employee empAndDept = mapper.getEmpAndDept(1);
			System.out.println(empAndDept);
			System.out.println(empAndDept.getDepartment());*/
			
			Employee employee = mapper.getEmpByIdStep(1);
			System.out.println(employee);
			System.out.println(employee.getDepartment());
		} finally {
            openSession.close();
		}
	}
	
	@Test
	public void test06() throws IOException{
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();
		try {
		 DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		/* Department department = mapper.getDeptByIdPlus(1);
		 System.out.println(department);
		 System.out.println(department.getEmps());*/
		 
		 Department deptByIdStep = mapper.getDeptByIdStep(1);
		 System.out.println(deptByIdStep);
		 System.out.println(deptByIdStep.getEmps());
		} finally {
             session.close();
		}
		
	}
	//Я�����ĸ��ֶβ�ѯ�����ʹ�������ֶε�ֵ
	@Test
	public void test07() throws IOException{
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
	    SqlSession session = sessionFactory.openSession();
	    try {
		EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);	
		/*Employee employee  = new Employee(1,null,null,null,null);
		List<Employee> list = mapper.getEmpsByConditionIf(employee);
		for(Employee emp:list){
			System.out.println(emp);
		}*/
		Employee employee2 = new Employee(1,"aaa",null,null,null);
		/*List<Employee> empsByConditionTrim = mapper.getEmpsByConditionTrim(employee2);
		for (Employee employee3 : empsByConditionTrim) {
			System.out.println(employee3);
		}
		*/
		/*List<Employee> empsByConditionChoose = mapper.getEmpsByConditionChoose(employee2);
		for (Employee employee : empsByConditionChoose) {
			System.out.println(employee);
		}*/
		mapper.updateEmp(employee2);
		session.commit();
		List<Employee> empsByConditionForeach = mapper.getEmpsByConditionForeach(Arrays.asList(1,2,3));
		for (Employee employee : empsByConditionForeach) {
           System.out.println(employee);

		}
		
	    } finally {
           session.close();
		}
	}
	
	@Test
	public void test08() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			List<Employee> emps = new ArrayList<>();
			emps.add(new Employee(null, "smith01", "smith01@atguigu.com", "1",new Department(1)));
			emps.add(new Employee(null, "allen01", "allen01@atguigu.com", "0",new Department(1)));
			mapper.addEmps(emps);
			openSession.commit();
		}finally{
			openSession.close();
		}
	}
}