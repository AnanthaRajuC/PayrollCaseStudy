package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request;

import java.time.LocalDate;

public class PaydayRequest implements Request {
	public LocalDate payDate;

	public PaydayRequest(LocalDate payDate) {
		this.payDate = payDate;
	}
}