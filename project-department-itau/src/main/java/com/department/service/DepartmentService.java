package com.department.service;

import java.awt.print.Pageable;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.department.model.DepartmentEntity;
import com.department.repository.DepartmentRepository;
import com.department.service.exception.DepartmentAlreadyRegisteredException;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public DepartmentEntity save(DepartmentEntity departmentEntity) {
		
		DepartmentEntity departmentEntityCompare = departmentRepository.findById(departmentEntity.getCode()).orElse(null);
		 
		if(departmentEntityCompare != null) {
			throw new DepartmentAlreadyRegisteredException();
		}
		
		return departmentRepository.save(departmentEntity);	
	}
	
	public DepartmentEntity update(Long code, DepartmentEntity departmentEntity) {
		
		DepartmentEntity departmentEntitySaved = departmentRepository.findById(code).orElse(null);
		
		if(departmentEntitySaved == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(departmentEntity, departmentEntitySaved, "code");
		return departmentRepository.save(departmentEntitySaved);
	}
	
	public void delete(Long code) {
		
		DepartmentEntity departmentEntity = departmentRepository.findById(code).orElse(null);
		
		if(departmentEntity == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		departmentRepository.delete(departmentEntity);
	}
	
	public Page<DepartmentEntity> resumir(Pageable pageable) {
		
		List<DepartmentEntity> departs = departmentRepository.findAll();
		Long a = 10L;
		return new PageImpl<>(departs);
	}

}
