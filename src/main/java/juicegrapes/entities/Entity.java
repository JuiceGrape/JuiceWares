package juicegrapes.entities;

import juicegrapes.juicewares;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Entity {

	static int startEntityId = 500;
	static int modEntityId = 0;
	
	
	public static void init() {
		EntityRegistry.registerModEntity(EntityEyeball.class, EntityInfo.EYEBALL_UNLOCALIZED_NAME, ++modEntityId, juicewares.instance, 80, 3, true);
	}
	
	public static void initNames() {
		LanguageRegistry.instance().addStringLocalization("entity.juicewares." + EntityInfo.EYEBALL_UNLOCALIZED_NAME + ".name", EntityInfo.EYEBALL_NAME);
	}
	
	public static void initEggs() {
		registerEntityEgg(EntityEyeball.class, 0xFFFFFF , 0xFF1C1C );
	}
	
	public static void initSpawns() {
		if (EntityInfo.EYEBALL_SPAWN) {
			for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
	                if (BiomeGenBase.biomeList[i] != null) {
	                	EntityRegistry.addSpawn(EntityEyeball.class, EntityInfo.EYEBALL_SPAWNRATE, 1, 2, EnumCreatureType.monster, BiomeGenBase.biomeList[i]);
	                }
	                
			}
			EntityRegistry.removeSpawn(EntityEyeball.class, EnumCreatureType.monster, BiomeGenBase.hell);
			EntityRegistry.removeSpawn(EntityEyeball.class, EnumCreatureType.monster, BiomeGenBase.sky);
		}
		
		
	}
    @SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class<? extends EntityLiving> entity, int primaryColor, int secondaryColor) {
            int id = getUniqueEntityId();
            EntityList.IDtoClassMapping.put(id, entity);
            EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
    }
    
    public static int getUniqueEntityId()
    {
           do
            {
                    startEntityId++;
            } while (EntityList.getStringFromID(startEntityId) != null);
            
            return startEntityId; 
    }
}
