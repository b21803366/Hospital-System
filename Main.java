import java.io.IOException;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		if(args.length != 1)
		{
			System.out.println("Wrong arguments");
			return;
		}
		Functions.exeInput(args[0], "output.txt");
	}

}
