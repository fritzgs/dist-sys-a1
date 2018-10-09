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


public class Main {
	private JFrame mainFrame, addFrame, showFrame, editFrame;
	private static SQLHandler sqlCtrl;
	
	private String empId; 
	
	private JTextField idField, nameField, deptField, managerField, managerIdField, locationField;
		
	private JButton showBack, addBack;
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.mainFrame();
		sqlCtrl = new SQLHandler();
		
	}
		
	private void mainFrame()
	{
		mainFrame = new JFrame("Distributed Systems A1"); //main window
		mainFrame.setSize(450, 200);
		JPanel mainPanel = new JPanel();
		backButton();

		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate the window when closed
		

		JLabel creator = new JLabel("Fritz Gerald Santos - 20071968");
		JLabel description = new JLabel("Click the Employee Directory below to view all the employees");
		JButton showAllBtn = new JButton("Employee Directory");
		JButton addNewBtn = new JButton("Add New Employee");
		
		showAllBtn.setActionCommand("Show");
		addNewBtn.setActionCommand("Add");
		
		showAllBtn.addActionListener(new ButtonClickListener());
		addNewBtn.addActionListener(new ButtonClickListener());	
		
		
		mainPanel.add(description);
		mainPanel.add(showAllBtn);
		mainPanel.add(addNewBtn);
		mainPanel.add(creator);
	
		mainFrame.add(mainPanel);
		
		mainFrame.setVisible(true);
						
	}
	
	private JButton backButton()
	{
		JButton back = new JButton("Back to Main");
		
		back.setActionCommand("Back");
		back.addActionListener(new ButtonClickListener());
		return back;
	}
	
	
	private void showAll() throws SQLException
	{
		showFrame = new JFrame();
		showFrame.setVisible(true);
		showFrame.setSize(800, 300);
		showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate the window when closed
	
		GridLayout grid = new GridLayout(0,6);
		
		
		JPanel empPanel = new JPanel();
		empPanel.setLayout(grid);
		
		showBack = new JButton("Back to Main");
		showBack.setActionCommand("showBack");
		showBack.addActionListener(new ButtonClickListener());
		empPanel.add(showBack);
				
		for (int i=0; i < 5; i++)	
		{
			JLabel blank = new JLabel();
			empPanel.add(blank);
		}

		empPanel.add(new JLabel("Employee ID"));
		empPanel.add(new JLabel("Name"));
		empPanel.add(new JLabel("Department"));
		empPanel.add(new JLabel("Manager"));
		empPanel.add(new JLabel("Manager ID"));
		empPanel.add(new JLabel("Location"));

		
		sqlCtrl.getAll(sqlCtrl.getConnection());
		for(int i = 0; i < sqlCtrl.getIdList().size(); i++)	
		{
			JButton idBtn = new JButton(sqlCtrl.getIdList().get(i).toString());
			empPanel.add(idBtn);
			idBtn.setActionCommand("id:"+sqlCtrl.getIdList().get(i).toString());
			idBtn.addActionListener(new ButtonClickListener());
			
			empPanel.add(new JLabel(sqlCtrl.getNameList().get(i)));
			empPanel.add(new JLabel(sqlCtrl.getDepartmentList().get(i)));
			empPanel.add(new JLabel(sqlCtrl.getManagerList().get(i)));
			empPanel.add(new JLabel(sqlCtrl.getManagerIDList().get(i).toString()));
			empPanel.add(new JLabel(sqlCtrl.getLocationList().get(i)));
		}
		
		showFrame.add(empPanel);
		
	}
	
	
	private void addNew()
	{
		addFrame = new JFrame("Add New Employee");
		addFrame.setVisible(true);
		addFrame.setSize(700,300);
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout grid = new GridLayout(0,2);
		
		JPanel editPanel = new JPanel();
		editPanel.setLayout(grid);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.setActionCommand("Save");
		saveBtn.addActionListener(new ButtonClickListener());
		
		JLabel title = new JLabel("");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel idTitle = new JLabel("ID");
		idTitle.setHorizontalAlignment(SwingConstants.CENTER);
		idField = new JTextField();
		
		JLabel nameTitle = new JLabel("Name");
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameField = new JTextField();
		
		JLabel deptTitle = new JLabel("Department");
		deptTitle.setHorizontalAlignment(SwingConstants.CENTER);
		deptField = new JTextField();

		
		JLabel managerTitle = new JLabel("Manager");
		managerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerField = new JTextField();
		
		JLabel managerIdTitle = new JLabel("Manager ID");
		managerIdTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerIdField = new JTextField();
		
		JLabel locationTitle = new JLabel("Location");
		locationTitle.setHorizontalAlignment(SwingConstants.CENTER);
		locationField = new JTextField();
	
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
		
		editPanel.add(new JLabel());
		editPanel.add(saveBtn);
		
		addFrame.add(editPanel);
	}
	
	
	private void edit(String id) throws SQLException
	{
		sqlCtrl.getEmployee(sqlCtrl.getConnection(), id);
		
		editFrame = new JFrame();
		editFrame.setVisible(true);
		editFrame.setSize(700,300);
		editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout grid = new GridLayout(0,2);
		
		JPanel editPanel = new JPanel();
		editPanel.setLayout(grid);
		
		JButton backEmp = new JButton("Back to Employee List");
		backEmp.setActionCommand("Show");
		backEmp.addActionListener(new ButtonClickListener());
		
		JButton saveBtn = new JButton("Save Changes");
		saveBtn.setActionCommand("edit");
		saveBtn.addActionListener(new ButtonClickListener());
		
		JButton delete = new JButton("Delete Entry");
		delete.setActionCommand("delete");
		delete.addActionListener(new ButtonClickListener());
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JLabel idTitle = new JLabel("ID");
		idTitle.setHorizontalAlignment(SwingConstants.CENTER);
		idField = new JTextField();
		idField.setText(sqlCtrl.getEmployeeList().get(0));
		
		JLabel nameTitle = new JLabel("Name");
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameField = new JTextField();
		nameField.setText(sqlCtrl.getEmployeeList().get(1));

		
		JLabel deptTitle = new JLabel("Department");
		deptTitle.setHorizontalAlignment(SwingConstants.CENTER);
		deptField = new JTextField();
		deptField.setText(sqlCtrl.getEmployeeList().get(2));

		
		JLabel managerTitle = new JLabel("Manager");
		managerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerField = new JTextField();
		managerField.setText(sqlCtrl.getEmployeeList().get(3));
		
		JLabel managerIdTitle = new JLabel("Manager ID");
		managerIdTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerIdField = new JTextField();
		managerIdField.setText(sqlCtrl.getEmployeeList().get(4));

		
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
		
		editPanel.add(new JLabel());
		editPanel.add(saveBtn);
		
		editFrame.add(editPanel);
		
	}
	
			
	public class ButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			
			if (command.equals("Show"))
			{
				mainFrame.dispose();
				try {
					showAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(command.equals("Add"))
			{
				mainFrame.dispose();
				addNew();

			}
			
			else if(command.equals("showBack"))
			{
				showFrame.dispose();
				mainFrame();
			}
			
			else if(command.equals("addBack"))
			{
				addFrame.dispose();
				mainFrame();
			}
			
			else if (command.equals("Save"))
			{			
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
						JOptionPane fail = new JOptionPane();
						fail.showMessageDialog(mainFrame, "Failed to Add a New Employee", "Saving Error" , JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				addFrame.dispose();
				mainFrame();
			}
			
			else if(command.contains("id:"))
			{
				try {
					empId = command.replaceAll("id:", "");
					showFrame.dispose();
					edit(empId);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if(command.equals("edit"))
			{
				String newId = idField.getText();
				String name = nameField.getText();
				String department = deptField.getText();
				String manager = managerField.getText();
				String manId = managerIdField.getText();
				String location = locationField.getText();
				
				try {
					sqlCtrl.alterEntry(sqlCtrl.getConnection(), empId, newId , name, department, manager, manId, location);
					editFrame.dispose();
					showAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if (command.equals("delete"))
			{
				try {
					int dialogBtn = JOptionPane.YES_NO_OPTION;
					int dialogRes = JOptionPane.showConfirmDialog(null, "Delete this Employee from the List", "Confirm", dialogBtn);
					if(dialogRes == JOptionPane.YES_OPTION)
					{
						sqlCtrl.delete(sqlCtrl.getConnection(), empId);
					}					
					showAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	

}
