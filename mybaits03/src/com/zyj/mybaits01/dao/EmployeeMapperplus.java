package com.zyj.mybaits01.dao;

import java.util.List;

import com.zyj.mybaits01.bean.Employee;

public interface EmployeeMapperplus {
	public Employee getEmpById(Integer id);

	public Employee getEmpAndDept(Integer id);

	public Employee getEmpByIdStep(Integer id);

	public List<Employee> getEmpsByDeptId(Integer deptId);
}
