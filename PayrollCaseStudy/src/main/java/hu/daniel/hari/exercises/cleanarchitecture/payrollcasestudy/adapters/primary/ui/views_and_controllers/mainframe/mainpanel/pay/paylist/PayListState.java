package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.pay.paylist;

public class PayListState {
	public int itemCount;
	public boolean isEmpty;
	
	public PayListState(int itemCount, boolean isEmpty) {
		this.itemCount = itemCount;
		this.isEmpty = isEmpty;
	}
	
}
