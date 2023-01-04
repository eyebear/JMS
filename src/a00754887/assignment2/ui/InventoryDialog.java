package a00754887.assignment2.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import a00754887.assignment2.control.Controller;
import a00754887.assignment2.data.Inventory;
import a00754887.assignment2.data.InventoryDAO;
import a00754887.assignment2.util.InventoryComparator.CompareByString;
import a00754887.assignment2.Assignment2;

/**
 * this class creates an inventory dialog
 * 
 * @author AoAo_Feng
 * 
 */
public class InventoryDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(InventoryDialog.class);
	private final JPanel contentPanel = new JPanel();
	private JTextField makeField;
	private JTextField modelField;
	private JTextField descriptionField;
	private JTextField purchaseField;
	private JTextField sellingPriceField;
	private JTextField QuantityInStockField;
	private JTextField QuantitySoldField;
	private JTextField numberRentedField;
	@SuppressWarnings("rawtypes")
	private final JComboBox comboBox;

	private JLabel lblMake;
	private JLabel lblModel;
	private JLabel lblDescription;
	private JLabel lblPurchasePrice;
	private JLabel lblSellingPrice;
	private JLabel lblQuantityInStock;
	private JLabel lblQuantitySold;
	private JLabel lblNumberRented;
	private JButton saveButton;
	private JButton closeButton;
	private AllActions alac = new AllActions();
	private Controller ctr = Controller.getControllerInstance();

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InventoryDialog(int inventoryComboBoxIndex) {
		setTitle("Inventory Information");
		setBounds(250, 250, 500, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]",
				"[][][][][][][][][]"));

		JLabel lblSku = new JLabel("SKU");
		contentPanel.add(lblSku, "cell 0 0,alignx trailing");

		lblMake = new JLabel("Make");
		contentPanel.add(lblMake, "cell 0 1,alignx trailing");

		makeField = new JTextField();
		contentPanel.add(makeField, "cell 1 1,growx");
		makeField.setColumns(10);

		lblModel = new JLabel("Model");
		contentPanel.add(lblModel, "cell 0 2,alignx trailing");

		modelField = new JTextField();
		contentPanel.add(modelField, "cell 1 2,growx");
		modelField.setColumns(10);

		lblDescription = new JLabel("Description");
		contentPanel.add(lblDescription, "cell 0 3,alignx trailing");

		descriptionField = new JTextField();
		contentPanel.add(descriptionField, "cell 1 3,growx");
		descriptionField.setColumns(10);

		lblPurchasePrice = new JLabel("Purchase Price");
		contentPanel.add(lblPurchasePrice, "cell 0 4,alignx trailing");

		purchaseField = new JTextField();
		contentPanel.add(purchaseField, "cell 1 4,growx");
		purchaseField.setColumns(10);

		lblSellingPrice = new JLabel("Selling Price");
		contentPanel.add(lblSellingPrice, "cell 0 5,alignx trailing");

		sellingPriceField = new JTextField();
		contentPanel.add(sellingPriceField, "cell 1 5,growx");
		sellingPriceField.setColumns(10);

		lblQuantityInStock = new JLabel("Quantity in Stock");
		contentPanel.add(lblQuantityInStock, "cell 0 6,alignx trailing");

		QuantityInStockField = new JTextField();
		contentPanel.add(QuantityInStockField, "cell 1 6,growx");
		QuantityInStockField.setColumns(10);

		lblQuantitySold = new JLabel("Quantity Sold");
		contentPanel.add(lblQuantitySold, "cell 0 7,alignx trailing");

		QuantitySoldField = new JTextField();
		contentPanel.add(QuantitySoldField, "cell 1 7,growx");
		QuantitySoldField.setColumns(10);

		lblNumberRented = new JLabel("Number Rented");
		contentPanel.add(lblNumberRented, "cell 0 8,alignx trailing");

		numberRentedField = new JTextField();
		contentPanel.add(numberRentedField, "cell 1 8,growx");
		numberRentedField.setColumns(10);

		CompareByString invcn = new CompareByString();
		ArrayList<String> invSKUList = new ArrayList<String>();
		ArrayList<Inventory> newInvList = new ArrayList<Inventory>();
		ArrayList<Inventory> oldInvList = ctr.retrieveInventoryList();
		Inventory currentInv = oldInvList.get(inventoryComboBoxIndex);
		for (Inventory inv : oldInvList) {
			invSKUList.add(inv.getSku());
		}
		Collections.sort(invSKUList, invcn);
		for (String invSKU : invSKUList) {
			java.util.Iterator<Inventory> itr = oldInvList.iterator();
			while (itr.hasNext()) {
				Inventory tempInv = itr.next();
				if (tempInv.getSku().equals(invSKU)) {
					newInvList.add(tempInv);
				}
			}
		}

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Assignment2
				.inventoryInformationToArray(false, newInvList)));
		comboBox.setSelectedIndex(newInvList.indexOf(currentInv));
		comboBox.setMaximumSize(new Dimension(359, 50));
		String str = (String) comboBox.getSelectedItem();
		for (Inventory inv : newInvList) {
			if (inv.getSku().equals(str)) {
				makeField.setText(inv.getMake());
				modelField.setText(inv.getModelNumber());
				descriptionField.setText(inv.getDescription());
				purchaseField.setText("" + inv.getPurchasePrice());
				sellingPriceField.setText("" + inv.getRetailPrice());
				QuantityInStockField.setText("" + inv.getQuantityInStock());
				QuantitySoldField.setText("" + inv.getQuantitySold());
				numberRentedField.setText("" + inv.getNumberRented());
				break;
			}
		}
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				String str = (String) comboBox.getSelectedItem();
				ArrayList<Inventory> oldInvList = ctr.retrieveInventoryList();
				for (Inventory inv : oldInvList) {
					if (inv.getSku().equals(str)) {
						makeField.setText(inv.getMake());
						modelField.setText(inv.getModelNumber());
						descriptionField.setText(inv.getDescription());
						purchaseField.setText("" + inv.getPurchasePrice());
						sellingPriceField.setText("" + inv.getRetailPrice());
						QuantityInStockField.setText(""
								+ inv.getQuantityInStock());
						QuantitySoldField.setText("" + inv.getQuantitySold());
						numberRentedField.setText("" + inv.getNumberRented());
						break;
					}
				}
			}
		});
		contentPanel.add(comboBox, "cell 1 0,growx");

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		saveButton = new JButton("Save");
		saveButton.addActionListener(alac);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);

		closeButton = new JButton("Close");
		closeButton.addActionListener(alac);
		buttonPane.add(closeButton);

	}

	/**
	 * inner class that implements all the actions of buttons
	 * 
	 * @author AoAo_Feng
	 * 
	 */
	private class AllActions implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Inventory inv = new Inventory();
			inv.setMake(makeField.getText());
			inv.setModelNumber(modelField.getText());
			inv.setDescription(descriptionField.getText());
			inv.setPurchasePrice(Double.parseDouble(purchaseField.getText()));
			inv.setRetailPrice(Double.parseDouble(sellingPriceField.getText()));
			inv.setSku((String) comboBox.getSelectedItem());
			inv.setQuantityInStock(Integer.parseInt(QuantityInStockField
					.getText()));
			inv.setQuantitySold(Integer.parseInt(QuantitySoldField.getText()));
			inv.setNumberRented(Integer.parseInt(numberRentedField.getText()));

			if (e.getSource().equals(saveButton)) {// save inv in database
				LOG.info("save button pressed");
				InventoryDAO.updateInvData(inv);
				setVisible(false);
				MainFrame.getFrameInstance().repaintComboBox();
			} else if (e.getSource().equals(closeButton)) {
				LOG.info("close button pressed");
				ArrayList<Inventory> oldInvList = ctr.retrieveInventoryList();
				for (Inventory invn : oldInvList) {
					if (invn.getSku().equals(inv.getSku())) {
						if (!invn.equals(inv)) {
							int userChoose = JOptionPane.showConfirmDialog(
									null, "Do you want to save the changes?",
									"Save or not",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (userChoose == JOptionPane.YES_OPTION) {
								InventoryDAO.updateInvData(inv);
								MainFrame.getFrameInstance().repaintComboBox();
								setVisible(false);
								break;
							} else if (userChoose == JOptionPane.NO_OPTION) {
								setVisible(false);
								break;
							} else {
								break;
							}
						}
						setVisible(false);
						break;
					}
				}
			} else {
				LOG.error("Error: Wrong Action");
			}
		}
	}
}
