package com.kodaichizero.heylookfairies.entity;

import com.kodaichizero.heylookfairies.util.enumerator.EnumHairStyle;
import com.kodaichizero.heylookfairies.util.enumerator.EnumMagicDyeColor;
import com.kodaichizero.heylookfairies.util.enumerator.EnumShoulderSide;

import javax.annotation.Nullable;

import com.kodaichizero.heylookfairies.util.EntityFairyUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityFairy extends EntityCreature implements EntityFlying {
	
	public static final DataParameter<Integer> shoulderRidingEntityID = EntityDataManager.createKey(EntityFairy.class, DataSerializers.VARINT);
	public static final DataParameter<Byte> shoulderSideID = EntityDataManager.createKey(EntityFairy.class, DataSerializers.BYTE);
	public static final DataParameter<Byte> hairStyleID = EntityDataManager.createKey(EntityFairy.class, DataSerializers.BYTE);
	public static final DataParameter<Byte> hairColorID = EntityDataManager.createKey(EntityFairy.class, DataSerializers.BYTE);
	public static final DataParameter<Boolean> isHairColorMagic = EntityDataManager.createKey(EntityFairy.class, DataSerializers.BOOLEAN);
	public static final DataParameter<Boolean> flightMode = EntityDataManager.createKey(EntityFairy.class, DataSerializers.BOOLEAN);
	
	public EntityFlyHelper moveHelperFly;
	
	//========================
	//Initialization Methods
	//========================
	
	public EntityFairy(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.6F);
		this.moveHelperFly = new EntityFlyHelper(this);
	}
	
	/**
	 * Initialize the synchronized data.
	 */
	@Override
	public void entityInit() {
		super.entityInit();
		this.dataManager.register(shoulderRidingEntityID, -1);
		this.dataManager.register(shoulderSideID, EnumShoulderSide.NONE.getByte());
		this.dataManager.register(hairStyleID, (byte)rand.nextInt(EnumHairStyle.getLength()));
		this.dataManager.register(hairColorID, (byte)rand.nextInt(16));
		this.dataManager.register(isHairColorMagic, true); //rand.nextInt(2) == 0);
		this.dataManager.register(flightMode, false);
	}

	/**
	 * Set up the entity's AI tasks.
	 */
	@Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
    }
	
	/**
	 * Set up the entity's basic attributes.
	 */
	@Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.2D);
    }
	
	/**
	 * Other initialization applied on first spawn.
	 */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        // TODO debug name tag for testing.
    	String name = "";
    	Object dye = this.getHairColor();
        if(dye instanceof EnumDyeColor) {
        	name = ((EnumDyeColor)dye).getUnlocalizedName() + ", ";
        } else if(dye instanceof EnumMagicDyeColor) {
        	name = ((EnumMagicDyeColor)dye).getUnlocalizedName() + ", ";
        }
        name += getHairStyle().getUnlocalizedName();
        setCustomNameTag(name);
        
        return super.onInitialSpawn(difficulty, livingdata);
    }

	
	//========================
	//Complex Overrides
	//========================
	
	/**
	 * Fairies should always dismount if their rider is dead.
	 */
	@Override
	public void onEntityUpdate() {
		if(!world.isRemote) {
			if(this.isShoulderRiding()) {
				EntityLivingBase shoulderRidingEntity = getShoulderRidingEntity();
				if(shoulderRidingEntity == null || shoulderRidingEntity.isDead || shoulderRidingEntity.getHealth() <= 0F) {
					this.dismountShoulder();
				}
		
			}
		}
		super.onEntityUpdate();
	}

	/**
	 * Used to update the fairy's position and movement at the proper moment when riding on a player's shoulder.
	 */
	@Override 
	public void travel(float strafe, float vertical, float forward) {
		EntityLivingBase shoulderRidingEntity = getShoulderRidingEntity();
		if(shoulderRidingEntity != null) {
			this.fallDistance = 0F;
			this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor = 0.0F;

			this.motionX = shoulderRidingEntity.motionX;
            this.motionY = (shoulderRidingEntity.onGround ? 0D : shoulderRidingEntity.motionY);
            this.motionZ = shoulderRidingEntity.motionZ;
            
            this.renderYawOffset += shoulderRidingEntity.renderYawOffset - shoulderRidingEntity.prevRenderYawOffset;
            this.rotationYaw += shoulderRidingEntity.renderYawOffset - shoulderRidingEntity.prevRenderYawOffset;
            
            double xOffset = MathHelper.cos((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (getShoulderSide() == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.4D;
            double zOffset = MathHelper.sin((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (getShoulderSide() == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.4D;
            this.setPosition(shoulderRidingEntity.posX + xOffset, shoulderRidingEntity.posY + (shoulderRidingEntity.isSneaking() ? 0.95D : 1.25D), shoulderRidingEntity.posZ + zOffset); 
		} else if(!onGround && !isInWater() && !isInLava() && !isOnLadder() && !hasNoGravity()) {
			
			//This makes fairies fall slowly.
			this.motionY += 0.05D;
		}
		
		super.travel(strafe, vertical, forward);
	}
	
	/**
	 * Avoid suffocation damage and player damage when riding on that player's shoulder.
	 */
	@Override //Avoid suffocation damage or player damage if we're riding on a player's shoulders.
	public boolean attackEntityFrom(DamageSource source, float amount) {
		this.setCustomNameTag("My number: " + dataManager.get(shoulderRidingEntityID));
		
		if(!world.isRemote && isShoulderRiding()) {
			if(source == DamageSource.IN_WALL) {
				return false;
			} else if(source instanceof EntityDamageSource) {
				Entity entity1 = ((EntityDamageSource)source).getTrueSource();
				Entity entity2 = getShoulderRidingEntity();
				if(entity1 != null && entity2 != null && entity1 == entity2) {
					return false;
				}
			}
		}
		
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
		// TODO This is temporary and will be replaced by a Fairy-COMM function.
		if(!world.isRemote && hand == EnumHand.MAIN_HAND) {
			
			//Get off of shoulder if already riding.
			EntityLivingBase shoulderRidingEntity = getShoulderRidingEntity();
			if(shoulderRidingEntity != null && shoulderRidingEntity == player) {
				
				//This bit is important to ensure the fairy does not get clipped into the wall when dismounting.
				double xOffset = MathHelper.cos((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (getShoulderSide() == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.1D;
	            double zOffset = MathHelper.sin((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (getShoulderSide() == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.1D;
	            this.setPosition(shoulderRidingEntity.posX + xOffset, shoulderRidingEntity.posY + 0.45D, shoulderRidingEntity.posZ + zOffset); 
	            
				this.dismountShoulder();
				return false;
			}
			
			//Get on the shoulder if there is an empty spot.
			EnumShoulderSide playerShoulder = EntityFairyUtil.playerHasFairyOnShoulder(player);
			if(playerShoulder == EnumShoulderSide.NONE || playerShoulder == EnumShoulderSide.RIGHT) {
				dataManager.set(shoulderRidingEntityID, player.getEntityId());
				dataManager.set(shoulderSideID, EnumShoulderSide.LEFT.getByte());
				return false;
			} else if(playerShoulder == EnumShoulderSide.LEFT) {
				dataManager.set(shoulderRidingEntityID, player.getEntityId());
				dataManager.set(shoulderSideID, EnumShoulderSide.RIGHT.getByte());
				return false;
			}
		}
        
        return super.processInteract(player, hand);
    }
	
	/**
	 * Stop colliding with the player we're riding.
	 */
	@Override
	protected void collideWithEntity(Entity entityIn) {
		if(this.isShoulderRiding() && entityIn.getEntityId() == this.dataManager.get(shoulderRidingEntityID)) {
			return;
		}
		
		// TODO This needs to be removed eventually or reworked.
		if(!world.isRemote && entityIn instanceof EntityChicken && !entityIn.isBeingRidden()) {
            this.startRiding(entityIn);
        }
		
		super.collideWithEntity(entityIn);
    }
	
	/**
	 * Stop colliding with the player we're riding.
	 */
	@Override
    public void applyEntityCollision(Entity entityIn) {
		if(this.isShoulderRiding() && entityIn.getEntityId() == this.dataManager.get(shoulderRidingEntityID)) {
			return;
		}
		
		super.applyEntityCollision(entityIn);
	}
	
    /**
     * Create path navigation.
     */
	@Override
    protected PathNavigate createNavigator(World worldIn) {
		if(inFlightMode() ) {
	        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
	        pathnavigateflying.setCanOpenDoors(false);
	        pathnavigateflying.setCanFloat(true);
	        pathnavigateflying.setCanEnterDoors(true);
	        return pathnavigateflying;
		} else {
			return new PathNavigateGround(this, worldIn);
		}
    }
	
	/**
	 * Get Movement Helper
	 */
    public EntityMoveHelper getMoveHelper() {
        if(inFlightMode()) {
        	return this.moveHelperFly;
        } else {
        	return this.moveHelper;
        }
    }

	
    /**
     * Helper method to write subclass entity data to NBT.
     */
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("hairStyleID", dataManager.get(hairStyleID));
        compound.setByte("hairColorID", dataManager.get(hairColorID));
        compound.setBoolean("isHairColorMagic", dataManager.get(isHairColorMagic));
        compound.setBoolean("flightMode", dataManager.get(flightMode));
    }

    /**
     * Helper method to read subclass entity data from NBT.
     */
	@Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        dataManager.set(hairStyleID, compound.getByte("hairStyleID"));
        dataManager.set(hairColorID, compound.getByte("hairColorID"));
        dataManager.set(isHairColorMagic, compound.getBoolean("isHairColorMagic"));
        dataManager.set(flightMode, compound.getBoolean("flightMode"));
    }
	
	//========================
	//Simple Overrides
	//========================
	
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
	
	/**
	 * Reduce the number of particles that appear when a fairy lands on the ground.
	 */
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    	super.updateFallState(y, onGroundIn, state, pos);
    	if(fallDistance > 3.0F) fallDistance = 3.0F;
    }
	
	/**
	 * Max number of fairies to be spawned in a chunk.
	 */
	@Override
    public int getMaxSpawnedInChunk() {
        return 4;
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
	 * This is where the fairy's eyes are.
	 */
	@Override
	public float getEyeHeight() {
        return 0.5F;
    }
	
	/**
	 * Fairies don't need to jump as high as other mobs because they have low-gravity.
	 */
	@Override
    protected float getJumpUpwardsMotion() {
        return 0.275F;
    }
	
	/**
	 * Returns the fairy's gravity state.
	 */
	@Override
	public boolean hasNoGravity() {
		return dataManager.get(flightMode) || super.hasNoGravity();
	}
	
	//========================
	//Custom Methods
	//========================

	/**
	 * Returns whether or not the fairy is riding on a player's shoulder.
	 */
	public boolean isShoulderRiding() {
		int id = dataManager.get(shoulderRidingEntityID);
		return id > 0;
	}
	
	/**
	 * Returns the entity that the fairy is riding, based on ID.
	 */
	public EntityLivingBase getShoulderRidingEntity() {
		int id = dataManager.get(shoulderRidingEntityID);
		if(id < 0) return null;
		
		Entity entity = world.getEntityByID(id);
		return entity instanceof EntityLivingBase ? (EntityLivingBase)entity : null;
	}
	
	/**
	 * Returns the side of the shoulder that the fairy is riding.
	 */
	public EnumShoulderSide getShoulderSide() {
		byte id = dataManager.get(shoulderSideID);
		return EnumShoulderSide.byMetadata(id);
	}
	
	/**
	 * Used when dismounting a player's shoulder.
	 */
	public void dismountShoulder() {
		dataManager.set(shoulderRidingEntityID, -1);
		dataManager.set(shoulderSideID, EnumShoulderSide.NONE.getByte());
	}
	
	/**
	 * Returns whether the fairy is currently engaged in fairy flight.
	 */
	public boolean inFlightMode() {
		return dataManager.get(flightMode);
	}
	
	/**
	 * Set the fairy's hair color to an EnumDyeColor or EnumMagicDyeColor.
	 */
	public void setHairColor(Object color) {
		if(color instanceof EnumDyeColor) {
			dataManager.set(isHairColorMagic, false);
			dataManager.set(hairColorID, (byte)((EnumDyeColor)color).getMetadata());
		} else if(color instanceof EnumMagicDyeColor) {
			dataManager.set(isHairColorMagic, true);
			dataManager.set(hairColorID, (byte)((EnumMagicDyeColor)color).getMetadata());
		} else {
			throw new IllegalArgumentException("ERROR: Object given was not an an EnumDyeColor or EnumMagicDyeColor!");
		}
	}
	
	/**
	 * Retrieve the fairy's hair color as an EnumDyeColor or EnumMagicDyeColor.
	 */
	public Object getHairColor() {
		boolean magic = dataManager.get(isHairColorMagic);
		if(!magic) {
			return EnumDyeColor.byMetadata(dataManager.get(hairColorID));
		} else {
			return EnumMagicDyeColor.byMetadata(dataManager.get(hairColorID));
		}
	}
	
	/**
	 * Set the fairy's hair style to an EnumHairStyle.
	 */
	public void setHairStyle(EnumHairStyle style) {
		dataManager.set(hairStyleID, (byte)style.getMetadata());
	}
	
	/**
	 * Retrieve the fairy's hair style as an EnumHairStyle.
	 */
	public EnumHairStyle getHairStyle() {
		return EnumHairStyle.byMetadata(dataManager.get(hairStyleID));
	}
}
