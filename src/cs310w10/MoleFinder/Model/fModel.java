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

	/**
	 * Super Creation function
	 */
	public fModel() {
		views = new ArrayList<V>();
	}

	/**
	 * adds a extended version of fView to a list of currently active views associated with this model
	 * @param view extends fView -  the view that has been made active
	 */
	public void addView(V view) {
		if (!views.contains(view)) {
			views.add(view);
		}
	}

	/**
	 * Removes a view from the list that has been deleted / dropped
	 * @param view extends fView - the view that has been deleted
	 */
	public void deleteView(V view) {
		views.remove(view);
	}

	/**
	 * Function call that should be made after any update of the model object.
	 * calls update in the fViews which will update the contents of the associated
	 * active views
	 */
	public void notifyViews() {
		for (V view : views) {
			view.update(this);
		}
	}
}
