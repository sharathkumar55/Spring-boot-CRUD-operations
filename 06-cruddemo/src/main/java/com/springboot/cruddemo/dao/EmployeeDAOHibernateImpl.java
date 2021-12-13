package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {


    //define field for entity Manager
    private EntityManager entityManager;

    //setup Constructor Injection for entity manager
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager)
    {
        this.entityManager=theEntityManager;
    }



    @Override
    @Transactional
    public List<Employee> findAll() {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

        //execute query and get result set
        //List<Employee> employees  = theQuery.getResultList();

        //return the results
        return theQuery.getResultList();


    }

    @Override
    public Employee findById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        //Employee theEmployee = currentSession.get(Employee.class,theId);

        return currentSession.get(Employee.class,theId);;
    }

    @Override
    public void save(Employee theEmployee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theEmployee);



    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery =currentSession.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId",theId);
        //below code works for both update and delete
        theQuery.executeUpdate();


    }
}
