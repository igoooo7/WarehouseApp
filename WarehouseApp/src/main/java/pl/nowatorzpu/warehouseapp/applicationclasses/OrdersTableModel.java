package pl.nowatorzpu.warehouseapp.applicationclasses;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class OrdersTableModel extends AbstractTableModel {

	private ArrayList<Order> orders = new ArrayList<>();

	private String[] columnNames = { "Manufacturer", "Order code", "Order date", "Part count", "Project", "User" };

	public OrdersTableModel() {

	}

	public void setData(ArrayList<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return orders.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Order order = orders.get(row);

		switch (col) {

		case 0:
			return order.getManufacturer();
		case 1:
			return order.getOrderCode();
		case 2:
			return order.getOrderDate();
		case 3:
			return order.getPartCount();
		case 4:
			return order.getProject();
		case 5:
			return order.getUser();		 
		}
		return null;
	}

}
