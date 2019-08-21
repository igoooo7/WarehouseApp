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
	private JTextField txtFirstName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JLabel lblUserName;
	private JTextField txtUserName;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel lblJobTitle;
	private JComboBox<Object> cBoxJobTitle;
	private JComboBox<Object> cBoxUserRights;
	private JLabel lblUserRights;
	private JButton btnCreate;

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
		setBounds(100, 100, 400, 450);
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
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(48, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(27)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(29)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE).addGap(30)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGap(385)));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblJobTitle, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFirstName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUserName)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(passwordField, Alignment.TRAILING)
									.addComponent(txtLastName)
									.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
									.addComponent(cBoxJobTitle, 0, 163, Short.MAX_VALUE)
									.addComponent(txtFirstName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblUserRights, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cBoxUserRights, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFirstName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxJobTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJobTitle))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserRights)
						.addComponent(cBoxUserRights, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
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
		passwordField = new JPasswordField();

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

				StringBuilder sb = new StringBuilder();
				char[] pass = passwordField.getPassword();
				String password = new String(pass);

				if (!txtFirstName.getText().isEmpty() && !txtLastName.getText().isEmpty()
						&& !txtUserName.getText().isEmpty() && !txtEmail.getText().isEmpty() && !password.isEmpty()) {

					txtUserName.setForeground(Color.black);

					ArrayList<User> allUsers = new ArrayList<>();
					allUsers = controller.getAllUsers();

					Boolean userAlreadyExist = false;
					for (User user : allUsers) {
						if (user.getUserName().equals(txtUserName.getText())) {
							userAlreadyExist = true;
						}
					}

					if (!userAlreadyExist) {

						JobTitles jt = controller.getJobTitleByTitle(cBoxJobTitle.getSelectedItem().toString());
						UserRights ur = controller.getUserRightsByRights(cBoxUserRights.getSelectedItem().toString());

						if ((user.getUserRightsLevel() < 3 && ur.getUserRightsId() == 3)
								|| (user.getUserRightsLevel() < 2 && ur.getUserRightsId() >= 2)) {

							JOptionPane.showMessageDialog(null, "Current user don't have rights to create new user!",
									"Warning", JOptionPane.INFORMATION_MESSAGE);

						} else {
							Users newUser = new Users();
							newUser.setFirstName(txtFirstName.getText());
							newUser.setLastName(txtLastName.getText());
							newUser.setUserName(txtUserName.getText());
							newUser.setUserEmail(txtEmail.getText());
							newUser.setUserPassword(password);
							newUser.setJobTitles(jt);
							newUser.setUserRights(ur);

							Boolean userSuccessfullyCreated = controller.newUser(newUser);

							if (userSuccessfullyCreated) {

								JOptionPane.showMessageDialog(null, "User successfully created", "Message",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, "Some problems appeared, user wasn't created!",
										"Warning", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					} else {

						txtUserName.setForeground(Color.red);
						JOptionPane.showMessageDialog(null, "User already exist, please specify different user name",
								"Warning", JOptionPane.INFORMATION_MESSAGE);

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
					sb.append(".");

					JOptionPane.showMessageDialog(null, "Please specify:" + sb.toString(), "Warning",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		txtFirstName.addKeyListener(this);
		txtLastName.addKeyListener(this);
		txtUserName.addKeyListener(this);
		txtEmail.addKeyListener(this);

		passwordField.addKeyListener(new KeyAdapter() {
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

		ArrayList<String> jtList = controller.getAllJobTitles();
		ArrayList<String> urList = controller.getAllUserRights();

		array = jtList.toArray(new String[jtList.size()]);
		cBoxJobTitle = new JComboBox<Object>(array);

		array = urList.toArray(new String[urList.size()]);
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
		clearAllTextFieldsBeforeFrameClose();
	}
	
	private void closeFrame() {	
		clearAllTextFieldsBeforeFrameClose();
		dispose();
		setVisible(false);		
	}
	
	private void clearAllTextFieldsBeforeFrameClose() {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtUserName.setText("");
		txtEmail.setText("");
		passwordField.setText("");
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
