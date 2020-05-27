import java.util.ArrayList;


public class Admission implements Comparable<Admission>
{
	private int id;
	private int patientID;
	private ArrayList<Examination> examinations = new ArrayList<Examination>();
	
	public Admission(int id, int patientID) 
	{
		this.id = id;
		this.patientID = patientID;
	}

	@Override
	public int compareTo(Admission comparestu)				//Compares by ID
	{
		int compareage = comparestu.getId();
		return this.id - compareage;
	}
	
	public int cost()										//Returns Total Cost
	{
		int cost = 0;
		for(int i=0; i<this.examinations.size(); i++)
			cost = cost + this.examinations.get(i).cost();
		
		return cost;
	}

	@Override
	public String toString()								
	{
		String str = id + "\t" + patientID;
		
		for(int i=0; i<this.examinations.size(); i++)
		{
			str = str + "\n" + examinations.get(i).toString();
		}
		return str;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(ArrayList<Examination> examinations) {
		this.examinations = examinations;
	}

}
