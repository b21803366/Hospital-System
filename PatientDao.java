import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PatientDao
{
	private String fileName;
	
	public PatientDao(String fileName)
	{
		this.fileName = fileName;
	}
	public Patient getByID(int ID) throws IOException					//Returns Patient by ID
	{
		ArrayList<Patient> tempPatients = readPatients();
		for(int i=0; i<tempPatients.size(); i++)
		{
			if(tempPatients.get(i).getId() == ID)
			{
				writePatients(tempPatients);
				return tempPatients.get(i);
			}	
		}
		writePatients(tempPatients);
		return null;
	}
	public Patient deleteByID(int ID) throws IOException				//Deletes the Patient by ID
	{
		ArrayList<Patient> tempPatients = readPatients();
		Patient temp;
		for(int i=0; i<tempPatients.size(); i++)
		{
			if(tempPatients.get(i).getId() == ID)
			{
				temp = tempPatients.get(i);
				tempPatients.remove(i);
				writePatients(tempPatients);
				return temp;
			}
		}
		writePatients(tempPatients);
		return null;
	}
	public void add(Patient patient) throws IOException					//Adds patient
	{
		ArrayList<Patient> tempPatients = readPatients();
		tempPatients.add(patient);
		Collections.sort(tempPatients,Patient.PatIDComparator);
		writePatients(tempPatients);
	}
	public ArrayList<Patient> getALL() throws FileNotFoundException		//Returns all patients
	{
		return readPatients();
	}
	private ArrayList<Patient> readPatients() throws FileNotFoundException		//Reads patients from text
	{
		ArrayList<Patient> tempPatients = new ArrayList<Patient>();
		Patient temp;
		
		String[] lines = Functions.readFileLines(this.fileName);
		
		for(int i=0; i<lines.length; i++)
		{
			temp = new Patient(Integer.parseInt(lines[i].split("\t")[0]),lines[i].split("\t")[1].split(" ")[0],lines[i].split("\t")[1].split(" ")[1],
								lines[i].split("\t")[3],lines[i].split("\t")[2]);
			tempPatients.add(temp);
			temp = null;
		}
		//for(int i=0; i<tempPatients.size(); i++)
			//System.out.println(tempPatients.get(i).toString());
		return tempPatients;
	}
	private void writePatients(ArrayList<Patient> patients) throws IOException		//Writes patients to text
	{
		File file = new File(this.fileName);
		file.delete();
		
		FileWriter writer = new FileWriter(this.fileName,true);
		for(int i=0; i<patients.size(); i++)
		{
			//System.out.println(patients.get(i).toString());
			writer.write(patients.get(i).toString() + "\n");
		}
		
		writer.close();
	}
}
