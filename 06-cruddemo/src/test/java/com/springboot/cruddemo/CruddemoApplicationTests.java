package com.springboot.cruddemo;

import com.springboot.cruddemo.rest.EmployeeRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CruddemoApplicationTests {

	@Autowired
	private EmployeeRestController employeeRestController;
	@Test
	void contextLoads() throws Exception{
		assertThat(employeeRestController).isNotNull();
	}

}
