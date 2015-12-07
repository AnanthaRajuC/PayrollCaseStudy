package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;

public class SalariedPaymentClassificationImpl extends SalariedPaymentClassification {

	private int monthlySalary;

	public SalariedPaymentClassificationImpl(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public int getMonthlySalary() {
		return monthlySalary;
	}

	@Override
	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

}
