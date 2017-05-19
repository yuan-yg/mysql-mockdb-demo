package com.supernova.mysqlmockdemo;

public class MyResponse {
	private long count;

	public MyResponse() {
	}

	public MyResponse(long count2) {
		this.count = count2;
	}

	public long getCount() {
		return this.count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
