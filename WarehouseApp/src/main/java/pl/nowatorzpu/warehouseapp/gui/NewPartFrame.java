package pl.nowatorzpu.warehouseapp.gui;

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
import pl.nowatorzpu.warehouseapp.applicationclasses.User;
import pl.nowatorzpu.warehouseapp.dataaccess.Controller;
import pl.nowatorzpu.warehouseapp.entities.Areas;
import pl.nowatorzpu.warehouseapp.entities.JobTitles;
import pl.nowatorzpu.warehouseapp.entities.Manufacturers;
import pl.nowatorzpu.warehouseapp.entities.PartGroups;
import pl.nowatorzpu.warehouseapp.entities.Parts;
import pl.nowatorzpu.warehouseapp.entities.Racks;
import pl.nowatorzpu.warehouseapp.entities.Shelfs;
import pl.nowatorzpu.warehouseapp.entities.Units;
import pl.nowatorzpu.warehouseapp.entities.UserRights;
import pl.nowatorzpu.warehouseapp.entities.Users;

public class NewPartFrame extends JFrame implements WindowListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());
	
	private JPanel contentPane;

	private JLabel lblPartName;
	private JLabel lblProductCode;
	private JLabel lblOrderCode;
	private JLabel lblLink;
	private JLabel lblManufacturer;
	private JLabel lblPartGroup;
	private JLabel lblUnit;
	private JLabel lblArea;
	private JLabel lblRack;
	private JLabel lblShelf;
	private JLabel lblQuantityMin;
	private JLabel lblQuantityMax;
	private JLabel lblImage;
	private JLabel lblDescription;

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

	private JButton btnCreate;

	private User user;

	private Controller controller = new Controller();
	private JPanel panel;

	public NewPartFrame() {

		setResizable(false);
		setTitle("New part");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 825);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		addWindowListener(this);

		createControls();
		addActionListenersForControls();
		prepareLayout();
		
	}

	private void prepareLayout() {

		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.YELLOW));
		panel.setBounds(31, 28, 391, 665);
		panel.setBackground(Color.BLACK);	
		panel.setLayout(null);
		panel.add(btnImage);
		panel.add(lblImage);
		panel.add(lblOrderCode);
		panel.add(lblProductCode);
		panel.add(lblPartName);
		panel.add(lblDescription);
		panel.add(lblLink);
		panel.add(scrollPane);
		panel.add(txtLink);
		panel.add(txtOrderCode);
		panel.add(txtProductCode);
		panel.add(txtPartName);
		panel.add(lblQuantityMin);
		panel.add(txtQuantityMin);
		panel.add(lblQuantityMax);
		panel.add(txtQuantityMax);
		panel.add(lblManufacturer);
		panel.add(lblPartGroup);
		panel.add(lblUnit);
		panel.add(lblArea);
		panel.add(lblRack);
		panel.add(lblShelf);
		panel.add(cBoxUnit);
		panel.add(cBoxArea);
		panel.add(cBoxRack);
		panel.add(cBoxShelf);
		panel.add(cBoxPartGroup);
		panel.add(cBoxManufacturer);
		
		contentPane.setLayout(null);
		contentPane.add(btnCreate);
		contentPane.add(panel);
	}

	private void createControls() {

		lblPartName = new JLabel("Part name:");
		lblPartName.setForeground(Color.LIGHT_GRAY);
		lblPartName.setBounds(12, 25, 79, 15);
		txtPartName = new JTextField();
		txtPartName.setBounds(134, 23, 234, 19);
		txtPartName.setColumns(15);

		lblProductCode = new JLabel("Product code:");
		lblProductCode.setForeground(Color.LIGHT_GRAY);
		lblProductCode.setBounds(12, 56, 114, 15);
		txtProductCode = new JTextField();
		txtProductCode.setBounds(134, 54, 234, 19);
		txtProductCode.setColumns(15);

		lblOrderCode = new JLabel("Order code:");
		lblOrderCode.setForeground(Color.LIGHT_GRAY);
		lblOrderCode.setBounds(12, 87, 97, 15);
		txtOrderCode = new JTextField();
		txtOrderCode.setBounds(134, 85, 234, 19);
		txtOrderCode.setColumns(15);
		
		lblLink = new JLabel("Link:");
		lblLink.setForeground(Color.LIGHT_GRAY);
		lblLink.setBounds(12, 115, 97, 15);
		txtLink = new JTextField();
		txtLink.setBounds(134, 116, 234, 19);
		txtLink.setColumns(15);
		
		lblDescription = new JLabel("Description:");		
		lblDescription.setForeground(Color.LIGHT_GRAY);
		lblDescription.setBounds(12, 150, 97, 15);
		txtrDescription = new JTextArea();
		txtrDescription.setToolTipText("describe part, please add link to site.");
		txtrDescription.setLineWrap(true);
		txtrDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 148, 234, 84);
		scrollPane.setViewportView(txtrDescription);

		lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setForeground(Color.LIGHT_GRAY);
		lblManufacturer.setBounds(12, 249, 114, 15);

		lblPartGroup = new JLabel("Part group:");
		lblPartGroup.setForeground(Color.LIGHT_GRAY);
		lblPartGroup.setBounds(12, 285, 97, 15);

		lblUnit = new JLabel("Unit:");
		lblUnit.setForeground(Color.LIGHT_GRAY);
		lblUnit.setBounds(12, 321, 97, 15);

		lblArea = new JLabel("Area:");
		lblArea.setForeground(Color.LIGHT_GRAY);
		lblArea.setBounds(12, 357, 97, 15);

		lblRack = new JLabel("Rack:");
		lblRack.setForeground(Color.LIGHT_GRAY);
		lblRack.setBounds(12, 393, 97, 15);

		lblShelf = new JLabel("Shelf:");
		lblShelf.setForeground(Color.LIGHT_GRAY);
		lblShelf.setBounds(12, 429, 97, 15);

		lblQuantityMin = new JLabel("Quantity min:");
		lblQuantityMin.setForeground(Color.LIGHT_GRAY);
		lblQuantityMin.setBounds(12, 468, 97, 15);
		txtQuantityMin = new JTextField();
		txtQuantityMin.setBounds(121, 466, 47, 19);
		txtQuantityMin.setToolTipText("minimum amount that could be ordered");
		txtQuantityMin.setColumns(15);

		lblQuantityMax = new JLabel("Quantity max:");
		lblQuantityMax.setForeground(Color.LIGHT_GRAY);
		lblQuantityMax.setBounds(186, 468, 103, 15);
		txtQuantityMax = new JTextField();
		txtQuantityMax.setBounds(301, 466, 47, 19);
		txtQuantityMax.setToolTipText("maximum amount that could be ordered");
		txtQuantityMax.setColumns(15);

		btnImage = new JButton("Load image");
		btnImage.setBounds(12, 603, 149, 50);
		Image btnLoadImageIcon = new ImageIcon(this.getClass().getResource("/load-image-32.png")).getImage();
		btnImage.setIcon(new ImageIcon(btnLoadImageIcon));

		lblImage = new JLabel("");
		lblImage.setBounds(179, 521, 189, 132);

		btnCreate = new JButton("Create");
		btnCreate.setBounds(286, 719, 136, 51);
		Image btnCreateIcon = new ImageIcon(this.getClass().getResource("/new-part-32.png")).getImage();
		btnCreate.setIcon(new ImageIcon(btnCreateIcon));

		prepareComboBoxes();
	}

	private void addActionListenersForControls() {

		btnCreate.addActionListener(new ActionListener() {
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

								JOptionPane.showMessageDialog(null, "Please enter valid values for quantity fields!",
										"Warning", JOptionPane.WARNING_MESSAGE);
							} else {

								Parts part = new Parts();

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

								part.setPartName(txtPartName.getText());
								part.setProductCode(txtProductCode.getText());
								part.setOrderCode(txtOrderCode.getText());
								part.setDescription(txtrDescription.getText());
								part.setLink(txtLink.getText());
								part.setManufacturers(m);
								part.setPartGroups(p);
								part.setUnits(u);
								part.setAreas(a);
								part.setRacks(r);
								part.setShelfs(s);
								part.setQuantityMin(q_min);
								part.setQuantityMax(q_max);

								Users us = controller.dbManagerForUsers.getUserEntityByUserName(user.getUserName());

								JobTitles jt = controller.dbManagerForUsers.getJobTitleByTitle(user.getJobTitle());
								UserRights ur = controller.dbManagerForUsers
										.getUserRightsByRights(user.getUserRights());

								us.setJobTitles(jt);
								us.setUserRights(ur);

								part.setUsers(us);

								LocalDateTime dateTime = LocalDateTime.now();
								Timestamp sqlDateTime = Timestamp.valueOf(dateTime);

								part.setCreationDate(sqlDateTime);

								partImage = null;
								if (partImagePath != null) {

									File file = new File(partImagePath);
									try {
										partImage = Files.readAllBytes(file.toPath());
									} catch (IOException e) {
										LOGGER.log(Level.WARNING, e.toString());
									}
								}

								part.setImage(partImage);

								Parts partToCheck = controller.dbManagerForParts
										.getPartEntityByOrderCodeAndManufacturerId(part.getOrderCode(),
												m.getManufacturerId());

								if (partToCheck == null) {

									Boolean partSuccessfullyCreated = controller.dbManagerForParts.newPart(part);

									if (partSuccessfullyCreated) {

										clearAllTextFields();
										JOptionPane.showMessageDialog(null, "Part successfully created", "Message",
												JOptionPane.INFORMATION_MESSAGE);

									} else {
										JOptionPane.showMessageDialog(null,
												"Some problems appeared, part wasn't created!", "Warning",
												JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "Part already exist!", "Warning",
											JOptionPane.WARNING_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "Current user doesn't have rights to create parts!",
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
		txtLink.addKeyListener(this);
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
		cBoxManufacturer.setBounds(134, 244, 234, 24);

		stringArray = partGroupList.toArray(new String[partGroupList.size()]);
		cBoxPartGroup = new JComboBox<Object>(stringArray);
		cBoxPartGroup.setBounds(134, 280, 234, 24);

		stringArray = unitList.toArray(new String[unitList.size()]);
		cBoxUnit = new JComboBox<Object>(stringArray);
		cBoxUnit.setBounds(134, 316, 234, 24);

		stringArray = areaList.toArray(new String[areaList.size()]);
		cBoxArea = new JComboBox<Object>(stringArray);
		cBoxArea.setBounds(134, 352, 234, 24);

		stringArray = rackList.toArray(new String[rackList.size()]);
		cBoxRack = new JComboBox<Object>(stringArray);
		cBoxRack.setBounds(134, 388, 234, 24);

		stringArray = shelfList.toArray(new String[shelfList.size()]);
		cBoxShelf = new JComboBox<Object>(stringArray);
		cBoxShelf.setBounds(134, 424, 234, 24);

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
