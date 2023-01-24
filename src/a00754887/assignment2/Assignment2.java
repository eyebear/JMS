package a00754887.assignment2;

import java.awt.EventQueue;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import a00754887.assignment2.control.Controller;
import a00754887.assignment2.data.CardType;
import a00754887.assignment2.data.Customer;
import a00754887.assignment2.data.Inventory;
import a00754887.assignment2.ui.MainFrame;

/**
 * this class contains the main method of the whole program. It initializes the
 * frame
 * 
 * @author AoAo_Feng
 * 
 */
public class Assignment2 { 

	private static Logger LOG = Logger.getLogger(Controller.class);
	private Controller ctr = Controller.getControllerInstance();

	/**
	 * the main method creates an object of Assignment class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("LabLogging.properties");
		new Assignment2();
	}

	/**
	 * this is the constructor of the class which imports data from txt files,
	 * and creates a main frame
	 */
	public Assignment2() {
		LOG.info("constructing Assignment2 object");
		ctr.establishDBConnection();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = MainFrame.getFrameInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * this method parse a string of data into an array of string
	 * 
	 * @param string
	 * @return String[]
	 */
	public static String[] readData(String string) {
		return string.split("\\|");
	}

	/**
	 * this method creates an object of Customer using an array of string parsed
	 * by readData method
	 * 
	 * @param data
	 * @return Customer
	 */
	public static Customer createCustomer(String[] data) {
		return new Customer(data[0], data[1], data[2], data[3],
				CardType.getCardIndexByName(data[4]), data[5], data[6],
				data[7], data[8]);
	}

	/**
	 * this method creates an object of Inventory using an array of string
	 * parsed by readData method
	 * 
	 * @param data
	 * @return Inventory
	 */
	public static Inventory createInventory(String[] data) {
		return new Inventory(data[0], data[1], data[2], data[3],
				Integer.parseInt(data[4]), Integer.parseInt(data[5]),
				Double.parseDouble(data[6]), Double.parseDouble(data[7]),
				Integer.parseInt(data[8]));
	}

	/**
	 * this method returns an array of inventory toString objects
	 * 
	 * @return invInfoArray
	 */
	public static String[] inventoryInformationToArray(boolean showMakeNModel,
			ArrayList<Inventory> invList) {
		String[] invInfoArray = new String[invList.size()];
		for (int i = 0; i < invList.size(); i++) {
			if (showMakeNModel == true) {
				invInfoArray[i] = invList.get(i).toSimpleString();
			} else {
				invInfoArray[i] = invList.get(i).getSku();
			}
		}
		return invInfoArray;
	}

}
