package com.cookies.ar;

public class Line {
	private String first = null;
	private String second = null;
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public Line(String first, String second) {
		super();
		this.first = first;
		this.second = second;
	}
}
