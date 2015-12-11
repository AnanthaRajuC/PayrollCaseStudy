package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao.JPAEmployeeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy.EmployeeProxyFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPAPayrollDatabase implements PayrollDatabase {

	@Inject private JPAEntityFactory jpaEntityFactory;
	@Inject private JPAEmployeeDao jPAEmployeeDao;
	@Inject private TransactionProvider transactionProvider;
	@Inject private EmployeeProxyFactory employeeProxyFactory;
	
	@Override
	public EntityFactory factory() {
		return jpaEntityFactory;
	}

	@Inject
	public JPAPayrollDatabase(JPAEmployeeDao jPAEmployeeDao, JPAEntityFactory jpaEntityFactory) {
		this.jPAEmployeeDao = jPAEmployeeDao;
		this.jpaEntityFactory = jpaEntityFactory;
	}

	@Override
	public EntityTransaction getTransaction() {
		return transactionProvider.getTransaction();
	}
	
	@Override
	public void addEmployee(Employee employee) {
		jPAEmployeeDao.persist(((EmployeeProxy) employee).getJpaEmployee());
	}

	@Override
	public Employee getEmployee(int employeeId) {
		return convertNullable(jPAEmployeeDao.find(employeeId));
	}
	
	@Override
	public Collection<Employee> getAllEmployees() {
		return convert(jPAEmployeeDao.findAll());
	}

	@Override
	public void deleteEmployee(int employeeId) {
		jPAEmployeeDao.delete(employeeId);
	}
	
	@Override
	public void clearDatabase() {
		jPAEmployeeDao.deleteAll();
	}

	private Collection<Employee> convert(List<JPAEmployee> findAll) {
		return findAll
				.stream()
				.map(jPAEmployee -> convertNullable(jPAEmployee))
				.collect(Collectors.toList());
	}

	private Employee convertNullable(JPAEmployee jpaEmployee) {
		return jpaEmployee == null ? null : employeeProxyFactory.create(jpaEmployee);
	}

	@Override
	public int getEmployeeIdByUnionMemberId(int unionMemberId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
