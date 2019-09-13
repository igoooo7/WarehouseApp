package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
import pl.nowator_zpu.warehouse_app.application_classes.Order;
import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.interfaces.ItemDeleteListener;
import pl.nowator_zpu.warehouse_app.interfaces.UserLoginListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderFrame extends JFrame implements WindowListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupLayout gl_contentPane;
	private JPanel contentPane;
	private JPanel panel;

	private JLabel lblManufacturer;
	private JLabel lblOrderCode;
	private JLabel lblProject;
	private JLabel lblCount;
	private JLabel lblPartNumber;

	private JTextField txtManufacturer;
	private JTextField txtOrderCode;
	private JTextField txtCount;

	private JButton btnNextPart;
	private JButton btnPreviousPart;
	private JButton btnClearAll;
	private JButton btnDeleteItem;
	private JButton btnSave;
	private JButton btnOk;
	
	JComboBox<Object> cBoxProject;

	private ArrayList<Part> partList = new ArrayList<>();;
	private ArrayList<Order> orderList = new ArrayList<>();

	private Integer partsCount;
	private Integer partNumber = 1;

	private ItemDeleteListener itemDeleteListener;

	private User user;

	private Controller controller;


	/**
	 * Create the frame.
	 */
	public OrderFrame() {

		setResizable(false);
		setTitle("Order list");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 450);
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
				.addGroup(gl_contentPane.createSequentialGroup().addGap(22)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(27, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane
						.createSequentialGroup().addGap(18).addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(299, Short.MAX_VALUE)
					.addComponent(btnPreviousPart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNextPart)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblManufacturer)
								.addComponent(lblOrderCode, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCount, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtOrderCode)
									.addComponent(txtManufacturer, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(txtCount, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnOk)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnDeleteItem, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnClearAll, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cBoxProject, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))))
					.addGap(23))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(392, Short.MAX_VALUE)
					.addComponent(lblPartNumber)
					.addGap(34))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNextPart)
						.addComponent(btnPreviousPart))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPartNumber)
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtManufacturer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblManufacturer))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrderCode)
						.addComponent(txtOrderCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCount)
						.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCount, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProject))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(btnClearAll, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeleteItem, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(27))
		);
		panel.setLayout(gl_panel);

	}

	public void setItemDeleteListener(ItemDeleteListener itemDeleteListener) {
		this.itemDeleteListener = itemDeleteListener;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private void createControls() {

		lblManufacturer = new JLabel("Manufacturer:");
		txtManufacturer = new JTextField();
		txtManufacturer.setEditable(false);
		txtManufacturer.setColumns(10);

		lblOrderCode = new JLabel("Order code:");
		txtOrderCode = new JTextField();
		txtOrderCode.setEditable(false);
		txtOrderCode.setColumns(10);

		lblProject = new JLabel("Project:");

		lblCount = new JLabel("Count:");
		txtCount = new JTextField();
		txtCount.setColumns(10);

		btnNextPart = new JButton("next");

		btnPreviousPart = new JButton("prev");

		lblPartNumber = new JLabel("1/n");

		btnClearAll = new JButton("Delete order");

		btnDeleteItem = new JButton("Delete item");

		btnSave = new JButton("Save");
		Image btnSaveIcon = new ImageIcon(this.getClass().getResource("/save-32.png")).getImage();
		btnSave.setIcon(new ImageIcon(btnSaveIcon));

		btnOk = new JButton("ok");
				
		prepareComboBoxes();

	}

	private void prepareComboBoxes() {

		controller = new Controller();
		String[] stringArray;

		ArrayList<String> projectList = controller.dbManagerForOrders.getAllProjects();

		stringArray = projectList.toArray(new String[projectList.size()]);
		cBoxProject = new JComboBox<Object>(stringArray);

	}

	private void addActionListenersForControls() {

		btnNextPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (partNumber < partsCount) {
					partNumber++;

					refreshForm();
				}
			}
		});

		btnPreviousPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (partNumber > 1) {
					partNumber--;
				
					refreshForm();
				}
			}
		});

		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (partsCount > 0) {

					partList.clear();
					orderList.clear();

					partNumber = 1;
					partsCount = 0;

					lblPartNumber.setVisible(false);

					clearAllTextFields();

					itemDeleteListener.itemDeleteEventPerformed(partList, partsCount);

				}
			}
		});

		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (partsCount > 0) {

					partList.remove(partNumber - 1);
					orderList.remove(partNumber - 1);

					partNumber = 1;
					partsCount--;

					if (partsCount > 0) {
						refreshForm();
					} else {
						clearAllTextFields();
						lblPartNumber.setVisible(false);
					}

					itemDeleteListener.itemDeleteEventPerformed(partList, partsCount);

				}
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setPartCount();
				refreshForm();				
			}
		});
	}

	private void refreshForm() {
		txtManufacturer.setText(orderList.get(partNumber - 1).getManufacturer());
		txtOrderCode.setText(orderList.get(partNumber - 1).getOrderCode());
		txtCount.setText(orderList.get(partNumber - 1).getPartCount().toString());

		showPartCount();
	}

	private void closeFrame() {
		clearAllTextFields();
		dispose();
		setVisible(false);
	}

	private void clearAllTextFields() {
		txtManufacturer.setText("");
		txtOrderCode.setText("");
		txtCount.setText("");

	}

	public void setPartsList(ArrayList<Part> partsToOrder, Integer partsToOrderCount) {

		partList = partsToOrder;
		partsCount = partsToOrderCount;

		setOrderList();
		refreshForm();

	}

	private void setOrderList() {

		for (Part part : partList) {

			Order order = new Order();
			order.setManufacturer(part.getManufacturer());
			order.setOrderCode(part.getOrderCode());
			order.setPartCount(0);

			if (!orderList.contains(order)) {
				orderList.add(order);
			}
		}
	}

	private void showPartCount() {

		lblPartNumber.setVisible(true);
		lblPartNumber.setText(partNumber + "/" + partsCount);
	}

	private void setPartCount() {

		try {
			Order o = orderList.get(partNumber - 1);
			o.setPartCount(Integer.parseInt(txtCount.getText()));
			orderList.set(partNumber - 1, o);
		} catch (Exception e) {
			txtCount.setText("0");

			JOptionPane.showMessageDialog(null, "Please enter a valid values for quantity field !", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
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
