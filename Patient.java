import java.util.Comparator;

public class Patient
{
	private int id;
	private String name;
	private String surname;
	private String address;
	private String phoneNumber;
	
	public Patient(int id,String name,String surname,String address,String phoneNumber) 
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public static Comparator<Patient> PatIDComparator = new Comparator<Patient>()			//Compares by ID
	{
		@Override
		public int compare(Patient p1, Patient p2)
		{
			return p1.getId() - p2.getId();
		}
	};
	public static Comparator<Patient> PatNameComparator = new Comparator<Patient>()			//Compares by Name
	{
		@Override
		public int compare(Patient p1, Patient p2)
		{
			String patientName1 = p1.getName().toUpperCase();
			String patientName2 = p2.getName().toUpperCase();
			
			return patientName1.compareTo(patientName2);
		}
	};
	
	@Override
	public String toString()
	{
		return id + "\t" + name + " " + surname + "\t" + phoneNumber + "\t" + address;
	}
	public String alternativeString()
	{
		return id + " " + name + " " + surname + " " + phoneNumber + " " + address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
