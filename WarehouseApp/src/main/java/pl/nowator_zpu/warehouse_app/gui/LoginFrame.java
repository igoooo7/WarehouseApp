package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.entities.Users;
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;

public class LoginFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private GroupLayout gl_contentPane;
	private JButton btnLogin;
	private JButton btnNewUserForm;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JTextField txtUsername;
	private JTextField txtPassword;

	private UserLoginListener userLoginListener;
	public User user;

	Controller controller;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setTitle("Login");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 275);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);

		createControls();
		addActionListenersForControls();

		prepareLayout();
		contentPane.setLayout(gl_contentPane);

	}

	private void prepareLayout() {

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(100, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(btnLogin)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblUserName)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtUsername,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 78,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtPassword,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))))
						.addGap(99))
						.addGroup(Alignment.TRAILING,
								gl_contentPane
										.createSequentialGroup().addComponent(btnNewUserForm,
												GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(54, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblUserName).addComponent(
						txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(22)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword).addComponent(
						txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnLogin).addGap(48)
				.addComponent(btnNewUserForm).addContainerGap()));

	}

	private void createControls() {

		btnLogin = new JButton("login");
		Image btnLoginIcon = new ImageIcon(this.getClass().getResource("/icons8-key-16.png")).getImage();
		btnLogin.setIcon(new ImageIcon(btnLoginIcon));

		btnNewUserForm = new JButton("Create new account");
		Image btnNewUserFormIcon = new ImageIcon(this.getClass().getResource("/icons8-customer-16.png")).getImage();
		btnNewUserForm.setIcon(new ImageIcon(btnNewUserFormIcon));

		lblUserName = new JLabel("User name:");

		lblPassword = new JLabel("Password:");

		txtUsername = new JTextField();
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);

	}

	public void setUserLoginListener(UserLoginListener userLoginListener) {

		this.userLoginListener = userLoginListener;

	}

	private void addActionListenersForControls() {

		btnLogin.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Login attempt
		JButton source = (JButton) e.getSource();

		if (userLoginListener != null) {
			
			if (source == btnLogin) {				
				
				txtUsername.setForeground(Color.black);
				txtPassword.setForeground(Color.black);

				if (!txtUsername.getText().isEmpty()) {

					controller = new Controller();
					user = controller.getUserByUserName(txtUsername.getText());

					if (!(user == null)) {

						if (user.getUserPassword().equals(txtPassword.getText())) {

							userLoginListener.loginEventPerformed(user);

							JOptionPane.showMessageDialog(null, "User successfully logged!", "Message",
									JOptionPane.INFORMATION_MESSAGE);

							dispose();

						} else {
							
							txtPassword.setForeground(Color.red);
							JOptionPane.showMessageDialog(null, "Password is not correct!", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						
						txtUsername.setForeground(Color.red);
						JOptionPane.showMessageDialog(null, "User does not exist!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					
					JOptionPane.showMessageDialog(null, "Please specify user name!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}
