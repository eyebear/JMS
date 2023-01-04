package a00754887.assignment2.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * This is a singleton class that handles operations with remote database server
 * 
 * @author Ao
 * 
 */
public class DAO {
	private static DAO databaseDAO = new DAO();
	private static Connection connection;
	private Statement statement;
	private static Logger LOG = Logger.getLogger(DAO.class);

	/**
	 * constructor
	 */
	private DAO() {
	}

	/**
	 * return the instance of this singleton
	 * 
	 * @return databaseDAO
	 */
	public static DAO getDAOIntance() {
		return databaseDAO;
	}

	/**
	 * load database driver
	 * 
	 * @param driverName
	 * @throws ClassNotFoundException
	 */
	public void loadDriver(String driverName) throws ClassNotFoundException {
		LOG.debug("load driver: " + driverName);
		Class.forName(driverName);
	}

	/**
	 * establish connection with database driver
	 * 
	 * @param url
	 * @param user
	 * @param password
	 * @throws SQLException
	 */
	public void connectioToDB(String url, String user, String password)
			throws SQLException {
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.createStatement();
		LOG.debug("connect to DB: " + url + " ; " + user + " ; " + password);
	}

	/**
	 * execute select query
	 * 
	 * @param selectQuery
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet queryDatabase(String selectQuery) throws SQLException {
		LOG.debug("queryDatabase: " + selectQuery);
		return statement.executeQuery(selectQuery);
	}

	/**
	 * execute update query
	 * 
	 * @param updateQuery
	 * @return int
	 * @throws SQLException
	 */
	public int updateDatabase(String updateQuery) throws SQLException {
		LOG.debug("updateDatabase: " + updateQuery);
		return statement.executeUpdate(updateQuery);
	}

	/**
	 * update create table query
	 * 
	 * @param createQuery
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean createTable(String createQuery) throws SQLException {
		LOG.debug("createTable: " + createQuery);
		return statement.execute(createQuery);
	}

	/**
	 * execute drop table query
	 * 
	 * @param dropQuery
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean dropTable(String dropQuery) throws SQLException {
		LOG.debug("dropTable: " + dropQuery);
		return statement.execute(dropQuery);
	}

	/**
	 * close the connection with database server
	 * 
	 * @throws SQLException
	 */
	public void closeDBConnection() throws SQLException {
		try {
			LOG.info("closeDBConnection");
			connection.close();
		} catch (SQLException e) {
			LOG.error("sql exception");
			e.printStackTrace();
		}
	}

	/**
	 * check if table exists on the database server
	 * 
	 * @param tableName
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean tableExists(String tableName) throws SQLException {
		LOG.debug("check if table " + tableName + " exists");
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = databaseMetaData.getTables(
				connection.getCatalog(), "%", "%", null);
		String name = null;
		while (resultSet.next()) {
			name = resultSet.getString("TABLE_NAME");
			if (name.equalsIgnoreCase(tableName)) {
				return true;
			}
		}
		return false;
	}
}
