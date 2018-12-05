package com.score.es.first.escorefirst;

import com.score.es.first.escorefirst.repository.EmployeeRepository;
import com.score.es.first.escorefirst.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsCoreFirstApplicationTests {
    @Autowired
    EmployeeRepository employeesRepository;

    @Test
    public void contextLoads() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("Tom");
        employee.setLastName("jery");
        employee.setAge(123);
        employeesRepository.save(employee);
    }

}
