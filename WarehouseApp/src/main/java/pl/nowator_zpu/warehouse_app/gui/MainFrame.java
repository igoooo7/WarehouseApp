package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import pl.nowator_zpu.warehouse_app.application_classes.PartsTableModel;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	private GroupLayout gl_contentPane;
	private JPanel contentPane;

	private JLabel lblUserName;
	private JTextField txtUserName;
	private JLabel lblJobTitle;
	private JLabel lblUserRights;
	private JTextField txtJobTitle;
	private JTextField txtUserRights;
	private JButton btnExit;
	private JButton btnLogin;
	private JButton btnNewUser;
	private JButton btnDeleteUser;

	private JScrollPane scrollPane;
	
	LoginFrame loginFrame;
	NewUserFrame newUserFrame;
	
	private PartsTableModel partsTableModel;

	private User user;

	private Controller controller;
	private JTable partsTable;

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

		setTitle("AVERNA Wrocław - Warehouse Management");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true), "User data", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(64, 64, 64)));		
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(94)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btnDeleteUser, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane)
							.addGap(250)))
					.addGap(184))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteUser, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblJobTitle)
						.addComponent(lblUserName)
						.addComponent(lblUserRights))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtJobTitle, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUserRights, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJobTitle)
						.addComponent(txtJobTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserRights)
						.addComponent(txtUserRights, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
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
		getDataForPartsTable();
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(partsTable);
		
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

						loginFrame = new LoginFrame();
						loginFrame.setVisible(true);

					} else {
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

					int userDecision = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete account?",
							"Question", JOptionPane.YES_NO_OPTION);

					if (userDecision == JOptionPane.YES_OPTION) {						

						controller = new Controller();
						Boolean userSuccessfullyDeleted = controller.deleteUser(user);

						if (userSuccessfullyDeleted) {

							JOptionPane.showMessageDialog(null, "User successfully deleted", "Message",
									JOptionPane.INFORMATION_MESSAGE);

							user = new User();
							setEmptyUser();
							refreshForm();

						} else {

							JOptionPane.showMessageDialog(null, "Some problems appeared, user wasn't deleted!",
									"Warning", JOptionPane.INFORMATION_MESSAGE);

						}
					}

				} else {

					JOptionPane.showMessageDialog(null, "User isn't logged", "Message",
							JOptionPane.INFORMATION_MESSAGE);

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
	
	private void getDataForPartsTable() {		
		
				controller = new Controller();			
				partsTableModel.setData(controller.getAllParts());
	}
}
