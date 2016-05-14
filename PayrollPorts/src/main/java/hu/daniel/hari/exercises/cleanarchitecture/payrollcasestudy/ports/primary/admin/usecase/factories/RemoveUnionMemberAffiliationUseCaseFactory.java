package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;

public interface RemoveUnionMemberAffiliationUseCaseFactory {
	CommandUseCase<RemoveUnionMemberAffiliationRequest> removeUnionMemberAffiliationUseCase();
}