import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author fritz
 *
 */
public class Main {
	private JFrame mainFrame, addFrame, showFrame, editFrame;
	private static SQLHandler sqlCtrl;
	private String empId; 
	private JTextField idField, nameField, deptField, managerField, managerIdField, locationField;
		
	private JButton showBack, addBack;
	JOptionPane fail;
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.mainFrame();
		sqlCtrl = new SQLHandler();
		
	}
		
	/**
	 * Main window where user can select to view all the employees in the database or add new employee to the database
	 */
	private void mainFrame()
	{
		mainFrame = new JFrame("Distributed Systems A1"); 
		mainFrame.setSize(450, 200);
		JPanel mainPanel = new JPanel();		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate the window when closed
		

		JLabel creator = new JLabel("Fritz Gerald Santos - 20071968");
		JLabel description = new JLabel("Click the Employee Directory below to view all the employees");
		JButton showAllBtn = new JButton("Employee Directory");
		JButton addNewBtn = new JButton("Add New Employee");
		
		//add an action command to trigger the button
		showAllBtn.setActionCommand("Show");
		addNewBtn.setActionCommand("Add");
		
		//add a listener for the button
		showAllBtn.addActionListener(new ButtonClickListener());
		addNewBtn.addActionListener(new ButtonClickListener());	
		
		//adding the contents to the panel
		mainPanel.add(description);
		mainPanel.add(showAllBtn);
		mainPanel.add(addNewBtn);
		mainPanel.add(creator);
	
		//adding panel to frame
		mainFrame.add(mainPanel);
		
		//allow the window to appear
		mainFrame.setVisible(true);
						
	}
	
	/**
	 * No longer used - causing error when used in multiple frames
	 * @return button
	 */
//	private JButton backButton()
//	{
//		JButton back = new JButton("Back to Main");
//		
//		back.setActionCommand("Back");
//		back.addActionListener(new ButtonClickListener());
//		return back;
//	}
	
	/**
	 * This displays the employee directory window where a user can view all the employees and click on their ID to make changes/delete employee.
	 * @throws SQLException
	 */
	private void showAll() throws SQLException
	{
		showFrame = new JFrame();
		showFrame.setVisible(true);
		showFrame.setSize(800, 300);
		showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate the window when closed
	
		GridLayout grid = new GridLayout(0,6);	
		
		JPanel empPanel = new JPanel();
		empPanel.setLayout(grid);
		
		//Back button for this window
		showBack = new JButton("Back to Main");
		showBack.setActionCommand("showBack");
		showBack.addActionListener(new ButtonClickListener());
		empPanel.add(showBack);
			
		//filling the first row as blanks.
		for (int i=0; i < 5; i++)	
		{
			JLabel blank = new JLabel();
			empPanel.add(blank);
		}

		//all the titles of the columns
		empPanel.add(new JLabel("Employee ID"));
		empPanel.add(new JLabel("Name"));
		empPanel.add(new JLabel("Department"));
		empPanel.add(new JLabel("Manager"));
		empPanel.add(new JLabel("Manager ID"));
		empPanel.add(new JLabel("Location"));

		//get all the entries in the database 
		sqlCtrl.getAll(sqlCtrl.getConnection());
		//loop through the employees arraylist to add into the window as components
		for(int i = 0; i < sqlCtrl.getIdList().size(); i++)	
		{
			JButton idBtn = new JButton(sqlCtrl.getIdList().get(i).toString()); //ID (as button so that it can be clicked)
			empPanel.add(idBtn);
			idBtn.setActionCommand("id:"+sqlCtrl.getIdList().get(i).toString()); //using the ID as an indicator for which to select
			idBtn.addActionListener(new ButtonClickListener());
			
			empPanel.add(new JLabel(sqlCtrl.getNameList().get(i))); //name of employee
			empPanel.add(new JLabel(sqlCtrl.getDepartmentList().get(i))); //department employee is in
			
			//if the employee does not have a manager - it will show NA.
			if(sqlCtrl.getManagerList().get(i) == null)
			{
				empPanel.add(new JLabel("NA")); //manager of employee
			}
			else
			{
				empPanel.add(new JLabel(sqlCtrl.getManagerList().get(i))); //manager of employee
			}
			
			empPanel.add(new JLabel(sqlCtrl.getManagerIDList().get(i).toString())); //manager id 
			empPanel.add(new JLabel(sqlCtrl.getLocationList().get(i))); //location of employee based
		}
		
		showFrame.add(empPanel);
		
	}
	
	/**
	 * Creates text fields for values to add to db table.
	 * The field variables are stored globally to be accessed later when the save button is clicked.
	 * @throws SQLException 
	 */
	private void addNew() throws SQLException
	{
		addFrame = new JFrame("Add New Employee");
		addFrame.setVisible(true);
		addFrame.setSize(700,300);
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//as many rows with two columns
		GridLayout grid = new GridLayout(0,2);
		
		JPanel editPanel = new JPanel();
		editPanel.setLayout(grid);
		
		//Save button - adds values to database
		JButton saveBtn = new JButton("Save");
		saveBtn.setActionCommand("Save");
		saveBtn.addActionListener(new ButtonClickListener());
		
		//blank placeholder
		JLabel title = new JLabel("Add Details Below");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		//ID field 
		JLabel idTitle = new JLabel("ID");
		idTitle.setHorizontalAlignment(SwingConstants.CENTER);
		idField = new JTextField();
		idField.setText(String.valueOf(sqlCtrl.getID(sqlCtrl.getConnection()))); //set the id as the last id + 1
		
		//name field 
		JLabel nameTitle = new JLabel("Name");
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameField = new JTextField();
		
		//department field
		JLabel deptTitle = new JLabel("Department");
		deptTitle.setHorizontalAlignment(SwingConstants.CENTER);
		deptField = new JTextField();

		//manager field
		JLabel managerTitle = new JLabel("Manager");
		managerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerField = new JTextField();
		
		//managerID field
		JLabel managerIdTitle = new JLabel("Manager ID");
		managerIdTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerIdField = new JTextField();
		
		//location field
		JLabel locationTitle = new JLabel("Location");
		locationTitle.setHorizontalAlignment(SwingConstants.CENTER);
		locationField = new JTextField();
	
		//back button 
		addBack = new JButton("Back to Main");
		addBack.setActionCommand("addBack");
		addBack.addActionListener(new ButtonClickListener());
		editPanel.add(addBack);
		editPanel.add(title);
		
		editPanel.add(idTitle);
		editPanel.add(idField);
		
		editPanel.add(nameTitle);
		editPanel.add(nameField);
		
		editPanel.add(deptTitle);
		editPanel.add(deptField);

		editPanel.add(managerTitle);
		editPanel.add(managerField);
		
		editPanel.add(managerIdTitle);
		editPanel.add(managerIdField);
		
		editPanel.add(locationTitle);
		editPanel.add(locationField);
		
		editPanel.add(clearBtn());
		editPanel.add(saveBtn);
		
		addFrame.add(editPanel);
	}
	
	/**
	 * Opens an edit window of the employee selected - based on ID
	 * @param id
	 * @throws SQLException
	 */
	private void edit(String id) throws SQLException
	{
		//get the single employee selected using the ID number
		sqlCtrl.getEmployee(sqlCtrl.getConnection(), id);
		
		editFrame = new JFrame();
		editFrame.setVisible(true);
		editFrame.setSize(700,300);
		editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//as many rows but two columns
		GridLayout grid = new GridLayout(0,2);
		
		JPanel editPanel = new JPanel();
		editPanel.setLayout(grid);
		
		//back button for this window
		JButton backEmp = new JButton("Back to Employee List");
		backEmp.setActionCommand("empBack");
		backEmp.addActionListener(new ButtonClickListener());
		
		//save button
		JButton saveBtn = new JButton("Save Changes");
		saveBtn.setActionCommand("edit");
		saveBtn.addActionListener(new ButtonClickListener());
		
		//delete button
		JButton delete = new JButton("Delete Entry");
		delete.setActionCommand("delete");
		delete.addActionListener(new ButtonClickListener());
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		//selected employee data is stored in an arraylist and is accessed by index.
		//ID
		JLabel idTitle = new JLabel("ID");
		idTitle.setHorizontalAlignment(SwingConstants.CENTER);
		idField = new JTextField();
		idField.setText(sqlCtrl.getEmployeeList().get(0));
		
		//name
		JLabel nameTitle = new JLabel("Name");
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameField = new JTextField();
		nameField.setText(sqlCtrl.getEmployeeList().get(1));

		//department
		JLabel deptTitle = new JLabel("Department");
		deptTitle.setHorizontalAlignment(SwingConstants.CENTER);
		deptField = new JTextField();
		deptField.setText(sqlCtrl.getEmployeeList().get(2));

		//manager name
		JLabel managerTitle = new JLabel("Manager");
		managerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerField = new JTextField();
		managerField.setText(sqlCtrl.getEmployeeList().get(3));
		
		//manager id
		JLabel managerIdTitle = new JLabel("Manager ID");
		managerIdTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerIdField = new JTextField();
		managerIdField.setText(sqlCtrl.getEmployeeList().get(4));

		//location
		JLabel locationTitle = new JLabel("Location");
		locationTitle.setHorizontalAlignment(SwingConstants.CENTER);
		locationField = new JTextField();
		locationField.setText(sqlCtrl.getEmployeeList().get(5));

		
		editPanel.add(backEmp);
		editPanel.add(delete);
		
		editPanel.add(idTitle);
		editPanel.add(idField);
		
		editPanel.add(nameTitle);
		editPanel.add(nameField);
		
		editPanel.add(deptTitle);
		editPanel.add(deptField);

		editPanel.add(managerTitle);
		editPanel.add(managerField);
		
		editPanel.add(managerIdTitle);
		editPanel.add(managerIdField);
		
		editPanel.add(locationTitle);
		editPanel.add(locationField);
		
		editPanel.add(clearBtn());
		
		editPanel.add(saveBtn);
		
		editFrame.add(editPanel);
		
	}
	
	/**
	 * The clear button for edit and add - Sets all the fields to a blank string.
	 * @return JButton
	 */
	private JButton clearBtn()
	{
		JButton clearBtn = new JButton("Clear");
		clearBtn.setActionCommand("clear");
		clearBtn.addActionListener(new ButtonClickListener());
		clearBtn.setHorizontalAlignment(SwingConstants.CENTER);
		return clearBtn;
	}
	
	/**
	 * Listener for the buttons.		
	 * @author fritz
	 *
	 */
	public class ButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			
			//Show all employees in the database
			if (command.equals("Show"))
			{
				mainFrame.dispose(); //gets rid of the main window
				try {
					showAll(); //displays all employees
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//displays the add window
			else if(command.equals("Add"))
			{
				mainFrame.dispose(); 	//gets rid of the main window
				try {
					addNew(); //displays the add employee window
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
			
			else if(command.equals("showBack"))
			{
				showFrame.dispose();	//gets rid of the employee list window
				mainFrame(); 			//displays main window
			}
			
			else if(command.equals("addBack"))
			{
				addFrame.dispose();	//gets rid of the add employee window
				mainFrame();		//display the main window
			}
			
			else if(command.equals("empBack"))
			{
				editFrame.dispose();
				try {
					showAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//saves the values into the database table
			else if (command.equals("Save"))
			{			
				//sets the text in the text field as variables
				String id = idField.getText();
				String name = nameField.getText();
				String department = deptField.getText();
				String manager = managerField.getText();
				String managerId = managerIdField.getText();
				String location = locationField.getText();
				
				//check if it saves to mysql -- add pop up for success
				try {
					if(!id.equals("") && !name.equals("") && !department.equals("") && !manager.equals("") && !location.equals(""))
					{
						sqlCtrl.addEntry(sqlCtrl.getConnection(), id, name, department, manager, managerId, location);
						JOptionPane success = new JOptionPane("Successfully Added a New Employee");
						success.showMessageDialog(addFrame, "Successfully Addded" , "Success", JOptionPane.NO_OPTION);
					}
					else
					{
						//error message if one or more text fields are blank (except for manager id)
						fail = new JOptionPane();
						fail.showMessageDialog(mainFrame, "Failed to Add a New Employee", "Saving Error" , JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				addFrame.dispose(); //close current the add window 
				try {
					addNew(); //go back to add window
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//opens edit window of employee selected by ID.
			else if(command.contains("id:"))
			{
				try {
					//delete the id: to get the id number
					empId = command.replaceAll("id:", "");
					showFrame.dispose(); //get rid of the show window
					edit(empId); //open the edit window of this employee
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//if edit is clicked - it will use the new field contents to update the entry on the db table
			else if(command.equals("edit"))
			{
				String newId = idField.getText();
				String name = nameField.getText();
				String department = deptField.getText();
				String manager = managerField.getText();
				String manId = managerIdField.getText();
				String location = locationField.getText();
				
				try {
					//updates the entry with the values of the variables above
					sqlCtrl.alterEntry(sqlCtrl.getConnection(), empId, newId , name, department, manager, manId, location);
					editFrame.dispose();
					showAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//sets all the fields to blank string
			else if(command.equals("clear"))
			{
				idField.setText("");
				nameField.setText("");
				deptField.setText("");
				managerField.setText("");
				managerIdField.setText("");
				locationField.setText("");
			}
			
			//deletes an employee
			else if (command.equals("delete"))
			{
				try {
					//yes or no dialog
					int dialogBtn = JOptionPane.YES_NO_OPTION;
					int dialogRes = JOptionPane.showConfirmDialog(null, "Delete this Employee from the List", "Confirm", dialogBtn);
					if(dialogRes == JOptionPane.YES_OPTION) //if yes
					{
						//run delete using the ID
						sqlCtrl.delete(sqlCtrl.getConnection(), empId);
						editFrame.dispose();
						JOptionPane success = new JOptionPane("Successfully Deleted");
						success.showMessageDialog(addFrame, "Successfully Deleted Employee" , "Success", JOptionPane.NO_OPTION);
						showAll(); //display the employee list after
					}
					else
					{
						//warn if no
						fail = new JOptionPane();
						fail.showMessageDialog(editFrame, "Canceled Delete", "Canceled" , JOptionPane.INFORMATION_MESSAGE);
					
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
