package com.department.repository.departments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.department.repository.filter.DepartmentFilter;
import com.department.repository.projection.DepartmentResume;

public interface DepartmentRepositoryQuery {

	public Page<DepartmentResume> resume(DepartmentFilter departmentFilter, Pageable pageable);
}
