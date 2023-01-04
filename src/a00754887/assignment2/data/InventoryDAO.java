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
 * this class imports and exports inventory data
 * 
 * @author AoAo_Feng
 * 
 */
public class InventoryDAO {
	private static InventoryDAO inventoryDAO = new InventoryDAO();
	private static DAO databaseDAO = DAO.getDAOIntance();
	private static Controller ctr = Controller.getControllerInstance();
	private static Logger LOG = Logger.getLogger(CustomerDAO.class);

	/**
	 * constructor
	 */
	private InventoryDAO() {
	}

	/**
	 * return instance of this singleton
	 * 
	 * @return studentDAO
	 */
	public static InventoryDAO getInventoryDAO() {
		return inventoryDAO;
	}

	/**
	 * save a new inventory object into the database
	 * 
	 * @param inv
	 */
	public static void saveNewInvData(Inventory inv)
			throws FileNotFoundException {
		LOG.info("insert query to insert new Inventory into database in saveNewInvData");
		StringBuilder stdb = new StringBuilder();
		stdb.append("INSERT INTO A00754887_INVENTORY (make, description, modelNumber, SKU, quantityInStock, quantitySold, purchasePrice, retailPrice, numberRented)");
		stdb.append("VALUES('");
		stdb.append(inv.getMake() + "','");
		stdb.append(inv.getDescription() + "','");
		stdb.append(inv.getModelNumber() + "','");
		stdb.append(inv.getSku() + "','");
		stdb.append(inv.getQuantityInStock() + "','");
		stdb.append(inv.getQuantitySold() + "','");
		stdb.append(inv.getPurchasePrice() + "','");
		stdb.append(inv.getRetailPrice() + "','");
		stdb.append(inv.getNumberRented() + "');");
		try {
			databaseDAO.updateDatabase(stdb.toString());
			LOG.info("done executing insert query in saveNewInvData");
		} catch (SQLException e) {
			LOG.error("SQLException thrown in saveNewInvData");
			e.printStackTrace();
		}
	}

	/**
	 * import data from inventory.txt file
	 * 
	 * @param inv
	 */
	public static void updateInvData(Inventory inv) {
		LOG.info("update query to update Inventory object in updateInvData");
		StringBuilder stdb = new StringBuilder();
		stdb.append("UPDATE A00754887_INVENTORY");
		stdb.append(" SET make = '" + inv.getMake() + "'");
		stdb.append(" , description = '" + inv.getDescription() + "'");
		stdb.append(" , modelNumber = '" + inv.getModelNumber() + "'");
		stdb.append(" , SKU = '" + inv.getSku() + "'");
		stdb.append(" , quantityInStock = " + inv.getQuantityInStock());
		stdb.append(" , quantitySold = " + inv.getQuantitySold());
		stdb.append(" , purchasePrice = " + inv.getPurchasePrice());
		stdb.append(" , retailPrice = " + inv.getRetailPrice());
		stdb.append(" , numberRented = " + inv.getNumberRented());
		stdb.append(" WHERE SKU = '" + inv.getSku() + "';");
		try {
			databaseDAO.updateDatabase(stdb.toString());
			LOG.info("done updating Inventory in updateInvData");
		} catch (SQLException e) {
			LOG.equals("SQLException thrown in updateInvData");
			e.printStackTrace();
		}
	}

	/**
	 * retrieve Inventory data
	 * 
	 * @param query
	 * @return tempList
	 */
	public ArrayList<Inventory> retrieveInvData(String query) {
		LOG.info("try to retrieve Inventory data in retrieveInvData");
		ArrayList<Inventory> tempList = new ArrayList<Inventory>();
		try {
			ResultSet set = databaseDAO.queryDatabase(query);
			while (set.next()) {
				tempList.add(new Inventory(set.getString(1), set.getString(2),
						set.getString(3), set.getString(4), set.getInt(5), set
								.getInt(6), set.getDouble(7), set.getDouble(8),
						set.getInt(9)));
			}
		} catch (SQLException e) {
			LOG.error("SQLException thrown in retrieveInvData");
			e.printStackTrace();
		}
		LOG.info("done to retrieve Inventory data in retrieveInvData");
		return tempList;
	}

	/**
	 * will call DAO.tableExists ¨C if false, then create a new empty table, else
	 * display a message to the console indicating the table already exists.
	 */
	public void checkForInvTable() {
		try {
			if (!DAO.tableExists("A00754887_INVENTORY")) {
				LOG.info("try to creat A00754887_INVENTORY table in checkForCusTable in checkForCusTable");
				StringBuilder stb = new StringBuilder();
				stb.append("CREATE TABLE A00754887_INVENTORY ("
						+ "make varchar(50)," + "description varchar(50),"
						+ "modelNumber varchar(50)," + "SKU varchar(50),"
						+ "quantityInStock int," + "quantitySold int,"
						+ "purchasePrice decimal(7, 2),"
						+ "retailPrice decimal(7, 2)," + "numberRented int);");
				databaseDAO.createTable(stb.toString());
				LOG.info("finish create inventory table and start to populate inventory database with inventory data in checkForInvTable");
				ArrayList<Inventory> tempInvList = readInventoryFile();
				for (Inventory inv : tempInvList) {
					saveNewInvData(inv);
				}
				LOG.info("done populate inventory database with inventory data in checkForInvTable");
			} else {
				LOG.info("A00754887_INVENTORY already exists in checkForInvTable");
			}
		} catch (SQLException e) {
			LOG.error("SQLException thrown in checkForInvTable");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFoundException thrown in checkForInvTable");
			e.printStackTrace();
		}
	}

	/**
	 * import data from inventory.txt file
	 * 
	 * @throws FileNotFoundException
	 */
	public static void writeInventoryFile() throws FileNotFoundException {
		LOG.info("try to write inventory file in writeInventoryFile");
		File file = new File("inventory.txt");
		// Create a file
		PrintWriter output = new PrintWriter(file);
		StringBuilder text = new StringBuilder();
		ArrayList<Inventory> invList = ctr.retrieveInventoryList();
		for (Inventory inv : invList) {
			text.append(inv.getMake() + "|");
			text.append(inv.getDescription() + "|");
			text.append(inv.getModelNumber() + "|");
			text.append(inv.getSku() + "|");
			text.append(inv.getQuantityInStock() + "|");
			text.append(inv.getQuantitySold() + "|");
			text.append(inv.getPurchasePrice() + "|");
			text.append(inv.getRetailPrice() + "|");
			text.append(inv.getNumberRented());
			output.println(text.toString());
			text.delete(0, text.length());
		}
		// Close the file
		output.close();
		LOG.info("done to write inventory file in writeInventoryFile");
	}

	/**
	 * export data from inventory.txt file
	 * 
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Inventory> readInventoryFile() {
		LOG.info("try to read Inventory file in readInventoryFile");
		ArrayList<Inventory> invList = new ArrayList<Inventory>();
		File file = new File("inventory.txt");
		Scanner input;
		try {
			input = new Scanner(file);

			while (input.hasNext()) {
				invList.add(Assignment2.createInventory(Assignment2
						.readData(input.nextLine())));
			}
			input.close();
			LOG.info("done to read Inventory file in readInventoryFile");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return invList;
	}

}
