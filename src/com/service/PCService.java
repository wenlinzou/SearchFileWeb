package com.service;

import com.util.TurnOffPCUtils;

public class PCService {
	private TurnOffPCUtils turnOff = new TurnOffPCUtils();
	
	public void turnOffPC(double time){
		turnOff.turnOffPC(time);
	}
	public void cancelOffPC(){
		turnOff.cancelOffPC();
	}
}
