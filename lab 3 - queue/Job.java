// Job.java

/**
* This is an implementation of a job class.
* @author Chris Banci
* @version 4.0 - 3/18/2016
*/
public class Job {
	private int pid;
	private int arrivalTime;
	private int cpuTimeRequired;
	private int cpuTimeRemaining;
	private int currentLevel;
	
	/**
	* This method is a class constructor for Job class.
	* @param arrival
	* @param pid
	* @param timeRequired
	* @param level
	*/
	public Job(int arrival, int pid, int timeRequired, int level){
		this.arrivalTime = arrival;
		this.pid = pid;
		this.cpuTimeRequired = timeRequired;
		this.cpuTimeRemaining = timeRequired;
		this.currentLevel = level;
	}
	
	/**
	* This method returns the arrival time of job.
	* @return arrivalTime
	*/
	public int get_arrivalTime(){
		return arrivalTime;
	}
	
	/**
	* This method returns the PID of job.
	* @return pid
	*/
	public int get_PID(){
		return pid;
	}
	
	/**
	* This method returns the current level queue of job.
	* @return currentLevel
	*/
	public int get_currentLevel(){
		return currentLevel;
	}
	
	/**
	* This method returns the time required of job.
	* @return cpuTimeRequired
	*/
	public int get_cpuTimeRequired(){
		return cpuTimeRequired;
	}
	
	/**
	* This method returns the time remaining of job.
	* @return cpuTimeRemaining
	*/
	public int get_cpuTimeRemaining(){
		return cpuTimeRemaining;
	}
	
	/**
	* This method sets the queue level of job.
	* @param level
	*/
	public void set_currentLevel(int level) {
		currentLevel = level;
	}
	
	/**
	* This method sets the time remaining of job.
	* @param time
	*/
	public void set_timeRemaining(int time){
		cpuTimeRemaining = time;
	}
}
