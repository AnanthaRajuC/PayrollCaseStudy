package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class InMemoryPayrollDatabase implements PayrollDatabase {

	private Map<Integer, Employee> employeesById = new HashMap<>();

	@Override
	public void addEmployee(Employee employee) {
		employeesById.put(employee.getId(), employee);
	}

	@Override
	public Employee getEmployee(int employeeId) {
		return employeesById.get(employeeId);
	}

	@Override
	public Collection<Employee> getAllEmployees() {
		return new ArrayList<>(employeesById.values());
	}
	
	@Override
	public void deleteAllEmployees() {
		employeesById.clear();
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeesById.remove(employeeId);
	}

	@Override
	public EntityTransaction createTransaction() {
		return new DummyTransaction();
	}

}
