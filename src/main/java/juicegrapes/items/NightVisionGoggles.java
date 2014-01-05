package juicegrapes.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import juicegrapes.juicewares;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class NightVisionGoggles extends ItemArmor{

	public NightVisionGoggles(int id, EnumArmorMaterial material, int renderIndex, int slotType) {
		super(id, material, renderIndex, slotType);
		setUnlocalizedName(ItemInfo.GOGGLES_UNLOCALIZED_NAME);
        this.setCreativeTab(juicewares.juiceTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.GOGGLES_ICON);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return ItemInfo.TEXTURE_LOCATION + ":textures/models/armour/" + ItemInfo.GOGGLES_TEXTURE;
	}
	
	

}
