package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize;

import javax.persistence.Entity;

/** FIXME: used shorter name for psql column size limit **/
@Entity(name="SalariedJPAPaymentClass")
public class SalariedJPAPaymentClassification extends JPAPaymentClassification {
	
	private int monthlySalary;

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
}
