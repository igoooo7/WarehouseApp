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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;

public class LoginFrame extends JFrame implements WindowListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupLayout gl_contentPane;
	private JPanel contentPane;
	private JPanel panel;
	
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JTextField txtUserName;
	private JPasswordField passwordField;

	private JButton btnLogin;
	
	private UserLoginListener userLoginListener;

	private User user;

	private Controller controller = new Controller();
	
	

	/**
	 * Create the frame.
	 */
	public LoginFrame() {

		setResizable(false);
		setTitle("Login");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 325, 275);
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

		panel = new JPanel();
		panel.setBackground(new Color(195, 203, 43));

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(25)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(29, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE).addGap(18)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGap(22)));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addGap(28)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblUserName)
								.addComponent(lblPassword))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addComponent(passwordField)
								.addComponent(txtUserName))
						.addGap(25)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(43, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUserName))
						.addGap(28)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(34)));
		panel.setLayout(gl_panel);

	}

	private void createControls() {

		lblPassword = new JLabel("Password:");
		passwordField = new JPasswordField();

		lblUserName = new JLabel("User name:");
		txtUserName = new JTextField();
		txtUserName.setColumns(10);

		btnLogin = new JButton("Enter");
		Image btnLoginIcon = new ImageIcon(this.getClass().getResource("/user-enter-32.png")).getImage();
		btnLogin.setIcon(new ImageIcon(btnLoginIcon));
	}

	public void setUserLoginListener(UserLoginListener userLoginListener) {
		this.userLoginListener = userLoginListener;
	}

	private void addActionListenersForControls() {

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});

		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER) {					
					login();
					
				} else if (keyCode == KeyEvent.VK_ESCAPE) {					
					closeFrame();				}
			}
		});
		
		txtUserName.addKeyListener(this);

	}

	private void login() {

		if (userLoginListener != null) {

			txtUserName.setForeground(Color.black);
			passwordField.setForeground(Color.black);

			char[] pass = passwordField.getPassword();
			String password = new String(pass);

			if (!txtUserName.getText().isEmpty()) {
			
				user = controller.dbManagerForUsers.getUserByUserName(txtUserName.getText());

				if (!(user == null)) {

					if (user.getUserPassword().equals(password)) {

						user.setLogged(true);
						userLoginListener.loginEventPerformed(user);

						JOptionPane.showMessageDialog(null, "User successfully logged!", "Message",
								JOptionPane.INFORMATION_MESSAGE);

						closeFrame();

					} else {
						passwordField.setForeground(Color.red);
						JOptionPane.showMessageDialog(null, "Password is not correct!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					txtUserName.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "User does not exist!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please specify user name!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private void closeFrame() {	
		clearAllTextFields();
		dispose();
		setVisible(false);		
	}
	
	private void clearAllTextFields() {
		txtUserName.setText("");
		passwordField.setText("");
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
