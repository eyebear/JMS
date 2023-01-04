package a00754887.assignment2.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;

import a00754887.assignment2.control.Controller;
import a00754887.assignment2.data.CardType;
import a00754887.assignment2.data.Customer;
import a00754887.assignment2.data.CustomerDAO;

/**
 * this class creates a customer dialog
 * 
 * @author AoAo_Feng
 * 
 */
public class CustomerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(CustomerDialog.class);
	private final JPanel contentPanel = new JPanel();
	private JTextField customerNumberField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private JTextField creditCardNumber;
	private JTextField streetField;
	private JTextField cityField;
	private JTextField postalCodeField;
	private String[] cardTypeArray = CardType.getAllCardTypes();
	private Customer selectedCustomer = null;
	private Controller ctr = Controller.getControllerInstance();

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomerDialog() {
		setTitle("Customer Information");
		setBounds(250, 250, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]",
				"[][][][][][][][][][]"));

		JLabel lblCustomer = new JLabel("Customer #");
		contentPanel.add(lblCustomer, "cell 0 0,alignx trailing");

		customerNumberField = new JTextField();
		contentPanel.add(customerNumberField, "cell 1 0,growx");
		customerNumberField.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name");
		contentPanel.add(lblFirstName, "cell 0 1,alignx trailing");

		firstNameField = new JTextField();
		contentPanel.add(firstNameField, "cell 1 1,growx");
		firstNameField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		contentPanel.add(lblLastName, "cell 0 2,alignx trailing");

		lastNameField = new JTextField();
		contentPanel.add(lastNameField, "cell 1 2,growx");
		lastNameField.setColumns(10);

		JLabel lblPhone = new JLabel("Phone #");
		contentPanel.add(lblPhone, "cell 0 3,alignx trailing");

		phoneNumberField = new JTextField();
		contentPanel.add(phoneNumberField, "cell 1 3,growx");
		phoneNumberField.setColumns(10);

		JLabel lblCredit = new JLabel("Credit Card Type");
		contentPanel.add(lblCredit, "cell 0 4,alignx trailing");

		// cardTypeArray
		final JComboBox creditCardTypeComboBox = new JComboBox();
		creditCardTypeComboBox
				.setModel(new DefaultComboBoxModel(cardTypeArray));
		contentPanel.add(creditCardTypeComboBox, "cell 1 4,growx");

		JLabel lblCreditCard = new JLabel("Credit Card #");
		contentPanel.add(lblCreditCard, "cell 0 5,alignx trailing");

		creditCardNumber = new JTextField();
		contentPanel.add(creditCardNumber, "cell 1 5,growx");
		creditCardNumber.setColumns(10);

		JLabel lblStreet = new JLabel("Street");
		contentPanel.add(lblStreet, "cell 0 6,alignx trailing");

		streetField = new JTextField();
		contentPanel.add(streetField, "cell 1 6,growx");
		streetField.setColumns(10);

		JLabel lblCity = new JLabel("City");
		contentPanel.add(lblCity, "cell 0 7,alignx trailing");

		cityField = new JTextField();
		contentPanel.add(cityField, "cell 1 7,growx");
		cityField.setColumns(10);

		JLabel lblPostalCode = new JLabel("Postal Code");
		contentPanel.add(lblPostalCode, "cell 0 8,alignx trailing");

		postalCodeField = new JTextField();
		contentPanel.add(postalCodeField, "cell 1 8,growx");
		postalCodeField.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info("find button pressed");
				if (customerNumberField.getText().isEmpty()) {
					ArrayList<Customer> oldCusList = ctr.retrieveCustomerList();
					int currentCustomerNumber = 0;
					customerNumberField.setText(oldCusList.get(
							currentCustomerNumber).getCustomerNumber());
					firstNameField.setText(oldCusList
							.get(currentCustomerNumber).getFirstName());
					lastNameField.setText(oldCusList.get(currentCustomerNumber)
							.getLastName());
					phoneNumberField.setText(oldCusList.get(
							currentCustomerNumber).getPhoneNumber());
					for (int i = 0; i < cardTypeArray.length; i++) {
						if (CardType.getCardIndexByName(cardTypeArray[i]) == oldCusList
								.get(currentCustomerNumber).getCardType()) {
							creditCardTypeComboBox.setSelectedIndex(i);
							break;
						}
					}
					creditCardNumber.setText(oldCusList.get(
							currentCustomerNumber).getCreditCardNumber());
					streetField.setText(oldCusList.get(currentCustomerNumber)
							.getStreet());
					cityField.setText(oldCusList.get(currentCustomerNumber)
							.getCity());
					postalCodeField.setText(oldCusList.get(
							currentCustomerNumber).getPostalCode());
				} else {
					int currentCustomerNumber = Integer
							.parseInt((customerNumberField.getText()));
					int customerListIndex = currentCustomerNumber - 1;
					ArrayList<Customer> oldCusList = ctr.retrieveCustomerList();
					if (customerListIndex < oldCusList.size()
							&& customerListIndex >= 0) {
						firstNameField.setText(oldCusList
								.get(customerListIndex).getFirstName());
						lastNameField.setText(oldCusList.get(customerListIndex)
								.getLastName());
						phoneNumberField.setText(oldCusList.get(
								customerListIndex).getPhoneNumber());
						for (int i = 0; i < cardTypeArray.length; i++) {
							if (CardType.getCardIndexByName(cardTypeArray[i]) == oldCusList
									.get(customerListIndex).getCardType()) {
								creditCardTypeComboBox.setSelectedIndex(i);
								break;
							}
						}
						creditCardNumber.setText(oldCusList.get(
								customerListIndex).getCreditCardNumber());
						streetField.setText(oldCusList.get(customerListIndex)
								.getStreet());
						cityField.setText(oldCusList.get(customerListIndex)
								.getCity());
						postalCodeField.setText(oldCusList.get(
								customerListIndex).getPostalCode());
					} else {
						JOptionPane.showMessageDialog(null,
								"Invalid Customer Number", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		buttonPane.add(btnFind);

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info("new button pressed");
				ArrayList<Customer> oldCusList = ctr.retrieveCustomerList();
				int newCustomerNumber = oldCusList.size() + 1;
				customerNumberField.setText("" + newCustomerNumber);
				firstNameField.setText("");
				lastNameField.setText("");
				phoneNumberField.setText("");
				creditCardNumber.setText("");
				streetField.setText("");
				cityField.setText("");
				postalCodeField.setText("");
			}
		});
		buttonPane.add(btnNew);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info("clear button pressed");
				firstNameField.setText("");
				lastNameField.setText("");
				phoneNumberField.setText("");
				creditCardNumber.setText("");
				streetField.setText("");
				cityField.setText("");
				postalCodeField.setText("");
			}
		});
		buttonPane.add(btnClear);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LOG.info("ok button pressed");
				if (customerNumberField.getText().isEmpty()) {
					setVisible(false);
					return;
				}
				int currentCustomerNumber = Integer
						.parseInt((customerNumberField.getText()));
				ArrayList<Customer> oldCusList = ctr.retrieveCustomerList();
				// checks if this is a new customer by checking customer number
				if (currentCustomerNumber > oldCusList.size()) {
					selectedCustomer = new Customer(String
							.valueOf(currentCustomerNumber), firstNameField
							.getText(), lastNameField.getText(),
							phoneNumberField.getText(), CardType
									.getCardIndexByName(creditCardTypeComboBox
											.getSelectedItem().toString()),
							creditCardNumber.getText(), streetField.getText(),
							cityField.getText(), postalCodeField.getText());
					CustomerDAO.saveNewCusData(selectedCustomer);
				} else {// if this is a old customer, the customer data
					oldCusList.get(currentCustomerNumber - 1).setFirstName(
							firstNameField.getText());
					oldCusList.get(currentCustomerNumber - 1).setLastName(
							lastNameField.getText());
					oldCusList.get(currentCustomerNumber - 1).setPhoneNumber(
							phoneNumberField.getText());
					oldCusList.get(currentCustomerNumber - 1).setCardType(
							CardType.getCardIndexByName(creditCardTypeComboBox
									.getSelectedItem().toString()));
					oldCusList.get(currentCustomerNumber - 1)
							.setCreditCardNumber(creditCardNumber.getText());
					oldCusList.get(currentCustomerNumber - 1).setStreet(
							streetField.getText());
					oldCusList.get(currentCustomerNumber - 1).setCity(
							cityField.getText());
					oldCusList.get(currentCustomerNumber - 1).setPostalCode(
							postalCodeField.getText());
					selectedCustomer = oldCusList
							.get(currentCustomerNumber - 1);
					CustomerDAO.updateCusData(selectedCustomer);
				}
				MainFrame.getCustomerField().setText(
						selectedCustomer.getFirstName() + " "
								+ selectedCustomer.getLastName());
				setVisible(false);
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LOG.info("cancel button pressed");
				setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	/**
	 * this method returns the customer selected in the comboBox in this class,
	 * so that main frame class can use it
	 * 
	 * @return selectedCustomer
	 */
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}
}
