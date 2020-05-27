
public class OutpatientExamination extends Examination
{
	@Override
	public int cost()
	{
		return super.cost() + 15;
	}
	
	@Override
	public String printoperations()
	{
		return super.toString();
	}
	
	@Override
	public String toString()
	{
		return "Outpatient\t" + super.toString();
	}
	@Override
	public String alternativeString()
	{
		return "Outpatient " + super.toString();
	}
}
