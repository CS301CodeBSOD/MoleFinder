package cs310w10.MoleFinder.Controller;


import org.junit.Test;

import cs310w10.MoleFinder.Model.Picture;


import android.net.Uri;
import android.test.AndroidTestCase;

public class PictureControllerTest extends AndroidTestCase {

	PictureController pController;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		pController = new PictureController(getContext());
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		pController.deletePicture();
	}

	@Test
	public void testcreatePicture() {
		Picture picture = pController.createPicture(1000, "a new picture", null);
		assertEquals("a new picture", picture.getDescription());
	}
	
	public void testeditPicture(){
		Picture picture = pController.createPicture(1000, "a new picture", null);
		picture = pController.editPicture(5000, "an old picture", Uri.EMPTY);
		assertEquals(5000 , picture.getDate().getTime().getTime());
		assertEquals("an old picture", picture.getDescription());
	}
	
	public void testgetPictureFromId(){
		Picture picture = pController.createPicture(1000, "a new picture", null);
		Picture newpic = pController.getPictureFromId(picture.getId());
		assertEquals(newpic.getId(), picture.getId());
	}

}
