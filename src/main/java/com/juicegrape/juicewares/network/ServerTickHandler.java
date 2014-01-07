package com.juicegrape.juicewares.network;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {


	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER, TickType.SERVER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
