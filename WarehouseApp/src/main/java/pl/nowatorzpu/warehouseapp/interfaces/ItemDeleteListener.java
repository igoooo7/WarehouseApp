package pl.nowatorzpu.warehouseapp.interfaces;

import java.util.ArrayList;

import pl.nowatorzpu.warehouseapp.applicationclasses.Part;

public interface ItemDeleteListener {

	public void itemDeleteEventPerformed(ArrayList<Part> p, Integer c);	
	
}
