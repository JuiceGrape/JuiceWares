package com.juicegrape.juicewares.compat;

import net.minecraft.enchantment.Enchantment;

import com.demoxin.minecraft.moreenchants.MoreEnchants;

public class MoreEnchantsWrapper {
	public static Enchantment poison;
	public static Enchantment venom;
	public static Enchantment mending;
	public static Enchantment cleave;
	public static Enchantment defusing;
	
	public static Enchantment knowledge;
	public static Enchantment berserking;
	public static Enchantment agility;
	public static Enchantment fleetfoot;
	public static Enchantment poisonprotect;
	
	public MoreEnchantsWrapper()
	{
		mending = MoreEnchants.enchantMending;
		poison = MoreEnchants.enchantPoison;
		venom = MoreEnchants.enchantVenom;
		defusing = MoreEnchants.enchantDefusing;
		cleave = MoreEnchants.enchantCleave;
			
		poisonprotect = MoreEnchants.enchantPoisonProtect;
		knowledge = MoreEnchants.enchantKnowledge;
		berserking = MoreEnchants.enchantBerserk;
		agility = MoreEnchants.enchantAgility;
		fleetfoot = MoreEnchants.enchantFleetfoot;
	}
}
