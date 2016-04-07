package com.capstone.model;

public class Tidal {
	private int swellHeight;
	private int swellDir;
	private int waterTempF;
	private int swellPeriod;
	
	public Tidal() {
	
	}

	public void setSwellHeight(int sh) {
		this.swellHeight = sh;
	}

	public void setSwellDir(int sd) {
		this.swellDir = sd;
		
	}

	public void setWaterTempF(int waterTempF) {
		this.waterTempF = waterTempF;
	}

	public void setSwellPeriod(int swellPeriod) {
		this.swellPeriod = swellPeriod;
	}

	public int getSwellHeight() {
		return swellHeight;
	}

	public int getSwellDir() {
		return swellDir;
	}

	public int getWaterTempF() {
		return waterTempF;
	}

	public int getSwellPeriod() {
		return swellPeriod;
	}
	
	
}