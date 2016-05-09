package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.error.UncaugthExceptionHandler;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.AddEmployeeUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addtimecard.AddTimeCardUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addunionmemberaffiliation.AddUnionMemberUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common.ConfirmDialogUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common.ErrorDialogUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardUI.AddTimeCardUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberController.AddUnionMemberControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberUI.AddUnionMemberUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.error.ErrorDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.AddUnionMemberAffiliationUseCase.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.GetUnionMemberAffiliationUseCase.GetUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.RemoveUnionMemberAffiliationUseCase.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod.ChangeToAbstractPaymentMethodUseCase.ChangeToAbstractPaymentMethodUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase.PaymentFulfillUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase.PayListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.UpdateTimeCardUseCase.UpdateTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;

public class SwingUIModule extends AbstractModule {
	private UseCaseFactories useCaseFactories;
	private EventBus eventBus;

	public SwingUIModule(UseCaseFactories useCaseFactories) {
		this.useCaseFactories = useCaseFactories;
		eventBus = createEventBus();
	}
	
	private EventBus createEventBus() {
		return new EventQueueAsyncEventBus();
	}

	@Override
	protected void configure() {
		bind(UseCaseFactories.class).toInstance(useCaseFactories);
		bind(EventBus.class).toInstance(eventBus);
		bind(UncaugthExceptionHandler.class).asEagerSingleton();
		bindUseCaseFactories();
		bindUIs();
		installAssistedFactories();
	}

	private void bindUseCaseFactories() {
		bind(DeleteEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(GetEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(ListEmployeesUseCaseFactory.class).toInstance(useCaseFactories);
		bind(PaymentFulfillUseCaseFactory.class).toInstance(useCaseFactories);
		bind(PayListUseCaseFactory.class).toInstance(useCaseFactories);
		bind(AddEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(AddTimeCardUseCaseFactory.class).toInstance(useCaseFactories);
		bind(UpdateTimeCardUseCaseFactory.class).toInstance(useCaseFactories);
		bind(ChangeToAbstractPaymentMethodUseCaseFactory.class).toInstance(useCaseFactories);
		bind(GetUnionMemberAffiliationUseCaseFactory.class).toInstance(useCaseFactories);
		bind(AddUnionMemberAffiliationUseCaseFactory.class).toInstance(useCaseFactories);
		bind(RemoveUnionMemberAffiliationUseCaseFactory.class).toInstance(useCaseFactories);
	}
	
	private void bindUIs() {
		bind(new TypeLiteral<AddEmployeeUI<?>>(){}).to(AddEmployeeUIImpl.class);
		bind(new TypeLiteral<ErrorDialogUI<?>>(){}).to(ErrorDialogUIImpl.class);
		bind(ConfirmDialogUI.class).to(ConfirmDialogUIImpl.class);
		bind(MainFrameUI.class).to(MainFrameUIImpl.class);
	}
	
	private void installAssistedFactories() {
		install(new FactoryModuleBuilder().implement(new TypeLiteral<AddTimeCardUI<? extends AddTimeCardView>>() {}, AddTimeCardUIImpl.class).build(AddTimeCardUIFactory.class));
		install(new FactoryModuleBuilder().implement(new TypeLiteral<AddUnionMemberUI<? extends AddUnionMemberView>>() {}, AddUnionMemberUIImpl.class).build(AddUnionMemberUIFactory.class));
		install(new FactoryModuleBuilder().build(AddTimeCardControllerFactory.class));
		install(new FactoryModuleBuilder().build(AddUnionMemberControllerFactory.class));
	}
	
}
