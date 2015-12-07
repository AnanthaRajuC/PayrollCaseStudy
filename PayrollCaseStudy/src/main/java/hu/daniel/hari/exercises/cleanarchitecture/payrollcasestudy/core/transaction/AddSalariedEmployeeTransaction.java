package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;


public class AddSalariedEmployeeTransaction extends AddEmployeeTransaction {

	private int monthlySalary;

	public AddSalariedEmployeeTransaction(PayrollDatabase payrollDatabase, int employeeId, String name, String address, int monthlySalary) {
		super(payrollDatabase, employeeId, name, address);
		this.monthlySalary = monthlySalary;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return payrollDatabase.create().salariedPaymentClassification(monthlySalary);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return payrollDatabase.create().monthlyPaymentSchedule();
	}

}
