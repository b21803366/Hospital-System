
public class Test extends OpDecorator 
{
	public Test(Operation newOp) 
	{
		super(newOp);
	}
	public Test() 
	{
		super();
	}
	
	@Override
	public int cost()
	{
		return super.cost() + 7;
	}
	
	@Override
	public String toString()
	{
		return "tests";
	}
}
