// Driver.java

import java.io.*;
import java.util.Scanner;

/**
 * This is an implementation of a Driver class for Multi-Feedback Queuing which runs the whole program.
 * @author Chris Banci
 * @version 4.0 - 3/18/2016
 */
public class Driver {
	/**
	 * This method is the main method of the program
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		//Chris Banci
		//ID: 010031304
		PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
		Scanner fileScan = new Scanner(new File("mfq.txt"));
        NewJFrame jFrame = new NewJFrame();
		MFQ mfq = new MFQ(fileScan, pw, jFrame);
        
        jFrame.setVisible(true);
		mfq.outputHeader();
		mfq.getJobs();
		mfq.runSimulation();
		mfq.outStats();
		
		fileScan.close();
		pw.close();
	}
}
