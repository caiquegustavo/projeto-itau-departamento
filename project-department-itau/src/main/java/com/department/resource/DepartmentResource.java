package com.department.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.department.event.ResourceCreatedEvent;
import com.department.model.DepartmentEntity;
import com.department.repository.DepartmentRepository;
import com.department.repository.filter.DepartmentFilter;
import com.department.repository.projection.DepartmentResume;
import com.department.service.DepartmentService;

@CrossOrigin
@RestController
@RequestMapping("/departments")
public class DepartmentResource {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@PostMapping
	public ResponseEntity<DepartmentEntity> create(@Valid @RequestBody DepartmentEntity departmentEntity, HttpServletResponse httpServletResponse){
		
		departmentService.save(departmentEntity);
		
		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, httpServletResponse, departmentEntity.getCode()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(departmentEntity);
	}
	
	@PutMapping("/{code}")
	public ResponseEntity<DepartmentEntity> update(@PathVariable Long code, @Valid @RequestBody DepartmentEntity departmentEntity) {
		
		DepartmentEntity departmentEntitySaved = departmentService.update(code, departmentEntity);
		
		return ResponseEntity.ok(departmentEntitySaved);
	}
	
	@GetMapping(params = "resume")
	public Page<DepartmentResume> resume(DepartmentFilter departmentFilter, Pageable pageable) {
		return departmentRepository.resume(departmentFilter, pageable);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<DepartmentEntity> searchForCode(@PathVariable Long code) {
		
		DepartmentEntity departmentEntity = departmentRepository.findById(code).orElse(null);
		
		return departmentEntity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(departmentEntity);
	}
	
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long code) {
		
		departmentService.delete(code);
	}
	
}
