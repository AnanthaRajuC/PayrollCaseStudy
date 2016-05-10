package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.MainPanelUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.StatusBarUIImpl;

@Singleton
public class MainFrameUIImpl implements MainFrameUI {

	public final MainFrameWindow view;
	
	@Inject
	public MainFrameUIImpl(
			MainPanelUIImpl mainPanelUI,
			StatusBarUIImpl statusBarUI 
			) {
		view = new MainFrameWindow(mainPanelUI.getView(), statusBarUI.getView());
	}
	
	@Override
	public void show() {
		view.showIt();
	}
	
}
