
public abstract class OpDecorator implements Operation 
{
	Operation newOp;
	
	public OpDecorator(Operation newOp) 
	{
		this.newOp = newOp;
	}
	public OpDecorator() {};

	@Override
	public int cost() 
	{
		if(newOp == null)
			return 0;
		return newOp.cost();
	}
	
	@Override
	public Operation getNewOp()
	{
		return this.newOp;
	}

}
