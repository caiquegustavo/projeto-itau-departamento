package com.department.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.department.model.DepartmentEntity;
import com.department.resource.DepartmentResource;

import io.restassured.http.ContentType;

@WebMvcTest
public class DepartmentsControllerTest {

	@Autowired
	private DepartmentResource departmentResource;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.departmentResource);
	}
	
	@Mock
	private ResponseEntity<DepartmentEntity> departmentEntity;
	
	@Test
	public void sucessReturnSearchForCode() {

		when(this.departmentResource.searchForCode(1L))
			.thenReturn(departmentEntity);			
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/departments/{code}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void failedReturnNotFoundSearchForCode() {
		
		when(this.departmentResource.searchForCode(1L))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/departments/{code}", 5L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void failedBadRequestSearchForCode() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/departments/{code}", -1L)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.departmentResource, never()).searchForCode(-1L);
	}
	
	@Test
	public void sucessDelete() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/departments/{code}", 1L)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
		
		verify(this.departmentResource).remove(1L);
	}
	
	@Test
	public void failedNotFoundDelete() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/departments/{code}", 500L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void failedBadRequestDelete() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/departments/{code}", -1L)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.departmentResource, never()).remove(-1L);
	}
	
	@Test
	public void sucessReturnCreate() {
		
		DepartmentEntity departmentEntityRequest = new DepartmentEntity(1l, "Contas a Pagar", "Rua Casa Branca", "Mauá", "SP", "E.I.S");

		when(this.departmentResource.create(departmentEntityRequest, null));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.post("/departments")
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void failedBadRequestCreate() {
		
		DepartmentEntity departmentEntityRequest = new DepartmentEntity(null, "Contas a Pagar", "Rua Casa Branca", "Mauá", "SP", "E.I.S");

		when(this.departmentResource.create(departmentEntityRequest, null));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.post("/departments")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void sucessReturnUpdate() {
		
		DepartmentEntity departmentEntityRequest = new DepartmentEntity(1L, "Recursos Humanos", "Rua Mandai", "São Paulo", "SP", "Recuperação");

		when(this.departmentResource.update(1L, departmentEntityRequest));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.put("/departments/{code}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void failedBadRequestUpdate() {
		
		DepartmentEntity departmentEntityRequest = new DepartmentEntity(1L, null, "Rua Mandai", "São Paulo", "SP", "Recuperação");

		when(this.departmentResource.update(1L, departmentEntityRequest));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.put("/departments/{code}", 1L)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}

}

  


