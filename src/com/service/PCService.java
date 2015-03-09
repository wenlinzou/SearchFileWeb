package com.service;

import com.util.TurnOffPC;

public class PCService {
	private TurnOffPC turnOff = new TurnOffPC();
	
	public void turnOffPC(int time){
		turnOff.turnOffPC(time);
	}
}
