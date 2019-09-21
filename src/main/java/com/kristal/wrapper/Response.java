package com.kristal.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private double last5Minutes;
	private double last10Minutes;
	private double last30Minutes;

	private int code;
	private String msg;

	public double getLast5Minutes() {
		return last5Minutes;
	}

	public void setLast5Minutes(double last5Minutes) {
		this.last5Minutes = last5Minutes;
	}

	public double getLast10Minutes() {
		return last10Minutes;
	}

	public void setLast10Minutes(double last10Minutes) {
		this.last10Minutes = last10Minutes;
	}

	public double getLast30Minutes() {
		return last30Minutes;
	}

	public void setLast30Minutes(double last30Minutes) {
		this.last30Minutes = last30Minutes;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
