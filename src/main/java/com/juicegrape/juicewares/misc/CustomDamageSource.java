package com.juicegrape.juicewares.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CustomDamageSource extends DamageSource {
	
	public static DamageSource timeWound;

	protected CustomDamageSource(String damageType) {
		super(damageType);
	}
	
	@Override
	public ChatMessageComponent getDeathMessage(EntityLivingBase entityLivingBase) {
		String message = null;
		if (this.damageType.equals("juicewares.timewoundDamage")) {
			message = entityLivingBase.getEntityName() + " skipped too far ahead.";
		}
		return message != null ? ChatMessageComponent.createFromText(message) : super.getDeathMessage(entityLivingBase);
	}
	
	

	public static void init() {
		timeWound = (new CustomDamageSource("juicewares.timewoundDamage")).setDamageBypassesArmor();
	}
	
	public static void initDeathMessages() {
		LanguageRegistry.instance().addStringLocalization("death.attack.juicewares.timewoundDamage", "skipped too far ahead");
	}
	
}
