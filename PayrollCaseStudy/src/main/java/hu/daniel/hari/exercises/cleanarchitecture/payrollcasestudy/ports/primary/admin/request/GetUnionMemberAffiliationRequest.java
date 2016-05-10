package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request;

public class GetUnionMemberAffiliationRequest implements Request {
	public int employeeId;
	public GetUnionMemberAffiliationRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}