package cs310w10.MoleFinder.Model;

/// Mostly copied from Abram Hindle's FillerCreep app.

import java.util.ArrayList;

import cs310w10.MoleFinder.View.fView;

/**
 * 
 * @author James Helberg
 * 
 * This is the abstract class for the Models. It allows for ease of updating
 * of the associated views of this model
 * 
 * @param <V>
 */
public class fModel<V extends fView> {
	private final ArrayList<V> views;

	public fModel() {
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
