package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

public class PaymentFulfillResponse implements Response {
	public int payCheckCount;
	public int totalNetAmount;

	public PaymentFulfillResponse(int payCheckCount, int totalGrossAmount) {
		this.payCheckCount = payCheckCount;
		this.totalNetAmount = totalGrossAmount;
	}
}
