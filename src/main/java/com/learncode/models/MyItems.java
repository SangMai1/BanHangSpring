package com.learncode.models;

public class MyItems {

	private String time;
	private Integer value;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "MyItems [time=" + time + ", value=" + value + "]";
	}
	
	
	
	
}
