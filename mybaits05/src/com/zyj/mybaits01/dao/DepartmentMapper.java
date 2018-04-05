package com.zyj.mybaits01.dao;

import com.zyj.mybaits01.bean.Department;

public interface DepartmentMapper {
	public Department getDeptById(Integer id);

	public Department getDeptByIdPlus(Integer id);

	public Department getDeptByIdStep(Integer id);
}
