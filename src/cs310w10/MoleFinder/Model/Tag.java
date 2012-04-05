package cs310w10.MoleFinder.Model;

import cs310w10.MoleFinder.View.fView;

/**
 * @author James Helberg The Tag class contains data pertaining to each
 *         individual tag. Within each tag will be the id that is used in the
 *         database; the name, description, and location pulled from the
 *         database; and a array of picture ids
 */
@SuppressWarnings("rawtypes")
public class Tag extends fModel<fView> {
	/**
	 * The variables within the Tag class id - Tag id within the database name -
	 * name of this tag as defined by the user description - details defining
	 * the tag that will be shown visually with picture objects location -
	 * location of the tag as described by the user photoId - A list of photoId
	 * from another database for reference to this tag
	 */
	private int id;
	private String name;
	private String description;
	private String location;

	/**
	 * @param n_id
	 *            Creation method for tag allows user to set the id (useful for
	 *            when pulling from database)
	 */
	public Tag(int n_id) {
		id = n_id;
		name = "";
		description = "";
		location = "";
	}

	/**
	 * Default creation for the moles defaults the id to be -1
	 */
	public Tag() {
		id = -1;
		name = "";
		description = "";
		location = "";
	}

	/**
	 * @return id returns the id of the tag
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            set the id of the tag (should be reserved for a tag
	 *            factory/database setup function)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name return the name of the tag
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            set the name of the tag
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return description The description of the tag that the user provides
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            set the description of the tag
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param location
	 *            the location of the tag on the body as described by the user
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return location returns the location that the tag is located (as
	 *         previously described by the user)
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
