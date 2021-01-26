package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.model.DepartmentEntity;
import com.department.repository.departments.DepartmentRepositoryQuery;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>, DepartmentRepositoryQuery{

}
