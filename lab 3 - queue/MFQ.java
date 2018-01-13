// MFQ.java

import java.io.*;
import java.util.Scanner;

/**
* This is an implementation of a Multi-level Feedback Queuing class which will run the simulation.
* @author Chris Banci
* @version 4.0 - 3/18/2016
*/
public class MFQ {
	private NewJFrame gui;
	private Scanner fileScan;
	private PrintWriter pw;
	private ObjectQueue inputQueue;
	private ObjectQueue queue_1;
	private ObjectQueue queue_2;
	private ObjectQueue queue_3;
	private ObjectQueue queue_4;
	
	private int systemClock;
	
	private int totalJobs;
	private int totalTime;
	private int total_Idle_Time;
	private int totalArriveTime;
	private int totalDepartureTime;
	
	private int totalCpuTimeNeeded;
	private int timeInSystem;
	
	private int avgResponseTime;
	private int avgTurnAround;
	private int avgWaitTime;
	private float avgThroughput;
	
	/**
	 * This method is the class constructor for MFQ class.
	 * @param fileScan
	 * @param pw
	 * @param gui
	 */
	public MFQ(Scanner fileScan, PrintWriter pw, NewJFrame gui){
		this.gui = gui;
		this.pw = pw;
		this.fileScan = fileScan;
		this.inputQueue = new ObjectQueue();
		this.queue_1 = new ObjectQueue();
		this.queue_2 = new ObjectQueue();
		this.queue_3 = new ObjectQueue();
		this.queue_4 = new ObjectQueue();
		
		this.systemClock = 0;
	}
	
	/**
	 * This method gets all the jobs from the mfq.txt file and inserts the jobs into the job queue.
	 * @throws InterruptedException
	 */
	public void getJobs() throws InterruptedException {
		while(fileScan.hasNext()){
			String buf = fileScan.nextLine();
			String[] tokens = buf.split("[ ]+");
			
			Job temp = new Job(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]), 0);
			inputQueue.insert(temp);
			
			Thread.sleep(gui.jSlider1.getValue());
			gui.inputQueue_model.addRow(new Object[] { temp.get_PID() });
		}
	}
	
	/**
	 * This method runs the multi-level feed back queue simulation.
	 * @throws InterruptedException
	 */
	public void runSimulation() throws InterruptedException{
		boolean preemption = false;
		CPU cpu_stat = new CPU(gui);
		
		for (systemClock = 0; systemClock < 156; systemClock++) { //System time run for 156 ticks.
			Thread.sleep(gui.jSlider1.getValue());
			gui.system.setText("System Time: " + systemClock);
			
			if(!inputQueue.isEmpty()) {
				Job tempJob = (Job) inputQueue.query();
				
				if(tempJob.get_arrivalTime() == systemClock) {
					Thread.sleep(gui.jSlider1.getValue());
					queue_1.insert((Job) inputQueue.remove());
					
					totalJobs++;
					totalArriveTime = totalArriveTime + systemClock;
					totalCpuTimeNeeded = totalCpuTimeNeeded + tempJob.get_cpuTimeRequired();
					
					gui.inputQueue_model.removeRow(0);
					gui.queue1_model.addRow((new Object[] { tempJob.get_PID() }));
					
					gui.console.append("Arrival" + " \t" + systemClock + "\t" + tempJob.get_PID() + "\t \t" + tempJob.get_cpuTimeRequired() + "\t \t" + "-" + "\t \t" + "- \n");
					System.out.println("Arrival" + " \t" + systemClock + "\t" + tempJob.get_PID() + "\t \t" + tempJob.get_cpuTimeRequired() + "\t \t \t" + "-" + "\t \t \t" + "-");
					pw.println("Arrival" + " \t" + systemClock + "\t" + tempJob.get_PID() + "\t \t" + tempJob.get_cpuTimeRequired() + "\t \t \t" + "-" + "\t \t \t" + "-");
				}
			}
			
			if(cpu_stat.get_busyFlag() == true) {
				Thread.sleep(gui.jSlider1.getValue());
				cpu_stat.RunCPU();
				
				if(!queue_1.isEmpty()){
					preemption = true;
				}
				
				if (cpu_stat.get_quantumClock() == 0) {
					preemption = true;
				}
				
				if(cpu_stat.get_CPUjob().get_cpuTimeRemaining() == 0) {
					Thread.sleep(gui.jSlider1.getValue());
					gui.cpu_model.removeRow(0);
					
					timeInSystem = systemClock - cpu_stat.get_CPUjob().get_arrivalTime();
					totalTime = totalTime + timeInSystem;
					totalDepartureTime = totalDepartureTime + systemClock;
					
					gui.console.append("Departure" + "\t" + systemClock + "\t" + cpu_stat.get_CPUjob().get_PID() + "\t \t" + "-"  + "\t \t" + timeInSystem + "\t \t" + cpu_stat.get_CPUjob().get_currentLevel() + "\n");
					System.out.println("Departure" + "\t" + systemClock + "\t" + cpu_stat.get_CPUjob().get_PID() + "\t \t" + "-"  + "\t \t \t" + timeInSystem + "\t \t \t" + cpu_stat.get_CPUjob().get_currentLevel());
					pw.println("Departure" + "\t" + systemClock + "\t" + cpu_stat.get_CPUjob().get_PID() + "\t \t" + "-"  + "\t \t \t" + timeInSystem + "\t \t \t" + cpu_stat.get_CPUjob().get_currentLevel());
					
					cpu_stat = new CPU(gui);
					preemption = false;	
				}
			}
			
			if (preemption == true) {		
				Thread.sleep(gui.jSlider1.getValue());
				gui.cpu_model.removeRow(0);
				
				Job reducedJob = new Job(cpu_stat.get_CPUjob().get_arrivalTime(),cpu_stat.get_CPUjob().get_PID(), cpu_stat.get_CPUjob().get_cpuTimeRemaining(), cpu_stat.get_CPUjob().get_currentLevel() + 1);
				moveNextLevel(reducedJob, reducedJob.get_currentLevel());
				
				cpu_stat = new CPU(gui);
				preemption = false;
			}
			
			if(cpu_stat.get_busyFlag() == false) {
				Thread.sleep(gui.jSlider1.getValue());
				moveToCPU(cpu_stat);
				
			}
		}
		
		avgTurnAround = (totalDepartureTime - totalArriveTime) / totalJobs;
		avgThroughput = (float)totalJobs / (float)totalTime;
		avgWaitTime = (totalTime - totalCpuTimeNeeded) / totalJobs;
	}
	
	/**
	* This method removes the job from queue and places it into the CPU.
	* @param cpu_stat
	*/
	public void moveToCPU(CPU cpu_stat) {
		if (!queue_1.isEmpty()){
			cpu_stat.setJob((Job) queue_1.remove(), 1);
			cpu_stat.setBusyFlag(true);
			
			gui.queue1_model.removeRow(0);
			gui.cpu_model.addRow((new Object[] { cpu_stat.get_CPUjob().get_PID() }));
		}
		else if (!queue_2.isEmpty()){
			cpu_stat.setJob((Job) queue_2.remove(), 2);
			cpu_stat.setBusyFlag(true);
			
			gui.queue2_model.removeRow(0);
			gui.cpu_model.addRow((new Object[] { cpu_stat.get_CPUjob().get_PID() }));
		}
		else if (!queue_3.isEmpty()){
			cpu_stat.setJob((Job) queue_3.remove(), 3);
			cpu_stat.setBusyFlag(true);
			
			gui.queue3_model.removeRow(0);	
			gui.cpu_model.addRow((new Object[] { cpu_stat.get_CPUjob().get_PID() }));
		}
		else if (!queue_4.isEmpty()){
			cpu_stat.setJob((Job) queue_4.remove(), 4);
			cpu_stat.setBusyFlag(true);
			
			gui.queue4_model.removeRow(0);
			gui.cpu_model.addRow((new Object[] { cpu_stat.get_CPUjob().get_PID() }));
		}
		else {
			total_Idle_Time++;	
		}
	}
	
	/**
	* This method moves the job to the next lower queue if preemption occurs.
	* preemption can occur when job does not complete in given quantum time
	* or new job is submitted into queue_1 while cpu_model is busy.
	* @param reducedJob
	* @param level
	*/
	public void moveNextLevel(Job reducedJob, int level) {
		if (level == 2){
			queue_2.insert(reducedJob);
			gui.queue2_model.addRow((new Object[] { reducedJob.get_PID() }));
		}
		else if (level == 3){
			queue_3.insert(reducedJob);	
			gui.queue3_model.addRow((new Object[] { reducedJob.get_PID() }));
		}
		else if (level >= 4){
			queue_4.insert(reducedJob);		
			gui.queue4_model.addRow((new Object[] { reducedJob.get_PID() }));
		}
	}
	
	/**
	* This method outputs the various statistics calculated from the simulation.
	*/
	public void outStats(){
		System.out.println("");
		System.out.println("//Simulation Statistics");
		System.out.println("Total Jobs = " + totalJobs);
		System.out.println("Total time of all jobs  = " + totalTime);
		System.out.println("Total idle time = " + total_Idle_Time);
		System.out.println("Average response time = " + avgResponseTime);
		System.out.println("Average wait time = " + avgWaitTime);
		System.out.println("Average turnaround time = " + avgTurnAround);
		System.out.println("Average throughput = " + avgThroughput);
		
		pw.println("");
		pw.println("//Simulation Statistics");
		pw.println("Total Jobs = " + totalJobs);
		pw.println("Total time of all jobs  = " + totalTime);
		pw.println("Total idle time = " + total_Idle_Time);
		pw.println("Average response time = " + avgResponseTime);
		pw.println("Average wait time = " + avgWaitTime);
		pw.println("Average turnaround time = " + avgTurnAround);
		pw.println("Average throughput = " + avgThroughput);
		
		gui.console.append("\n");
		gui.console.append("//Simulation Statistics \n");
		gui.console.append("Total Jobs = " + totalJobs + "\n");
		gui.console.append("Total time of all jobs  = " + totalTime + "\n");
		gui.console.append("Total idle time = " + total_Idle_Time + "\n");
		gui.console.append("Average response time = " + avgResponseTime + "\n");
		gui.console.append("Average wait time = " + avgWaitTime + "\n");
		gui.console.append("Average turnaround time = " + avgTurnAround + "\n");
		gui.console.append("Average throughput = " + avgThroughput + "\n");
	}
	
	/**
	* This method outputs the header of the job information.
	*/
	public void outputHeader(){
		System.out.println("Event \t System time \t PID \t CPU time needed \t Total time in system \t Lowest level queue \t");
		pw.println("Event \t System time \t PID \t CPU time needed \t Total time in system \t Lowest level queue \t");
		
		gui.console.append("Event \t System time \t PID \t CPU time needed \t Total time in system \t Lowest level queue \t \n");
	}
}
