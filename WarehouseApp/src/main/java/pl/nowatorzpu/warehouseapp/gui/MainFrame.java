package pl.nowatorzpu.warehouseapp.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import pl.nowatorzpu.warehouseapp.applicationclasses.OrdersTableModel;
import pl.nowatorzpu.warehouseapp.applicationclasses.Part;
import pl.nowatorzpu.warehouseapp.applicationclasses.PartsTableModel;
import pl.nowatorzpu.warehouseapp.applicationclasses.User;
import pl.nowatorzpu.warehouseapp.dataaccess.Controller;
import pl.nowatorzpu.warehouseapp.entities.Manufacturers;
import pl.nowatorzpu.warehouseapp.interfaces.ItemDeleteListener;
import pl.nowatorzpu.warehouseapp.interfaces.UserLoginListener;

public class MainFrame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;

	private JLabel lblUserName;
	private JLabel lblJobTitle;
	private JLabel lblUserRights;
	private JLabel lblSearch;
	private JLabel lblImage;
	private JLabel lblManufacturer;
	private JLabel lblPartGroup;

	private JTextField txtUserName;
	private JTextField txtJobTitle;
	private JTextField txtUserRights;
	private JTextField txtFilter;

	private JButton btnExit;
	private JButton btnLogin;
	private JButton btnNewUser;
	private JButton btnDeleteUser;
	private JButton btnDeletePart;
	private JButton btnUpdate;
	private JButton btnNewPart;
	private JButton btnShowPicture;
	private JButton btnChangePart;
	private JButton btnAddToOrder;
	private JButton btnOrder;

	private JRadioButton rdbtnParts;
	private JRadioButton rdbtnOrders;

	private ButtonGroup bgPartsOrders;

	private JComboBox<Object> cBoxManufacturer;
	private JComboBox<Object> cBoxPartGroup;

	private LoginFrame loginFrame;
	private NewUserFrame newUserFrame;
	private NewPartFrame newPartFrame;
	private ChangePartFrame changePartFrame;
	private OrderFrame orderFrame;

	private PartsTableModel partsTableModel;
	private OrdersTableModel ordersTableModel;
	private JTable table;
	private JScrollPane scrollPane;

	private User user = new User();

	private Controller controller = new Controller();

	private ArrayList<Part> partsToOrder = new ArrayList<>();
	private Integer partsToOrderCount = 0;

	public static void main(String[] args) {

		try {
			// here you can put the selected theme class name in JTattoo
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");

		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mainframe = new MainFrame();
					mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {

		setTitle("Warehouse Management V1.0.0");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1550, 1200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);

		createControls();
		addActionListenersForControls();
		prepareLayout();

	}

	private void prepareLayout() {

		panel1 = new JPanel();
		panel1.setForeground(Color.LIGHT_GRAY);
		panel1.setBounds(60, 28, 280, 122);
		panel1.setBackground(Color.BLACK);
		panel1.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 0)), "User data", TitledBorder.LEFT, TitledBorder.TOP, null, Color.YELLOW));
		panel1.setLayout(null);
		panel1.add(lblUserRights);
		panel1.add(lblUserName);
		panel1.add(lblJobTitle);
		panel1.add(txtJobTitle);
		panel1.add(txtUserRights);
		panel1.add(txtUserName);

		panel2 = new JPanel();
		panel2.setBorder(new LineBorder(Color.YELLOW));
		panel2.setBounds(440, 37, 701, 77);
		panel2.setBackground(Color.BLACK);
		panel2.setLayout(null);
		panel2.add(btnLogin);
		panel2.add(btnNewUser);
		panel2.add(btnDeleteUser);
		panel2.add(btnExit);

		panel3 = new JPanel();
		panel3.setBounds(182, 313, 240, 369);
		panel3.setBackground(Color.BLACK);
		panel3.setLayout(null);
		panel3.add(btnDeletePart);
		panel3.add(btnNewPart);
		panel3.add(btnChangePart);
		panel3.add(btnUpdate);
		panel3.add(btnShowPicture);

		panel4 = new JPanel();
		panel4.setBounds(440, 189, 701, 112);
		panel4.setBorder(new TitledBorder(new LineBorder(new Color(195, 203, 43)), "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, Color.YELLOW));
		panel4.setBackground(Color.BLACK);
		panel4.setLayout(null);
		panel4.add(lblManufacturer);
		panel4.add(cBoxManufacturer);
		panel4.add(lblSearch);
		panel4.add(txtFilter);
		panel4.add(lblPartGroup);
		panel4.add(cBoxPartGroup);

		panel5 = new JPanel();
		panel5.setBounds(12, 313, 158, 369);
		panel5.setBackground(Color.BLACK);
		panel5.setLayout(null);
		panel5.add(btnOrder);
		panel5.add(btnAddToOrder);

		panel6 = new JPanel();
		panel6.setBounds(440, 124, 212, 53);
		panel6.setBorder(new TitledBorder(new LineBorder(new Color(195, 203, 43)), "View", TitledBorder.LEADING, TitledBorder.TOP, null, Color.YELLOW));
		panel6.setBackground(Color.BLACK);
		panel6.setLayout(null);
		panel6.add(rdbtnParts);
		panel6.add(rdbtnOrders);

		contentPane.setLayout(null);
		contentPane.add(panel1);
		contentPane.add(panel2);
		contentPane.add(panel3);
		contentPane.add(panel4);
		contentPane.add(panel5);
		contentPane.add(panel6);

		contentPane.add(scrollPane);
		contentPane.add(lblImage);

	}

	private void createControls() {

		Image btnLoginIcon = new ImageIcon(this.getClass().getResource("/login-form-open-32.png")).getImage();
		btnLogin = new JButton("Login");
		btnLogin.setBounds(22, 13, 154, 55);
		btnLogin.setIcon(new ImageIcon(btnLoginIcon));

		Image btnNewUserFormIcon = new ImageIcon(this.getClass().getResource("/new-user-32.png")).getImage();
		btnNewUser = new JButton("New user");
		btnNewUser.setBounds(194, 12, 149, 56);
		btnNewUser.setIcon(new ImageIcon(btnNewUserFormIcon));

		Image btnDeleteUserIcon = new ImageIcon(this.getClass().getResource("/delete-user-32.png")).getImage();
		btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.setBounds(361, 12, 149, 56);
		btnDeleteUser.setIcon(new ImageIcon(btnDeleteUserIcon));

		Image btnExitIcon = new ImageIcon(this.getClass().getResource("/close-app-32.png")).getImage();
		btnExit = new JButton("Exit");
		btnExit.setBounds(528, 12, 150, 56);
		btnExit.setIcon(new ImageIcon(btnExitIcon));

		lblUserName = new JLabel("User name:");
		lblUserName.setForeground(Color.LIGHT_GRAY);
		lblUserName.setBounds(28, 31, 81, 15);
		lblUserName.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtUserName = new JTextField();
		txtUserName.setBounds(120, 29, 141, 19);
		txtUserName.setEditable(false);
		txtUserName.setColumns(10);

		lblJobTitle = new JLabel("Job title:");
		lblJobTitle.setForeground(Color.LIGHT_GRAY);
		lblJobTitle.setBounds(28, 56, 56, 15);
		lblJobTitle.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtJobTitle = new JTextField();
		txtJobTitle.setBounds(120, 54, 141, 19);
		txtJobTitle.setEditable(false);
		txtJobTitle.setColumns(10);

		lblUserRights = new JLabel("User rights:");
		lblUserRights.setForeground(Color.LIGHT_GRAY);
		lblUserRights.setBounds(28, 81, 81, 15);
		lblUserRights.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtUserRights = new JTextField();
		txtUserRights.setBounds(120, 79, 141, 19);
		txtUserRights.setEditable(false);
		txtUserRights.setColumns(10);

		partsTableModel = new PartsTableModel();
		table = new JTable();
		table.setModel(partsTableModel);

		table.setFont(new Font("Dialog", Font.PLAIN, 11));
		table.setSelectionBackground(Color.LIGHT_GRAY);

		partsTableModel.setData(controller.dbManagerForParts.getAllParts());
		partsTableModel.fireTableDataChanged();

		ordersTableModel = new OrdersTableModel();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(452, 313, 1455, 695);
		scrollPane.setViewportView(table);

		btnDeletePart = new JButton("Delete part");
		btnDeletePart.setBounds(0, 24, 212, 56);

		btnNewPart = new JButton("New part");
		btnNewPart.setBounds(0, 92, 212, 56);

		Image btnUpdateIcon = new ImageIcon(this.getClass().getResource("/update-32.png")).getImage();
		btnChangePart = new JButton("Change part");
		btnChangePart.setBounds(0, 160, 212, 56);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(0, 228, 212, 56);
		btnUpdate.setIcon(new ImageIcon(btnUpdateIcon));

		Image btnShowPictureIcon = new ImageIcon(this.getClass().getResource("/show-picture-32.png")).getImage();
		btnShowPicture = new JButton("Show picture");
		btnShowPicture.setBounds(0, 296, 212, 56);
		btnShowPicture.setIcon(new ImageIcon(btnShowPictureIcon));

		lblImage = new JLabel("");
		lblImage.setBounds(182, 700, 212, 144);

		lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setForeground(Color.LIGHT_GRAY);
		lblManufacturer.setBounds(17, 34, 104, 15);

		lblPartGroup = new JLabel("Part group:");
		lblPartGroup.setForeground(Color.LIGHT_GRAY);
		lblPartGroup.setBounds(17, 70, 97, 15);

		lblSearch = new JLabel("Search:");
		lblSearch.setForeground(Color.LIGHT_GRAY);
		lblSearch.setBounds(391, 34, 75, 15);
		txtFilter = new JTextField();
		txtFilter.setBounds(467, 31, 208, 22);
		txtFilter.setColumns(10);

		Image btnAddToOrderIcon = new ImageIcon(this.getClass().getResource("/add-to-cart-32.png")).getImage();
		btnAddToOrder = new JButton("Add");
		btnAddToOrder.setBounds(0, 23, 138, 57);
		btnAddToOrder.setIcon(new ImageIcon(btnAddToOrderIcon));

		Image btnOrderIcon = new ImageIcon(this.getClass().getResource("/order-32.png")).getImage();
		btnOrder = new JButton("Order");
		btnOrder.setBounds(0, 92, 138, 57);
		btnOrder.setIcon(new ImageIcon(btnOrderIcon));

		rdbtnParts = new JRadioButton("Parts");
		rdbtnParts.setForeground(Color.LIGHT_GRAY);
		rdbtnParts.setBounds(10, 22, 86, 23);
		rdbtnParts.setBackground(Color.BLACK);
		rdbtnParts.setSelected(true);

		rdbtnOrders = new JRadioButton("Orders");
		rdbtnOrders.setForeground(Color.LIGHT_GRAY);
		rdbtnOrders.setBounds(118, 22, 86, 23);
		rdbtnOrders.setBackground(Color.BLACK);

		bgPartsOrders = new ButtonGroup();
		bgPartsOrders.add(rdbtnParts);
		bgPartsOrders.add(rdbtnOrders);

		prepareComboBoxes();

	}

	private void addActionListenersForControls() {

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int userDecision = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Question",
						JOptionPane.YES_NO_OPTION);

				if (userDecision == JOptionPane.YES_OPTION) {

					closeFrame();
				}
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!user.getLogged()) {

					if (loginFrame == null) {

						closeOtherFramesBeforeOpeningLoginFrme();

						loginFrame = new LoginFrame();
						loginFrame.setVisible(true);

					} else {
						closeOtherFramesBeforeOpeningLoginFrme();
						loginFrame.setVisible(true);
					}

					loginFrame.setUserLoginListener(new UserLoginListener() {

						@Override
						public void loginEventPerformed(User u) {

							user = u;

							btnLogin.setText("Logout");
							refreshForm();
						}
					});

				} else if (user.getLogged()) {

					setEmptyUser();
					refreshForm();

					JOptionPane.showMessageDialog(null, "User successfully logged out", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (newUserFrame == null) {

					newUserFrame = new NewUserFrame();
					newUserFrame.setUser(user);
					newUserFrame.setVisible(true);
				} else {
					newUserFrame.setUser(user);
					newUserFrame.setVisible(true);
				}
			}
		});

		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (user.getLogged()) {

					if (user.getUserRightsLevel() == 1) {

						int userDecision = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to delete account?", "Question", JOptionPane.YES_NO_OPTION);

						if (userDecision == JOptionPane.YES_OPTION) {

							Boolean userSuccessfullyDeleted = controller.dbManagerForUsers.deleteUser(user);

							if (userSuccessfullyDeleted) {

								JOptionPane.showMessageDialog(null, "User successfully deleted", "Message",
										JOptionPane.INFORMATION_MESSAGE);

								setEmptyUser();
								refreshForm();

							} else {
								JOptionPane.showMessageDialog(null, "Some problems appeared, user wasn't deleted!",
										"Warning", JOptionPane.WARNING_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "This user can't be deleted!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "User isn't logged", "Warning", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnDeletePart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletePart();
			}
		});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_DELETE) {
					deletePart();
				}
				if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
					table.setSelectionBackground(Color.LIGHT_GRAY);
				}
			}
		});

		btnNewPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (newPartFrame == null) {
					newPartFrame = new NewPartFrame();
					newPartFrame.setUser(user);
					newPartFrame.setVisible(true);
				} else {
					newPartFrame.setUser(user);
					newPartFrame.setVisible(true);
				}
			}
		});

		btnChangePart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = table.getSelectedRow();

				if (selectedRow > -1) {

					String manufacturer = (String) table.getValueAt(selectedRow, 0);
					String orderCode = (String) table.getValueAt(selectedRow, 3);

					Manufacturers m = controller.dbManagerForParts.getManufacturerByManufacturer(manufacturer);
					Part part = controller.dbManagerForParts.getPartByOrderCodeAndManufacturerId(orderCode,
							m.getManufacturerId());

					changePartFrame = new ChangePartFrame(part);
					changePartFrame.setUser(user);
					changePartFrame.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Please select parts you want to change!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (rdbtnParts.isSelected()) {
					getAllPartsAndRefreshPartsTableModel();
				}

				if (rdbtnOrders.isSelected()) {
					getAllOrdersAndRefreshOrdersTableModel();
				}
			}
		});

		txtFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String query = txtFilter.getText();

				if (rdbtnParts.isSelected()) {
					partsTableFilter(query);
				}

				if (rdbtnOrders.isSelected()) {
					ordersTableFilter(query);
				}
			}
		});

		btnShowPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int result = showImageForSelectedPart();

				if (result == 1) {
					table.setSelectionBackground(Color.GREEN);
				} else if (result == -1) {
					table.setSelectionBackground(Color.ORANGE);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {

				if (mouseEvent.getClickCount() == 2) {
					int result = showImageForSelectedPart();

					if (result == 1) {
						table.setSelectionBackground(Color.GREEN);
					} else if (result == -1) {
						table.setSelectionBackground(Color.ORANGE);
					}
				}

				if (mouseEvent.getClickCount() == 1) {
					table.setSelectionBackground(Color.LIGHT_GRAY);
				}
			}
		});

		cBoxManufacturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getFilteredPartsAndRefreshPartsTableModel();

			}
		});

		cBoxPartGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getFilteredPartsAndRefreshPartsTableModel();

			}
		});

		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (orderFrame != null) {
					orderFrame.dispose();
					orderFrame.setVisible(false);
				}

				if (user.getLogged()) {

					int selectedRow = table.getSelectedRow();

					if (selectedRow > -1) {

						ArrayList<Part> allParts = controller.dbManagerForParts.getAllParts();

						int[] selectedRows = table.getSelectedRows();

						for (int row : selectedRows) {

							String manufacturer = (String) table.getValueAt(row, 0);
							String orderCode = (String) table.getValueAt(row, 3);

							for (Part part : allParts) {
								if (part.getManufacturer().equals(manufacturer)
										&& part.getOrderCode().equals(orderCode)) {

									if (!partsToOrder.contains(part)) {

										partsToOrder.add(part);
										partsToOrderCount++;
									}

									btnAddToOrder.setText("Add(" + partsToOrderCount + ")");

								}
							}
						}
					} else {

						JOptionPane.showMessageDialog(null, "Please select parts you want to order!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {

					JOptionPane.showMessageDialog(null, "User isn't logged!", "Warning", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (user.getLogged()) {

					if (user.getUserRightsLevel() >= 2) {

						if (partsToOrderCount > 0) {

							if (orderFrame == null) {
								orderFrame = new OrderFrame();
								orderFrame.setUser(user);
								orderFrame.setPartsList(partsToOrder, partsToOrderCount);
								orderFrame.setVisible(true);
							} else {
								orderFrame.setUser(user);
								orderFrame.setPartsList(partsToOrder, partsToOrderCount);
								orderFrame.setVisible(true);
							}
						} else {
							JOptionPane.showMessageDialog(null, "You don't have any items added to basket!", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Current user doesn't have rights to create orders!",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "User isn't logged!", "Warning", JOptionPane.WARNING_MESSAGE);
				}

				orderFrame.setItemDeleteListener(new ItemDeleteListener() {

					@Override
					public void itemDeleteEventPerformed(ArrayList<Part> p, Integer c) {

						partsToOrder = p;
						partsToOrderCount = c;
						btnAddToOrder.setText("Add(" + partsToOrderCount + ")");
					}
				});
			}
		});

		rdbtnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnDeletePart.setEnabled(false);
				btnChangePart.setEnabled(false);
				btnShowPicture.setEnabled(false);
				btnAddToOrder.setEnabled(false);
				lblManufacturer.setVisible(false);
				lblPartGroup.setVisible(false);
				cBoxManufacturer.setVisible(false);
				cBoxPartGroup.setVisible(false);

				table.setRowSorter(null);

				table.setModel(ordersTableModel);
				getAllOrdersAndRefreshOrdersTableModel();
			}
		});

		rdbtnParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnDeletePart.setEnabled(true);
				btnChangePart.setEnabled(true);
				btnShowPicture.setEnabled(true);
				btnAddToOrder.setEnabled(true);
				lblManufacturer.setVisible(true);
				lblPartGroup.setVisible(true);
				cBoxManufacturer.setVisible(true);
				cBoxPartGroup.setVisible(true);

				table.setRowSorter(null);

				table.setModel(partsTableModel);
				getAllPartsAndRefreshPartsTableModel();
			}
		});
	}

	private void refreshForm() {

		txtUserName.setText(user.getFirstName() + " " + user.getLastName());
		txtJobTitle.setText(user.getJobTitle());
		txtUserRights.setText(user.getUserRights());

	}

	private void closeFrame() {
		dispose();
		setVisible(false);
	}

	private void setEmptyUser() {

		user.setUserId(0);
		user.setJobTitle("");
		user.setUserRights("");
		user.setUserName("");
		user.setUserPassword("");
		user.setUserEmail("");
		user.setFirstName("");
		user.setLastName("");
		user.setLogged(false);

		btnLogin.setText("Login");
	}

	private void getAllPartsAndRefreshPartsTableModel() {

		partsTableModel.setData(controller.dbManagerForParts.getAllParts());
		partsTableModel.fireTableDataChanged();

		cBoxManufacturer.setSelectedIndex(0);
		cBoxPartGroup.setSelectedIndex(0);

	}

	private void getAllOrdersAndRefreshOrdersTableModel() {

		ordersTableModel.setData(controller.dbManagerForOrders.getAllOrders());
		ordersTableModel.fireTableDataChanged();

		cBoxManufacturer.setSelectedIndex(0);
		cBoxPartGroup.setSelectedIndex(0);

	}

	private void getFilteredPartsAndRefreshPartsTableModel() {

		if ((cBoxManufacturer.getSelectedItem() == "All") && (cBoxPartGroup.getSelectedItem() == "All")) {
			getAllPartsAndRefreshPartsTableModel();
		} else if ((cBoxManufacturer.getSelectedItem() != "All") && (cBoxPartGroup.getSelectedItem() == "All")) {

			partsTableModel.setData(
					controller.dbManagerForParts.getPartsByManufacturer(cBoxManufacturer.getSelectedItem().toString()));
			partsTableModel.fireTableDataChanged();
		} else if ((cBoxManufacturer.getSelectedItem() == "All") && (cBoxPartGroup.getSelectedItem() != "All")) {

			partsTableModel.setData(
					controller.dbManagerForParts.getPartsByPartGroup(cBoxPartGroup.getSelectedItem().toString()));
			partsTableModel.fireTableDataChanged();
		} else {

			partsTableModel.setData(controller.dbManagerForParts.getPartsByManufacturerAndPartGroup(
					cBoxManufacturer.getSelectedItem().toString(), cBoxPartGroup.getSelectedItem().toString()));
			partsTableModel.fireTableDataChanged();
		}
	}

	private void deletePart() {

		if (user.getLogged()) {

			if (user.getUserRightsLevel() >= 2) {

				int selectedRow = table.getSelectedRow();

				if (selectedRow > -1) {

					table.setSelectionBackground(Color.RED);

					int userDecision = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete selected parts?", "Question", JOptionPane.YES_NO_OPTION);

					if (userDecision == JOptionPane.YES_OPTION) {

						ArrayList<Part> allParts = controller.dbManagerForParts.getAllParts();
						ArrayList<Part> partsToDelete = new ArrayList<Part>();

						int[] selectedRows = table.getSelectedRows();

						Boolean partsSuccessfullyDeleted = true;

						for (int row : selectedRows) {

							String manufacturer = (String) table.getValueAt(row, 0);
							String orderCode = (String) table.getValueAt(row, 3);

							for (Part part : allParts) {
								if (part.getManufacturer().equals(manufacturer)
										&& part.getOrderCode().equals(orderCode)) {

									partsToDelete.add(part);

								}
							}
						}

						for (Part part : partsToDelete) {

							Boolean deleteOk = controller.dbManagerForParts.deletePartById(part.getPartId());

							if (!deleteOk) {
								partsSuccessfullyDeleted = false;
							}
						}

						getAllPartsAndRefreshPartsTableModel();

						if (partsSuccessfullyDeleted) {

							JOptionPane.showMessageDialog(null, "Selected part successfully deleted", "Message",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							JOptionPane.showMessageDialog(null, "Some problems appeared, selected parts isn't deleted!",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select parts you want to delete!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Current user doesn't have rights to delete parts!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "User isn't logged!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void partsTableFilter(String query) {

		TableRowSorter<PartsTableModel> sorter = new TableRowSorter<PartsTableModel>(partsTableModel);
		table.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(query));

	}

	private void ordersTableFilter(String query) {

		TableRowSorter<OrdersTableModel> sorter = new TableRowSorter<OrdersTableModel>(ordersTableModel);
		table.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(query));

	}

	private void closeOtherFramesBeforeOpeningLoginFrme() {

		if (!(newUserFrame == null)) {
			newUserFrame.dispose();
			newUserFrame.setVisible(false);
		}

		if (!(newPartFrame == null)) {
			newPartFrame.dispose();
			newPartFrame.setVisible(false);
		}

		if (!(changePartFrame == null)) {
			changePartFrame.dispose();
			changePartFrame.setVisible(false);
		}
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

	private int showImageForSelectedPart() {

		int selectedRow = table.getSelectedRow();
		String orderCode = "";
		String manufacturer = "";

		if (selectedRow != -1) {
			manufacturer = (String) table.getValueAt(selectedRow, 0);
			orderCode = (String) table.getValueAt(selectedRow, 3);

			Manufacturers m = controller.dbManagerForParts.getManufacturerByManufacturer(manufacturer);
			byte[] image = controller.dbManagerForParts.getImageByOrderCodeAndManufacturerId(orderCode,
					m.getManufacturerId());

			if (image != null) {
				lblImage.setIcon(resizeImage(null, image));
				return 1;
			} else {
				lblImage.setIcon(null);
				JOptionPane.showMessageDialog(null, "Image for selected part could not be found!", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return -1;
			}

		} else {
			JOptionPane.showMessageDialog(null, "Please select parts!", "Warning", JOptionPane.WARNING_MESSAGE);
			return 0;
		}
	}

	private void prepareComboBoxes() {

		String[] stringArray;

		ArrayList<String> manufacturerList = controller.dbManagerForParts.getAllManufacturers();
		manufacturerList.add(0, "All");
		ArrayList<String> partGroupList = controller.dbManagerForParts.getAllPartGroups();
		partGroupList.add(0, "All");

		stringArray = manufacturerList.toArray(new String[manufacturerList.size()]);
		cBoxManufacturer = new JComboBox<Object>(stringArray);
		cBoxManufacturer.setBounds(139, 29, 234, 24);
		stringArray = partGroupList.toArray(new String[partGroupList.size()]);
		cBoxPartGroup = new JComboBox<Object>(stringArray);
		cBoxPartGroup.setBounds(139, 65, 234, 24);

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
