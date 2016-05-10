package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.UIImplConstants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.StatusBarPanel;

public class MainFrameWindow extends JFrame {
	private JPanel mainPanelHolder;
	private JPanel statusBarHolder;
	
	public MainFrameWindow(
			MainPanel mainPanel, 
			StatusBarPanel statusBarPanel
			) {
		initUI();
		mainPanelHolder.add(mainPanel);
		statusBarHolder.add(statusBarPanel);
	}

	
	
	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(UIImplConstants.APP_TITLE);
		setSize(850, 500);
		setLocationByPlatform(true);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(contentPane);
		
		mainPanelHolder = new JPanel();
		centerPanel.add(mainPanelHolder);
		mainPanelHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		statusBarHolder = new JPanel();
		bottomPanel.add(statusBarHolder, BorderLayout.NORTH);
		statusBarHolder.setLayout(new BorderLayout(0, 0));
	}



	public void showIt() {
		SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});		
	}

}
