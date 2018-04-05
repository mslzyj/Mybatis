package com.zyj.mybaits01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zyj.mybaits01.bean.Employee;

public interface EmployeeMapperDynamicSQL {
	public List<Employee> getEmpsTestInnerParameter(Employee employee);

	// Я�����ĸ��ֶβ�ѯ�����ʹ�������ֶε�ֵ
	public List<Employee> getEmpsByConditionIf(Employee employee);

	public List<Employee> getEmpsByConditionTrim(Employee employee);

	public List<Employee> getEmpsByConditionChoose(Employee employee);

	public void updateEmp(Employee employee);

	// ��ѯԱ��id'�ڸ��������е�
	public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

	public void addEmps(@Param("emps") List<Employee> emps);

}

