/**
 * 
 */
package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import android.test.AndroidTestCase;


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
    @Before
    public void setUp() throws Exception
    {
            source = new MolesDataSource(MoleFinderApplication.getAppContext());
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MolesDataSource#MolesDataSource(android.content.Context)}.
     */
    @Test
    public final void testMolesDataSource()
    {
        assertNotNull(source);
    }
    
    @Test
    public final void testCreateMole()
    {   
        testmole = source.createMole("asdf", "testmole", "back");
        assertNotNull(testmole);
        assertEquals("asdf", testmole.getName());
        assertEquals("testmole", testmole.getDescription());
        assertEquals("back", testmole.getLocation());
        Mole pullmole = source.getMoleFromId(testmole.getId());
        assertSame(pullmole, testmole); 
    }
    
    @Test
    public final void testEditMole()
    { 
        testmole = source.editMole(testmole, "newtest", "newtestmole", "front");
        assertEquals("newtest", testmole.getName());
        assertEquals("newtestmole", testmole.getDescription());
        assertEquals("front", testmole.getLocation());;
        Mole pullmole = source.getMoleFromId(testmole.getId());
        assertSame(pullmole, testmole); 
    }
    
    @Test
    public final void testDeleteMole()
    { 
        testmole = source.editMole(testmole, "newtest", "newtestmole", "front");
        int testid = testmole.getId();
        
        source.deleteMole(testmole);
        assertNull(source.getMoleFromId(testid).getName());
        
    }
    
    @Test
    public final void testGetAllMoles()
    { 
        String description = "description";
        String location = "location";
        source.createMole("one", description, location);
        source.createMole("two", description, location);
        source.createMole("three", description, location);

        ArrayList<Mole> moles = source.getAllMoles();
        assertEquals(moles.get(-3).getName(), "one");
        assertEquals(moles.get(-2).getName(), "two");
        assertEquals(moles.get(-1).getName(), "three");

    }
    
    @Test
    public final void testDeleteAllMoles()
    { 
        Mole mole = source.getMoleFromId(-3);
        assertEquals(mole.getName(), "one");

        source.deleteAllMoles();
        ArrayList<Mole> moles = source.getAllMoles();
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
