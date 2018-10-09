
public class Employee {
	
	private String name, department, manager, location;
	private int id, managerId;
	
	public Employee(int id, String name, String department, String manager, int managerId, String location)
	{
		this.id = id;
		this.name = name;
		this.department = department;
		this.manager = manager;
		this.managerId = managerId;
		this.location = location;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getManagerId()
	{
		return managerId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDepartment()
	{
		return department;
	}
	
	public String getManager()
	{
		return manager;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setManagerId(int managerId)
	{
		this.managerId = managerId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDepartment(String dept)
	{
		this.department = dept;
	}
	
	public void setLocation(String loc)
	{
		this.location = loc;
	}
	
	public void setManager(String manager)
	{
		this.manager = manager;
	}

}
