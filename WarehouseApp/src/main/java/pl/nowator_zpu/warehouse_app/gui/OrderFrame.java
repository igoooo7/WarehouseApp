package pl.nowator_zpu.warehouse_app.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import pl.nowator_zpu.warehouse_app.application_classes.Order;
import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.data_access.Controller;
import pl.nowator_zpu.warehouse_app.entities.Manufacturers;
import pl.nowator_zpu.warehouse_app.entities.Orders;
import pl.nowator_zpu.warehouse_app.entities.Projects;
import pl.nowator_zpu.warehouse_app.entities.Users;
import pl.nowator_zpu.warehouse_app.interfaces.ItemDeleteListener;
import javax.swing.JTextArea;

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
	private JLabel lblDescription;

	private JTextField txtManufacturer;
	private JTextField txtOrderCode;
	private JTextField txtCount;
	
	private JTextArea txtrDescription;

	private JButton btnNextPart;
	private JButton btnPreviousPart;
	private JButton btnDeleteAll;
	private JButton btnDeleteItem;
	private JButton btnSave;
	private JButton btnOk;

	private JComboBox<Object> cBoxProject;

	private ArrayList<Part> partList = new ArrayList<>();
	private ArrayList<Order> orderList = new ArrayList<>();

	private Integer partsCount;
	private Integer partNumber = 1;

	private ItemDeleteListener itemDeleteListener;

	private User user;

	private Controller controller = new Controller();

	/**
	 * Create the frame.
	 */
	public OrderFrame() {

		setResizable(false);
		setTitle("Order list");
		Image formIcon = new ImageIcon(this.getClass().getResource("/averna_ico.png")).getImage();
		setIconImage(formIcon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 515);
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
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(198, Short.MAX_VALUE)
					.addComponent(btnPreviousPart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNextPart)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPartNumber)
					.addGap(32))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(274, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnOk)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnDeleteItem, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDeleteAll, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSave))
								.addComponent(cBoxProject, Alignment.LEADING, 0, 337, Short.MAX_VALUE))
							.addGap(30))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblManufacturer)
									.addComponent(lblOrderCode, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDescription, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCount, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtCount, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtManufacturer, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(txtOrderCode)
								.addComponent(txtrDescription))
							.addGap(32))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNextPart)
								.addComponent(btnPreviousPart)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(36)
							.addComponent(lblPartNumber)))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtManufacturer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblManufacturer))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOrderCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrderCode))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCount)
						.addComponent(txtCount, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(txtrDescription, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(lblProject)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cBoxProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeleteItem, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteAll, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
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

		btnNextPart = new JButton();
		Image btnNextIcon = new ImageIcon(this.getClass().getResource("/forward-16.png")).getImage();
		btnNextPart.setIcon(new ImageIcon(btnNextIcon));

		btnPreviousPart = new JButton();
		Image btnPreviousIcon = new ImageIcon(this.getClass().getResource("/back-16.png")).getImage();
		btnPreviousPart.setIcon(new ImageIcon(btnPreviousIcon));
		
		lblDescription = new JLabel("Description:");
		
		txtrDescription = new JTextArea((String) null);
		txtrDescription.setEditable(false);
		txtrDescription.setLineWrap(true);
		txtrDescription.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 13));

		lblPartNumber = new JLabel("1 of n");

		btnDeleteAll = new JButton("Delete All");

		btnDeleteItem = new JButton("Delete item");

		btnSave = new JButton("Save");

		Image btnSaveIcon = new ImageIcon(this.getClass().getResource("/save-32.png")).getImage();
		btnSave.setIcon(new ImageIcon(btnSaveIcon));

		btnOk = new JButton("ok");

		prepareComboBoxes();

	}

	private void prepareComboBoxes() {

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

		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int userDecision = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all items?",
						"Question", JOptionPane.YES_NO_OPTION);

				if (userDecision == JOptionPane.YES_OPTION) {

					if (partsCount > 0) {

						partList.clear();
						orderList.clear();

						partNumber = 1;
						partsCount = 0;

						lblPartNumber.setVisible(false);

						clearAllTextFields();

						itemDeleteListener.itemDeleteEventPerformed(partList, partsCount);

						closeFrame();

					}
				}
			}
		});

		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int userDecision = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
						"Question", JOptionPane.YES_NO_OPTION);

				if (userDecision == JOptionPane.YES_OPTION) {

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

						if (partsCount == 0) {
							closeFrame();
						}
					}
				}
			}
		});

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setPartCount();
				assignProjectToPart();
				refreshForm();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (user.getLogged()) {

					if (user.getUserRightsLevel() >= 2) {

						Boolean partCountOk = true;

						for (Order order : orderList) {

							if (order.getPartCount() == 0) {
								partCountOk = false;
							}
						}

						if (partCountOk) {

							Boolean allOrdersSuccessfullyCreated = true;

							for (Order order : orderList) {

								Orders o = new Orders();

								Manufacturers manufacturer = controller.dbManagerForParts
										.getManufacturerByManufacturer(order.getManufacturer());

								o.setParts(controller.dbManagerForParts.getPartEntityByOrderCodeAndManufacturerId(
										order.getOrderCode(), manufacturer.getManufacturerId()));
								o.setPartCount(order.getPartCount());

								Projects project = controller.dbManagerForOrders
										.getProjectEntityByProject(order.getProject());
								o.setProjects(project);

								Users user = controller.dbManagerForUsers.getUserEntityByUserName(order.getUser());
								o.setUsers(user);

								LocalDateTime dateTime = LocalDateTime.now();
								Timestamp sqlDateTime = Timestamp.valueOf(dateTime);

								o.setOrderDate(sqlDateTime);

								Boolean orderSuccessfullyCreated = controller.dbManagerForOrders.newOrder(o);

								if (!orderSuccessfullyCreated) {
									allOrdersSuccessfullyCreated = false;
								}
							}

							if (allOrdersSuccessfullyCreated) {

								// Print2PDF

								JFileChooser fileChooser = new JFileChooser();
								fileChooser.setDialogTitle("Specify a file to save");

								if (fileChooser.showSaveDialog(btnSave) == JFileChooser.APPROVE_OPTION) {
									File fileToSave = fileChooser.getSelectedFile();

									Date date = new Date();
									SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

									Document document = new Document();
									Font DocumentTitleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD,
											new CMYKColor(0, 0, 0, 255));
									Font SectionTitleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD,
											new CMYKColor(0, 0, 0, 255));
									Font textFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL,
											new CMYKColor(0, 0, 0, 255));

									try {
										PdfWriter writer = PdfWriter.getInstance(document,
												new FileOutputStream(fileToSave + ".pdf"));
										document.open();

										try {
											com.itextpdf.text.Image image = com.itextpdf.text.Image
													.getInstance(this.getClass().getResource("/logo_averna.png"));
											image.scaleAbsolute(165f, 35f);
											document.add(image);
										} catch (BadElementException | IOException e1) {
											e1.printStackTrace();
										} catch (DocumentException e) {
											e.printStackTrace();
										}

										document.add(new Paragraph("AVERNA Test & Measurement Solutions, Poland",
												SectionTitleFont));
										document.add(new Paragraph(" "));
										document.add(new Paragraph(" "));
										document.add(new Paragraph(" "));

										Paragraph title = new Paragraph("ORDER REQUEST", DocumentTitleFont);
										title.setAlignment(Element.ALIGN_CENTER);
										document.add(title);

										document.add(new Paragraph(" "));
										document.add(new Paragraph(" "));
										document.add(new Paragraph(" "));

										document.add(
												new Paragraph("Creation date: " + formatter.format(date), textFont));
										document.add(new Paragraph(
												"User name: " + user.getFirstName() + " " + user.getLastName(),
												textFont));
										document.add(new Paragraph(" "));

										int i = 0;
										for (Order order : orderList) {

											i++;
											document.add(new Paragraph(
													"-------------------------------------------------------------------------------------", textFont));
											document.add(new Paragraph("Position number: " + i, textFont));

											document.add(new Paragraph("Manufacturer: " + order.getManufacturer(),
													textFont));
											document.add(
													new Paragraph("Order code: " + order.getOrderCode(), textFont));
											document.add(new Paragraph("Quantity: " + order.getPartCount(), textFont));
											document.add(new Paragraph("Description: " + order.getDescription(), textFont));
											document.add(new Paragraph("Project: " + order.getProject(), textFont));
											document.add(new Paragraph(" "));
										}

										document.close();
										writer.close();
									} catch (DocumentException e) {
										e.printStackTrace();
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								}

								JOptionPane.showMessageDialog(null, "Order successfully created", "Message",
										JOptionPane.INFORMATION_MESSAGE);

							} else {
								JOptionPane.showMessageDialog(null, "Some problems appeared, order wasn't created!",
										"Warning", JOptionPane.WARNING_MESSAGE);
							}

							deleteAllItems();
							closeFrame();

						} else {
							JOptionPane.showMessageDialog(null, "Please enter valid values for quantity field!",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Current user doesn't have rights to create orders!",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "User isn't logged!", "Warning", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

	}

	private void refreshForm() {
		txtManufacturer.setText(orderList.get(partNumber - 1).getManufacturer());
		txtOrderCode.setText(orderList.get(partNumber - 1).getOrderCode());
		txtCount.setText(orderList.get(partNumber - 1).getPartCount().toString());
		txtrDescription.setText(orderList.get(partNumber - 1).getDescription());

		int i;
		for (i = 0; i < cBoxProject.getItemCount(); i++) {

			Object cbItem = cBoxProject.getItemAt(i);

			if (cbItem.toString() == orderList.get(partNumber - 1).getProject()) {
				cBoxProject.setSelectedIndex(i);
				break;
			}
		}

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
		txtrDescription.setText("");
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
			order.setUser(user.getUserName());
			order.setManufacturer(part.getManufacturer());
			order.setOrderCode(part.getOrderCode());
			order.setPartCount(0);
			order.setDescription(part.getDescription());

			if (!orderList.contains(order)) {
				orderList.add(order);
			}
		}
	}

	private void showPartCount() {

		lblPartNumber.setVisible(true);
		lblPartNumber.setText(partNumber + "/" + partsCount);
	}

	private void deleteAllItems() {

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

	private void setPartCount() {

		try {
			Order o = orderList.get(partNumber - 1);
			o.setPartCount(Integer.parseInt(txtCount.getText()));

			int min = partList.get(partNumber - 1).getQuantityMin();
			int max = partList.get(partNumber - 1).getQuantityMax();

			if (o.getPartCount() >= min && o.getPartCount() <= max) {
				orderList.set(partNumber - 1, o);
			} else {
				JOptionPane.showMessageDialog(null,
						"Please enter part count inside permitted limits " + min + "-" + max + "!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception e) {
			txtCount.setText("0");

			JOptionPane.showMessageDialog(null, "Please enter valid values for quantity field!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void assignProjectToPart() {

		Order o = orderList.get(partNumber - 1);
		o.setProject(cBoxProject.getSelectedItem().toString());
		orderList.set(partNumber - 1, o);
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
