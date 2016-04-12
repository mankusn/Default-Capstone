package com.capstone.model;

public class Tidal {
	private int swellHeight;
	private int swellDir;
	private int waterTempF;
	private int swellPeriod;
	private int boatCount1;
	private int boatCount2;
	private int boatCount3;
	private int boatCount4;
	private int total_boatcount;
	
	public Tidal() {
	
	}

	public void setSwellHeight(int sh) {
		this.swellHeight = sh;
	}

	public void setSwellDir(int sd) {
		this.swellDir = sd;
	}
	
	public void setBoatCount1(int bc){
		this.boatCount1 = bc;
	}
	
	public void setBoatCount2(int bc){
		this.boatCount2 = bc;
	}
	
	public void setBoatCount3(int bc){
		this.boatCount3 = bc;
	}
	
	public void setBoatCount4(int bc){
		this.boatCount4 = bc;
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
	
	public int getBoatCount1(){
		return boatCount1;
	}
	
	public int getBoatCount2(){
		return boatCount2;
	}
	
	public int getBoatCount3(){
		return boatCount3;
	}
	
	public int getBoatCount4(){
		return boatCount4;
	}

	public int getTotal_boatcount() {
		return total_boatcount;
	}

	public void setTotal_boatcount(int total_boatcount) {
		this.total_boatcount = total_boatcount;
	}
}