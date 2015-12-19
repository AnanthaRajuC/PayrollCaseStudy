package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard.TimeCardFactory;

public class AddTimeCardUseCase extends TransactionalUseCase<AddTimeCardRequest> {

	private TimeCardFactory timeCardFactory;

	public AddTimeCardUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			TimeCardFactory timeCardFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.timeCardFactory = timeCardFactory;
	}

	@Override
	protected void executeInTransaction(AddTimeCardRequest request) {
		Employee employee = employeeGateway.getEmployee(request.employeeId);
		
		castHourlyPaymentClassification(employee.getPaymentClassification())
			.addTimeCard(createTimeCard(request));
	}

	private HourlyPaymentClassification castHourlyPaymentClassification(PaymentClassification paymentClassification) {
		if(paymentClassification instanceof HourlyPaymentClassification) {
			return (HourlyPaymentClassification) paymentClassification;
		} else {
			throw new TriedToAddTimeCardToNonHourlyEmployeeException();
		}
	}

	private TimeCard createTimeCard(AddTimeCardRequest request) {
		return timeCardFactory.timeCard(request.date, request.workingHoursQty);
	}
	
	public static class TriedToAddTimeCardToNonHourlyEmployeeException extends RuntimeException {
	}

	public static interface AddTimeCardUseCaseFactory {
		AddTimeCardUseCase addTimeCardUseCase();
	}
	
}
