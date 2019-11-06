package com.kodaichizero.heylookfairies.entity;

import java.util.List;
import com.kodaichizero.heylookfairies.util.EnumShoulderSide;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityFairy extends EntityCreature {
	
	public EntityLivingBase shoulderRidingEntity;
	public EnumShoulderSide shoulderSide;
	
	public EntityFairy(World worldIn) {
		super(worldIn);
		this.setSize(0.375F, 0.5F);
		this.shoulderSide = EnumShoulderSide.NONE;
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
	
	/**
	 * Fairies should always dismount if their rider is dead.
	 */
	@Override
	public void onLivingUpdate() {
		if(this.isShoulderRiding() && (this.shoulderRidingEntity.isDead || this.shoulderRidingEntity.getHealth() <= 0F)) {
            this.dismountShoulder();
		}
		
		super.onLivingUpdate();
	}
	
	/**
	 * Used when dismounting a player's shoulder.
	 */
	private void dismountShoulder() {
		this.shoulderRidingEntity = null;
        this.shoulderSide = EnumShoulderSide.NONE;
	}

	/**
	 * Used to update the fairy's position and movement at the proper moment when riding on a player's shoulder.
	 */
	@Override 
	public void travel(float strafe, float vertical, float forward) {
		if(this.isShoulderRiding()) {
			setCustomNameTag("" + this.posX + " - " + this.posZ);
			this.fallDistance = 0F;

			this.motionX = shoulderRidingEntity.motionX;
            this.motionY = (shoulderRidingEntity.onGround ? 0D : shoulderRidingEntity.motionY);
            this.motionZ = shoulderRidingEntity.motionZ;
            
            this.renderYawOffset += shoulderRidingEntity.renderYawOffset - shoulderRidingEntity.prevRenderYawOffset;
            this.rotationYaw += shoulderRidingEntity.renderYawOffset - shoulderRidingEntity.prevRenderYawOffset;
            
            double xOffset = MathHelper.cos((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (this.shoulderSide == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.4D;
            double zOffset = MathHelper.sin((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (this.shoulderSide == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.4D;
            this.setPosition(shoulderRidingEntity.posX + xOffset, shoulderRidingEntity.posY + (shoulderRidingEntity.isSneaking() ? 0.95D : 1.25D), shoulderRidingEntity.posZ + zOffset); 
		} else if(!onGround && !isInWater() && !isInLava() && !isOnLadder() && !hasNoGravity() && !inFairyFlightMode()) {
			this.motionY += 0.03D;
		}
		
		super.travel(strafe, vertical, forward);
	}

	/**
	 * Avoid suffocation damage and player damage when riding on that player's shoulder.
	 */
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
	
	/**
	 * This is used to avoid processing collisions with the entity that the fairy is riding on.
	 */
	@Override
    public void applyEntityCollision(Entity entityIn) {
		if(this.isShoulderRiding() && shoulderRidingEntity != entityIn) {
			return;
		}
		super.applyEntityCollision(entityIn);
	}
	
	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
		// TODO This is temporary and will be replaced by a Fairy-COMM function.
		
		//Get off of shoulder if already riding.
		if(this.isShoulderRiding() && this.shoulderRidingEntity == player) {
			
			//This bit is important to ensure the fairy does not get clipped into the wall when dismounting.
			double xOffset = MathHelper.cos((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (this.shoulderSide == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.1D;
            double zOffset = MathHelper.sin((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (this.shoulderSide == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.1D;
            this.setPosition(shoulderRidingEntity.posX + xOffset, shoulderRidingEntity.posY + 0.45D, shoulderRidingEntity.posZ + zOffset); 
            
			this.dismountShoulder();
			return true;
		}
		
		//Get on the shoulder if there is an empty spot.
		EnumShoulderSide playerShoulder = this.playerHasFairyOnShoulder(player);
		if(playerShoulder == EnumShoulderSide.NONE || playerShoulder == EnumShoulderSide.RIGHT) {
			this.shoulderRidingEntity = player;
			this.shoulderSide = EnumShoulderSide.LEFT;
			return true;
		} else if(playerShoulder == EnumShoulderSide.LEFT) {
			this.shoulderRidingEntity = player;
			this.shoulderSide = EnumShoulderSide.RIGHT;
			return true;
		}
        
        return super.processInteract(player, hand);
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
	
	/**
	 * Prevents fairies from taking any kind of fall damage.
	 */
	@Override
    public void fall(float distance, float damageMultiplier) {
    }
	
	@Override
    public int getMaxSpawnedInChunk() {
        return 6;
    }
	
	/**
	 * Prevents fairies from trampling crops.
	 */
	@Override
    protected boolean canTriggerWalking() {
        return false;
    }
	
	/**
	 * Prevents fairies from despawning.
	 */
	@Override
	protected boolean canDespawn() {
        return false;
    }
	
	
	/**
	 * This is how high the fairy should be positioned above its mount.
	 */
	@Override
	public double getYOffset() {
        return 0.175D;
    }
	
	/**
	 * Fairies are short! Didja know?
	 */
	@Override
	public float getEyeHeight()
    {
        return 0.65F;
    }
	
	@Override //Temporary, will allow a fairy to ride a chicken.
	protected void collideWithEntity(Entity entityIn) {
		// TODO This needs to be removed eventually.
		
		if(entityIn instanceof EntityChicken && !entityIn.isBeingRidden()) {
            this.startRiding(entityIn);
        }
    }

	/**
	 * Returns whether or not the fairy is riding on a player's shoulder.
	 */
	public boolean isShoulderRiding() {
		return shoulderRidingEntity != null;
	}
	
	/**
	 * Returns whether the fairy is currently engaged in fairy flight.
	 */
	private boolean inFairyFlightMode() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Determines if the player given as a parameter has a fairy on their shoulder. Returns an enum value.
	 */
	public EnumShoulderSide playerHasFairyOnShoulder(EntityPlayer player) {
		boolean left = false;
		boolean right = false;
		Vec3d pos = player.getPositionVector();
		List<EntityFairy> fairies = this.world.getEntitiesWithinAABB(EntityFairy.class, new AxisAlignedBB(pos.x - 5D, pos.y - 5D, pos.z - 5D, pos.x + 5D, pos.y + 5D, pos.z + 5D));
		for(EntityFairy fairy : fairies) {
			if(fairy.isShoulderRiding()) {
				if(fairy.shoulderSide == EnumShoulderSide.LEFT) {
					left = true;
				} else if(fairy.shoulderSide == EnumShoulderSide.RIGHT) {
					right = true;
				}
			}
			
			if(left && right) {
				break;
			}
		}
		
		if(left && right) {
			return EnumShoulderSide.BOTH;
		} else if(left) {
			return EnumShoulderSide.LEFT;
		} else if(right) {
			return EnumShoulderSide.RIGHT;
		} 
		
		return EnumShoulderSide.NONE;
	}
}
