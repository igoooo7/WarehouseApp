package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;

public class NewPartFrame extends JFrame implements WindowListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupLayout gl_contentPane;
	private JPanel contentPane;

	private JLabel lblPartName;
	private JLabel lblProductCode;
	private JLabel lblOrderCode;
	private JLabel lblDescription;
	private JLabel lblManufacturer;
	private JLabel lblPartGroup;
	private JLabel lblUnit;
	private JLabel lblArea;
	private JLabel lblRack;
	private JLabel lblShelf;
	private JLabel lblQuantityMin;
	private JLabel lblQuantityMax;

	private JTextField txtPartName;
	private JTextField txtProductCode;
	private JTextField txtOrderCode;
	private JTextField txtQuantityMin;
	private JTextField txtQuantityMax;

	private JTextArea txtrDescription;

	private JComboBox cBoxManufacturer;
	private JComboBox cBoxPartGroup;
	private JComboBox cBoxUnit;
	private JComboBox cBoxArea;
	private JComboBox cBoxRack;
	private JComboBox cBoxShelf;

	private JButton btnImage;
	private JButton btnCreate;

	private User user;

	private Controller controller;

	/**
	 * Create the frame.
	 */
	public NewPartFrame() {

		setResizable(false);
		setTitle("New part");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		addWindowListener(this);

		createControls();
		addActionListenersForControls();

		prepareLayout();
		contentPane.setLayout(gl_contentPane);

	}

	private void prepareLayout() {

		JPanel panel = new JPanel();

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(28)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE).addGap(31)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGap(138)));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
												.createSequentialGroup()
												.addComponent(lblOrderCode, GroupLayout.PREFERRED_SIZE, 97,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtOrderCode,
														GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
												.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
														.createParallelGroup(Alignment.LEADING)
														.addComponent(lblProductCode, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPartName)).addPreferredGap(
																ComponentPlacement.RELATED)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(txtPartName, GroupLayout.DEFAULT_SIZE,
																		211, Short.MAX_VALUE)
																.addComponent(txtProductCode, GroupLayout.DEFAULT_SIZE,
																		211, Short.MAX_VALUE))))
										.addGap(23))
								.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
										.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
												.createSequentialGroup()
												.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 97,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtrDescription, GroupLayout.PREFERRED_SIZE, 209,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_panel
														.createSequentialGroup()
														.addComponent(lblManufacturer, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(
																cBoxManufacturer, 0, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
												.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
														.createParallelGroup(Alignment.LEADING)
														.addComponent(lblPartGroup, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblUnit, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblArea, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblRack, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblShelf, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(cBoxUnit, GroupLayout.PREFERRED_SIZE, 209,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(cBoxPartGroup, GroupLayout.PREFERRED_SIZE,
																		209, GroupLayout.PREFERRED_SIZE)
																.addComponent(cBoxArea, GroupLayout.PREFERRED_SIZE, 209,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(cBoxRack, GroupLayout.PREFERRED_SIZE, 209,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(cBoxShelf, GroupLayout.PREFERRED_SIZE,
																		209, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblQuantityMin, GroupLayout.PREFERRED_SIZE, 97,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(btnImage)
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(txtQuantityMin,
																		GroupLayout.PREFERRED_SIZE, 47,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(lblQuantityMax,
																		GroupLayout.PREFERRED_SIZE, 97,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(txtQuantityMax,
																		GroupLayout.PREFERRED_SIZE, 47,
																		GroupLayout.PREFERRED_SIZE)))))
										.addContainerGap(20, Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(23)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblPartName).addComponent(
						txtPartName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblProductCode).addComponent(
						txtProductCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblOrderCode).addComponent(
						txtOrderCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblDescription)
						.addComponent(txtrDescription, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblManufacturer).addComponent(
						cBoxManufacturer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxPartGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPartGroup))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUnit))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblArea))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxRack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRack))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxShelf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblShelf))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblQuantityMin)
						.addComponent(txtQuantityMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantityMax).addComponent(txtQuantityMax, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
				.addComponent(btnImage, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		panel.setLayout(gl_panel);

	}

	private void createControls() {

		lblPartName = new JLabel("Part name:");

		txtPartName = new JTextField();
		txtPartName.setColumns(15);

		lblProductCode = new JLabel("Product code:");

		txtProductCode = new JTextField();
		txtProductCode.setColumns(15);

		lblOrderCode = new JLabel("Order code:");

		txtOrderCode = new JTextField();
		txtOrderCode.setColumns(15);

		lblDescription = new JLabel("Description:");

		txtrDescription = new JTextArea();
		txtrDescription.setLineWrap(true);
		txtrDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblManufacturer = new JLabel("Manufacturer:");

		lblPartGroup = new JLabel("Part group:");

		lblUnit = new JLabel("Unit:");

		lblArea = new JLabel("Area:");

		lblRack = new JLabel("Rack:");

		lblShelf = new JLabel("Shelf:");

		lblQuantityMin = new JLabel("Quantity min:");

		txtQuantityMin = new JTextField();
		txtQuantityMin.setColumns(15);

		lblQuantityMax = new JLabel("Quantity max:");

		txtQuantityMax = new JTextField();
		txtQuantityMax.setColumns(15);

		btnImage = new JButton("Load image");
		Image btnLoadImageIcon = new ImageIcon(this.getClass().getResource("/load-image-32.png")).getImage();
		btnImage.setIcon(new ImageIcon(btnLoadImageIcon));

		btnCreate = new JButton("Create");
		Image btnCreateIcon = new ImageIcon(this.getClass().getResource("/new-part-32.png")).getImage();
		btnCreate.setIcon(new ImageIcon(btnCreateIcon));

		prepareComboBoxes();
	}

	private void addActionListenersForControls() {

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

	}

	public void setUser(User user) {
		this.user = user;
	}

	private void prepareComboBoxes() {

		controller = new Controller();
		String[] stringArray;
		Integer[] integerArray;

		ArrayList<String> manufacturerList = controller.dbManagerForParts.getAllManufacturers();
		ArrayList<String> partGroupList = controller.dbManagerForParts.getAllPartGroups();
		ArrayList<String> unitList = controller.dbManagerForParts.getAllUnits();
		ArrayList<String> areaList = controller.dbManagerForParts.getAllAreas();
		ArrayList<Integer> rackList = controller.dbManagerForParts.getAllRacks();
		ArrayList<Integer> shelfList = controller.dbManagerForParts.getAllShelfs();

		stringArray = manufacturerList.toArray(new String[manufacturerList.size()]);
		cBoxManufacturer = new JComboBox<Object>(stringArray);

		stringArray = partGroupList.toArray(new String[partGroupList.size()]);
		cBoxPartGroup = new JComboBox<Object>(stringArray);

		stringArray = unitList.toArray(new String[unitList.size()]);
		cBoxUnit = new JComboBox<Object>(stringArray);

		stringArray = areaList.toArray(new String[areaList.size()]);
		cBoxArea = new JComboBox<Object>(stringArray);

		integerArray = rackList.toArray(new Integer[rackList.size()]);
		cBoxRack = new JComboBox<Object>(integerArray);

		integerArray = shelfList.toArray(new Integer[shelfList.size()]);
		cBoxShelf = new JComboBox<Object>(integerArray);

	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		clearAllTextFieldsBeforeFrameClose();
	}

	private void closeFrame() {
		clearAllTextFieldsBeforeFrameClose();
		dispose();
		setVisible(false);
	}

	private void clearAllTextFieldsBeforeFrameClose() {
		txtPartName.setText("");
		txtProductCode.setText("");
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
