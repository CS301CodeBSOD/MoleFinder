/**
 * 
 */
package cs310w10.MoleFinder.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import android.test.AndroidTestCase;
import android.R.string;
import android.text.Editable.Factory;

import cs310w10.MoleFinder.Controller.MoleFinderApplication;


/**
 * @author ryalls
 *
 */
public class MolesDataSourceTest extends AndroidTestCase
{
    private MolesDataSource source;
    private Mole testmole;
    
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {

    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSource#MolesDataSource(android.content.Context)}.
     */
    @Test
    public final void testMolesDataSource()
    {

        source = new MolesDataSource(MoleFinderApplication.getAppContext());
        assertNotNull(source);
        source.open();
        
        
        testmole = source.createMole("asdf", "testmole", "back");
        assertNotNull(testmole);
        assertEquals("asdf", testmole.getName());
        Mole pullmole = source.getMoleFromId(testmole.getId());
        assertEquals(pullmole.getName(), "test"); 
   
        testmole = source.editMole(testmole, "newtest", "newtestmole", "front");
        assertEquals(testmole.getName(), "newtest");
        pullmole = source.getMoleFromId(testmole.getId());
        assertEquals(pullmole.getName(), "newtest"); 
    
        
        int testid = testmole.getId();
        
        source.deleteMole(testmole);
        assertNull(source.getMoleFromId(testid).getName());
        
        String description = "description";
        String location = "location";
        source.createMole("one", description, location);
        source.createMole("two", description, location);
        source.createMole("three", description, location);

        ArrayList<Mole> moles = source.getAllMoles();
        assertEquals(moles.get(0).getName(), "one");
        assertEquals(moles.get(1).getName(), "two");
        assertEquals(moles.get(2).getName(), "three");

        Mole mole = source.getMoleFromId(1);
        assertEquals(mole.getName(), "two");

        source.deleteAllMoles();
        moles = source.getAllMoles();
        assertEquals(moles.get(0).getName(), null);
    
    }


    
    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSourceTesttest#testInsertPicture()}.
     */
    @Test
    public final void testTestInsertPicture()
    {

        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSourceTesttest#testDeletePicture()}.
     */
    @Test
    public final void testTestDeletePicture()
    {

        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSourceTesttest#testGetAllPictures()}.
     */
    @Test
    public final void testTestGetAllPictures()
    {

        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSourceTesttest#testInsertMolePicturePair()}.
     */
    @Test
    public final void testTestInsertMolePicturePair()
    {

        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSourceTesttest#testGetPhotoIdsFromeMole()}.
     */
    @Test
    public final void testTestGetPhotoIdsFromeMole()
    {

        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSourceTesttest#testGetListPictureFromMole()}.
     */
    @Test
    public final void testTestGetListPictureFromMole()
    {

        fail("Not yet implemented"); // TODO
    }



}
