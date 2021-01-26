package com.department.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartmentEntity_.class)
public abstract class DepartmentEntity_ {
	
	public static volatile SingularAttribute<DepartmentEntity, Long> code;
	public static volatile SingularAttribute<DepartmentEntity, String> name;
	public static volatile SingularAttribute<DepartmentEntity, String> address;
	public static volatile SingularAttribute<DepartmentEntity, String> city;
	public static volatile SingularAttribute<DepartmentEntity, String> state;
	public static volatile SingularAttribute<DepartmentEntity, String> board;

}
