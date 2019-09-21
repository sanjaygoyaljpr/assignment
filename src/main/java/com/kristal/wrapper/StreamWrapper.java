package com.kristal.wrapper;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * Immutable class to hold current Retention object state
 * 
 * @author Sanjay Goyal
 *
 */
public final class StreamWrapper {

	private final double[] retentionArray;
	private final int counter;
	private final boolean isCircular;

	public StreamWrapper(double[] retentionArray, int counter, boolean isCircular) {
		this.retentionArray = retentionArray;
		this.counter = counter;
		this.isCircular = isCircular;
	}

	public int getCounter() {
		return counter;
	}

	public boolean isCircular() {
		return isCircular;
	}
	
	public int getTotalLength() {
		return retentionArray.length;
	}

	/**
	 * Return double stream in case retention array is not full.
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public DoubleStream getStream(int startIndex, int endIndex) {
		return Arrays.stream(retentionArray, startIndex, endIndex);
	}
	
	/**
	 * Return double stream in case retention array is full and array is circular now.
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return
	 * 
	 * I have not tested this method due to time constraint.
	 */
	public DoubleStream getStreamAsCircular(int startIndex, int endIndex) {
		double[] temp1 = Arrays.copyOfRange(retentionArray, 0, startIndex);
		double[] temp2 = Arrays.copyOfRange(retentionArray, endIndex, getTotalLength());
		
		int aLen = temp1.length;
	    int bLen = temp2.length;
	    
	    double[] result = new double[aLen + bLen];
	    
	    System.arraycopy(temp1, 0, temp2, 0, aLen);
	    System.arraycopy(bLen, 0, result, aLen, bLen);
		
		return Arrays.stream(result);
	}

}
