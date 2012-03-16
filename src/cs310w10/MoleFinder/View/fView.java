package cs310w10.MoleFinder.View;

/**
 * 
 * @author James Helberg
 * 
 * Abstract class that will be extended by views that are associated
 * with specific models
 *
 * @param <M>
 */
public interface fView<M> {
	/**
	 * update is an abstract class that will be implemented by the 
	 * views that extend this abstract.
	 * @param model
	 */
	public void update(M model);
}
