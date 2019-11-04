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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());
	
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
	private JLabel lblLink;

	private JTextField txtPartName;
	private JTextField txtProductCode;
	private JTextField txtOrderCode;
	private JTextField txtQuantityMin;
	private JTextField txtQuantityMax;
	private JTextField txtLink;
	
	private JTextArea txtrDescription;

	private JComboBox<Object> cBoxManufacturer;
	private JComboBox<Object> cBoxPartGroup;
	private JComboBox<Object> cBoxUnit;
	private JComboBox<Object> cBoxArea;
	private JComboBox<Object> cBoxRack;
	private JComboBox<Object> cBoxShelf;

	private JButton btnImage;
	
	private JScrollPane scrollPane;

	private byte[] partImage;
	private String partImagePath;

	private JButton btnChange;

	private User user;
	private Part part;

	private Controller controller = new Controller();	
	private JPanel panel;
		
	public ChangePartFrame(Part part) {

		setResizable(false);
		setTitle("Change part");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 825);
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
		
	}

	private void prepareLayout() {

		panel = new JPanel();
		panel.setBounds(31, 30, 391, 661);
		panel.setBackground(new Color(195, 203, 43));
		panel.setLayout(null);
		panel.add(lblQuantityMin);
		panel.add(txtQuantityMin);
		panel.add(lblQuantityMax);
		panel.add(txtQuantityMax);
		panel.add(btnImage);
		panel.add(lblImage);
		panel.add(lblPartGroup);
		panel.add(lblUnit);
		panel.add(lblArea);
		panel.add(lblRack);
		panel.add(lblShelf);
		panel.add(lblManufacturer);
		panel.add(lblOrderCode);
		panel.add(lblProductCode);
		panel.add(lblPartName);
		panel.add(lblLink);
		panel.add(lblDescription);
		panel.add(txtOrderCode);
		panel.add(txtProductCode);
		panel.add(txtPartName);
		panel.add(cBoxUnit);
		panel.add(cBoxArea);
		panel.add(cBoxRack);
		panel.add(cBoxShelf);
		panel.add(cBoxPartGroup);
		panel.add(cBoxManufacturer);
		panel.add(txtLink);
		panel.add(scrollPane);
		
		contentPane.setLayout(null);
		contentPane.add(panel);
		contentPane.add(btnChange);
	}

	private void createControls() {

		lblPartName = new JLabel("Part name:");
		lblPartName.setBounds(12, 25, 78, 15);
		txtPartName = new JTextField(part.getPartName());
		txtPartName.setBounds(128, 23, 241, 19);
		txtPartName.setColumns(15);

		lblProductCode = new JLabel("Product code:");
		lblProductCode.setBounds(12, 56, 97, 15);
		txtProductCode = new JTextField(part.getProductCode());
		txtProductCode.setBounds(128, 54, 241, 19);
		txtProductCode.setColumns(15);

		lblOrderCode = new JLabel("Order code:");
		lblOrderCode.setBounds(12, 87, 97, 15);
		txtOrderCode = new JTextField(part.getOrderCode());
		txtOrderCode.setBounds(128, 85, 241, 19);
		txtOrderCode.setColumns(15);
		
		lblLink = new JLabel("Link:");
		lblLink.setBounds(12, 116, 97, 15);
		txtLink = new JTextField(part.getLink());
		txtLink.setBounds(128, 114, 241, 19);
		txtLink.setColumns(15);		
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(12, 146, 97, 15);
		txtrDescription = new JTextArea(part.getDescription());
		txtrDescription.setToolTipText("describe part, please add link to site.");
		txtrDescription.setLineWrap(true);
		txtrDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 144, 241, 80);
		scrollPane.setViewportView(txtrDescription);

		lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setBounds(12, 241, 98, 15);

		lblPartGroup = new JLabel("Part group:");
		lblPartGroup.setBounds(12, 277, 97, 15);

		lblUnit = new JLabel("Unit:");
		lblUnit.setBounds(12, 313, 97, 15);

		lblArea = new JLabel("Area:");
		lblArea.setBounds(12, 349, 97, 15);

		lblRack = new JLabel("Rack:");
		lblRack.setBounds(12, 385, 97, 15);

		lblShelf = new JLabel("Shelf:");
		lblShelf.setBounds(12, 421, 97, 15);

		lblQuantityMin = new JLabel("Quantity min:");
		lblQuantityMin.setBounds(12, 460, 97, 15);
		txtQuantityMin = new JTextField(part.getQuantityMin().toString());
		txtQuantityMin.setBounds(121, 458, 47, 19);
		txtQuantityMin.setToolTipText("minimum amount that could be ordered");
		txtQuantityMin.setColumns(15);

		lblQuantityMax = new JLabel("Quantity max:");
		lblQuantityMax.setBounds(186, 460, 98, 15);
		txtQuantityMax = new JTextField(part.getQuantityMax().toString());
		txtQuantityMax.setBounds(301, 458, 47, 19);
		txtQuantityMax.setToolTipText("maximum amount that could be ordered");
		txtQuantityMax.setColumns(15);

		btnImage = new JButton("Load image");
		btnImage.setBounds(12, 599, 149, 50);
		Image btnLoadImageIcon = new ImageIcon(this.getClass().getResource("/load-image-32.png")).getImage();
		btnImage.setIcon(new ImageIcon(btnLoadImageIcon));

		lblImage = new JLabel("");
		lblImage.setBounds(186, 517, 183, 132);

		btnChange = new JButton("Update");
		btnChange.setBounds(286, 718, 136, 51);
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

								JOptionPane.showMessageDialog(null, "Please enter a valid values for quantity fields!",
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
								partToChange.setLink(txtLink.getText());
								partToChange.setManufacturers(m);
								partToChange.setPartGroups(p);
								partToChange.setUnits(u);
								partToChange.setAreas(a);
								partToChange.setRacks(r);
								partToChange.setShelfs(s);
								partToChange.setQuantityMin(q_min);
								partToChange.setQuantityMax(q_max);

								Users us = controller.dbManagerForUsers.getUserEntityByUserName(user.getUserName());

								JobTitles jt = controller.dbManagerForUsers.getJobTitleByTitle(user.getJobTitle());
								UserRights ur = controller.dbManagerForUsers
										.getUserRightsByRights(user.getUserRights());

								us.setJobTitles(jt);
								us.setUserRights(ur);

								partToChange.setUsers(us);

								LocalDateTime dateTime = LocalDateTime.now();
								Timestamp sqlDateTime = Timestamp.valueOf(dateTime);

								partToChange.setLastChangeDate(sqlDateTime);

								partToChange.setCreationDate(part.getCreationDate());

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

								closeFrame();
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
						JOptionPane.showMessageDialog(null, "Current user doesn't have rights to change parts!",
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
		txtLink.addKeyListener(this);
		txtrDescription.addKeyListener(this);
		txtQuantityMin.addKeyListener(this);
		txtQuantityMax.addKeyListener(this);

	}

	public void setUser(User user) {
		this.user = user;
	}

	private void prepareComboBoxes() {

		String[] stringArray;	

		ArrayList<String> manufacturerList = controller.dbManagerForParts.getAllManufacturers();
		ArrayList<String> partGroupList = controller.dbManagerForParts.getAllPartGroups();
		ArrayList<String> unitList = controller.dbManagerForParts.getAllUnits();
		ArrayList<String> areaList = controller.dbManagerForParts.getAllAreas();
		ArrayList<String> rackList = controller.dbManagerForParts.getAllRacks();
		ArrayList<String> shelfList = controller.dbManagerForParts.getAllShelfs();

		stringArray = manufacturerList.toArray(new String[manufacturerList.size()]);
		cBoxManufacturer = new JComboBox<Object>(stringArray);
		cBoxManufacturer.setBounds(128, 236, 241, 24);

		stringArray = partGroupList.toArray(new String[partGroupList.size()]);
		cBoxPartGroup = new JComboBox<Object>(stringArray);
		cBoxPartGroup.setBounds(128, 272, 241, 24);

		stringArray = unitList.toArray(new String[unitList.size()]);
		cBoxUnit = new JComboBox<Object>(stringArray);
		cBoxUnit.setBounds(128, 308, 241, 24);

		stringArray = areaList.toArray(new String[areaList.size()]);
		cBoxArea = new JComboBox<Object>(stringArray);
		cBoxArea.setBounds(128, 344, 241, 24);

		stringArray = rackList.toArray(new String[rackList.size()]);
		cBoxRack = new JComboBox<Object>(stringArray);
		cBoxRack.setBounds(128, 380, 241, 24);

		stringArray = shelfList.toArray(new String[shelfList.size()]);
		cBoxShelf = new JComboBox<Object>(stringArray);
		cBoxShelf.setBounds(128, 416, 241, 24);

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
		txtLink.setText("");
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
