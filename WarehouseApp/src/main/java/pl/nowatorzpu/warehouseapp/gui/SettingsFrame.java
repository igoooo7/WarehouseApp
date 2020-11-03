package pl.nowatorzpu.warehouseapp.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import pl.nowatorzpu.warehouseapp.dataaccess.Controller;
import pl.nowatorzpu.warehouseapp.entities.Areas;
import pl.nowatorzpu.warehouseapp.entities.Manufacturers;
import pl.nowatorzpu.warehouseapp.entities.PartGroups;
import pl.nowatorzpu.warehouseapp.entities.Projects;
import pl.nowatorzpu.warehouseapp.entities.Racks;
import pl.nowatorzpu.warehouseapp.entities.Shelfs;
import pl.nowatorzpu.warehouseapp.entities.Units;

public class SettingsFrame extends JFrame implements WindowListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panel;

	private JLabel lblManufacturers;
	private JLabel lblPartGroups;
	private JLabel lblUnits;
	private JLabel lblAreas;
	private JLabel lblRacks;
	private JLabel lblShelfs;
	private JLabel lblProjects;
	private JLabel lblGroup;
	private JLabel lblContent;

	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;
	private JScrollPane scrollPane5;
	private JScrollPane scrollPane6;
	private JScrollPane scrollPane7;

	private JTextArea txtaManufacturers;
	private JTextArea txtaPartGroups;
	private JTextArea txtaUnits;
	private JTextArea txtaAreas;
	private JTextArea txtaRacks;
	private JTextArea txtaShelfs;
	private JTextArea txtaProjects;

	private JComboBox<Object> cBoxGroup;
	private JComboBox<Object> cBoxContent;

	private JButton btnDeleteRecord;
	private JButton btnAddRecord;

	private Controller controller = new Controller();

	public SettingsFrame() {

		setResizable(false);
		setTitle("Settings");
		Image formIcon = new ImageIcon(this.getClass().getResource("/storage.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-29, -33, 677, 779);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.foreground"));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		addWindowListener(this);

		createControls();
		addActionListenersForControls();
		prepareLayout();
		fillTextAreas();
		prepareComboBoxes();

	}

	private void prepareLayout() {

		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.YELLOW));
		panel.setBounds(30, 25, 615, 703);
		panel.setBackground(UIManager.getColor("Button.foreground"));
		panel.setLayout(null);

		contentPane.setLayout(null);
		contentPane.add(panel);

		panel.add(lblManufacturers);
		panel.add(scrollPane1);
		panel.add(lblPartGroups);
		panel.add(scrollPane2);
		panel.add(lblUnits);
		panel.add(scrollPane3);
		panel.add(lblAreas);
		panel.add(scrollPane4);
		panel.add(lblRacks);
		panel.add(scrollPane5);
		panel.add(lblShelfs);
		panel.add(scrollPane6);
		panel.add(lblProjects);
		panel.add(scrollPane7);
		panel.add(lblGroup);
		panel.add(cBoxGroup);
		panel.add(lblContent);
		panel.add(cBoxContent);
		panel.add(btnAddRecord);
		panel.add(btnDeleteRecord);

	}

	private void createControls() {

		lblManufacturers = new JLabel("Manufacturers:");
		lblManufacturers.setForeground(Color.LIGHT_GRAY);
		lblManufacturers.setBounds(31, 33, 90, 15);

		txtaManufacturers = new JTextArea();
		txtaManufacturers.setEditable(false);
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(41, 59, 165, 162);
		scrollPane1.setViewportView(txtaManufacturers);

		lblPartGroups = new JLabel("Part groups:");
		lblPartGroups.setForeground(Color.LIGHT_GRAY);
		lblPartGroups.setBounds(216, 33, 90, 15);

		txtaPartGroups = new JTextArea();
		txtaPartGroups.setEditable(false);
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(226, 59, 165, 162);
		scrollPane2.setViewportView(txtaPartGroups);

		lblUnits = new JLabel("Units:");
		lblUnits.setForeground(Color.LIGHT_GRAY);
		lblUnits.setBounds(401, 34, 90, 15);

		txtaUnits = new JTextArea();
		txtaUnits.setEditable(false);
		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(411, 59, 165, 162);
		scrollPane3.setViewportView(txtaUnits);

		lblAreas = new JLabel("Areas:");
		lblAreas.setForeground(Color.LIGHT_GRAY);
		lblAreas.setBounds(31, 238, 90, 15);

		txtaAreas = new JTextArea();
		txtaAreas.setEditable(false);
		scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(41, 264, 165, 162);
		scrollPane4.setViewportView(txtaAreas);

		lblRacks = new JLabel("Racks:");
		lblRacks.setForeground(Color.LIGHT_GRAY);
		lblRacks.setBounds(216, 239, 90, 15);

		txtaRacks = new JTextArea();
		txtaRacks.setEditable(false);
		scrollPane5 = new JScrollPane();
		scrollPane5.setBounds(226, 264, 165, 162);
		scrollPane5.setViewportView(txtaRacks);

		lblShelfs = new JLabel("Shelfs:");
		lblShelfs.setForeground(Color.LIGHT_GRAY);
		lblShelfs.setBounds(402, 238, 90, 15);

		txtaShelfs = new JTextArea();
		txtaShelfs.setEditable(false);
		scrollPane6 = new JScrollPane();
		scrollPane6.setBounds(411, 264, 165, 162);
		scrollPane6.setViewportView(txtaShelfs);

		lblProjects = new JLabel("Projects:");
		lblProjects.setForeground(Color.LIGHT_GRAY);
		lblProjects.setBounds(32, 448, 90, 15);

		txtaProjects = new JTextArea();
		txtaProjects.setEditable(false);
		scrollPane7 = new JScrollPane();
		scrollPane7.setBounds(41, 473, 535, 118);
		scrollPane7.setViewportView(txtaProjects);

		lblGroup = new JLabel("Group:");
		lblGroup.setForeground(Color.LIGHT_GRAY);
		lblGroup.setBounds(31, 629, 90, 15);

		cBoxGroup = new JComboBox<Object>(
				new String[] { "Manufacturers", "Part groups", "Units", "Areas", "Racks", "Shelfs", "Projects" });
		cBoxGroup.setBounds(88, 625, 154, 22);

		lblContent = new JLabel("Content:");
		lblContent.setForeground(Color.LIGHT_GRAY);
		lblContent.setBounds(31, 671, 90, 15);

		cBoxContent = new JComboBox<Object>(new String[] { " " });
		cBoxContent.setEditable(true);
		cBoxContent.setBounds(88, 667, 363, 22);

		Image btnAddRecordIcon = new ImageIcon(this.getClass().getResource("/add-32.png")).getImage();
		btnAddRecord = new JButton("Add ");
		btnAddRecord.setBounds(477, 603, 99, 41);
		btnAddRecord.setIcon(new ImageIcon(btnAddRecordIcon));

		Image btnDeleteRecordIcon = new ImageIcon(this.getClass().getResource("/minus-32.png")).getImage();
		btnDeleteRecord = new JButton("Delete");
		btnDeleteRecord.setBounds(477, 648, 99, 41);
		btnDeleteRecord.setIcon(new ImageIcon(btnDeleteRecordIcon));
		
	}

	private void addActionListenersForControls() {

		cBoxGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				prepareComboBoxes();
			}
		});

		cBoxContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBoxContent.getSelectedIndex() == 0) {
					cBoxContent.setEditable(true);
				} else {
					cBoxContent.setEditable(false);
				}
			}
		});

		btnAddRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<String> list;
				
				if (cBoxGroup.getSelectedItem().equals("Manufacturers")) {

					list = controller.dbManagerForParts.getAllManufacturers();
					if (!list.contains(cBoxContent.getSelectedItem()) && !cBoxContent.getSelectedItem().toString().trim().equals("")) {
						controller.dbManagerForParts
								.newManufacturer(new Manufacturers(cBoxContent.getSelectedItem().toString().trim()));
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Part groups")) {

					list = controller.dbManagerForParts.getAllPartGroups();
					if (!list.contains(cBoxContent.getSelectedItem()) && !cBoxContent.getSelectedItem().toString().trim().equals("")) {
						controller.dbManagerForParts
								.newPartGroup(new PartGroups(cBoxContent.getSelectedItem().toString().trim()));
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Units")) {

					list = controller.dbManagerForParts.getAllUnits();
					if (!list.contains(cBoxContent.getSelectedItem()) && !cBoxContent.getSelectedItem().toString().trim().equals("")) {
						controller.dbManagerForParts
								.newUnit(new Units(cBoxContent.getSelectedItem().toString().trim()));
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Areas")) {

					list = controller.dbManagerForParts.getAllAreas();
					if (!list.contains(cBoxContent.getSelectedItem()) && !cBoxContent.getSelectedItem().toString().trim().equals("")) {
						controller.dbManagerForParts
								.newArea(new Areas(cBoxContent.getSelectedItem().toString().trim()));
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Racks")) {

					list = controller.dbManagerForParts.getAllRacks();
					if (!list.contains(cBoxContent.getSelectedItem()) && !cBoxContent.getSelectedItem().toString().trim().equals("")) {
						controller.dbManagerForParts
								.newRack(new Racks(cBoxContent.getSelectedItem().toString().trim()));
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Shelfs")) {

					list = controller.dbManagerForParts.getAllRacks();
					if (!list.contains(cBoxContent.getSelectedItem()) && !cBoxContent.getSelectedItem().toString().trim().equals("")) {
						controller.dbManagerForParts
								.newShelf(new Shelfs(cBoxContent.getSelectedItem().toString().trim()));
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Projects")) {

					list = controller.dbManagerForParts.getAllRacks();
					if (!list.contains(cBoxContent.getSelectedItem()) && !cBoxContent.getSelectedItem().toString().trim().equals("")) {
						controller.dbManagerForOrders
								.newProject(new Projects(cBoxContent.getSelectedItem().toString().trim()));
						fillTextAreas();
						prepareComboBoxes();
					}
				}
			}
		});

		btnDeleteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<String> list;
				
				if (cBoxGroup.getSelectedItem().equals("Manufacturers")) {

					list = controller.dbManagerForParts.getAllManufacturers();
					if (list.contains(cBoxContent.getSelectedItem())) {

						Manufacturers m = controller.dbManagerForParts
								.getManufacturerByManufacturer(cBoxContent.getSelectedItem().toString());
						controller.dbManagerForParts.deleteManufacturerById(m.getManufacturerId());
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Part groups")) {

					list = controller.dbManagerForParts.getAllPartGroups();
					if (list.contains(cBoxContent.getSelectedItem())) {

						PartGroups pg = controller.dbManagerForParts
								.getPartGroupByPartGroup(cBoxContent.getSelectedItem().toString());
						controller.dbManagerForParts.deletePartGroupById(pg.getPartGroupId());
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Units")) {

					list = controller.dbManagerForParts.getAllUnits();
					if (list.contains(cBoxContent.getSelectedItem())) {

						Units u = controller.dbManagerForParts
								.getUnitByUnit(cBoxContent.getSelectedItem().toString());
						controller.dbManagerForParts.deleteUnitById(u.getUnitId());
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Areas")) {

					list = controller.dbManagerForParts.getAllAreas();
					if (list.contains(cBoxContent.getSelectedItem())) {

						Areas a = controller.dbManagerForParts
								.getAreaByArea(cBoxContent.getSelectedItem().toString());
						controller.dbManagerForParts.deleteAreaById(a.getAreaId());
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Racks")) {

					list = controller.dbManagerForParts.getAllRacks();
					if (list.contains(cBoxContent.getSelectedItem())) {

						Racks r = controller.dbManagerForParts
								.getRackByRack(cBoxContent.getSelectedItem().toString());
						controller.dbManagerForParts.deleteRackById(r.getRackId());
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Shelfs")) {

					list = controller.dbManagerForParts.getAllShelfs();
					if (list.contains(cBoxContent.getSelectedItem())) {

						Shelfs s = controller.dbManagerForParts
								.getShelfByShelf(cBoxContent.getSelectedItem().toString());
						controller.dbManagerForParts.deleteShelfById(s.getShelfId());
						fillTextAreas();
						prepareComboBoxes();
					}
				}
				
				if (cBoxGroup.getSelectedItem().equals("Projects")) {

					list = controller.dbManagerForOrders.getAllProjects();
					if (list.contains(cBoxContent.getSelectedItem())) {

						Projects p = controller.dbManagerForOrders
								.getProjectByProject(cBoxContent.getSelectedItem().toString());
						controller.dbManagerForOrders.deleteProjectById(p.getProjectId());
						fillTextAreas();
						prepareComboBoxes();
					}
				}				
			}
		});
	}

	private void closeFrame() {
		dispose();
		setVisible(false);
	}

	private void fillTextAreas() {

		ArrayList<String> list;

		list = controller.dbManagerForParts.getAllManufacturers();
		txtaManufacturers.setText("");
		for (String m : list) {
			txtaManufacturers.append(m + "\n");
		}

		list = controller.dbManagerForParts.getAllPartGroups();
		txtaPartGroups.setText("");
		for (String pg : list) {
			txtaPartGroups.append(pg + "\n");
		}

		list = controller.dbManagerForParts.getAllUnits();
		txtaUnits.setText("");
		for (String u : list) {
			txtaUnits.append(u + "\n");
		}

		list = controller.dbManagerForParts.getAllAreas();
		txtaAreas.setText("");
		for (String a : list) {
			txtaAreas.append(a + "\n");
		}

		list = controller.dbManagerForParts.getAllRacks();
		txtaRacks.setText("");
		for (String r : list) {
			txtaRacks.append(r + "\n");
		}

		list = controller.dbManagerForParts.getAllShelfs();
		txtaShelfs.setText("");
		for (String s : list) {
			txtaShelfs.append(s + "\n");
		}

		list = controller.dbManagerForOrders.getAllProjects();
		txtaProjects.setText("");
		for (String p : list) {
			txtaProjects.append(p + "\n");
		}
	}

	private void prepareComboBoxes() {
		
		ArrayList<String> list;
		int itemCount;
		
		if (cBoxGroup.getSelectedItem().equals("Manufacturers")) {
			
			list = controller.dbManagerForParts.getAllManufacturers();
			itemCount = cBoxContent.getItemCount();

			for (int i = 1; i < itemCount; i++) {
				cBoxContent.removeItemAt(1);
			}
			for (String s : list) {
				cBoxContent.addItem(s);
			}
			cBoxContent.setSelectedIndex(0);
		}

		if (cBoxGroup.getSelectedItem().equals("Part groups")) {

			list = controller.dbManagerForParts.getAllPartGroups();			
			itemCount = cBoxContent.getItemCount();

			for (int i = 1; i < itemCount; i++) {
				cBoxContent.removeItemAt(1);
			}
			for (String s : list) {
				cBoxContent.addItem(s);
			}
			cBoxContent.setSelectedIndex(0);
		}

		if (cBoxGroup.getSelectedItem().equals("Units")) {

			list = controller.dbManagerForParts.getAllUnits();			
			itemCount = cBoxContent.getItemCount();

			for (int i = 1; i < itemCount; i++) {
				cBoxContent.removeItemAt(1);
			}
			for (String s : list) {
				cBoxContent.addItem(s);
			}
			cBoxContent.setSelectedIndex(0);
		}
		
		if (cBoxGroup.getSelectedItem().equals("Areas")) {

			list = controller.dbManagerForParts.getAllAreas();
			itemCount = cBoxContent.getItemCount();

			for (int i = 1; i < itemCount; i++) {
				cBoxContent.removeItemAt(1);
			}
			for (String s : list) {
				cBoxContent.addItem(s);
			}
			cBoxContent.setSelectedIndex(0);
		}
		
		if (cBoxGroup.getSelectedItem().equals("Racks")) {

			list = controller.dbManagerForParts.getAllRacks();
			itemCount = cBoxContent.getItemCount();

			for (int i = 1; i < itemCount; i++) {
				cBoxContent.removeItemAt(1);
			}
			for (String s : list) {
				cBoxContent.addItem(s);
			}
			cBoxContent.setSelectedIndex(0);
		}
		
		if (cBoxGroup.getSelectedItem().equals("Shelfs")) {

			list = controller.dbManagerForParts.getAllShelfs();
			itemCount = cBoxContent.getItemCount();

			for (int i = 1; i < itemCount; i++) {
				cBoxContent.removeItemAt(1);
			}
			for (String s : list) {
				cBoxContent.addItem(s);
			}
			cBoxContent.setSelectedIndex(0);
		}
		
		if (cBoxGroup.getSelectedItem().equals("Projects")) {

			list = controller.dbManagerForOrders.getAllProjects();
			itemCount = cBoxContent.getItemCount();

			for (int i = 1; i < itemCount; i++) {
				cBoxContent.removeItemAt(1);
			}
			for (String s : list) {
				cBoxContent.addItem(s);
			}
			cBoxContent.setSelectedIndex(0);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			closeFrame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
