package com.juicegrape.juicewares.entities;

import com.juicegrape.juicewares.ModInformation;

public class EntityInfo {
	
	
	public static final String EYEBALL_SYSTEM_NAME = "eyeball";
	public static final String EYEBALL_UNLOCALIZED_NAME = "entity." + ModInformation.ID + "." + EntityInfo.EYEBALL_SYSTEM_NAME + ".name";
	public static final String EYEBALL_NAME = "Eyeball";
	
	public static final int EYEBALL_SPAWNRATE_DEF = 6;
	public static int EYEBALL_SPAWNRATE;
	public static final String EYEBALL_SPAWNRATE_CONF = "Eyeball spawnrate, lower is less.";
	
	public static final boolean EYEBALL_SPAWN_DEF = true;
	public static boolean EYEBALL_SPAWN;
	public static final String EYEBALL_SPAWN_CONF = "Enable eyeball spawning.";

}
