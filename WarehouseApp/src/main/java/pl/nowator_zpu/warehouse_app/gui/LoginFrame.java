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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;

public class LoginFrame extends JFrame implements WindowListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
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
		
	}

	private void prepareLayout() {

		panel = new JPanel();
		panel.setBounds(26, 13, 267, 143);
		panel.setBackground(new Color(195, 203, 43));
		panel.setLayout(null);
		panel.add(lblUserName);
		panel.add(lblPassword);
		panel.add(passwordField);
		panel.add(txtUserName);
		
		contentPane.setLayout(null);
		contentPane.add(btnLogin);
		contentPane.add(panel);
	}

	private void createControls() {

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(36, 92, 70, 15);
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 90, 124, 19);

		lblUserName = new JLabel("User name:");
		lblUserName.setBounds(28, 45, 78, 15);
		txtUserName = new JTextField();
		txtUserName.setBounds(118, 43, 124, 19);
		txtUserName.setColumns(10);

		btnLogin = new JButton("Enter");
		btnLogin.setBounds(157, 174, 136, 51);
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
						user.setUserPassword("");
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
