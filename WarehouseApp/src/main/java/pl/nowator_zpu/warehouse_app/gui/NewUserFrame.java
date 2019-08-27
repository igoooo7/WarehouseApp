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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.entities.JobTitles;
import pl.nowator_zpu.warehouse_app.entities.UserRights;
import pl.nowator_zpu.warehouse_app.entities.Users;

public class NewUserFrame extends JFrame implements WindowListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupLayout gl_contentPane;
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

	private Controller controller;

	/**
	 * Create the frame.
	 */
	public NewUserFrame() {

		setResizable(false);
		setTitle("New user");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
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
		panel.setBackground(new Color(175, 185, 0));

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPane.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGap(27)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(32)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE).addGap(80)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGap(291)));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(25)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(lblFirstName)).addComponent(lblRepeatPass, GroupLayout.PREFERRED_SIZE, 78,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(txtUserName)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(passUserPassword, Alignment.TRAILING).addComponent(txtLastName)
										.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
										.addComponent(txtFirstName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 155,
												Short.MAX_VALUE)
										.addComponent(passUserRepeatedPassword, GroupLayout.PREFERRED_SIZE, 171,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblUserRights, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblJobTitle, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(cBoxUserRights, 0, 171, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(cBoxJobTitle, 0, 171, Short.MAX_VALUE))))
				.addGap(43)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(23)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFirstName))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastName))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblUserName).addComponent(
						txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword).addComponent(
						passUserPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passUserRepeatedPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRepeatPass))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxJobTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJobTitle))
				.addGap(18)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(cBoxUserRights,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserRights))
				.addGap(26)));
		panel.setLayout(gl_panel);

	}

	private void createControls() {

		lblFirstName = new JLabel("First name:");
		txtFirstName = new JTextField();
		txtFirstName.setColumns(15);

		lblLastName = new JLabel("Last name:");
		txtLastName = new JTextField();
		txtLastName.setColumns(15);

		lblUserName = new JLabel("User name:");
		txtUserName = new JTextField();
		txtUserName.setColumns(15);

		lblEmail = new JLabel("Email:");
		txtEmail = new JTextField();
		txtEmail.setColumns(25);

		lblPassword = new JLabel("Password:");
		passUserPassword = new JPasswordField();

		passUserRepeatedPassword = new JPasswordField();
		lblRepeatPass = new JLabel("Repeat pass:");

		lblJobTitle = new JLabel("Job title:");

		lblUserRights = new JLabel("Rights:");

		btnCreate = new JButton("Create");
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

						ArrayList<User> allUsers = new ArrayList<>();
						allUsers = controller.dbManagerForUsers.getAllUsers();

						Boolean userAlreadyExist = false;
						for (User user : allUsers) {
							if (user.getUserName().equals(txtUserName.getText())) {
								userAlreadyExist = true;
							}
						}

						if (!userAlreadyExist) {

							if (password.equals(repeatedPassword)) {

								JobTitles jt = controller.dbManagerForUsers
										.getJobTitleByTitle(cBoxJobTitle.getSelectedItem().toString());
								UserRights ur = controller.dbManagerForUsers
										.getUserRightsByRights(cBoxUserRights.getSelectedItem().toString());

								if ((user.getUserRightsLevel() < 3 && ur.getUserRightsId() == 3)
										|| (user.getUserRightsLevel() < 2 && ur.getUserRightsId() >= 2)) {

									JOptionPane.showMessageDialog(null,
											"Current user don't have rights to create new user!", "Warning",
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

		controller = new Controller();
		String[] array;

		ArrayList<String> jobTitleList = controller.dbManagerForUsers.getAllJobTitles();
		ArrayList<String> userRightsList = controller.dbManagerForUsers.getAllUserRights();

		array = jobTitleList.toArray(new String[jobTitleList.size()]);
		cBoxJobTitle = new JComboBox<Object>(array);

		array = userRightsList.toArray(new String[userRightsList.size()]);
		cBoxUserRights = new JComboBox<Object>(array);

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
