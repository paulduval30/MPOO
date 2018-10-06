package serie02;

import java.io.File;
import java.io.IOException;

import serie02.model.StdSplitManager;

public class TestMain {
	public static void main(String[] argv) throws IOException
	{
		StdSplitManager spliter = new StdSplitManager(new File("Plop.txt"));
		spliter.setSplitsNumber(5);
		spliter.split();
	}
	
}
