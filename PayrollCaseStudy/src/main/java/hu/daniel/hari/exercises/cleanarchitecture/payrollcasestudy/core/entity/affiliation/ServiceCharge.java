package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation;

import java.time.LocalDate;

public interface ServiceCharge {

	int getAmount();
	LocalDate getDate();
	
	void setAmount(int amount);
	void setDate(LocalDate date);
	
}
