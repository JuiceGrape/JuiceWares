package juicegrapes.proxies;

import juicegrapes.blocks.Blocks;
import juicegrapes.client.models.ModelEyeball;
import juicegrapes.client.render.AltarRender;
import juicegrapes.client.render.CabinetRender;
import juicegrapes.client.render.ItemTileEntityRenderer;
import juicegrapes.client.render.RenderEyeball;
import juicegrapes.client.sound.SoundHandler;
import juicegrapes.config.Enabling;
import juicegrapes.entities.EntityEyeball;
import juicegrapes.tileentities.TileEntityAltar;
import juicegrapes.tileentities.TileEntityDrawer;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void initSounds() {
		MinecraftForge.EVENT_BUS.register(new SoundHandler());


		
	}

	public void initRenderers() {
		//init the rendering stuff
		RenderingRegistry.registerEntityRenderingHandler(EntityEyeball.class, new RenderEyeball(new ModelEyeball(), 0.5F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDrawer.class, new CabinetRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new AltarRender());
		MinecraftForgeClient.registerItemRenderer(Blocks.drawer.blockID, new ItemTileEntityRenderer(new TileEntityDrawer()));
		MinecraftForgeClient.registerItemRenderer(Blocks.altar.blockID, new ItemTileEntityRenderer(new TileEntityAltar()));
	}
	
	@Override
	public void registerTileEntities() {
		super.registerTileEntities();

	}
	
	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public void updateTileEntityDrawer(int x, int y, int z, int slot) {
		TileEntity te = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(x, y, z);
		
		if (te != null) {
			if (te instanceof TileEntityDrawer) {
				((TileEntityDrawer)te).setInventorySlotContents(slot, null);
			}
		}
	}
	
	@Override
	public void handleCabinet(int x, int y, int z, int itemID, int stackSize, int metaData, int slot) {
		
		World world = FMLClientHandler.instance().getClient().theWorld;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		this.updateTileEntityDrawer(x, y, z, slot);
		
		if (te != null) {
			if (te instanceof TileEntityDrawer) {
				ItemStack item = new ItemStack(itemID, stackSize, metaData);
				
				((TileEntityDrawer)te).setInventorySlotContents(slot, item);
				world.updateAllLightTypes(x, y, z);
			}
		}
	}
	
	@Override
	public void handleAltar(int x, int y, int z, boolean hasStone, boolean hasLens, boolean isNormalLens, boolean stoneDone) { 
		World world = FMLClientHandler.instance().getClient().theWorld;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if (te != null) {
			if (te instanceof TileEntityAltar) {
				((TileEntityAltar)te).handleProxy(hasStone, hasLens, isNormalLens, stoneDone);
				world.updateAllLightTypes(x, y, z);
			}
		}
	}
	
	@Override
	public void addStringLocalization() {
		LanguageRegistry.instance().addStringLocalization("itemGroup.juicewares_JuiceTab", "JuiceWares");
		LanguageRegistry.instance().addStringLocalization("potion.cWaterBreathing", "Oxygen Left");
		if (Enabling.mysteryOption) {
			LanguageRegistry.instance().addStringLocalization(Enchantment.baneOfArthropods.getName(), "Bane of Awfulturds" );
			LanguageRegistry.instance().addStringLocalization(Block.blockGold.getUnlocalizedName() + ".name", "Butter");
		}
	}

}
