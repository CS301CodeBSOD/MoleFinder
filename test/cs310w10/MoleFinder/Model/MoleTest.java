/**
 * 
 */
package cs310w10.MoleFinder.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import android.test.AndroidTestCase;


/**
 * @author ryalls
 *
 */
public class MoleTest extends AndroidTestCase
{

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.Mole#Mole(int)}.
     */
    @Test
    public final void testMoleInt()
    {
        Mole mymole = new Mole(3);
        assertEquals(mymole.getId(), 3);
        assertEquals(mymole.getName(), "");
        assertEquals(mymole.getDescription(), "");
        assertEquals(mymole.getLocation(), "");
    }

    /**
     * Test method for {@link cs310w10.MoleFinder.Model.Mole#Mole()}.
     */
    @Test
    public final void testMole()
    {
        Mole mymole = new Mole();
        assertEquals(mymole.getId(), -1);
        assertEquals(mymole.getName(), "");
        assertEquals(mymole.getDescription(), "");
        assertEquals(mymole.getLocation(), "");
    }

}
