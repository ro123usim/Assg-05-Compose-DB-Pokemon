package myPackage;

/**
 * The first sentence is the overall key sentence. The second
 * sentence adds more detail and may be unnecessary.
 * 
 * @author Russ
 *
 */
public class peopleClass extends Person {

	/**
	 *  This is Russ's really cool JavaDoc Method. This works well in JavaDoc.
	 *  The quick brown fox jumped over the lazy dog.
	 * @param first_Name The first name of the user.
	 * @param last_Name
	 * @param street
	 * @param employment
	 */
	public peopleClass(String first_Name, String last_Name, String street,
			String employment) {
		super(first_Name, last_Name, street, employment);
		// TODO Auto-generated constructor stub
	}

    /**
     * Supposed to add doc here
     * @param first_Name
     * @param last_Name
     * @param street
     * @param employment
     * @param phone  ...supposed to say what to do with this...
     */
    public peopleClass(String first_Name, String last_Name, String street,
                       String employment, String phone) {
        super(first_Name, last_Name, street, employment);
        // TODO Auto-generated constructor stub
    }


}
