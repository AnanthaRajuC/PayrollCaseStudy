package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.ClosableView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;

/**
 * Don't closes by itself, just sends message to listener  
 * @author Dani
 *
 * @param <T>
 */
public class DefaultModalDialog<T extends CloseableViewListener> extends JDialog implements ClosableView<T> {

	private T listener;
	
	public DefaultModalDialog(
			JFrame parentFrame, String title
			) {
		super(parentFrame);
		setTitle(title);
		setModal(true);
		setSize(450, 360);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseRequested();
			}
		});
	}

	@Override
	public void setListener(T listener) {
		this.listener = listener;
	}
	
	protected T getListener() {
		return listener;
	}
	
	@Override
	public void close() {
		dispose();
	}

	protected void centerParent() {
		setLocationRelativeTo(getParent());
	}
	
	protected void setFocusField(JComponent field) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				field.requestFocusInWindow();
			}
		});
	}

}
