package pl.nowator_zpu.warehouse_app.interfaces;

import java.util.ArrayList;

import pl.nowator_zpu.warehouse_app.application_classes.Part;

public interface ItemDeleteListener {

	public void itemDeleteEventPerformed(ArrayList<Part> p, Integer c);	
	
}
