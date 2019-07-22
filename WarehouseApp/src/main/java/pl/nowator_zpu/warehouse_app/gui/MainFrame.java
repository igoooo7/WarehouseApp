package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import javassist.bytecode.stackmap.TypeData.ClassName;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());
	
	LoginFrame loginFrame;

	public User user;

	private JPanel contentPane;
	private GroupLayout gl_contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JMenu mnAccount;
	private JMenuItem mntmLogin;
	private JMenuItem mntmLogout;	
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenu mnEdit;
	private JMenuItem mntmData;
	private JMenu mnGenerate;
	private JMenuItem mntmNewItem;
	private JMenuItem mntmNewOrder;	
	private JLabel lblUserName;	
	private JTextField txtUserName;
	private JLabel lblJobTitle;
	private JLabel lblUserRights;
	private JTextField txtJobTitle;
	private JTextField txtUserRights;
	

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
		setBounds(100, 100, 600, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);

		createControls();
		addActionListenersForControls();

		prepareLayout();
		contentPane.setLayout(gl_contentPane);

		// Set new user info
		user = new User(0,"","","","","","",false);	

	}

	private void prepareLayout() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true), "User logged", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(64, 64, 64)));

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1626, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(877, Short.MAX_VALUE))
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
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
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
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

	private void createControls() {
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmData = new JMenuItem("Properties");
		mnEdit.add(mntmData);

		mnAccount = new JMenu("User");
		menuBar.add(mnAccount);

		mntmLogin = new JMenuItem("Login");
		mnAccount.add(mntmLogin);

		mntmLogout = new JMenuItem("Logout");
		mnAccount.add(mntmLogout);
		
		mnGenerate = new JMenu("Add");
		menuBar.add(mnGenerate);
		
		mntmNewItem = new JMenuItem("New Item");
		mnGenerate.add(mntmNewItem);
		
		mntmNewOrder = new JMenuItem("New Order");
		mnGenerate.add(mntmNewOrder);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
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
	}

	private void addActionListenersForControls() {

		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int userDecision = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Question",
						JOptionPane.YES_NO_OPTION);

				if (userDecision == JOptionPane.YES_OPTION) {
					
					setVisible(false);
					dispose();
				}
			}
		});

		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				

				if (!user.getLogged() && loginFrame == null) {
					loginFrame = new LoginFrame();
					loginFrame.setUserLoginListener(new UserLoginListener() {
						
						@Override
						public void loginEventPerformed(User ui) {
							
							user = ui;
							refreshForm();		
						}		
					});	
					
					loginFrame.setVisible(true);	
					
				} else if (user.getLogged()) {
					
					JOptionPane.showMessageDialog(null, "User is already logged!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					loginFrame.setVisible(true);
				}
			}
		});

		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				user.setUserId(0);
				user.setJobTitle("");
				user.setUserRights("");
				user.setUserName("");
				user.setUserPassword("");
				user.setFirstName("");
				user.setLastName("");	
				user.setLogged(false);
				
				refreshForm();
				
				JOptionPane.showMessageDialog(null, "User successfully logged out", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}
	
	private void refreshForm() {
		
		txtUserName.setText(user.getFirstName() + " " + user.getLastName());
		txtJobTitle.setText(user.getJobTitle());
		txtUserRights.setText(user.getUserRights());
		
	}
}
