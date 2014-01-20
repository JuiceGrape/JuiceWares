package com.juicegrape.juicewares.proxies;

import com.juicegrape.juicewares.blocks.BlockInfo;
import com.juicegrape.juicewares.network.ServerTickHandler;
import com.juicegrape.juicewares.tileentities.TileEntityAltar;
import com.juicegrape.juicewares.tileentities.TileEntityDrawer;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

	public void initSounds() {

		
	}

	public void initRenderers() {

		
	}
	
	public void registerServerTickHandler() {
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityDrawer.class, BlockInfo.DRAWER_NAME);
		GameRegistry.registerTileEntity(TileEntityAltar.class, BlockInfo.ALTAR_NAME);
	}
	

	public int addArmor(String armor) {
		return 0;
	}
	
	public void addStringLocalization() {
		
	}
}
