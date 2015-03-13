package com.service;

import com.util.TurnOffPC;

public class PCService {
	private TurnOffPC turnOff = new TurnOffPC();
	
	public void turnOffPC(double time){
		turnOff.turnOffPC(time);
	}
	public void cancelOffPC(){
		turnOff.cancelOffPC();
	}
}
