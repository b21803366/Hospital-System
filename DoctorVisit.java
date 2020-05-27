
public class DoctorVisit extends OpDecorator 
{
	public DoctorVisit(Operation newOp) 
	{
		super(newOp);
	}
	public DoctorVisit() 
	{
		super();
	}
	
	@Override
	public int cost()
	{
		return super.cost() + 15;
	}
	
	@Override
	public String toString()
	{
		return "doctorvisit";
	}
}
