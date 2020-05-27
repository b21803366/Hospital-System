
public class Imaging extends OpDecorator 
{
	public Imaging(Operation newOp) 
	{
		super(newOp);
	}
	public Imaging() 
	{
		super();
	}
	
	@Override
	public int cost()
	{
		return super.cost() + 10;
	}
	
	@Override
	public String toString()
	{
		return "imaging";
	}
}
