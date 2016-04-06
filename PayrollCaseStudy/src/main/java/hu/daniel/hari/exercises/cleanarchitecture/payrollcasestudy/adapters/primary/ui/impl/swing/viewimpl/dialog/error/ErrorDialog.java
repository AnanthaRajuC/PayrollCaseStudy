package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.error;

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

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.DefaultDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.error.ErrorDialogView;

public class ErrorDialog extends DefaultDialog<CloseableViewListener> implements ErrorDialogView {

	private JTextPane textPane;
	
	public ErrorDialog(JFrame parentFrame) {
		super(parentFrame);
		initUI();
		centerParent();
	}
	
	private void centerParent() {
		setLocationRelativeTo(getParent());
	}
	
	private void initUI() {
		setLocationRelativeTo(getParent());
		setModal(true);

		setBounds(100, 100, 450, 300);
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
						getListener().onCloseRequested();
					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}

	}

	@Override
	public void setModel(ErrorViewModel viewModel) {
		textPane.setText(viewModel.errorMessage);
		textPane.setCaretPosition(0);
	}

}
