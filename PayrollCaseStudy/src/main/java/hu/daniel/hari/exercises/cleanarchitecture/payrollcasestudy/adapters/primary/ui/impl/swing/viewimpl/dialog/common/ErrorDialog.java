package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.DefaultModalDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.error.ErrorDialogView;

public class ErrorDialog extends DefaultModalDialog<CloseableViewListener> implements ErrorDialogView {

	private JTextPane textPane;
	
	public ErrorDialog(JFrame parentFrame) {
		super(parentFrame, "Error");
		initUI();
	}
	
	@Override
	public void setModel(ErrorViewModel viewModel) {
		textPane.setText(viewModel.errorMessage);
		textPane.setCaretPosition(0);
	}

	private void initUI() {
		setLocationRelativeTo(getParent());
		setModal(true);

		setSize(450, 300);
		getContentPane().setLayout(new BorderLayout());

		JLabel lblNewLabel = new JLabel("Uncaugth exception:");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(new BorderLayout(0, 0));

				textPane = new JTextPane();
				textPane.setEditable(false);
				panel.add(textPane);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getViewListener().onCloseRequested();
					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}

	}
	
}
