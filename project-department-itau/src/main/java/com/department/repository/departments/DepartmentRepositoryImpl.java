package com.department.repository.departments;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.department.model.DepartmentEntity;
import com.department.model.DepartmentEntity_;
import com.department.repository.filter.DepartmentFilter;
import com.department.repository.projection.DepartmentResume;



public class DepartmentRepositoryImpl implements DepartmentRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<DepartmentResume> resume(DepartmentFilter departmentFilter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DepartmentResume> criteria = criteriaBuilder.createQuery(DepartmentResume.class);
		Root<DepartmentEntity> root = criteria.from(DepartmentEntity.class);
		
		criteria.select(criteriaBuilder.construct(DepartmentResume.class, 
				root.get(DepartmentEntity_.code), 
				root.get(DepartmentEntity_.name)));
		
		Predicate[] predicates = createRestrictions(departmentFilter, criteriaBuilder, root);
		criteria.where(predicates);
		
		TypedQuery<DepartmentResume> query = entityManager.createQuery(criteria);
		addPageRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(departmentFilter));
	}
	
	private Predicate[] createRestrictions(DepartmentFilter departmentFilter, CriteriaBuilder criteriaBuilder,
			Root<DepartmentEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(departmentFilter.getName())) {
			predicates.add(criteriaBuilder.like(
					criteriaBuilder.lower(root.get(DepartmentEntity_.name)), "%" + departmentFilter.getName().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(departmentFilter.getCity())) {
			predicates.add(criteriaBuilder.like(
					criteriaBuilder.lower(root.get(DepartmentEntity_.city)), "%" + departmentFilter.getCity().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(departmentFilter.getAddress())) {
			predicates.add(criteriaBuilder.like(
					criteriaBuilder.lower(root.get(DepartmentEntity_.address)), "%" + departmentFilter.getAddress().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPageRestrictions(TypedQuery<?> query, Pageable pageable) {
		
		int currentPage = pageable.getPageNumber();
		int totalRegistrationsByPage = pageable.getPageSize();
		int firstPageRegister = currentPage * totalRegistrationsByPage;
		
		query.setFirstResult(firstPageRegister);
		query.setMaxResults(totalRegistrationsByPage);
	}
	
	private Long total(DepartmentFilter departmentFilter) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<DepartmentEntity> root = criteriaQuery.from(DepartmentEntity.class);
		
		Predicate[] predicates = createRestrictions(departmentFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}
