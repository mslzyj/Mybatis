package com.zyj.mybaits01.dao;

import java.util.List;
import java.util.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.zyj.mybaits01.bean.Employee;

public interface EmployeeMapper {
	    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
		//@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
		@MapKey("id")
		public Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);
		
		//返回一条记录的map；key就是列名，值就是对应的值
		public Map<String, Object> getEmpByIdReturnMap(Integer id);
		
		public List<Employee> getEmpsByLastNameLike(String lastName);
		
		public Employee getEmpByMap(Map<String, Object> map);
		
		//通过Param注解 明确指定参数的名字
		public Employee getEmpByIdAndLastName(@Param("id")Integer id,@Param("lastName")String lastName);
		
	    public Employee getEmpById(Integer id);

	    public Long addEmp(Employee employee);

	    public boolean updateEmp(Employee employee);

	    public void deleteEmpById(Integer id);

}
