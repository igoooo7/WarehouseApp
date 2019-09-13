package pl.nowator_zpu.warehouse_app.gui;

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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import pl.nowator_zpu.warehouse_app.application_classes.Order;
import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.application_classes.PartsTableModel;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.entities.Manufacturers;
import pl.nowator_zpu.warehouse_app.interfaces.ItemDeleteListener;
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;
import javax.swing.JComboBox;
import java.awt.GridLayout;

public class MainFrame extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupLayout gl_contentPane;
	private JPanel contentPane;

	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;

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

	private JComboBox<Object> cBoxManufacturer;
	private JComboBox<Object> cBoxPartGroup;

	private LoginFrame loginFrame;
	private NewUserFrame newUserFrame;
	private NewPartFrame newPartFrame;
	private ChangePartFrame changePartFrame;
	private OrderFrame orderFrame;

	private PartsTableModel partsTableModel;
	private JTable partsTable;
	private JScrollPane scrollPane;

	private User user;

	private Controller controller;

	private ArrayList<Part> partsToOrder = new ArrayList<>();
	private Integer partsToOrderCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

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

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		setTitle("AVERNA Wrocław - Warehouse Management V1.0.0");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 1200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);

		createControls();
		addActionListenersForControls();

		prepareLayout();
		contentPane.setLayout(gl_contentPane);

		user = new User();

	}

	private void prepareLayout() {

		panel1 = new JPanel();
		panel1.setBackground(new Color(195, 203, 43));
		panel1.setBorder(new TitledBorder(new LineBorder(new Color(195, 203, 43), 2, true), "User data",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(64, 64, 64)));

		panel2 = new JPanel();
		panel2.setBackground(new Color(119, 136, 153));

		panel3 = new JPanel();
		panel3.setBackground(new Color(119, 136, 153));

		panel4 = new JPanel();
		panel4.setBackground(new Color(119, 136, 153));

		panel5 = new JPanel();
		panel5.setBackground(new Color(119, 136, 153));

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(19, Short.MAX_VALUE)
								.addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 212,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(66)
								.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(panel2,
										GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(30)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1455,
														Short.MAX_VALUE)
												.addComponent(panel4, GroupLayout.PREFERRED_SIZE, 701,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(36)
								.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(78)
								.addComponent(panel4, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(49).addComponent(panel1,
								GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(panel5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panel3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 369,
												Short.MAX_VALUE))
								.addGap(18)
								.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));

		GroupLayout gl_panel5 = new GroupLayout(panel5);
		gl_panel5.setHorizontalGroup(gl_panel5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel5.createSequentialGroup()
						.addGroup(gl_panel5.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnOrder, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addComponent(btnAddToOrder, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel5.setVerticalGroup(gl_panel5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel5.createSequentialGroup().addGap(23)
						.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(220, Short.MAX_VALUE)));
		panel5.setLayout(gl_panel5);
		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(gl_panel4.createParallelGroup(Alignment.LEADING).addGroup(gl_panel4
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING).addGroup(gl_panel4.createSequentialGroup()
						.addComponent(lblManufacturer).addGap(24)
						.addComponent(cBoxManufacturer, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lblSearch).addGap(24)
						.addComponent(txtFilter, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel4.createSequentialGroup()
								.addComponent(lblPartGroup, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(25).addComponent(cBoxPartGroup, GroupLayout.PREFERRED_SIZE, 234,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel4.setVerticalGroup(gl_panel4.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel4.createSequentialGroup().addContainerGap(73, Short.MAX_VALUE)
						.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel4.createSequentialGroup().addGap(5).addComponent(lblManufacturer))
								.addComponent(cBoxManufacturer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel4.createSequentialGroup().addGap(5).addComponent(lblSearch))
								.addGroup(gl_panel4.createSequentialGroup().addGap(2).addComponent(txtFilter,
										GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addGap(12)
						.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel4.createSequentialGroup().addGap(5).addComponent(lblPartGroup))
								.addComponent(cBoxPartGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(25)));
		panel4.setLayout(gl_panel4);

		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup().addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDeletePart, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewPart, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChangePart, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowPicture, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_panel3
				.setVerticalGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel3.createSequentialGroup().addGap(24)
								.addComponent(btnDeletePart, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnNewPart, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnChangePart, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnShowPicture,
										GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(26, Short.MAX_VALUE)));
		panel3.setLayout(gl_panel3);
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup().addContainerGap()
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnDeleteUser, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(63, Short.MAX_VALUE)));
		gl_panel2.setVerticalGroup(gl_panel2.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel2.createSequentialGroup().addContainerGap(21, Short.MAX_VALUE)
						.addGroup(gl_panel2.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDeleteUser, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panel2.setLayout(gl_panel2);

		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup().addGap(23)
						.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUserRights, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJobTitle, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_panel1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
										.addComponent(txtJobTitle, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtUserRights, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_panel1.setVerticalGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUserName))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtJobTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJobTitle))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUserRights, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUserRights))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel1.setLayout(gl_panel1);

	}

	private void createControls() {

		Image btnLoginIcon = new ImageIcon(this.getClass().getResource("/login-form-open-32.png")).getImage();
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(btnLoginIcon));

		Image btnNewUserFormIcon = new ImageIcon(this.getClass().getResource("/new-user-32.png")).getImage();
		btnNewUser = new JButton("New user");
		btnNewUser.setIcon(new ImageIcon(btnNewUserFormIcon));

		Image btnDeleteUserIcon = new ImageIcon(this.getClass().getResource("/delete-user-32.png")).getImage();
		btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.setIcon(new ImageIcon(btnDeleteUserIcon));

		Image btnExitIcon = new ImageIcon(this.getClass().getResource("/close-app-32.png")).getImage();
		btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(btnExitIcon));

		lblUserName = new JLabel("User name:");
		lblUserName.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtUserName = new JTextField();
		txtUserName.setEditable(false);
		txtUserName.setColumns(10);

		lblJobTitle = new JLabel("Job title:");
		lblJobTitle.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtJobTitle = new JTextField();
		txtJobTitle.setEditable(false);
		txtJobTitle.setColumns(10);

		lblUserRights = new JLabel("User rights:");
		lblUserRights.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtUserRights = new JTextField();
		txtUserRights.setEditable(false);
		txtUserRights.setColumns(10);

		partsTableModel = new PartsTableModel();
		partsTable = new JTable(partsTableModel);
		partsTable.setFont(new Font("Dialog", Font.PLAIN, 11));
		partsTable.setSelectionBackground(Color.LIGHT_GRAY);

		controller = new Controller();
		partsTableModel.setData(controller.dbManagerForParts.getAllParts());
		partsTableModel.fireTableDataChanged();

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(partsTable);

		btnDeletePart = new JButton("Delete part");

		btnNewPart = new JButton("New part");

		Image btnUpdateIcon = new ImageIcon(this.getClass().getResource("/update-32.png")).getImage();
		btnChangePart = new JButton("Change part");

		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(btnUpdateIcon));

		Image btnShowPictureIcon = new ImageIcon(this.getClass().getResource("/show-picture-32.png")).getImage();
		btnShowPicture = new JButton("Show picture");
		btnShowPicture.setIcon(new ImageIcon(btnShowPictureIcon));

		lblImage = new JLabel("");

		lblManufacturer = new JLabel("Manufacturer:");

		lblPartGroup = new JLabel("Part group:");

		lblSearch = new JLabel("Search:");
		txtFilter = new JTextField();
		txtFilter.setColumns(10);

		Image btnAddToOrderIcon = new ImageIcon(this.getClass().getResource("/add-to-cart-32.png")).getImage();
		btnAddToOrder = new JButton("Add");
		btnAddToOrder.setIcon(new ImageIcon(btnAddToOrderIcon));

		Image btnOrderIcon = new ImageIcon(this.getClass().getResource("/order-32.png")).getImage();
		btnOrder = new JButton("Order");
		btnOrder.setIcon(new ImageIcon(btnOrderIcon));

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

							controller = new Controller();
							Boolean userSuccessfullyDeleted = controller.dbManagerForUsers.deleteUser(user);

							if (userSuccessfullyDeleted) {

								JOptionPane.showMessageDialog(null, "User successfully deleted", "Message",
										JOptionPane.INFORMATION_MESSAGE);

								user = new User();
								setEmptyUser();
								refreshForm();

							} else {
								JOptionPane.showMessageDialog(null, "Some problems appeared, user wasn't deleted!",
										"Warning", JOptionPane.WARNING_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "This user could not be deleted !", "Warning",
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

		partsTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_DELETE) {
					deletePart();
				}
				if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
					partsTable.setSelectionBackground(Color.LIGHT_GRAY);
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

				int selectedRow = partsTable.getSelectedRow();

				if (selectedRow > -1) {

					String manufacturer = (String) partsTable.getValueAt(selectedRow, 0);
					String orderCode = (String) partsTable.getValueAt(selectedRow, 3);

					controller = new Controller();

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
				getAllPartsAndRefreshPartsTableModel();
			}
		});

		txtFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String query = txtFilter.getText();
				partsTableFilter(query);
			}
		});

		btnShowPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int result = showImageForSelectedPart();

				if (result == 1) {
					partsTable.setSelectionBackground(Color.GREEN);
				} else if (result == -1) {
					partsTable.setSelectionBackground(Color.ORANGE);
				}
			}
		});

		partsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {

				if (mouseEvent.getClickCount() == 2) {
					int result = showImageForSelectedPart();

					if (result == 1) {
						partsTable.setSelectionBackground(Color.GREEN);
					} else if (result == -1) {
						partsTable.setSelectionBackground(Color.ORANGE);
					}
				}

				if (mouseEvent.getClickCount() == 1) {
					partsTable.setSelectionBackground(Color.LIGHT_GRAY);
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

					int selectedRow = partsTable.getSelectedRow();

					if (selectedRow > -1) {

						controller = new Controller();
						ArrayList<Part> allParts = controller.dbManagerForParts.getAllParts();

						int[] selectedRows = partsTable.getSelectedRows();

						for (int row : selectedRows) {

							String manufacturer = (String) partsTable.getValueAt(row, 0);
							String orderCode = (String) partsTable.getValueAt(row, 3);

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

		controller = new Controller();
		partsTableModel.setData(controller.dbManagerForParts.getAllParts());
		partsTableModel.fireTableDataChanged();

		cBoxManufacturer.setSelectedIndex(0);
		cBoxPartGroup.setSelectedIndex(0);

	}

	private void getFilteredPartsAndRefreshPartsTableModel() {

		controller = new Controller();

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

				int selectedRow = partsTable.getSelectedRow();

				if (selectedRow > -1) {

					partsTable.setSelectionBackground(Color.RED);

					int userDecision = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete selected parts?", "Question", JOptionPane.YES_NO_OPTION);

					if (userDecision == JOptionPane.YES_OPTION) {

						controller = new Controller();
						ArrayList<Part> allParts = controller.dbManagerForParts.getAllParts();
						ArrayList<Part> partsToDelete = new ArrayList<Part>();

						int[] selectedRows = partsTable.getSelectedRows();

						Boolean partsSuccessfullyDeleted = true;

						for (int row : selectedRows) {

							String manufacturer = (String) partsTable.getValueAt(row, 0);
							String orderCode = (String) partsTable.getValueAt(row, 3);

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

				JOptionPane.showMessageDialog(null, "Current user don't have rights to delete parts!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		else {

			JOptionPane.showMessageDialog(null, "User isn't logged!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void partsTableFilter(String query) {

		TableRowSorter<PartsTableModel> sorter = new TableRowSorter<PartsTableModel>(partsTableModel);
		partsTable.setRowSorter(sorter);
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

		int selectedRow = partsTable.getSelectedRow();
		String orderCode = "";
		String manufacturer = "";

		if (selectedRow != -1) {
			manufacturer = (String) partsTable.getValueAt(selectedRow, 0);
			orderCode = (String) partsTable.getValueAt(selectedRow, 3);

			controller = new Controller();

			Manufacturers m = controller.dbManagerForParts.getManufacturerByManufacturer(manufacturer);
			byte[] image = controller.dbManagerForParts.getImageByOrderCodeAndManufacturerId(orderCode,
					m.getManufacturerId());

			if (image != null) {
				lblImage.setIcon(resizeImage(null, image));
				return 1;
			} else {
				JOptionPane.showMessageDialog(null, "Image for selected part could not be found !", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return -1;
			}

		} else {
			JOptionPane.showMessageDialog(null, "Please select parts !", "Warning", JOptionPane.WARNING_MESSAGE);
			return 0;
		}
	}

	private void prepareComboBoxes() {

		controller = new Controller();
		String[] stringArray;

		ArrayList<String> manufacturerList = controller.dbManagerForParts.getAllManufacturers();
		manufacturerList.add(0, "All");
		ArrayList<String> partGroupList = controller.dbManagerForParts.getAllPartGroups();
		partGroupList.add(0, "All");

		stringArray = manufacturerList.toArray(new String[manufacturerList.size()]);
		cBoxManufacturer = new JComboBox<Object>(stringArray);
		stringArray = partGroupList.toArray(new String[partGroupList.size()]);
		cBoxPartGroup = new JComboBox<Object>(stringArray);

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
