package pl.nowator_zpu.warehouse_app.application_classes;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PartsTableModel extends AbstractTableModel {

	private ArrayList<Part> parts = new ArrayList<>();

	private String[] columnNames = new String[] { "Area", "Manufacturer", "Part Group", "Rack", "Shelf", "Unit", "User",
			"Order code", "Product code", "Part name", "Description", "Quantity min.", "Quantity max", "Creation date",
			"Last change" };

	public PartsTableModel() {

	}

	public void setData(ArrayList<Part> parts) {
		this.parts = parts;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return 15;
	}

	@Override
	public int getRowCount() {
		return parts.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Part part = parts.get(row);

		switch (col) {

		case 0:
			return part.getArea();
		case 1:
			return part.getManufacturer();
		case 2:
			return part.getPartGroup();
		case 3:
			return part.getRack();
		case 4:
			return part.getShelf();
		case 5:
			return part.getUnit();
		case 6:
			return part.getUser();
		case 7:
			return part.getOrderCode();
		case 8:
			return part.getProductCode();
		case 9:
			return part.getPartName();
		case 10:
			return part.getDescription();
		case 11:
			return part.getQuantityMin();
		case 12:
			return part.getQuantityMax();
		case 13:
			return part.getCreationDate();
		case 14:
			return part.getLastChangeDate();
		}
		return null;
	}

}
