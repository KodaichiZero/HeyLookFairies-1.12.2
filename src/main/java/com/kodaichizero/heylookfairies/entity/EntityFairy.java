package com.kodaichizero.heylookfairies.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFairy extends EntityCreature {
	
	public EntityLivingBase shoulderRidingEntity;
	public boolean isOnLeftShoulder;
	
	public EntityFairy(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.5F);
	}
	
	@Override //Set up the fairy's basic AI tasks.
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
    }
	
	@Override //Apply basic attributes.
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
	
	@Override //Entity Updates
	public void onEntityUpdate() {
		if(this.isShoulderRiding() && this.shoulderRidingEntity.isDead) {
			this.attackEntityFrom(DamageSource.FALL, 5F);
            this.shoulderRidingEntity = null;
        }
		
		if(this.isShoulderRiding()) {
			this.setNoGravity(true);
			this.noClip = true;
			this.motionX = shoulderRidingEntity.motionX;
            this.motionY = (shoulderRidingEntity.onGround ? 0D : shoulderRidingEntity.motionY);
            this.motionZ = shoulderRidingEntity.motionZ;
            
            double xOffset = MathHelper.cos((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (this.isOnLeftShoulder ? (float)Math.PI : 0F)) * 0.375D;
            double zOffset = MathHelper.sin((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (this.isOnLeftShoulder ? (float)Math.PI : 0F)) * 0.375D;
            this.setPosition(shoulderRidingEntity.posX + xOffset, shoulderRidingEntity.posY + (shoulderRidingEntity.isSneaking() ? 0.95D : 1.25D), shoulderRidingEntity.posZ + zOffset);
		} else {
			this.setNoGravity(false);
			this.noClip = false;
		}
		
		super.onEntityUpdate();
	}
	
	@Override //Avoid suffocation damage or player damage if we're riding on a player's shoulders.
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(isShoulderRiding()) {
			if(source == DamageSource.IN_WALL) {
				return false;
			} else if(source instanceof EntityDamageSource) {
				Entity entity = ((EntityDamageSource)source).getTrueSource();
				if(entity == this.shoulderRidingEntity) {
					return false;
				}
			}
		}
		
		return super.attackEntityFrom(source, amount);
	}
	
	@Override //Make sure we don't apply collisions to players if we're sitting on their shoulders.
    public void applyEntityCollision(Entity entityIn) {
		if(shoulderRidingEntity == null || shoulderRidingEntity != entityIn) {
			super.applyEntityCollision(entityIn);
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		// TODO Auto-generated method stub
		return super.getAmbientSound();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		// TODO Auto-generated method stub
		return super.getHurtSound(damageSourceIn);
	}

	@Override
	protected SoundEvent getDeathSound() {
		// TODO Auto-generated method stub
		return super.getDeathSound();
	}
	
	@Override //Fairies are immune to fall damage.
    public void fall(float distance, float damageMultiplier) {
    }
	
	@Override //6 fairies can spawn in a group.
    public int getMaxSpawnedInChunk() {
        return 6;
    }
	
	@Override //Fairies are too light to trample crops.
    protected boolean canTriggerWalking() {
        return false;
    }
	
	@Override //Put you on my shoulder!
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
		
        this.shoulderRidingEntity = player;
        this.isOnLeftShoulder = true;
        return true;
        
		//return super.processInteract(player, hand);
    }
	
	@Override
	public double getYOffset() {
        return 0.175D;
    }
	
	@Override
	protected void collideWithEntity(Entity entityIn) {
        if(entityIn instanceof EntityChicken && !entityIn.isBeingRidden()) {
            this.startRiding(entityIn);
        }
    }

	//Is this fairy sitting on a player's shoulders?
	public boolean isShoulderRiding() {
		return shoulderRidingEntity != null;
	}
}
