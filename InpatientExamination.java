
public class InpatientExamination extends Examination 
{
	@Override
	public int cost()
	{
		return super.cost() + 10;
	}
	
	@Override
	public String printoperations()
	{
		return super.toString();
	}
	
	@Override
	public String toString()
	{
		return "Inpatient\t" + super.toString();
	}
	@Override
	public String alternativeString()
	{
		return "Inpatient " + super.toString();
	}
}
