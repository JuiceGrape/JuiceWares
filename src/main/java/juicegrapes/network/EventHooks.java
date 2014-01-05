package juicegrapes.network;

import juicegrapes.items.Items;
import juicegrapes.potionEffects.Potions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EventHooks {

	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event) {
		
		divingHelmetHandler(event.entityLiving);
		
		nightVisionGogglesHandler(event.entityLiving);
		
		customPotionEffectHandler(event.entityLiving);
		
	}
	
	public void divingHelmetHandler(EntityLivingBase entityLivingBase) {
		ItemStack helmet = entityLivingBase.getCurrentItemOrArmor(4);
		
		//Main loop for checking
		if (helmet != null) {
			if(helmet.getItem() == Items.divinghelmet) {
				if (entityLivingBase.worldObj.isAnyLiquid(entityLivingBase.boundingBox) && entityLivingBase.isOffsetPositionInLiquid(0, 1, 0)) {
					entityLivingBase.addPotionEffect(new PotionEffect(Potions.cWaterBreathing.getId(), 1200, 0, true));
				}
			} else {
				if (entityLivingBase.isPotionActive(Potions.cWaterBreathing)) {
					entityLivingBase.removePotionEffect(Potions.cWaterBreathing.getId());
					entityLivingBase.removePotionEffectClient(Potions.cWaterBreathing.getId());
				}
			}
		} else {
			if (entityLivingBase.isPotionActive(Potions.cWaterBreathing)) {
				entityLivingBase.removePotionEffect(Potions.cWaterBreathing.getId());
				entityLivingBase.removePotionEffectClient(Potions.cWaterBreathing.getId());
			}
		}
		
		//Removing the potioneffect if the entity is not in water. Makes it a bit cleaner
		if (entityLivingBase.isPotionActive(Potions.cWaterBreathing)) {
			if (!entityLivingBase.worldObj.isAnyLiquid(entityLivingBase.boundingBox)) {
				entityLivingBase.removePotionEffectClient(Potions.cWaterBreathing.getId());
				entityLivingBase.removePotionEffect(Potions.cWaterBreathing.getId());
			}
		}
		
		
		//debugging (removing glitches caused by certain circomestances
		if (entityLivingBase.isPotionActive(Potions.cWaterBreathing)) {
			if (entityLivingBase.getActivePotionEffect(Potions.cWaterBreathing).getDuration() == 0) {
				entityLivingBase.removePotionEffectClient(Potions.cWaterBreathing.getId());
				entityLivingBase.removePotionEffect(Potions.cWaterBreathing.getId());
			}
		}
	}
	
	public void nightVisionGogglesHandler(EntityLivingBase entityLivingBase) {
		ItemStack helmet = entityLivingBase.getCurrentItemOrArmor(4);
		if (helmet != null) {
			if (helmet.getItem() == Items.nightvisiongoggles) {
				entityLivingBase.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 300, 0, true));
			}
		}
	}
	
	public void customPotionEffectHandler(EntityLivingBase entityLivingBase) {
		if (entityLivingBase.isPotionActive(Potions.cWaterBreathing)) {
			entityLivingBase.setAir(300);
		}
	}
	
	

	

}
