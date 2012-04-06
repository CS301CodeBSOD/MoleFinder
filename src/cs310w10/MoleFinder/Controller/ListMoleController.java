package cs310w10.MoleFinder.Controller;

import java.util.ArrayList;
import cs310w10.MoleFinder.Model.Mole;

/**
 * This class holds tools we use to manage lists of moles. 
 * Primarily used in Search and Listing views
 * 
 * @author John Ryalls
 */

public class ListMoleController 
{
	private ArrayList<Mole> sMoles;
	private static MoleController mController;
	
	
	/**
	 * Get a list of moles from the database
	 * 
	 * @return an ArrayList<Mole> of Moles
	 */
	public ArrayList<Mole> getMoles() 
	{
	    getMController();
	    sMoles = mController.getAllMoles();

	    return sMoles;
	}

	private static void getMController(){
	       if (mController == null){
                   mController = new MoleController(MoleFinderApplication.getAppContext());
	}
       
	}
        /**
         *
         * 
         * @return an ArrayList<Mole> of Moles
         */
	//public int getIndexFromMole(Mole mole) 
	//{
//		sMoles = getMoles();
//		int id = sMoles.indexOf(mole);
//		return id;
//	}
	
	//Get IDs
	//Get Names
	//Get Descriptions
	//Get Locations

	public void deleteAllMoles() 
	{
	    sMoles = getMoles();
	    
	    //TODO: Delete all moles with the database. I'll figure it out later
	    for (int i = 0; i < sMoles.size(); i++)
            {
                mController.getMoleFromId(sMoles.get(i).getId());
                mController.deleteMole();
            }


	}
}
