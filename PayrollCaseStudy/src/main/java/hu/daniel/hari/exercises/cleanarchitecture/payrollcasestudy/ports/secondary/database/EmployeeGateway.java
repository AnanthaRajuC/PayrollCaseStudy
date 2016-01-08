package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database;

import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;

public interface EmployeeGateway {
	
	/**
	 * @throws NoSuchEmployeeException
	 */
	Employee findById(int employeeId);
	/**
	 * @throws NoSuchEmployeeException
	 */
	int findEmployeeIdByUnionMemberId(int unionMemberId);
	
	Collection<Employee> findAll();
	
	void addNew(Employee employee);
	
	void deleteById(int employeeId);
	
	void deleteAll();
	
	public static class NoSuchEmployeeException extends RuntimeException {}

}
