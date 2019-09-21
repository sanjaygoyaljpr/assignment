package com.kristal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kristal.service.reports.IReportService;
import com.kristal.wrapper.Response;

/**
 * This class contains reports end-points.
 * 
 * @author Sanjay Goyal
 *
 */
@RestController
@RequestMapping("/report")
public class ReportController {

	Logger LOG = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private IReportService reportService;

	/**
	 * This method is used to get cumulative data as average.
	 */
	@GetMapping(value = { "/avg" })
	public Response get() {
		Response response = null;
		try {
			response = reportService.getCumulativeAvarage();
		} catch (Exception e) {
			LOG.error(e.getStackTrace().toString());
		}
		return response;
	}

}
