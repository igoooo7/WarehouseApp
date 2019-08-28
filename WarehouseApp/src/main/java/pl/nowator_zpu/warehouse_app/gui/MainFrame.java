package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
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

import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.application_classes.PartsTableModel;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.entities.Manufacturers;
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;

public class MainFrame extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupLayout gl_contentPane;
	private JPanel contentPane;

	private JLabel lblUserName;
	private JLabel lblJobTitle;
	private JLabel lblUserRights;
	private JLabel lblNewLabel;
	private JLabel lblImage;

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

	private LoginFrame loginFrame;
	private NewUserFrame newUserFrame;
	private NewPartFrame newPartFrame;
	private ChangePartFrame changePartFrame;

	private PartsTableModel partsTableModel;
	private JTable partsTable;
	private JScrollPane scrollPane;

	private User user;

	private Controller controller;

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
		setBounds(100, 100, 1200, 950);
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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(195, 203, 43));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(195, 203, 43), 2, true), "User data",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(64, 64, 64)));

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(94)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnShowPicture, GroupLayout.PREFERRED_SIZE, 212,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 212,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnChangePart, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane
										.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnNewPart, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
										.addComponent(btnDeletePart, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
								.addGap(64)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtFilter,
														GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														517, Short.MAX_VALUE)
												.addGroup(Alignment.LEADING,
														gl_contentPane.createSequentialGroup()
																.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 154,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(32)
																.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE,
																		149, GroupLayout.PREFERRED_SIZE)
																.addGap(33).addComponent(btnDeleteUser,
																		GroupLayout.PREFERRED_SIZE, 149,
																		GroupLayout.PREFERRED_SIZE))))))
				.addGap(293)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(41)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDeleteUser, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGap(56)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup()
								.addComponent(btnDeletePart, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnNewPart, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnChangePart, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnShowPicture, GroupLayout.PREFERRED_SIZE, 56,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										ComponentPlacement.UNRELATED)
								.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel).addComponent(txtFilter,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)))
						.addContainerGap()));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblJobTitle)
						.addComponent(lblUserName).addComponent(lblUserRights))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtJobTitle, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUserRights, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(28, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblUserName).addComponent(
						txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblJobTitle).addComponent(
						txtJobTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblUserRights).addComponent(
						txtUserRights, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panel.setLayout(gl_panel);

	}

	private void createControls() {

		btnExit = new JButton("Exit");
		Image btnExitIcon = new ImageIcon(this.getClass().getResource("/close-app-32.png")).getImage();
		btnExit.setIcon(new ImageIcon(btnExitIcon));

		btnLogin = new JButton("Login");
		Image btnLoginIcon = new ImageIcon(this.getClass().getResource("/login-form-open-32.png")).getImage();
		btnLogin.setIcon(new ImageIcon(btnLoginIcon));

		btnNewUser = new JButton("New user");
		Image btnNewUserFormIcon = new ImageIcon(this.getClass().getResource("/new-user-32.png")).getImage();
		btnNewUser.setIcon(new ImageIcon(btnNewUserFormIcon));

		btnDeleteUser = new JButton("Delete user");
		Image btnDeleteUserIcon = new ImageIcon(this.getClass().getResource("/delete-user-32.png")).getImage();
		btnDeleteUser.setIcon(new ImageIcon(btnDeleteUserIcon));

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

		refreshPartsTableModel();

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(partsTable);

		btnDeletePart = new JButton("Delete part");

		btnNewPart = new JButton("New part");

		btnChangePart = new JButton("Change part");

		btnUpdate = new JButton("Update");
		Image btnUpdateIcon = new ImageIcon(this.getClass().getResource("/update-32.png")).getImage();
		btnUpdate.setIcon(new ImageIcon(btnUpdateIcon));

		lblNewLabel = new JLabel("Filter:");
		txtFilter = new JTextField();
		txtFilter.setColumns(10);

		btnShowPicture = new JButton("Show picture");
		Image btnShowPictureIcon = new ImageIcon(this.getClass().getResource("/show-picture-32.png")).getImage();
		btnShowPicture.setIcon(new ImageIcon(btnShowPictureIcon));

		lblImage = new JLabel("");

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
			}
		});

		btnNewPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (newPartFrame == null) {
					newPartFrame = new NewPartFrame();
					newPartFrame.setUser(user);
					newPartFrame.setVisible(true);
				} else {
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
				refreshPartsTableModel();
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
				showImageForSelectedPart();
			}
		});

		partsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {

				if (mouseEvent.getClickCount() == 2) {
					showImageForSelectedPart();
				}
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

	private void refreshPartsTableModel() {

		controller = new Controller();
		partsTableModel.setData(controller.dbManagerForParts.getAllParts());
		partsTableModel.fireTableDataChanged();

	}

	private void deletePart() {

		if (user.getLogged()) {

			if (user.getUserRightsLevel() >= 2) {

				int selectedRow = partsTable.getSelectedRow();

				if (selectedRow > -1) {

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

						refreshPartsTableModel();

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

	private void showImageForSelectedPart() {

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
			} else {
				JOptionPane.showMessageDialog(null, "Image for selected part could not be found !", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Please select parts !", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_DELETE) {
			deletePart();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
