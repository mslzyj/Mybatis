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
 * 1、接口式编程 原生： Dao ====> DaoImpl mybatis： Mapper ====> xxMapper.xml
 * 
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。 （将接口和xml进行绑定） EmployeeMapper
 * empMapper = sqlSession.getMapper(EmployeeMapper.class); 5、两个重要的配置文件：
 * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息，可以不用配置文件的方式， 直接将需要用到的对象new出来即可。
 * sql映射文件：保存了每一个sql语句的映射信息： 将sql抽取出来。 必须要有sql映射文件。
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
	 * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
	 * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。 3、将sql映射文件注册在全局配置文件中 4、写代码：
	 * 1）、根据全局配置文件得到SqlSessionFactory；
	 * 2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
	 * 一个sqlSession就是代表和数据库的一次会话，用完关闭
	 * 3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
	 * 
	 * @throws IOException
	 */

	@Test
	public void test01() throws IOException {
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现类对象
			// 会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
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
	 * 测试增删改
	 * 1、mybatis允许增删改直接定义以下类型返回值
	 * 		Integer、Long、Boolean、void
	 * 2、我们需要手动提交数据
	 * 		sqlSessionFactory.openSession();===》手动提交
	 * 		sqlSessionFactory.openSession(true);===》自动提交
	 * @throws IOException 
	 */
	@Test
	public void test03() throws IOException{
	  SqlSessionFactory sessionFactory =  getSqlSessionFactory();
	  //1.获取SqlSession不会自动提交数据
	  SqlSession  openSession=sessionFactory.openSession();
	  //自动提交：   SqlSession  openSession=sessionFactory.openSession(true);
	  try{
		  EmployeeMapper mapper =  openSession.getMapper(EmployeeMapper.class);
	      Employee employee = new Employee(null,"ddd","dd@qq.com","0", null);
		  //添加数据
	      mapper.addEmp(employee);
	      System.out.println(employee.getId());
	       //mapper.updateEmp(employee);
	      
	     // mapper.deleteEmpById(4);
		  //手动提交数据
		  openSession.commit();
	  }finally{
		  openSession.close();
	  }
	}

	@Test
	public void test04() throws IOException{
	  SqlSessionFactory sessionFactory =  getSqlSessionFactory();
	  //1.获取SqlSession不会自动提交数据
	  SqlSession  openSession=sessionFactory.openSession();
	  //自动提交：   SqlSession  openSession=sessionFactory.openSession(true);
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
	//携带了哪个字段查询条件就带上这个字段的值
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