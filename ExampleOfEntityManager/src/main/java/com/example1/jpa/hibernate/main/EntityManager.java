package com.example1.jpa.hibernate.main;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example1.jpa.hibernate.model.Employee;


public class EntityManager {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		javax.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("Starting Transaction");
		((javax.persistence.EntityManager) entityManager).getTransaction().begin();
		Employee employee = new Employee();
		employee.setName("dhrumil vyas");
		
		System.out.println("Saving Employee to Database");

		((javax.persistence.EntityManager) entityManager).persist(employee);
		((javax.persistence.EntityManager) entityManager).getTransaction().commit();
		System.out.println("Generated Employee ID = " + employee.getEmployeeId());

		// get an object using primary key.
		Employee emp = ((javax.persistence.EntityManager) entityManager).find(Employee.class, employee.getEmployeeId());
		System.out.println("got object " + emp.getName() + " " + emp.getEmployeeId());

		// get all the objects from Employee table
		@SuppressWarnings("unchecked")
		List<Employee> listEmployee = ((javax.persistence.EntityManager) entityManager).createQuery("SELECT e FROM Employee e").getResultList();

		if (listEmployee == null) {
			System.out.println("No employee found . ");
		} else {
			for (Employee empl : listEmployee) {
				System.out.println("Employee name= " + empl.getName() + ", Employee id " + empl.getEmployeeId());
			}
		}
		// remove and entity
		((javax.persistence.EntityManager) entityManager).getTransaction().begin();
		System.out.println("Deleting Employee with ID = " + emp.getEmployeeId());
		((javax.persistence.EntityManager) entityManager).remove(emp);
		((javax.persistence.EntityManager) entityManager).getTransaction().commit();

		// close the entity manager
		entityManager.close();
		entityManagerFactory.close();

	

		
	}


}
