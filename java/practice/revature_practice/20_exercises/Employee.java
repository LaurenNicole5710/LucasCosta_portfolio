package com.revature.q7;

public class Employee {
	
	private String name;
	private String dpt;
	private int age;
	
	public Employee(String name, String dpt, int age)
	{
		this.name = name;
		this.dpt = dpt;
		this.age = age;
	}

	public String getName()
	{
		return name;
	}
	
	public String getDpt()
	{
		return dpt;
	}
	
	public int getAge()
	{
		return age;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", dpt=" + dpt + ", age=" + age + "]";
	}
	
	

}
