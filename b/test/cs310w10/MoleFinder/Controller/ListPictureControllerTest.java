package cs310w10.MoleFinder.Controller;


import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs310w10.MoleFinder.Model.Picture;

import android.test.AndroidTestCase;

public class ListPictureControllerTest extends AndroidTestCase {

	ListPictureController lpController;
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		lpController = new ListPictureController(getContext());
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetPictureById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllPictures() {
		ArrayList<Picture> pictures = lpController.getAllPictures();
		assertEquals();
	}

	@Test
	public void testGetListPictureFromMole() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteListPicture() {
		fail("Not yet implemented");
	}

}
