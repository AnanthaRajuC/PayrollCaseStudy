package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui;

import javax.inject.Inject;
import javax.swing.SwingUtilities;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.MainPanelUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.StatusBarUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameUI;

public class MainFrameUIImpl implements MainFrameUI {

	public final MainFrameWindow view;
	
	@Inject
	public MainFrameUIImpl(
			MainPanelUIImpl mainPanelUI,
			StatusBarUIImpl statusBarUI 
			) {
		view = new MainFrameWindow(mainPanelUI.view, statusBarUI.view);
	}
	
	@Override
	public void show() {
		SwingUtilities.invokeLater(() -> {
			view.setVisible(true);
		});
	}
	
}
