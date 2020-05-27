import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AdmissionDao 
{
	private String fileName;
	
	public AdmissionDao(String fileName)
	{
		this.fileName = fileName;
	}
	public Admission getByID(int ID) throws IOException						//Returns Admission By ID
	{
		ArrayList<Admission> tempAdmissions = readAdmissions();
		for(int i=0; i<tempAdmissions.size(); i++)
		{
			if(tempAdmissions.get(i).getId() == ID)
			{
				writeAdmissions(tempAdmissions);
				return tempAdmissions.get(i);
			}	
		}
		writeAdmissions(tempAdmissions);
		return null;
	}
	public Admission deleteByID(int ID) throws IOException					//Deletes Admission By ID
	{
		ArrayList<Admission> tempAdmissions = readAdmissions();
		Admission temp;
		for(int i=0; i<tempAdmissions.size(); i++)
		{
			if(tempAdmissions.get(i).getId() == ID)
			{
				temp = tempAdmissions.get(i);
				tempAdmissions.remove(i);
				writeAdmissions(tempAdmissions);
				return temp;
			}
		}
		writeAdmissions(tempAdmissions);
		return null;
	}
	public void add(Admission admission) throws IOException					//Adds Admission
	{
		ArrayList<Admission> tempAdmissions = readAdmissions();
		
		tempAdmissions.add(admission);
		
		Collections.sort(tempAdmissions);
		writeAdmissions(tempAdmissions);
	}
	public ArrayList<Admission> getALL() throws FileNotFoundException		//Returns All Admissions
	{
		return readAdmissions();
	}
	public void addExamination(int ID, Examination newExamination) throws IOException		//Adds an Examination to An Admission
	{
		ArrayList<Admission> tempAdmissions = readAdmissions();
		int index=-1;
		for(int i=0; i<tempAdmissions.size(); i++)
		{
			if(tempAdmissions.get(i).getId() == ID)
			{
				index = i;
				break;
			}	
		}
		if(index == -1)
			return;
		tempAdmissions.get(index).getExaminations().add(newExamination);
		writeAdmissions(tempAdmissions);
	}
	private ArrayList<Admission> readAdmissions() throws FileNotFoundException				//Reads Admissions From Text
	{
		ArrayList<Admission> tempAdmissions = new ArrayList<Admission>();
		Admission temp = null;
		Examination tempExa = null;
		Operation tempOp = null;
		
		String[] lines = Functions.readFileLines(this.fileName);
		
		for(int i=0; i<lines.length; i++)
		{
			//System.out.println(lines[i]);
			if(Functions.isNumeric(lines[i].split("\t")[0]))
			{
				temp = new Admission(Integer.parseInt(lines[i].split("\t")[0]),Integer.parseInt(lines[i].split("\t")[1]));
				tempAdmissions.add(temp);
				temp = null;
			}
			else
			{
				if(lines[i].split("\t")[0].equalsIgnoreCase("Inpatient"))
					tempExa = new InpatientExamination();
				else
					tempExa = new OutpatientExamination();
				
				for(int j=(lines[i].split("\t")[1].split(" ").length - 1); j>=0 ;j--)
				{
					if(lines[i].split("\t")[1].split(" ").length > 3)
						break;
					
					if(lines[i].split("\t")[1].split(" ")[j].equalsIgnoreCase("imaging"))
						tempOp = new Imaging(tempOp);
					else if(lines[i].split("\t")[1].split(" ")[j].equalsIgnoreCase("measurements"))
						tempOp = new Measurement(tempOp);
					else if(lines[i].split("\t")[1].split(" ")[j].equalsIgnoreCase("tests"))
						tempOp = new Test(tempOp);
					else
						tempOp = new DoctorVisit(tempOp);
				}
				
				tempExa.addOperation(tempOp);
				tempOp = null;
				
				tempAdmissions.get(tempAdmissions.size()-1).getExaminations().add(tempExa);
				tempExa = null;
			}
		}
		//for(int i=0; i<tempAdmissions.size(); i++)
		//	System.out.println(tempAdmissions.get(i).toString());
		return tempAdmissions;
	}
	private void writeAdmissions(ArrayList<Admission> admissions) throws IOException			//Writes Admissions To Text
	{
		File file = new File(this.fileName);
		file.delete();
		
		FileWriter writer = new FileWriter(this.fileName,true);
		for(int i=0; i<(admissions.size() - 1); i++)
		{
			//System.out.println(admissions.get(i).toString());
			writer.write(admissions.get(i).toString() + "\n");
		}
		
		//System.out.println(admissions.get(admissions.size() - 1).toString());
		writer.write(admissions.get(admissions.size() - 1).toString());
		
		writer.close();
	}
}
