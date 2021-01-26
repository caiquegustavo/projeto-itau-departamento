package com.department.repository.projection;

import lombok.Data;

@Data
public class DepartmentResume {

	private Long code;
	private String name;
	
	public DepartmentResume(Long code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

}
