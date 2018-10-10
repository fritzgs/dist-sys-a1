import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SQLHandler {
	
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "password";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "Assignment1";
		
	//ArrayList of all the columns data
	private ArrayList<String> nameArr, deptArr, managerArr, locationArr, employee;
	private ArrayList<Integer> idArr, manIdArr;
	
	/**make a connection to the mysql database table
	**/
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}
	
	/**
	 * get the id of the last entry in the database table
	 * @param con
	 * @return int id 
	 */
	public int getID(Connection con)
	{
		String query = "SELECT MAX(ID) FROM Employees";
		Statement st = null;
		int id = 0;
		try
		{
			st = con.createStatement();
			ResultSet result = st.executeQuery(query); //create a result set of the query
			result.next();
			id = result.getInt(1);	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * query all the data of the table and save them into their respective arraylists
	 * @param con
	 */
	public void getAll(Connection con)
	{
		String query = "SELECT * FROM Employees";
		Statement st = null;
		idArr = new ArrayList<>();
		nameArr = new ArrayList<>();
		deptArr = new ArrayList<>();
		managerArr = new ArrayList<>();
		manIdArr = new ArrayList<>();
		locationArr = new ArrayList<>();


		try
		{
			st = con.createStatement();
			ResultSet result = st.executeQuery(query); //create a result set of the query
			
			//for each result - add data to arraylists
			while(result.next())
			{
				idArr.add(result.getInt(1));
				nameArr.add(result.getString(2));
				deptArr.add(result.getString(3));
				managerArr.add(result.getString(4));
				manIdArr.add(result.getInt(5));
				locationArr.add(result.getString(6));
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Get a single employee data from the table - uses the id that user clicked
	 * @param con
	 * @param id
	 * @throws SQLException
	 */
	public void getEmployee(Connection con, String id) throws SQLException
	{
		employee = new ArrayList<>();
		String query = "SELECT * FROM Employees WHERE ID=" + id;
		Statement st = null;
		try
		{
			st = con.createStatement();
			ResultSet res = st.executeQuery(query);
			while(res.next())
			{
				
				//add each column data into the array as their own indexes.
				for (int i = 1; i < 7; i++)
				{
					employee.add(res.getString(i));
				}

			}
			
		}
		finally
		{
			if(st != null)
			{
				st.close();
			}
		}
	}
	
	/**
	 * Edits the database table entry and updates with the parameters passed
	 * @param con
	 * @param oldId
	 * @param newId
	 * @param name
	 * @param department
	 * @param manager
	 * @param manId
	 * @param location
	 * @throws SQLException
	 */
	public void alterEntry(Connection con, String oldId, String newId, String name, String department, String manager, String manId, String location) throws SQLException
	{
		String change = "UPDATE Employees SET ID = '" + newId +"' , Name = '"  + name + "' , Department = '" + department +  "' , Manager = '"+ manager + "' , ManagerID = '" + manId + "' , Location = " + "'" + location + "' WHERE ID = " + oldId;
		
		Statement st = null;
		try
		{
			st = con.createStatement();
			st.executeUpdate(change);
		}
		finally
		{
			if(st != null)
			{
				st.close();
			}
		}
		
	}
	
	/**
	 * Adds a new entry to the database table using the parameters as the values.
	 * @param con
	 * @param id
	 * @param name
	 * @param department
	 * @param manager
	 * @param manId
	 * @param location
	 * @throws SQLException
	 */
	public void addEntry(Connection con, String id, String name, String department, String manager, String manId, String location) throws SQLException
	{
		String insert = "INSERT INTO Employees " + "VALUES (" +
				id +  ", '" + name + "' , '" + department + "' , '" + manager + "' , " + manId + " , '" + location + "')";
		
		Statement st = null;
		try
		{
			st = con.createStatement();
			st.executeUpdate(insert);
		}
		finally
		{
			if(st != null)
			{
				st.close();
			}
		}
	}
	
	/**
	 * Deletes entry from database using the ID.
	 * @param con
	 * @param id
	 * @throws SQLException
	 */
	public void delete(Connection con, String id) throws SQLException
	{
		String delete = "DELETE FROM Employees WHERE ID = " + id;
		Statement st = null;
		try
		{
			st = con.createStatement();
			st.executeUpdate(delete);
		}
		finally
		{
			if(st != null)
			{
				st.close();
			}
		}
	}
	
	//Accessors and Mutators of the arraylists
	/**
	 * 
	 * @return ArrayList of names
	 */
	public ArrayList<String> getNameList()
	{
		return nameArr;
	}
	
	/**
	 * 
	 * @return ArrayList of IDs
	 */
	public ArrayList<Integer> getIdList()
	{
		return idArr;
	}
	
	/**
	 * 
	 * @return ArrayList of Departments
	 */
	public ArrayList<String> getDepartmentList()
	{
		return deptArr;
	}

	/**
	 * 
	 * @return ArrayList of Manager Names
	 */
	public ArrayList<String> getManagerList()
	{
		return managerArr;
	}
	
	/**
	 * 
	 * @return ArrayList of ManagerID
	 */
	public ArrayList<Integer> getManagerIDList()
	{
		return manIdArr;
	}
	
	/**
	 * 
	 * @return ArrayList of Locations
	 */
	public ArrayList<String> getLocationList()
	{
		return locationArr;
	}
	
	/**
	 * 
	 * @return ArrayList of single Employee
	 */
	public ArrayList<String> getEmployeeList()
	{
		return employee;
	}
	

}
