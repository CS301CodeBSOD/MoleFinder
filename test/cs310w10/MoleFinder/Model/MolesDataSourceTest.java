/**
 * 
 */
package cs310w10.MoleFinder.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
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

    @Before
    public void setUp() throws Exception
    {
        source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        source.deleteAllMoles();
        source.close();
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
        source.close();
    }
    
    @Test
    public final void testCreateMole()
    {
        source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        testmole = source.createMole("asdf", "testmole", "back");
       
        assertNotNull(testmole);
        assertEquals("asdf", testmole.getName());
        assertEquals("testmole", testmole.getDescription());
        assertEquals("back", testmole.getLocation());

        Mole pullmole = source.getMoleFromId(testmole.getId());
        assertEquals(pullmole.getName(), "test"); 
        
        source.close();
    }
    
    @Test
    public final void testEditMole()
    {
        source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        testmole = source.createMole("asdf", "testmole", "back");
        
        Mole newmole = source.editMole(testmole, "newtest", "newtestmole", "front");
        assertEquals(newmole.getName(), "newtest");
        Mole pullmole = source.getMoleFromId(testmole.getId());
        assertEquals(pullmole.getName(), "newtest"); 
        source.close();
    }
    
    @Test
    public final void testDeleteMole()
    {    
        source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        testmole = source.createMole("asdf", "testmole", "back");
        
        int testid = testmole.getId();

        assertEquals(source.getMoleFromId(testid).getName(), "asdf");
        assertEquals(source.getMoleFromId(testid).getDescription(), "testmole");
        assertEquals(source.getMoleFromId(testid).getLocation(), "back");
        
        source.deleteMole(testmole);
        assertEquals(source.getMoleFromId(testid).getName(), "");
        assertEquals(source.getMoleFromId(testid).getDescription(), "");
        assertEquals(source.getMoleFromId(testid).getLocation(), "");
        source.close();
    }
    
    @Test
    public final void testGetAllMoles()
    {  
        source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
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
        source.close();

    }
    
    @Test
    public final void testDeleteAllMoles()
    {     
        source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        source.deleteAllMoles();
        ArrayList<Mole> moles = source.getAllMoles();
        assertEquals(moles.get(0).getName(), null);
        source.close();
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
