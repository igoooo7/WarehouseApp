package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.entities.JobTitles;
import pl.nowator_zpu.warehouse_app.entities.UserRights;
import pl.nowator_zpu.warehouse_app.entities.Users;

public class NewUserFrame extends JFrame implements WindowListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblUserName;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblJobTitle;
	private JLabel lblUserRights;
	private JLabel lblRepeatPass;

	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUserName;
	private JTextField txtEmail;

	private JPasswordField passUserPassword;
	private JPasswordField passUserRepeatedPassword;

	private JButton btnCreate;

	private JComboBox<Object> cBoxJobTitle;
	private JComboBox<Object> cBoxUserRights;

	private User user;

	private Controller controller = new Controller();
	private JPanel panel;

	public NewUserFrame() {

		setResizable(false);
		setTitle("New user");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 395, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		addWindowListener(this);

		createControls();
		addActionListenersForControls();

		prepareLayout();
	
	}

	private void prepareLayout() {

		panel = new JPanel();
		panel.setBounds(35, 38, 327, 326);
		panel.setBackground(new Color(195, 203, 43));
		panel.setLayout(null);
		panel.add(lblEmail);
		panel.add(lblUserName);
		panel.add(lblPassword);
		panel.add(lblLastName);
		panel.add(lblFirstName);
		panel.add(lblRepeatPass);
		panel.add(lblUserRights);
		panel.add(lblJobTitle);
		panel.add(cBoxUserRights);
		panel.add(cBoxJobTitle);
		panel.add(txtUserName);
		panel.add(passUserPassword);
		panel.add(txtLastName);
		panel.add(txtEmail);
		panel.add(passUserRepeatedPassword);
		panel.add(txtFirstName);
		
		contentPane.setLayout(null);
		contentPane.add(btnCreate);
		contentPane.add(panel);

	}

	private void createControls() {

		lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(25, 25, 88, 15);
		txtFirstName = new JTextField();
		txtFirstName.setBounds(131, 23, 171, 19);
		txtFirstName.setColumns(15);

		lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(25, 56, 88, 15);
		txtLastName = new JTextField();
		txtLastName.setBounds(131, 54, 171, 19);
		txtLastName.setColumns(15);

		lblUserName = new JLabel("User name:");
		lblUserName.setBounds(25, 87, 88, 15);
		txtUserName = new JTextField();
		txtUserName.setBounds(131, 85, 171, 19);
		txtUserName.setColumns(15);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(25, 118, 88, 15);
		txtEmail = new JTextField();
		txtEmail.setBounds(131, 116, 171, 19);
		txtEmail.setColumns(25);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(25, 149, 88, 15);
		passUserPassword = new JPasswordField();
		passUserPassword.setBounds(131, 147, 171, 19);

		passUserRepeatedPassword = new JPasswordField();
		passUserRepeatedPassword.setBounds(131, 178, 171, 19);
		lblRepeatPass = new JLabel("Repeat pass:");
		lblRepeatPass.setBounds(25, 180, 88, 15);

		lblJobTitle = new JLabel("Job title:");
		lblJobTitle.setBounds(25, 220, 78, 15);

		lblUserRights = new JLabel("Rights:");
		lblUserRights.setBounds(25, 262, 78, 15);

		btnCreate = new JButton("Create");
		btnCreate.setBounds(226, 439, 136, 51);
		Image btnCreateIcon = new ImageIcon(this.getClass().getResource("/create-user-32.png")).getImage();
		btnCreate.setIcon(new ImageIcon(btnCreateIcon));

		prepareComboBoxes();
	}

	private void addActionListenersForControls() {

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtUserName.setForeground(Color.black);
				passUserPassword.setForeground(Color.black);
				passUserRepeatedPassword.setForeground(Color.black);

				if (user.getLogged()) {

					StringBuilder sb = new StringBuilder();
					char[] pass = passUserPassword.getPassword();
					char[] reppass = passUserRepeatedPassword.getPassword();
					String password = new String(pass);
					String repeatedPassword = new String(reppass);

					if (!txtFirstName.getText().isEmpty() && !txtLastName.getText().isEmpty()
							&& !txtUserName.getText().isEmpty() && !txtEmail.getText().isEmpty() && !password.isEmpty()
							&& !repeatedPassword.isEmpty()) {
						 
						User userToCheck = controller.dbManagerForUsers.getUserByUserName(txtUserName.getText());
						
						if (userToCheck == null) {

							if (password.equals(repeatedPassword)) {

								JobTitles jt = controller.dbManagerForUsers
										.getJobTitleByTitle(cBoxJobTitle.getSelectedItem().toString());
								UserRights ur = controller.dbManagerForUsers
										.getUserRightsByRights(cBoxUserRights.getSelectedItem().toString());

								if ((user.getUserRightsLevel() < 3 && ur.getUserRightsId() == 3)
										|| (user.getUserRightsLevel() < 2 && ur.getUserRightsId() >= 2)) {

									JOptionPane.showMessageDialog(null,
											"Current user doesn't have rights to create new user!", "Warning",
											JOptionPane.WARNING_MESSAGE);

								} else {
									Users newUser = new Users();
									newUser.setFirstName(txtFirstName.getText());
									newUser.setLastName(txtLastName.getText());
									newUser.setUserName(txtUserName.getText());
									newUser.setUserEmail(txtEmail.getText());
									newUser.setUserPassword(password);
									newUser.setJobTitles(jt);
									newUser.setUserRights(ur);

									Boolean userSuccessfullyCreated = controller.dbManagerForUsers.newUser(newUser);

									if (userSuccessfullyCreated) {
										
										clearAllTextFields();
										JOptionPane.showMessageDialog(null, "User successfully created", "Message",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null,
												"Some problems appeared, user wasn't created!", "Warning",
												JOptionPane.WARNING_MESSAGE);
									}
								}

							} else {
								passUserPassword.setForeground(Color.red);
								passUserRepeatedPassword.setForeground(Color.red);
								JOptionPane.showMessageDialog(null, "Password fields doesn't match, please check it!",
										"Warning", JOptionPane.WARNING_MESSAGE);
							}

						} else {

							txtUserName.setForeground(Color.red);
							JOptionPane.showMessageDialog(null,
									"User already exist, please specify different user name", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}

					} else {

						if (txtFirstName.getText().isEmpty()) {
							sb.append(" first name");
						}
						if (txtLastName.getText().isEmpty()) {
							if (!sb.toString().isEmpty()) {
								sb.append(", ");
							}
							sb.append(" last name");
						}
						if (txtUserName.getText().isEmpty()) {
							if (!sb.toString().isEmpty()) {
								sb.append(", ");
							}
							sb.append(" user name");
						}
						if (txtEmail.getText().isEmpty()) {
							if (!sb.toString().isEmpty()) {
								sb.append(", ");
							}
							sb.append(" email");
						}
						if (password.isEmpty()) {
							if (!sb.toString().isEmpty()) {
								sb.append(", ");
							}
							sb.append(" password");
						}
						if (repeatedPassword.isEmpty()) {
							if (!sb.toString().isEmpty()) {
								sb.append(", ");
							}
							sb.append("repeated password");
						}
						sb.append(".");

						JOptionPane.showMessageDialog(null, "Please specify:" + sb.toString(), "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "User isn't logged!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		txtFirstName.addKeyListener(this);
		txtLastName.addKeyListener(this);
		txtUserName.addKeyListener(this);
		txtEmail.addKeyListener(this);

		passUserPassword.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ESCAPE) {
					closeFrame();
				}
			}

		});
	}

	public void setUser(User user) {
		this.user = user;
	}

	private void prepareComboBoxes() {
		 
		String[] array;

		ArrayList<String> jobTitleList = controller.dbManagerForUsers.getAllJobTitles();
		ArrayList<String> userRightsList = controller.dbManagerForUsers.getAllUserRights();

		array = jobTitleList.toArray(new String[jobTitleList.size()]);
		cBoxJobTitle = new JComboBox<Object>(array);
		cBoxJobTitle.setBounds(131, 215, 171, 24);

		array = userRightsList.toArray(new String[userRightsList.size()]);
		cBoxUserRights = new JComboBox<Object>(array);
		cBoxUserRights.setBounds(131, 257, 171, 24);

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
		txtFirstName.setText("");
		txtLastName.setText("");
		txtUserName.setText("");
		txtEmail.setText("");
		passUserPassword.setText("");
		passUserRepeatedPassword.setText("");
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
