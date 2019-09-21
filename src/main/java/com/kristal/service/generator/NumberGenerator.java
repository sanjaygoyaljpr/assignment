package com.kristal.service.generator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristal.config.RetentionPolicy;

@Service
public class NumberGenerator {
	
	@Autowired
	private RetentionPolicy retentionPolicy;
	
	@PostConstruct
	public void startGeneration() {
		System.out.println("Method startGeneration starts");
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable task = new Runnable() {
		    public void run() {
		    	/*
		    	 * Mutliply by 100 to make number big as Math.random only produces between 0 and 1.
		    	 */
		    	retentionPolicy.add(Math.random()*100);
		    }
		};
		scheduler.scheduleAtFixedRate(task, 0, 200, TimeUnit.MILLISECONDS);
		
	}

}
