package cs310w10.MoleFinder.Model;

/// Mostly copied from Abram Hindle's FillerCreep app.

import java.util.ArrayList;

import cs310w10.MoleFinder.View.ViewActivity;

public class MoleModel<V extends ViewActivity> {
	private final ArrayList<V> views;

	public MoleModel() {
		views = new ArrayList<V>();
	}

	public void addView(V view) {
		if (!views.contains(view)) {
			views.add(view);
		}
	}

	public void deleteView(V view) {
		views.remove(view);
	}

	public void notifyViews() {
		for (V view : views) {
			view.update(this);
		}
	}
}
