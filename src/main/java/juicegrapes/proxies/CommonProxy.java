package juicegrapes.proxies;

import juicegrapes.blocks.BlockInfo;
import juicegrapes.network.ServerTickHandler;
import juicegrapes.tileentities.TileEntityAltar;
import juicegrapes.tileentities.TileEntityDrawer;
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
	
	public void updateTileEntityDrawer(int x, int y, int z, int slot) {
		
	}
	
	public void handleCabinet(int x, int y, int z, int itemID, int stackSize, int metaData, int slot) {
		
	}
	
	public void handleAltar(int x, int y, int z, boolean hasStone, boolean hasLens, boolean isNormalLens, boolean stoneDone) {
		
	}
	
	public void addStringLocalization() {
		
	}
}
