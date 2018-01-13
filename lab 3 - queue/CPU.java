// CPU.java

/**
* This is an implementation of a CPU class where jobs are placed in.
* @author Chris Banci
* @version 4.0 - 3/18/2016
*/
public class CPU {
	private NewJFrame gui;
	private Job currentJob;
	private int quantum;
	private int quantumClock;
	private int jobClock;
	private boolean busyFlag;
	
	/**
	 * This method is the default constructor for CPU class.
	 * @param gui
	 */
	public CPU (NewJFrame gui){
		this.currentJob = new Job(0, 0, 0, 0);
		this.quantum = 0;
		this.quantumClock = 0;
		this.jobClock = 0;
		this.busyFlag = false;
		
		this.gui = gui;
		this.gui.setBusyFlag_GUI(false);
	}
	
	/**
	* This method sets the current job in the CPU.
	* @param newjob
	* @param level
	*/
	public void setJob(Job newjob, int level){
		currentJob = newjob;
		currentJob.set_currentLevel(level);
		jobClock = currentJob.get_cpuTimeRequired();
		quantumClock = get_quantum(currentJob.get_currentLevel());
	}
	
	/**
	* This method runs the job in the cpu by decrementing the quantum clock and job_clock.
	*/
	public void RunCPU(){
		if (quantumClock > 0) {
			gui.quantum.setText("Quantum Time: " + quantumClock);
			gui.job.setText("Job Time: " + jobClock);
			
			quantumClock = quantumClock - 1;
			jobClock = jobClock - 1;
			
			currentJob.set_timeRemaining(jobClock);
		}
		gui.quantum.setText("Quantum Time: " + quantumClock);
		gui.job.setText("Job Time: " + jobClock);
	}
	
	/**
	* This method returns the QuantumTime by current job queue level.
	* @param level
	* @return quantum
	*/
	public int get_quantum(int level){
		if (level == 1) {
			quantum = 2;
		}
		else if (level == 2){
			quantum = 4;
		}
		else if (level == 3){
			quantum = 8;
		}
		else if (level == 4){
			quantum = 16;
		}
		return quantum;
	}
	
	/**
	* This method sets the busyflag in the cpu.
	* @param status
	*/
	public void setBusyFlag(boolean status){
		gui.setBusyFlag_GUI(status);
		busyFlag = status;
	}
	
	/**
	* This method returns the current job in the cpu.
	* @return currentJob
	*/
	public Job get_CPUjob(){
		return currentJob;
	}
	
	/**
	* This method returns the current job's time remaining in the cpu.
	* @return jobClock
	*/
	public int get_jobClock(){
		return jobClock;
	}
	
	/**
	* This method returns the current job's quantum time in the cpu.
	* @return quantumClock
	*/
	public int get_quantumClock(){
		return quantumClock;
	}
	
	/**
	* This method returns whether or not the cpu is busy.
	* @return busyFlag
	*/
	public boolean get_busyFlag(){
		return busyFlag;
	}
}
