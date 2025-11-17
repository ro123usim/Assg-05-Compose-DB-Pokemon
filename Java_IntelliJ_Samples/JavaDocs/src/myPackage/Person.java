package myPackage;




/**
 * This class encapsulates the data for a person.  All person data is private and cannot be used against the will of the person.
 * 
 * @author w0091766
 *
 */
public class Person {
	
	/**
	 * This is the first name which may change
	 */
	public String first_Name; /** This is the first name which may change*/
	
	
	public String Last_Name;
	
	
	/**
	 * this is last name
	 */
	
	/** the street*/
	protected String Street;
	
	/**
	 * employment
	 */
	private String Employment;
	

	
	/**
	 * This is the constructor for a person.  
	 * @param first_Name The first name
	 * @param last_Name The last name
	 * @param street
	 * @param employment
	 */
	public Person(String first_Name, String last_Name, String street,
			String employment) {
		super();
		Employment = employment;
		Last_Name = last_Name;
		Street = street;
		this.first_Name = first_Name;
	}

	/**
	 * This method gets the first name.
	 * 
	 * @return the first name of the person
	 */
	public synchronized String getFirst_Name() {
		return first_Name;
	}


	/**
	 * This is a description of the method.
	 * @param first_Name this is a description of the parameter
	 */
	public synchronized void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}


	public synchronized String getLast_Name() {
		return Last_Name;
	}


	public synchronized void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}


	public synchronized String getStreet() {
		return Street;
	}


	public synchronized void setStreet(String street) {
		Street = street;
	}


	public synchronized String getEmployment() {
		return Employment;
	}


	public synchronized void setEmployment(String employment) {
		Employment = employment;
	}
	

}
