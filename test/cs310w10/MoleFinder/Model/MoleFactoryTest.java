/**
 * 
 */
package cs310w10.MoleFinder.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.View.EditMoleViewActivity;


/**
 * @author ryalls
 *
 */
public class MoleFactoryTest
{

    private MolesDataSource factory;
    private Mole testmole;
    
    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MoleFactory#MoleFactory(android.database.sqlite.SQLiteDatabase)}.
     */
    @Test
    public final void testMoleFactory()
    {
        factory = new MolesDataSource(MoleFinderApplication.getAppContext());
        assertNotNull(factory);
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MoleFactory#createMole(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testCreateMole()
    {
       testmole = factory.createMole("test", "testmole", "back");
       assertNotNull(testmole);
       assertEquals(testmole.getName(), "test");
       Mole pullmole = factory.getMoleFromId(testmole.getId());
       assertEquals(pullmole.getName(), "test"); 

    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MoleFactory#editMole(cs310w10.MoleFinder.Model.Mole, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testEditMole()
    {
        testmole = factory.editMole(testmole, "newtest", "newtestmole", "front");
        assertEquals(testmole.getName(), "newtest");
        Mole pullmole = factory.getMoleFromId(testmole.getId());
        assertEquals(pullmole.getName(), "newtest"); 
        
    }
    
    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MoleFactory#deleteMole(cs310w10.MoleFinder.Model.Mole)}.
     */
    @Test
    public final void testDeleteMole()
    {
        int testid = testmole.getId();
        
        factory.deleteMole(testmole);
        assertNull(factory.getMoleFromId(testid).getName());
        
    }
    
    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MoleFactory#getAllMoles()}.
     */
    @Test
    public final void testGetAllMoles()
    {

    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MoleFactory#deleteAllMoles()}.
     */
    @Test
    public final void testDeleteAllMoles()
    {

        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.MoleFactory#getPhotoIdsFromeMole(int)}.
     */
    @Test
    public final void testGetPhotoIdsFromeMole()
    {

        fail("Not yet implemented"); // TODO
    }
    
    

}
