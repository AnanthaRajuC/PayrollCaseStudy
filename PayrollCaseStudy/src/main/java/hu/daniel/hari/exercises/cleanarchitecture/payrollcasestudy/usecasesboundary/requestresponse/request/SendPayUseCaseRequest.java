package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;

public class SendPayUseCaseRequest implements Request {

	public List<PayCheck> payChecks;

	public SendPayUseCaseRequest(List<PayCheck> payChecks) {
		this.payChecks = payChecks;
	}
}