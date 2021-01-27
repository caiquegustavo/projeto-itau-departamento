package com.department.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity {
	
	@Id
	private Long code;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String address;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private String board;

}
