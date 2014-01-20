package com.juicegrape.juicewares.proxies;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.juicegrape.juicewares.blocks.Blocks;
import com.juicegrape.juicewares.client.models.ModelEyeball;
import com.juicegrape.juicewares.client.render.AltarRender;
import com.juicegrape.juicewares.client.render.CabinetRender;
import com.juicegrape.juicewares.client.render.ItemTileEntityRenderer;
import com.juicegrape.juicewares.client.render.RenderEyeball;
import com.juicegrape.juicewares.client.sound.SoundHandler;
import com.juicegrape.juicewares.config.Enabling;
import com.juicegrape.juicewares.entities.Entity;
import com.juicegrape.juicewares.entities.EntityEyeball;
import com.juicegrape.juicewares.misc.CustomDamageSource;
import com.juicegrape.juicewares.tileentities.TileEntityAltar;
import com.juicegrape.juicewares.tileentities.TileEntityDrawer;

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
	public void addStringLocalization() {
		LanguageRegistry.instance().addStringLocalization("itemGroup.juicewares_JuiceTab", "JuiceWares");
		LanguageRegistry.instance().addStringLocalization("potion.cWaterBreathing", "Oxygen Left");
		Entity.initNames();
		CustomDamageSource.initDeathMessages();
		if (Enabling.mysteryOption) {
			LanguageRegistry.instance().addStringLocalization(Enchantment.baneOfArthropods.getName(), "Bane of Awfulturds" );
			LanguageRegistry.instance().addStringLocalization(Block.blockGold.getUnlocalizedName() + ".name", "Butter");
		}
	}

}
