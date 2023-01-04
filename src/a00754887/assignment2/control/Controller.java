package a00754887.assignment2.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.apache.log4j.Logger;

import a00754887.assignment2.data.Customer;
import a00754887.assignment2.data.CustomerDAO;
import a00754887.assignment2.data.DAO;
import a00754887.assignment2.data.Inventory;
import a00754887.assignment2.data.InventoryDAO;
import a00754887.assignment2.util.InventoryComparator.CompareByString;

/**
 * this is the singleton controller class that handles connection with database,
 * and other functions that concerns with datastructure
 * 
 * @author AoAo_Feng
 * 
 */
public class Controller {
	private static Logger LOG = Logger.getLogger(Controller.class);
	private static Controller ctr = new Controller();
	private static final String FILENAME = "dbprops.properties";
	private static DAO databaseDAO = DAO.getDAOIntance();
	private static InventoryDAO invDAO = InventoryDAO.getInventoryDAO();
	private static CustomerDAO cusDAO = CustomerDAO.getCustomerDAO();
	private Properties prop;

	/**
	 * private constructor
	 */
	private Controller() {

	}

	/**
	 * return the instance of the controller class
	 * 
	 * @return ctr
	 */
	public static Controller getControllerInstance() {
		LOG.info("controller instance is retrieved in getControllerInstance");
		return ctr;
	}

	/**
	 * closes the connection with database
	 */
	public void ctrCloseDB() {
		try {
			databaseDAO.closeDBConnection();
			LOG.info("DB connection is closed in establishDBConnection");
		} catch (SQLException e) {
			LOG.error("SQLException thrown when closing DB in ctrCloseDB");
			e.printStackTrace();
		}
	}

	/**
	 * establish connection with database
	 */
	public void establishDBConnection() {
		try {

			// load .properties file
			prop = new Properties();
			prop.load(new FileInputStream(FILENAME));
			// load driver
			databaseDAO.loadDriver(prop.getProperty("mssqlserver.classname"));
			// establish connection
			databaseDAO.connectioToDB(prop.getProperty("mssqlserver.url"),
					prop.getProperty("mssqlserver.user"),
					prop.getProperty("mssqlserver.password"));
			LOG.info("connection success");
			invDAO.checkForInvTable();
			cusDAO.checkForCusTable();
			LOG.info("done check table in establishDBConnection");
		} catch (FileNotFoundException e) {
			LOG.error("file not found exception in establishDBConnection");
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("io excecption in establishDBConnection");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			LOG.error("class not found exception in establishDBConnection");
			e.printStackTrace();
		} catch (SQLException e) {
			LOG.error("SQL exception in establishDBConnection");
			e.printStackTrace();
		}
	}

	/**
	 * write both customer and inventory data back into local files
	 */
	public void writeBackBothFiles() {
		try {
			CustomerDAO.writeCustomerFile();
			InventoryDAO.writeInventoryFile();
			LOG.info("done write back both files in writeBackBothFiles");
		} catch (FileNotFoundException e) {
			LOG.error("SQL exception in establishDBConnection in writeBackBothFiles");
			e.printStackTrace();
		}
	}

	/**
	 * retrieve inventory list data from database server
	 * 
	 * @return arraylist of inventory
	 */
	public ArrayList<Inventory> retrieveInventoryList() {
		LOG.info("retrieve Inventory list in retrieveInventoryList");
		return invDAO.retrieveInvData("SELECT * FROM A00754887_INVENTORY");
	}

	/**
	 * retrieve customer list data from database server
	 * 
	 * @return
	 */
	public ArrayList<Customer> retrieveCustomerList() {
		LOG.info("retrieve Customer list in retrieveCustomerList");
		return cusDAO.retrieveCusData("SELECT * FROM A00754887_CUSTOMER");
	}

	/**
	 * drop inventory table from database
	 */
	public void dropInventoryTable() {
		try {
			databaseDAO.dropTable("DROP TABLE A00754887_INVENTORY");
			LOG.info("drop inventory table from database in dropInventoryTable");
		} catch (SQLException e) {
			LOG.error("SQLException thrown in dropInventoryTable");
			e.printStackTrace();
		}
	}

	/**
	 * drop customer table from database
	 */
	public void dropCustomerTable() {
		try {
			databaseDAO.dropTable("DROP TABLE A00754887_CUSTOMER");
			LOG.info("drop customer table from database in dropCustomerTable");
		} catch (SQLException e) {
			LOG.error("SQLException thrown in dropCustomerTable");
			e.printStackTrace();
		}
	}

	/**
	 * sort the inventory list and return the new sorted inventory list
	 * 
	 * @return sorted inventory list, sort by make
	 */
	public ArrayList<Inventory> getSortedInvList() {
		LOG.info("start sorting list in getSortedInvList");
		CompareByString invcn = new CompareByString();
		ArrayList<String> invMakeList = new ArrayList<String>();
		ArrayList<Inventory> newInvByMakeList = new ArrayList<Inventory>();
		ArrayList<Inventory> oldInvList = retrieveInventoryList();
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
		LOG.info("done sorting list in getSortedInvList");
		return newInvByMakeList;
	}
}
