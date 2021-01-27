package com.department;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.department.model.DepartmentEntity;
import com.department.repository.DepartmentRepository;
import com.department.repository.filter.DepartmentFilter;
import com.department.service.DepartmentService;

@SpringBootTest
class ProjectDepartmentItauApplicationTests {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmentRepository departmentRepository;
		
	private DepartmentFilter departmentFilter = new DepartmentFilter();
	
	DepartmentEntity departmentEntity = new DepartmentEntity();
	
	@Test
    public void saveDepartmentSucess() {
		
		departmentEntity.setCode(1L);
		departmentEntity.setName("Contas a Pagar");
		departmentEntity.setAddress("Rua Casa Branca");
		departmentEntity.setCity("Mauá");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("E.I.S");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.save(departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void saveDepartmentNameNullFailed() {
		
		departmentEntity.setCode(2L);
		departmentEntity.setName(null);
		departmentEntity.setAddress("Rua Santana");
		departmentEntity.setCity("Santo André");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Recuperação");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.save(departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void saveDepartmentAddressNullFailed() {
		
		departmentEntity.setCode(3L);
		departmentEntity.setName("Recursos Humanos");
		departmentEntity.setAddress(null);
		departmentEntity.setCity("São Caetano");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Negócios");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.save(departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void saveDepartmentCityNullFailed() {
		
		departmentEntity.setCode(4L);
		departmentEntity.setName("Contabilidade");
		departmentEntity.setAddress("Santa Monica");
		departmentEntity.setCity(null);
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Recuperação");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.save(departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void saveDepartmentStateNullFailed() {
		
		departmentEntity.setCode(5L);
		departmentEntity.setName("Desenvolvimento");
		departmentEntity.setAddress("Santa Monica");
		departmentEntity.setCity("São José");
		departmentEntity.setState(null);
		departmentEntity.setBoard("E.I.S");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.save(departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void saveDepartmentBoardNullFailed() {
		
		departmentEntity.setCode(6L);
		departmentEntity.setName("Recursos Humanos");
		departmentEntity.setAddress("Rua David Silva");
		departmentEntity.setCity("São Paulo");
		departmentEntity.setState("SP");
		departmentEntity.setBoard(null);
	   
	    DepartmentEntity departmentEntityResponse = departmentService.save(departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void saveDepartmentRepeatedValueFailed() {
		
		departmentEntity.setCode(1L);
		departmentEntity.setName("Contas a Pagar");
		departmentEntity.setAddress("Rua Casa Branca");
		departmentEntity.setCity("Mauá");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("E.I.S");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.save(departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	  
	@Test
    public void updateDepartmentSucess() {
		
		departmentEntity.setCode(1L);
		departmentEntity.setName("Compras");
		departmentEntity.setAddress("Rua jundiai");
		departmentEntity.setCity("São José");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Recuperação");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.update(departmentEntity.getCode(), departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void updateUnregisteredDepartmentFailed() {
		
		departmentEntity.setCode(900L);
		departmentEntity.setName("Recursos Humanos");
		departmentEntity.setAddress("Rua Sabiá");
		departmentEntity.setCity("Mauá");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Negócios");
	   
	    DepartmentEntity departmentEntityResponse = departmentService.update(departmentEntity.getCode(), departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void updateDepartmentNameNullFailed() {
		
		departmentEntity.setCode(2L);
		departmentEntity.setName(null);
		departmentEntity.setAddress("Rua Santana");
		departmentEntity.setCity("Santo André");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Recuperação");
	   
		DepartmentEntity departmentEntityResponse = departmentService.update(departmentEntity.getCode(), departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void updateDepartmentAddressNullFailed() {
		
		departmentEntity.setCode(3L);
		departmentEntity.setName("Recursos Humanos");
		departmentEntity.setAddress(null);
		departmentEntity.setCity("São Caetano");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Negócios");
	   
		DepartmentEntity departmentEntityResponse = departmentService.update(departmentEntity.getCode(), departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void updateDepartmentCityNullFailed() {
		
		departmentEntity.setCode(4L);
		departmentEntity.setName("Contabilidade");
		departmentEntity.setAddress("Santa Monica");
		departmentEntity.setCity(null);
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Recuperação");
	   
		DepartmentEntity departmentEntityResponse = departmentService.update(departmentEntity.getCode(), departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void updateDepartmentStateNullFailed() {
		
		departmentEntity.setCode(5L);
		departmentEntity.setName("Desenvolvimento");
		departmentEntity.setAddress("Santa Monica");
		departmentEntity.setCity("São José");
		departmentEntity.setState(null);
		departmentEntity.setBoard("E.I.S");
	   
		DepartmentEntity departmentEntityResponse = departmentService.update(departmentEntity.getCode(), departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void updateDepartmentBoardNullFailed() {
		
		departmentEntity.setCode(6L);
		departmentEntity.setName("Recursos Humanos");
		departmentEntity.setAddress("Rua David Silva");
		departmentEntity.setCity("São Paulo");
		departmentEntity.setState("SP");
		departmentEntity.setBoard(null);
	   
		DepartmentEntity departmentEntityResponse = departmentService.update(departmentEntity.getCode(), departmentEntity);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void searchDepartmentSucess() {
		
		departmentEntity.setCode(1L);
		departmentEntity.setName("Compras");
		departmentEntity.setAddress("Rua jundiai");
		departmentEntity.setCity("São José");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Recuperação");
	   
		DepartmentEntity departmentEntityResponse = departmentRepository.findById(1L).orElse(null);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }
	
	@Test
    public void searchUnregisteredDepartmentFailed() {
		
		departmentEntity.setCode(900L);
		departmentEntity.setName("Recursos Humanos");
		departmentEntity.setAddress("Rua Sabiá");
		departmentEntity.setCity("Mauá");
		departmentEntity.setState("SP");
		departmentEntity.setBoard("Negócios");
	   
		DepartmentEntity departmentEntityResponse = departmentRepository.findById(900L).orElse(null);
   
        assertEquals(departmentEntity, departmentEntityResponse);
    }

}
