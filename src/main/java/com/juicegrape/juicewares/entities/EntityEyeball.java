package com.juicegrape.juicewares.entities;


import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.juicegrape.juicewares.entities.ai.EyeBallScaredAI;
import com.juicegrape.juicewares.items.Items;

public class EntityEyeball extends EntityMob {
	
	protected boolean shouldTrip;
	private final Random random = new Random();
	private final String tripTag = "shouldTrip";
	

	public EntityEyeball(World world) {
		super(world);
		this.experienceValue = 0;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EyeBallScaredAI(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 120.0F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setSize(0.625F, 0.625F);
		this.shouldTrip = false;
	}
	
	@Override
	protected boolean isAIEnabled() {
		return true;
	}
	
	@Override
	public boolean canBePushed() {
		return true;
	}
	
	@Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.40D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(4.0D);
        //0.23000000417232513
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(120.0D);
    } 
	
	@Override
	protected void entityInit() {
		super.entityInit();
		
	}
	
	@Override
	protected void dropFewItems(boolean playerHit, int fortune) {
		for(int i = 0; i <= fortune; i++) {
			int chance = random.nextInt(100);
			if (chance > 90) {
				if (this.isPotionActive(Potion.nightVision)) {
					this.entityDropItem(new ItemStack(Items.lens, 1, 1), 0F);
				} else {
					this.entityDropItem(new ItemStack(Items.lens, 1, 0), 0F);
				}
				return;
			}
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damage, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		}
		setScared(true);
		return super.attackEntityFrom(damage, par2);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		setScared(true);
		return super.attackEntityAsMob(entity);
	}


	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!this.worldObj.isRemote) {
			if (this.worldObj.getCurrentMoonPhaseFactor() >= 0.5F && !this.isPotionActive(Potion.nightVision) && !this.worldObj.isDaytime() && !this.isPotionActive(Potion.nightVision.getId())) {
				this.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 200, 0, true));
			}
		}
	} 
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}
	
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		shouldTrip = nbt.getBoolean(tripTag);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean(tripTag, shouldTrip);
	}
	
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
    	return "juicewares:eyeballhit";
    } 

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "juicewares:eyeballhit";
    } 

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
    	return "juicewares:eyeballhit";
    } 
    
    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    public boolean isScared() {
    	return shouldTrip;
    }
    
    public void setScared(boolean yn) {
    	shouldTrip = yn;
    }

}
