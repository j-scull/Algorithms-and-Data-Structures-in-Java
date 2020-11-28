package inplace_heap_sort;

/*
 * Implements a stop watch for measuring run times
 */
public class StopWatch {

	private long elapsedTime = 0;
	private long startTime;
	private boolean isRunning;
	
	/*
	 * Construct the stopwatch
	 */
	public StopWatch() {
		reset();
	}
	
	/*
	 * Starts the timer
	 */
	public void start() {
		if (isRunning)
			return;
		isRunning = true;
		startTime = System.currentTimeMillis();
	}
	
	/*
	 * Stops the timer
	 */
	public void stop() {
		if (!isRunning)
			return;
		long endTime = System.currentTimeMillis();
		isRunning = false;
		elapsedTime = elapsedTime + endTime - startTime; 
	}
	
	/*
	 * Gets the elapsed time
	 * @returns elapsedTime the elapsed time
	 */
	public long getElapsedTime() {
		if (isRunning) {
			long endTime = System.currentTimeMillis();
			return elapsedTime + endTime - startTime; 
		}
		else 
			return elapsedTime;			
	}
	
	
	/*
	 * Resets the stop watch
	 */
	public void reset() {
		elapsedTime = 0;
		isRunning = false;
	}
	
	
}




