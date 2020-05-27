public class Examination 
{
	private Operation operation;
	public int cost()
	{
		return this.operation.cost();
	}
	public void addOperation(Operation newOp)
	{
		operation = newOp;
	}
	public String printoperations()
	{
		return this.toString();
	}
	public Operation getOperation() {
		return operation;
	}
	@Override
	public String toString()
	{
		Operation temp = operation;
		if(temp == null)
			return "";
		
		String str = temp.toString();
		temp = temp.getNewOp();
		while(temp != null)
		{
			str = str + " " + temp.toString();
			temp = temp.getNewOp();
		}
		return str;
	}
	public String alternativeString()
	{
		Operation temp = operation;
		if(temp == null)
			return "";
		
		String str = temp.toString();
		temp = temp.getNewOp();
		while(temp != null)
		{
			str = str + " " + temp.toString();
			temp = temp.getNewOp();
		}
		return str;
	}
}
