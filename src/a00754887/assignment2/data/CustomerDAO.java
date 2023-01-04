package a00754887.assignment2.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import a00754887.assignment2.control.Controller;
import a00754887.assignment2.Assignment2;

/**
 * this class imports and exports customer data
 * 
 * @author AoAo_Feng
 * 
 */
public class CustomerDAO {
	private static CustomerDAO customerDAO = new CustomerDAO();
	private static DAO databaseDAO = DAO.getDAOIntance();
	private static Controller ctr = Controller.getControllerInstance();
	private static Logger LOG = Logger.getLogger(CustomerDAO.class);

	/**
	 * constructor
	 */
	private CustomerDAO() {
	}

	/**
	 * return instance of this singleton
	 * 
	 * @return customerDAO
	 */
	public static CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	/**
	 * save a new customer object into the database
	 * 
	 * @param cus
	 */
	public static void saveNewCusData(Customer cus) {
		LOG.info("insert query to insert new customer into database in saveNewCusData");
		StringBuilder stdb = new StringBuilder();
		stdb.append("INSERT INTO A00754887_CUSTOMER (customerNumber, firstName, lastName, phoneNumber, cardType, creditCardNumber, street, city, postalCode)");
		stdb.append("VALUES('");
		stdb.append(cus.getCustomerNumber() + "','");
		stdb.append(cus.getFirstName() + "','");
		stdb.append(cus.getLastName() + "','");
		stdb.append(cus.getPhoneNumber() + "','");
		stdb.append(cus.getCardType() + "','");
		stdb.append(cus.getCreditCardNumber() + "','");
		stdb.append(cus.getStreet() + "','");
		stdb.append(cus.getCity() + "','");
		stdb.append(cus.getPostalCode() + "');");
		try {
			databaseDAO.updateDatabase(stdb.toString());
			LOG.info("done executing insert query in saveNewCusData");
		} catch (SQLException e) {
			LOG.error("SQLException thrown in saveNewCusData");
			e.printStackTrace();
		}
	}

	/**
	 * import data from customer.txt file
	 * 
	 * @param cus
	 */
	public static void updateCusData(Customer cus) {
		LOG.info("update query to update customer object in updateCusData");
		StringBuilder stdb = new StringBuilder();
		stdb.append("UPDATE A00754887_CUSTOMER");
		stdb.append(" SET customerNumber = '" + cus.getCustomerNumber() + "'");
		stdb.append(" , firstName = '" + cus.getFirstName() + "'");
		stdb.append(" , lastName = '" + cus.getLastName() + "'");
		stdb.append(" , phoneNumber = '" + cus.getPhoneNumber() + "'");
		stdb.append(" , cardType = " + cus.getCardType());
		stdb.append(" , creditCardNumber = '" + cus.getCreditCardNumber() + "'");
		stdb.append(" , street = '" + cus.getStreet() + "'");
		stdb.append(" , city = '" + cus.getCity() + "'");
		stdb.append(" , postalCode = '" + cus.getPostalCode() + "'");
		stdb.append(" WHERE customerNumber = '" + cus.getCustomerNumber()
				+ "';");
		try {
			databaseDAO.updateDatabase(stdb.toString());
			LOG.info("done updating customer in updateCusData");
		} catch (SQLException e) {
			LOG.equals("SQLException thrown in updateCusData");
			e.printStackTrace();
		}
	}

	/**
	 * retrieve customer data
	 * 
	 * @param query
	 * @return tempList
	 */
	public ArrayList<Customer> retrieveCusData(String query) {
		LOG.info("try to retrieve customer data in retrieveCusData");
		ArrayList<Customer> tempList = new ArrayList<Customer>();
		try {
			ResultSet set = databaseDAO.queryDatabase(query);
			while (set.next()) {
				tempList.add(new Customer(set.getString(1), set.getString(2),
						set.getString(3), set.getString(4), set.getInt(5), set
								.getString(6), set.getString(7), set
								.getString(8), set.getString(9)));
			}
		} catch (SQLException e) {
			LOG.error("SQLException thrown in retrieveCusData");
			e.printStackTrace();
		}
		LOG.info("done to retrieve customer data in retrieveCusData");
		return tempList;
	}

	/**
	 * will call DAO.tableExists ¨C if false, then create a new empty table, else
	 * display a message to the console indicating the table already exists.
	 */
	public void checkForCusTable() {
		try {
			if (!DAO.tableExists("A00754887_CUSTOMER")) {
				LOG.info("try to creat A00754887_CUSTOMER table in checkForCusTable in checkForCusTable");
				StringBuilder stb = new StringBuilder();
				stb.append("CREATE TABLE A00754887_CUSTOMER ("
						+ "customerNumber varchar(50),"
						+ "firstName varchar(50)," + "lastName varchar(50),"
						+ "phoneNumber varchar(50)," + "cardType int,"
						+ "creditCardNumber varchar(50),"
						+ "street varchar(50)," + "city varchar(50),"
						+ "postalCode varchar(50));");
				databaseDAO.createTable(stb.toString());
				LOG.info("finish create Customer table and start to populate Customer database with Customer data in checkForCusTable");
				ArrayList<Customer> tempCusList = readCustomerFile();
				for (Customer cus : tempCusList) {
					saveNewCusData(cus);
				}
				LOG.info("done populate Customer database with Customer data in checkForCusTable");
			} else {
				LOG.info("A00754887_CUSTOMER already exists in checkForCusTable");
			}
		} catch (SQLException e) {
			LOG.error("SQLException thrown in checkForCusTable");
			e.printStackTrace();
		}
	}

	/**
	 * export data to customer.txt file
	 */
	public static void writeCustomerFile() {
		LOG.info("try to write customer file in writeCustomerFile");
		ArrayList<Customer> cusList = ctr.retrieveCustomerList();
		File file = new File("customer.txt");
		// Create a file
		PrintWriter output;
		try {
			output = new PrintWriter(file);
			StringBuilder text = new StringBuilder();
			for (Customer cus : cusList) {
				text.append(cus.getCustomerNumber() + "|");
				text.append(cus.getFirstName() + "|");
				text.append(cus.getLastName() + "|");
				text.append(cus.getPhoneNumber() + "|");
				text.append(cus.getCardType() + "|");
				text.append(cus.getCreditCardNumber() + "|");
				text.append(cus.getStreet() + "|");
				text.append(cus.getCity() + "|");
				text.append(cus.getPostalCode());
				output.println(text.toString());
				text.delete(0, text.length());
			}
			// Close the file
			output.close();
			LOG.info("done to write customer file in writeCustomerFile");
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFoundException thrown in writeCustomerFile");
			e.printStackTrace();
		}
	}

	/**
	 * import data from customer.txt file
	 * 
	 * @return cusList
	 */
	public static ArrayList<Customer> readCustomerFile() {
		LOG.info("try to read customer file in readCustomerFile");
		ArrayList<Customer> cusList = new ArrayList<Customer>();
		File file = new File("customer.txt");
		Scanner input;
		try {
			input = new Scanner(file);
			while (input.hasNext()) {
				cusList.add(Assignment2.createCustomer(Assignment2
						.readData(input.nextLine())));
			}
			input.close();
			LOG.info("done to read customer file in readCustomerFile");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cusList;
	}

}
