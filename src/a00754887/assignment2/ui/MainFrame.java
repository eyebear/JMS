package a00754887.assignment2.ui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.JTextArea;

import a00754887.assignment2.control.Controller;
import a00754887.assignment2.data.CustomerDAO;
import a00754887.assignment2.data.Inventory;
import a00754887.assignment2.data.InventoryDAO;
import a00754887.assignment2.util.InventoryComparator.CompareByString;
import a00754887.assignment2.Assignment2;

import javax.swing.JSpinner;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import java.awt.Color;

/**
 * this is the class for UI, it create a main frame
 * 
 * @author AoAo_Feng
 * 
 */
public class MainFrame extends JFrame {
	private static MainFrame mf = new MainFrame();
	private static Logger LOG = Logger.getLogger(MainFrame.class);
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField customerField;
	private JTextField unitPriceField;
	private JFormattedTextField grossTotalPrice;
	private JMenuItem mntmSaveData;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JButton btnAdd;
	private JSpinner quantitySpinner;
	@SuppressWarnings("rawtypes")
	private final JComboBox comboBox;
	private JTextArea inventoryTextArea;
	private JTextArea totalPriceOneItemArea;
	private JButton btnPrintReceipt;
	private JButton customerDetailButton;
	private JMenuItem mntmInventory;
	private JMenuItem mntmCustomers;
	private CustomerDialog dialog;

	private ArrayList<Double> pricesOfItemsToBePurchased = new ArrayList<Double>();
	private double unitPrice;
	private AllActions alac = new AllActions();
	private StringBuilder textArea_1Content = new StringBuilder();
	private StringBuffer textAreaContent = new StringBuffer();
	private StringBuilder receiptBuilder = new StringBuilder();
	private double totalPriceOfAllItems = 0;
	private JButton itemDetailButton;
	private int inventoryComboBoxIndex = 0;
	private Controller ctr = Controller.getControllerInstance();
	private InventoryDAO invDAO = InventoryDAO.getInventoryDAO();
	private CustomerDAO cusDAO = CustomerDAO.getCustomerDAO();
	private JMenu mnDroptable;
	private JMenuItem mntmDropInventory;
	private JMenuItem mntmDropCustomer;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private MainFrame() {
		setTitle("Java Music Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				int userChoose = JOptionPane.showConfirmDialog(null,
						"Do you want to save any changes back to local files?",
						"Save or not", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (userChoose == JOptionPane.YES_OPTION) {
					LOG.info("choose yes in save panel");
					ctr.writeBackBothFiles();
					ctr.ctrCloseDB();
					System.exit(0);
				} else if (userChoose == JOptionPane.NO_OPTION) {// if no is //
																	// pressed,
																	// //nothing
																	// //
																	// happens
																	// // other
																	// // than
																	// exit
					LOG.info("choose no in save panel");
					ctr.ctrCloseDB();
					System.exit(0);
				} else {
					LOG.error("Error: Wrong dialog panel buttom pressed");
				}
			}

			@Override
			public void windowActivated(WindowEvent e) { // TODO Auto-generated
															// method stub

			}

			@Override
			public void windowClosed(WindowEvent e) { // TODO Auto-generated
														// method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) { // TODO
															// Auto-generated
															// method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) { // TODO
															// Auto-generated
															// method stub

			}

			@Override
			public void windowIconified(WindowEvent e) { // TODO Auto-generated
															// method stub

			}

			@Override
			public void windowOpened(WindowEvent e) { // TODO Auto-generated
														// method stub
			}
		});

		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		mntmSaveData = new JMenuItem("Save Data");
		mnFile.add(mntmSaveData);
		mntmSaveData.setMnemonic('S');
		mntmSaveData.addActionListener(alac);

		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.setMnemonic('x');
		mntmExit.addActionListener(alac);

		JMenu mnManage = new JMenu("Manage");
		mnManage.setMnemonic('M');
		menuBar.add(mnManage);

		mntmCustomers = new JMenuItem("Customers");
		mntmCustomers.addActionListener(alac);
		mnManage.add(mntmCustomers);
		mntmCustomers.setMnemonic('C');

		mntmInventory = new JMenuItem("Inventory");
		mntmInventory.addActionListener(alac);
		mnManage.add(mntmInventory);
		mntmInventory.setMnemonic('I');

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmAbout.setMnemonic('A');
		mntmAbout.addActionListener(alac);
		mnHelp.add(mntmAbout);

		mnDroptable = new JMenu("DropTable");
		menuBar.add(mnDroptable);

		mntmDropInventory = new JMenuItem("Drop Inventory");
		mntmDropInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info("drop inventory button pressed");
				ctr.dropInventoryTable();
				invDAO.checkForInvTable();
				repaintComboBox();
			}
		});
		mnDroptable.add(mntmDropInventory);

		mntmDropCustomer = new JMenuItem("Drop Customer");
		mntmDropCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info("drop customer button pressed");
				ctr.dropCustomerTable();
				cusDAO.checkForCusTable();
			}
		});
		mnDroptable.add(mntmDropCustomer);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][][][][][][]",
				"[][][][][][][grow][][]"));

		JLabel lblCustomer = new JLabel("Customer");
		contentPane.add(lblCustomer, "cell 1 1,alignx trailing");

		customerField = new JTextField();
		customerField.setBackground(Color.WHITE);
		customerField.setEditable(false);
		contentPane.add(customerField, "cell 2 1 5 1,growx");
		customerField.setColumns(10);

		customerDetailButton = new JButton("Details...");
		customerDetailButton.addActionListener(alac);
		contentPane.add(customerDetailButton, "cell 8 1,growx");

		JLabel label = new JLabel("$");
		contentPane.add(label, "flowx,cell 6 3");

		unitPriceField = new JTextField();
		unitPriceField.setBackground(Color.WHITE);
		unitPriceField.setEditable(false);
		contentPane.add(unitPriceField, "cell 6 3,alignx right");
		unitPriceField.setColumns(10);

		JLabel lblItem = new JLabel("Item");
		contentPane.add(lblItem, "cell 1 2,alignx trailing");

		CompareByString invcn = new CompareByString();
		ArrayList<String> invMakeList = new ArrayList<String>();
		ArrayList<Inventory> newInvByMakeList = new ArrayList<Inventory>();
		ArrayList<Inventory> oldInvList = ctr.retrieveInventoryList();
		for (Inventory inv : oldInvList) {
			invMakeList.add(inv.getMake());
		}
		Collections.sort(invMakeList, invcn);
		for (String invMake : invMakeList) {
			java.util.Iterator<Inventory> itr = oldInvList.iterator();
			while (itr.hasNext()) {
				Inventory tempInv = itr.next();
				if (tempInv.getMake().equals(invMake)) {
					newInvByMakeList.add(tempInv);
				}
			}
		}
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Assignment2
				.inventoryInformationToArray(true, newInvByMakeList)));
		comboBox.setSelectedIndex(inventoryComboBoxIndex);
		comboBox.setMaximumSize(new Dimension(505, 50));
		contentPane.add(comboBox, "cell 2 2 5 1,growx");
		unitPrice = Double.valueOf(newInvByMakeList.get(inventoryComboBoxIndex)
				.getRetailPrice());
		unitPriceField
				.setText(""
						+ newInvByMakeList.get(inventoryComboBoxIndex)
								.getRetailPrice());

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				LOG.info("select combobox");
				ArrayList<Inventory> oldInvList = ctr.retrieveInventoryList();
				String str = (String) comboBox.getSelectedItem();
				for (int i = 0; i < oldInvList.size(); i++) {
					if (oldInvList.get(i).toSimpleString().equals(str)) {
						inventoryComboBoxIndex = i;
						unitPrice = Double.valueOf(oldInvList.get(
								inventoryComboBoxIndex).getRetailPrice());
						unitPriceField.setText(""
								+ oldInvList.get(inventoryComboBoxIndex)
										.getRetailPrice());
						break;
					}
				}
			}
		});

		itemDetailButton = new JButton("Details...");
		itemDetailButton.addActionListener(alac);

		contentPane.add(itemDetailButton, "cell 8 2,growx");

		JLabel lblX = new JLabel("X");
		contentPane.add(lblX, "flowx,cell 6 4");

		SpinnerModel sm = new SpinnerNumberModel(1, 1, 10000, 1);
		quantitySpinner = new JSpinner(sm);
		contentPane.add(quantitySpinner, "cell 6 4,growx");

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(alac);
		contentPane.add(btnAdd, "cell 8 4,growx");

		JLabel lblPurchasedItems = new JLabel("Purchased Items");
		contentPane.add(lblPurchasedItems, "cell 1 5");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 6 6 1,grow");

		inventoryTextArea = new JTextArea();
		inventoryTextArea.setEditable(false);
		scrollPane.setViewportView(inventoryTextArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 8 6,grow");

		totalPriceOneItemArea = new JTextArea();
		totalPriceOneItemArea.setEditable(false);
		scrollPane_1.setViewportView(totalPriceOneItemArea);

		JLabel lblTotal = new JLabel("Total");
		contentPane.add(lblTotal, "cell 7 7,alignx trailing");

		grossTotalPrice = new JFormattedTextField();
		grossTotalPrice.setBackground(Color.WHITE);
		grossTotalPrice.setEditable(false);
		contentPane.add(grossTotalPrice, "cell 8 7,growx");
		grossTotalPrice.setColumns(10);

		btnPrintReceipt = new JButton("Print Receipt...");
		btnPrintReceipt.addActionListener(alac);
		contentPane.add(btnPrintReceipt, "cell 8 8,growx");
	}

	/**
	 * this inner class implements all the actions of buttons or menu elements
	 * 
	 * @author AoAo_Feng
	 * 
	 */
	private class AllActions implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(mntmExit)) {
				LOG.info("exit button pressed");
				ctr.ctrCloseDB();
				System.exit(0);
			} else if (e.getSource().equals(customerDetailButton)
					|| e.getSource().equals(mntmCustomers)) {
				LOG.info("customerDetailButton or mntmCustomers button pressed");
				try {
					dialog = new CustomerDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				} catch (Exception e3) {
					e3.printStackTrace();
				}
			} else if (e.getSource().equals(itemDetailButton)
					|| e.getSource().equals(mntmInventory)) {
				LOG.info("itemDetailButton or mntmInventory button pressed");
				try {
					ArrayList<Inventory> oldInvList = ctr
							.retrieveInventoryList();
					String str = (String) comboBox.getSelectedItem();
					for (int i = 0; i < oldInvList.size(); i++) {
						if (oldInvList.get(i).toSimpleString().equals(str)) {
							InventoryDialog dialog = new InventoryDialog(i);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							break;
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else if (e.getSource().equals(mntmAbout)) {
				LOG.info("mntmAbout button pressed");
				JOptionPane.showMessageDialog(null, "COMP 2613 Assignment 2 \n"
						+ " by Oliver Feng, A00754887", "About",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (e.getSource().equals(btnAdd)) {// when add button is
				// pressed
				LOG.info("btnAdd button pressed");
				double totalPriceOfOneItem = unitPrice
						* (int) quantitySpinner.getValue();
				pricesOfItemsToBePurchased.add(new Double(totalPriceOfOneItem));
				totalPriceOneItemArea.setText(textArea_1Content.append(
						String.format("%30s%n", totalPriceOfOneItem))
						.toString());

				String str = (String) comboBox.getSelectedItem();
				ArrayList<Inventory> oldInvList = ctr.retrieveInventoryList();
				for (Inventory inv : oldInvList) {
					if (inv.toSimpleString().equals(str)) {
						if (inv.getQuantityInStock() > 0) {
							inv.setQuantityInStock(inv.getQuantityInStock() - 1);
							inv.setQuantitySold(inv.getQuantitySold() + 1);
							InventoryDAO.updateInvData(inv);
							textAreaContent.append(String.format("%-20s%10s",
									inv.getMake(), inv.getModelNumber())
									+ String.format("\t%10s%10s%10s%n",
											(int) quantitySpinner.getValue(),
											" X ", inv.getRetailPrice()));
							inventoryTextArea.setText(textAreaContent
									.toString());
							receiptBuilder.append(inv.detailToString()
									+ "-----------$" + totalPriceOfOneItem
									+ "\n");
							for (Double dou : pricesOfItemsToBePurchased) {
								totalPriceOfAllItems += dou.doubleValue();
							}
							grossTotalPrice.setText("" + totalPriceOfAllItems);
							break;
						} else {
							JOptionPane
									.showMessageDialog(
											null,
											"Sorry, this inventory is currently out of stock",
											"Out of Stock",
											JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}
			} else if (e.getSource().equals(btnPrintReceipt)) {// print receipt
				LOG.info("btnPrintReceipt button pressed");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							String customerName = "no name";
							String customerNumber = "no number";
							if (!customerField.getText().isEmpty()
									&& !dialog.getSelectedCustomer()
											.getFirstName().isEmpty()) {
								customerName = dialog.getSelectedCustomer()
										.getFirstName()
										+ " "
										+ dialog.getSelectedCustomer()
												.getLastName();
								customerNumber = dialog.getSelectedCustomer()
										.getCustomerNumber();
							}

							ReceiptFrame frame = new ReceiptFrame();

							frame.getReceiptTextArea()
									.setText(
											"\n"
													+ "****************************************************\n"
													+ "          Thank you for shopping at\n"
													+ "                Quade & McLong\n"
													+ GregorianCalendar
															.getInstance()
															.getTime()
													+ "\n"
													+ customerName
													+ "\n"
													+ "Customer #"
													+ customerNumber
													+ "\n\n"
													+ "Your purchases: \n"

													+ textAreaContent
															.toString()
													+ "\n"
													+ "                                                         -------------\n"
													+ "                                                         "
													+ totalPriceOfAllItems
													+ "\n\n"
													+ "****************************************************\n");
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else if (e.getSource().equals(mntmSaveData)) {
				LOG.info("mntmSaveData button pressed");
				ctr.writeBackBothFiles();
			} else {
				LOG.error("Error: Wrong Action");
			}
		}
	}

	/**
	 * return the instance of this singlton
	 * 
	 * @return mf
	 */
	public static MainFrame getFrameInstance() {
		return mf;
	}

	/**
	 * this static method returns the Customer text field for other class to use
	 * 
	 * @return customerField
	 */
	public static JTextField getCustomerField() {
		return customerField;
	}

	/**
	 * repaint the combobox on main frame
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void repaintComboBox() {
		LOG.info("try to repaint combobox");
		CompareByString invcn = new CompareByString();
		ArrayList<String> invMakeList = new ArrayList<String>();
		ArrayList<Inventory> newInvByMakeList = new ArrayList<Inventory>();
		ArrayList<Inventory> oldInvList = ctr.retrieveInventoryList();
		for (Inventory inv : oldInvList) {
			invMakeList.add(inv.getMake());
		}
		Collections.sort(invMakeList, invcn);
		for (String invMake : invMakeList) {
			java.util.Iterator<Inventory> itr = oldInvList.iterator();
			while (itr.hasNext()) {
				Inventory tempInv = itr.next();
				if (tempInv.getMake().equals(invMake)) {
					newInvByMakeList.add(tempInv);
				}
			}
		}
		comboBox.setModel(new DefaultComboBoxModel(Assignment2
				.inventoryInformationToArray(true, newInvByMakeList)));
		comboBox.setSelectedIndex(inventoryComboBoxIndex);
		comboBox.setMaximumSize(new Dimension(505, 50));
		contentPane.add(comboBox, "cell 2 2 5 1,growx");
		this.repaint();
		LOG.info("try to done combobox");
	}

}
