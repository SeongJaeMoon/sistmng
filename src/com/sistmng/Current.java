package com.sistmng;

public class Current {
	
	private static class Singleton{
		private static final Current current = new Current();
	}
	
	private String mid;
	
	private Current() {
		
	}

	public static Current getInstance() {
		return Singleton.current;
	}
	
	public void setCurrent(String mid) {
		this.mid = mid;
	}
	public String getCurrent() {
		return mid;
	}
}
