package com.kristal.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;

import com.kristal.wrapper.StreamWrapper;

/**
 * This class contains retention array with counter.
 * 
 * @author Sanjay Goyal
 *
 */
@Configuration
public class RetentionPolicy {

	private double[] retentionArray;

	private int counter;

	private boolean isCircular;

	public RetentionPolicy() {
		this.retentionArray = new double[30 * 60 * 5];
	}

	public RetentionPolicy(int retentionTimeInMinutes) {
		this.retentionArray = new double[retentionTimeInMinutes * 60 * 5];
	}

	public void add(double randomNumber) {
		// System.out.println("counter: " + counter + " :: randomNumber: " + randomNumber);

		if (counter == retentionArray.length) {
			counter = 0;
			isCircular = true;
		}

		retentionArray[counter++] = randomNumber;
	}

	/**
	 * Gives snapshot of Retention array with counter.
	 */
	public StreamWrapper getCurrentState() {
		return new StreamWrapper(Arrays.copyOf(retentionArray, retentionArray.length), counter, isCircular);
	}

}
