package com.springboot.cruddemo.rest;

import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //inject Employee DAO using constructor injection
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService)
    {
        this.employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()
    {
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeesId}")
    public Employee getEmployee(@PathVariable int employeesId)
    {
        //check the studentId against list size


        Employee theEmployee =employeeService.findById(employeesId);
        if (theEmployee == null)
            throw new EmployeeNotFoundException("Employee id not found :"+theEmployee);

        return  theEmployee;
    }
    //add mapping for POST /customers - add new customer
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)
    {
        //set id to 0 this will  force a save of new item instead of update
        theEmployee.setId(0);

       employeeService.save(theEmployee);

        return theEmployee;
    }

    @PutMapping("/employees")
    public Employee updateCustomer(@RequestBody Employee theEmployee)
    {
        employeeService.save(theEmployee);
        return theEmployee;
    }
    @DeleteMapping("/employees/{employeesId}")
    public String deleteEmployee(@PathVariable int employeesId)
    {
        Employee theEmployee =employeeService.findById(employeesId);
        if (theEmployee == null)
            throw new EmployeeNotFoundException("theEmployee id not found :"+theEmployee);

        employeeService.deleteById(employeesId);

        return  "the deleted employee is :"+ theEmployee;
    }



}
