import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Functions 
{
	public static void exeInput(String inputFile,String outputFile) throws NumberFormatException, IOException		//Reads instructions from input file and execute them
	{
		File file = new File(outputFile);
		file.delete();
		
		FileWriter writer = new FileWriter(outputFile,true);
		
		PatientDao patDao = new PatientDao("patient.txt");
		AdmissionDao adDao = new AdmissionDao("admission.txt");
		String temp = null;
		
		String[] lines = readFileLines(inputFile);
		
		for(int i=0; i<lines.length; i++)
		{
			//System.out.println(lines[i]);
			
			if(lines[i].split(" ")[0].equalsIgnoreCase("AddPatient"))
			{
				temp = lines[i].split(" ")[5];
				for(int j=6; j<lines[i].split(" ").length; j++)
				{
					temp = temp + " " + lines[i].split(" ")[j];
				}
				patDao.add(new Patient(Integer.parseInt(lines[i].split(" ")[1]),lines[i].split(" ")[2],lines[i].split(" ")[3],"Address: " + temp,lines[i].split(" ")[4]));
				temp = null;
				//System.out.println("Patient " + lines[i].split(" ")[1] + " " + lines[i].split(" ")[2] + " added");
				writer.write("Patient " + lines[i].split(" ")[1] + " " + lines[i].split(" ")[2] + " added\n");
			}
			else if(lines[i].split(" ")[0].equalsIgnoreCase("RemovePatient"))
			{
				//System.out.println("Patient " + lines[i].split(" ")[1] + " " + patDao.deleteByID(Integer.parseInt(lines[i].split(" ")[1])).getName() + " removed");
				writer.write("Patient " + lines[i].split(" ")[1] + " " + patDao.deleteByID(Integer.parseInt(lines[i].split(" ")[1])).getName() + " removed\n");
			}
			else if(lines[i].split(" ")[0].equalsIgnoreCase("CreateAdmission"))
			{
				adDao.add(new Admission(Integer.parseInt(lines[i].split(" ")[1]),Integer.parseInt(lines[i].split(" ")[2])));
				//System.out.println("Admission " + lines[i].split(" ")[1] + " created");
				writer.write("Admission " + lines[i].split(" ")[1] + " created\n");
			}
			else if(lines[i].split(" ")[0].equalsIgnoreCase("AddExamination"))
			{
				Examination tempExa = null;
				Operation tempOp = null;
				if(lines[i].split(" ")[2].equalsIgnoreCase("Inpatient"))
					tempExa = new InpatientExamination();
				else
					tempExa = new OutpatientExamination();
				
				for(int j=(lines[i].split(" ").length - 1); j>=3 ;j--)
				{
					if(lines[i].split(" ").length > 6)
						break;
					
					if(lines[i].split(" ")[j].equalsIgnoreCase("imaging"))
						tempOp = new Imaging(tempOp);
					else if(lines[i].split(" ")[j].equalsIgnoreCase("measurements"))
						tempOp = new Measurement(tempOp);
					else if(lines[i].split(" ")[j].equalsIgnoreCase("tests"))
						tempOp = new Test(tempOp);
					else
						tempOp = new DoctorVisit(tempOp);
				}
				tempExa.addOperation(tempOp);
				adDao.addExamination(Integer.parseInt(lines[i].split(" ")[1]),tempExa);
				//System.out.println(lines[i].split(" ")[2] + " examination added to admission " + lines[i].split(" ")[1]);
				writer.write(lines[i].split(" ")[2] + " examination added to admission " + lines[i].split(" ")[1] + "\n");
			}
			else if(lines[i].split(" ")[0].equalsIgnoreCase("TotalCost"))
			{
				Admission tempAd = adDao.getByID(Integer.parseInt(lines[i].split(" ")[1]));
				//System.out.println("TotalCost for admission " + lines[i].split(" ")[1]);
				writer.write("TotalCost for admission " + lines[i].split(" ")[1] + "\n");
				for(int j=0; j<tempAd.getExaminations().size(); j++)
				{
					//System.out.println("\t" + tempAd.getExaminations().get(j).alternativeString() + " " + tempAd.getExaminations().get(j).cost() + "$");
					writer.write("\t" + tempAd.getExaminations().get(j).alternativeString() + " " + tempAd.getExaminations().get(j).cost() + "$\n");
				}
				//System.out.println("\tTotal: " + tempAd.cost() + "$");
				writer.write("\tTotal: " + tempAd.cost() + "$\n");
				
			}
			else if(lines[i].split(" ")[0].equalsIgnoreCase("ListPatients"))
			{
				ArrayList<Patient> patients = patDao.getALL();
				Collections.sort(patients,Patient.PatNameComparator);
				
				//System.out.println("Patient List:");
				writer.write("Patient List:\n");
				for(int j=0; j<patients.size(); j++)
				{
					//System.out.println(patients.get(j).alternativeString());
					writer.write(patients.get(j).alternativeString() + "\n");
				}
					
			}
		}
		writer.close();
	}
	public static String[] readFileLines(String fileName) throws FileNotFoundException //reads a file line by line
	{
		File file = new File(fileName);
		String[] lines = new String[lineCount(fileName)];
		
		Scanner sc = new Scanner(file);
		
		for(int i=0;sc.hasNextLine();i++)
		{
			lines[i] = sc.nextLine();
		}
		sc.close();
		return lines;
	}
	public static int lineCount(String fileName) throws FileNotFoundException		//returns a file's lineCount
	{
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		int i;
		
		for(i=0;sc.hasNextLine();i++)
			sc.nextLine();
		
		sc.close();
		return i;
	}
	public static boolean isNumeric(String str) 									//Checks if string is numeric
	{ 
		  try 
		  {  
		    Double.parseDouble(str);  
		    return true;
		  } 
		  catch(NumberFormatException e)
		  {  
		    return false;  
		  }  
	}
}
