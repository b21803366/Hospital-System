
public class Measurement extends OpDecorator {

	public Measurement(Operation newOp) 
	{
		super(newOp);
	}
	public Measurement() 
	{
		super();
	}

	@Override
	public int cost()
	{
		return super.cost() + 5;
	}
	
	@Override
	public String toString()
	{
		return "measurements";
	}
}
