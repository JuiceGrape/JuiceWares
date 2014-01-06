package juicegrapes.items;

import juicegrapes.juicewares;
import juicegrapes.blocks.Blocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemReed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHemp extends ItemReed {
	
	public ItemHemp(int id) {
		super(id, Blocks.stringreed);
		setCreativeTab(juicewares.juiceTab);
		setUnlocalizedName(ItemInfo.STRINGREED_UNLOCALIZED_NAME);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.STRINGREED_ICON);
	}
	
}