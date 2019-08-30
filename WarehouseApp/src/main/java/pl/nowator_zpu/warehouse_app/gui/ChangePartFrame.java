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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javassist.bytecode.stackmap.TypeData.ClassName;
import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.entities.Areas;
import pl.nowator_zpu.warehouse_app.entities.JobTitles;
import pl.nowator_zpu.warehouse_app.entities.Manufacturers;
import pl.nowator_zpu.warehouse_app.entities.PartGroups;
import pl.nowator_zpu.warehouse_app.entities.Parts;
import pl.nowator_zpu.warehouse_app.entities.Racks;
import pl.nowator_zpu.warehouse_app.entities.Shelfs;
import pl.nowator_zpu.warehouse_app.entities.Units;
import pl.nowator_zpu.warehouse_app.entities.UserRights;
import pl.nowator_zpu.warehouse_app.entities.Users;

public class ChangePartFrame extends JFrame implements WindowListener, KeyListener {

	private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());
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
	private JLabel lblImage;

	private JTextField txtPartName;
	private JTextField txtProductCode;
	private JTextField txtOrderCode;
	private JTextField txtQuantityMin;
	private JTextField txtQuantityMax;

	private JTextArea txtrDescription;

	private JComboBox<Object> cBoxManufacturer;
	private JComboBox<Object> cBoxPartGroup;
	private JComboBox<Object> cBoxUnit;
	private JComboBox<Object> cBoxArea;
	private JComboBox<Object> cBoxRack;
	private JComboBox<Object> cBoxShelf;

	private JButton btnImage;

	private byte[] partImage;
	private String partImagePath;

	private JButton btnChange;

	private User user;
	private Part part;

	private Controller controller;

	/**
	 * Create the frame.
	 */
	public ChangePartFrame(Part part) {

		setResizable(false);
		setTitle("Change part");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		addWindowListener(this);

		this.part = part;

		createControls();
		addActionListenersForControls();

		prepareLayout();
		contentPane.setLayout(gl_contentPane);

	}

	private void prepareLayout() {

		JPanel panel = new JPanel();
		panel.setBackground(new Color(195, 203, 43));

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChange, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
					.addGap(25))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 582, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnChange, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(89))
		);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnImage)
							.addContainerGap(230, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblQuantityMin, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtQuantityMin, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblImage, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblQuantityMax)
											.addGap(17)
											.addComponent(txtQuantityMax, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblOrderCode, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblProductCode, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPartName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPartGroup, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUnit, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblArea, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRack, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblShelf, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblManufacturer, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.UNRELATED)))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(cBoxUnit, 0, 226, Short.MAX_VALUE)
											.addComponent(cBoxArea, 0, 226, Short.MAX_VALUE)
											.addComponent(cBoxRack, 0, 226, Short.MAX_VALUE)
											.addComponent(cBoxShelf, 0, 226, Short.MAX_VALUE)
											.addComponent(cBoxPartGroup, Alignment.TRAILING, 0, 226, Short.MAX_VALUE))
										.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(cBoxManufacturer, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtrDescription, 0, 0, Short.MAX_VALUE)
											.addComponent(txtOrderCode, Alignment.TRAILING)
											.addComponent(txtProductCode, Alignment.TRAILING)
											.addComponent(txtPartName, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))))
							.addGap(23))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPartName)
						.addComponent(txtPartName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductCode)
						.addComponent(txtProductCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrderCode)
						.addComponent(txtOrderCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(txtrDescription, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblManufacturer)
						.addComponent(cBoxManufacturer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxPartGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPartGroup))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUnit))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblArea))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxRack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRack))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxShelf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblShelf))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantityMin)
						.addComponent(txtQuantityMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantityMax)
						.addComponent(txtQuantityMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnImage, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

	}

	private void createControls() {

		lblPartName = new JLabel("Part name:");
		txtPartName = new JTextField(part.getPartName());
		txtPartName.setColumns(15);

		lblProductCode = new JLabel("Product code:");
		txtProductCode = new JTextField(part.getProductCode());
		txtProductCode.setColumns(15);

		lblOrderCode = new JLabel("Order code:");
		txtOrderCode = new JTextField(part.getOrderCode());
		txtOrderCode.setColumns(15);

		lblDescription = new JLabel("Description:");
		txtrDescription = new JTextArea(part.getDescription());
		txtrDescription.setLineWrap(true);
		txtrDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblManufacturer = new JLabel("Manufacturer:");

		lblPartGroup = new JLabel("Part group:");

		lblUnit = new JLabel("Unit:");

		lblArea = new JLabel("Area:");

		lblRack = new JLabel("Rack:");

		lblShelf = new JLabel("Shelf:");

		lblQuantityMin = new JLabel("Quantity min:");
		txtQuantityMin = new JTextField(part.getQuantityMin().toString());
		txtQuantityMin.setColumns(15);

		lblQuantityMax = new JLabel("Quantity max:");
		txtQuantityMax = new JTextField(part.getQuantityMax().toString());
		txtQuantityMax.setColumns(15);

		btnImage = new JButton("Load image");
		Image btnLoadImageIcon = new ImageIcon(this.getClass().getResource("/load-image-32.png")).getImage();
		btnImage.setIcon(new ImageIcon(btnLoadImageIcon));

		lblImage = new JLabel("");

		btnChange = new JButton("Update");
		Image btnCreateIcon = new ImageIcon(this.getClass().getResource("/new-part-32.png")).getImage();
		btnChange.setIcon(new ImageIcon(btnCreateIcon));

		prepareComboBoxes();
	}

	private void addActionListenersForControls() {

		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int q_min = 0;
				int q_max = 0;

				if (user.getLogged()) {

					StringBuilder sb = new StringBuilder();

					if (user.getUserRightsLevel() >= 2) {

						if (!txtPartName.getText().isEmpty() && !txtProductCode.getText().isEmpty()
								&& !txtOrderCode.getText().isEmpty() && !txtQuantityMin.getText().isEmpty()
								&& !txtQuantityMax.getText().isEmpty()) {

							Boolean quantityValuesAreCorrect = true;

							try {
								q_min = Integer.parseInt(txtQuantityMin.getText());
							} catch (Exception e) {
								txtQuantityMin.setText("0");
								quantityValuesAreCorrect = false;
							}
							try {
								q_max = Integer.parseInt(txtQuantityMax.getText());
							} catch (Exception e) {
								txtQuantityMax.setText("0");
								quantityValuesAreCorrect = false;
							}

							if (q_min > q_max) {
								quantityValuesAreCorrect = false;
							}

							if (!quantityValuesAreCorrect) {

								JOptionPane.showMessageDialog(null, "Please enter a valid values for quantity fields !",
										"Warning", JOptionPane.WARNING_MESSAGE);
							} else {

								Parts partToChange = new Parts();

								Manufacturers m = controller.dbManagerForParts
										.getManufacturerByManufacturer(cBoxManufacturer.getSelectedItem().toString());

								PartGroups p = controller.dbManagerForParts
										.getPartGroupByPartGroup(cBoxPartGroup.getSelectedItem().toString());

								Units u = controller.dbManagerForParts
										.getUnitByUnit(cBoxUnit.getSelectedItem().toString());

								Areas a = controller.dbManagerForParts
										.getAreaByArea(cBoxArea.getSelectedItem().toString());

								Racks r = controller.dbManagerForParts
										.getRackByRack(cBoxRack.getSelectedItem().toString());

								Shelfs s = controller.dbManagerForParts
										.getShelfByShelf(cBoxShelf.getSelectedItem().toString());

								partToChange.setPartId(part.getPartId());
								partToChange.setPartName(txtPartName.getText());
								partToChange.setProductCode(txtProductCode.getText());
								partToChange.setOrderCode(txtOrderCode.getText());
								partToChange.setDescription(txtrDescription.getText());
								partToChange.setManufacturers(m);
								partToChange.setPartGroups(p);
								partToChange.setUnits(u);
								partToChange.setAreas(a);
								partToChange.setRacks(r);
								partToChange.setShelfs(s);
								partToChange.setQuantityMin(q_min);
								partToChange.setQuantityMax(q_max);

								Users us = new Users();
								us.setUserId(user.getUserId());
								us.setFirstName(user.getFirstName());
								us.setLastName(user.getLastName());
								us.setUserName(user.getUserName());
								us.setUserEmail(user.getUserEmail());
								us.setUserPassword(user.getUserPassword());

								JobTitles jt = controller.dbManagerForUsers.getJobTitleByTitle(user.getJobTitle());
								UserRights ur = controller.dbManagerForUsers
										.getUserRightsByRights(user.getUserRights());

								us.setJobTitles(jt);
								us.setUserRights(ur);

								partToChange.setUsers(us);

								LocalDateTime dateTime = LocalDateTime.now();
								Timestamp sqlDateTime = Timestamp.valueOf(dateTime);

								partToChange.setLastChangeDate(sqlDateTime);

								partImage = null;
								if (partImagePath != null) {

									File file = new File(partImagePath);
									try {
										partImage = Files.readAllBytes(file.toPath());
										partToChange.setImage(partImage);
									} catch (IOException e) {
										LOGGER.log(Level.WARNING, e.toString());
									}
								}

								Boolean partSuccessfullyChanged = controller.dbManagerForParts.changePart(partToChange);

								if (partSuccessfullyChanged) {

									JOptionPane.showMessageDialog(null, "Part successfully changed", "Message",
											JOptionPane.INFORMATION_MESSAGE);

								} else {
									JOptionPane.showMessageDialog(null, "Some problems appeared, part wasn't created!",
											"Warning", JOptionPane.WARNING_MESSAGE);
								}
							}

						} else {

							if (txtPartName.getText().isEmpty()) {
								sb.append(" part name");
							}
							if (txtProductCode.getText().isEmpty()) {
								if (!sb.toString().isEmpty()) {
									sb.append(", ");
								}
								sb.append(" product code");
							}
							if (txtOrderCode.getText().isEmpty()) {
								if (!sb.toString().isEmpty()) {
									sb.append(", ");
								}
								sb.append(" order code");
							}
							if (txtQuantityMin.getText().isEmpty()) {
								if (!sb.toString().isEmpty()) {
									sb.append(", ");
								}
								sb.append(" quantity min");
							}
							if (txtQuantityMax.getText().isEmpty()) {
								if (!sb.toString().isEmpty()) {
									sb.append(", ");
								}
								sb.append(" quantity max");
							}
							sb.append(".");

							JOptionPane.showMessageDialog(null, "Please specify:" + sb.toString(), "Warning",
									JOptionPane.WARNING_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Current user don't have rights to change parts!",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "User isn't logged!", "Warning", JOptionPane.WARNING_MESSAGE);
				}

			}

		});

		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));

				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {

					File selectedFile = file.getSelectedFile();
					partImagePath = selectedFile.getAbsolutePath();
					lblImage.setIcon(resizeImage(partImagePath, null));

				}
			}
		});

		txtPartName.addKeyListener(this);
		txtProductCode.addKeyListener(this);
		txtOrderCode.addKeyListener(this);
		txtrDescription.addKeyListener(this);
		txtQuantityMin.addKeyListener(this);
		txtQuantityMax.addKeyListener(this);

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
		clearAllTextFields();
	}

	private void closeFrame() {
		clearAllTextFields();
		dispose();
		setVisible(false);
	}

	private void clearAllTextFields() {
		txtPartName.setText("");
		txtProductCode.setText("");
		txtOrderCode.setText("");
		txtrDescription.setText("");
		txtQuantityMin.setText("");
		txtQuantityMax.setText("");
	}

	private ImageIcon resizeImage(String imagePath, byte[] picture) {

		ImageIcon myImage = null;

		if (imagePath != null) {
			myImage = new ImageIcon(imagePath);
		} else {
			myImage = new ImageIcon(picture);
		}

		Image img1 = myImage.getImage();
		Image img2 = img1.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(img2);
		return image;
	}

	private void showPartImage() {

		byte[] image;
		image = part.getImage();
		if (image != null) {
			lblImage.setIcon(resizeImage(null, image));
		}
	}

	public static void setSelectedValueOfComboBox(JComboBox<Object> comboBox, String value) {

		for (int i = 0; i < comboBox.getItemCount(); i++) {
			if (comboBox.getItemAt(i).toString().equals(value)) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}
	}

	public static void setSelectedValueOfComboBox(JComboBox<Object> comboBox, int value) {

		for (int i = 0; i < comboBox.getItemCount(); i++) {
			if (comboBox.getItemAt(i).equals(value)) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}
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
		showPartImage();
		setSelectedValueOfComboBox(cBoxManufacturer, part.getManufacturer());
		setSelectedValueOfComboBox(cBoxPartGroup, part.getPartGroup());
		setSelectedValueOfComboBox(cBoxUnit, part.getUnit());
		setSelectedValueOfComboBox(cBoxArea, part.getArea());
		setSelectedValueOfComboBox(cBoxRack, part.getRack());
		setSelectedValueOfComboBox(cBoxShelf, part.getShelf());
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
