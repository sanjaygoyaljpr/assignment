package com.kristal.service.reports.impl;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristal.config.RetentionPolicy;
import com.kristal.constant.ApplicationConstant.DURATION;
import com.kristal.service.reports.IReportService;
import com.kristal.wrapper.Response;
import com.kristal.wrapper.StreamWrapper;

/**
 * This class provide functionality to reports end-points.
 * 
 * @author Sanjay Goyal
 *
 */
@Service
public class ReportService implements IReportService {

	@Autowired
	private RetentionPolicy retentionPolicy;

	@Override
	public Response getCumulativeAvarage() {
		Response response = new Response();

		StreamWrapper streamWrapper = retentionPolicy.getCurrentState();

		// last five minutes calculation
		response.setLast5Minutes(getAverage(DURATION.FIVE_MINUTES, streamWrapper));

		// last 10 minutes calculation
		response.setLast10Minutes(getAverage(DURATION.TEM_MINUTES, streamWrapper));

		// last 10 minutes calculation
		response.setLast30Minutes(getAverage(DURATION.THIRTY_MINUTES, streamWrapper));

		return response;

	}

	private double getAverage(DURATION duration, StreamWrapper streamWrapper) {
		double avarage = 0;

		int startIndex;
		int endIndex;
		DoubleStream doubleStream = DoubleStream.empty();

		if (!streamWrapper.isCircular()
				|| (streamWrapper.isCircular() && streamWrapper.getCounter() >= duration.getDuration())) {
			int diff = streamWrapper.getCounter() - duration.getDuration();
			if (diff >= 0) {
				endIndex = streamWrapper.getCounter();
				startIndex = diff;
			} else {
				endIndex = streamWrapper.getCounter();
				startIndex = 0;
			}

			doubleStream = streamWrapper.getStream(startIndex, endIndex);
		} else {

			startIndex = streamWrapper.getCounter();
			int remainingIndex = duration.getDuration() - streamWrapper.getCounter();
			endIndex = streamWrapper.getTotalLength() - remainingIndex;
			doubleStream = streamWrapper.getStreamAsCircular(startIndex, endIndex);

		}

		OptionalDouble optinalAvg = doubleStream.average();
		if (optinalAvg.isPresent()) {
			avarage = optinalAvg.orElse(0);
			System.out.println(avarage);
		}

		return Math.round(avarage);

	}

}
