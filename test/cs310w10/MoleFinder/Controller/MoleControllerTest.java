package cs310w10.MoleFinder.Controller;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import android.test.AndroidTestCase;
import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.Picture;

public class MoleControllerTest extends AndroidTestCase {

	MoleController mController;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		mController = new MoleController(getContext());
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		mController.deleteMole();
	}

	@Test
	public void testCreateMole() {
		mController.createMole("newmole", "a new mole", "arm");
		Mole mole = mController.getMole();
		assertEquals("newmole", mole.getName());
		assertEquals("a new mole", mole.getDescription());
		assertEquals("arm", mole.getLocation());
	}

	@Test
	public void testDeleteMole() {
		mController.createMole("newmole", "a new mole", "arm");
		Mole mole = mController.getMole();
		int id = mole.getId();
		mController.deleteMole();
		mController.getMoleFromId(id);
		mole = mController.getMole();
		assertEquals(null, mole);
	}

	@Test
	public void testEditMole() {
		mController.createMole("newmole", "a new mole", "arm");
		Mole mole = mController.getMole();
		mController.editMole("oldmole", "an old mole", "head");
		mole = mController.getMole();
		assertEquals("oldmole", mole.getName());
		assertEquals("an old mole", mole.getDescription());
		assertEquals("head", mole.getLocation());
	}

	@Test
	public void testGetMoleFromId() {
		mController.createMole("newmole", "a new mole", "arm");
		Mole mole = mController.getMole();
		int id = mole.getId();
		MoleController newController = new MoleController(getContext());
		newController.getMoleFromId(id);
		mole = newController.getMole();
		assertEquals(id, mole.getId());
	}

	@Test
	public void testAssociateMoleWithPicture() {
		mController.createMole("newmole", "a new mole", "arm");
		Mole mole = mController.getMole();
		PictureController pController = new PictureController(getContext());
		pController.createPicture(1000, "a new picture", null);
		Picture picture = pController.getPicture();
		mController.associateMoleWithPicture(picture.getId());
		ArrayList<Integer> photoIds = mController.getPhotoIdsFromeMole(mole
				.getId());
		assertEquals(picture.getId(), (int) photoIds.get(0));

	}

}
