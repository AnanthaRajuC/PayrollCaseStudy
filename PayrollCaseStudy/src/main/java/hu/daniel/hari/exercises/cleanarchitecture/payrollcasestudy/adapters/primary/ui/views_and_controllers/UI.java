package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers;

import javax.inject.Inject;

public abstract class UI<V extends View, C extends Controller<? super V>> {

	protected final C controller;
	private V view;

	public UI(C controller) {
		this.controller = controller;
	}

	@Inject
	private void init() {
		view = createView();
		controller.setView(view);
	}

	protected abstract V createView();
	
	public V getView() {
		return view;
	}
}

