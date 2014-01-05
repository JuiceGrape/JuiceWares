package juicegrapes.potionEffects;

import net.minecraft.potion.Potion;

public class Potions {
	
	public static Potion cWaterBreathing;
	
	public static void init() {
		cWaterBreathing = (new CustomPotions(32, false, 0));
		cWaterBreathing.setIconIndex(0, 0);
		cWaterBreathing.setPotionName("potion.cWaterBreathing");
	}

	
	

}