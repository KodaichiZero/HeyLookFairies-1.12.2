package com.kodaichizero.heylookfairies.entity;

import com.kodaichizero.heylookfairies.util.enumerator.EnumHairStyle;
import com.kodaichizero.heylookfairies.util.enumerator.EnumMagicDyeColor;
import com.kodaichizero.heylookfairies.util.enumerator.EnumShoulderSide;
import javax.annotation.Nullable;

import com.kodaichizero.heylookfairies.entity.ai.FairyAIHover;
import com.kodaichizero.heylookfairies.entity.ai.FairyAIWander;
import com.kodaichizero.heylookfairies.init.InitItems;
import com.kodaichizero.heylookfairies.util.EntityFairyUtil;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
	public static final DataParameter<Byte> wingCollapseFrames = EntityDataManager.createKey(EntityFairy.class, DataSerializers.BYTE);
	
	public final EntityMoveHelper walkHelper;
	public final EntityFlyHelper flyHelper;
	public final PathNavigateFlying flyNavigator;
	public final PathNavigate walkNavigator;
	
	public int timeUntilNextDust;
	public int timeUntilModeChange;
	
	//========================
	//Initialization Methods
	//========================
	
	public EntityFairy(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.6F);
		
		//Initialize move helpers
		this.walkHelper = this.moveHelper;// = new EntityMoveHelper(this);
		this.flyHelper = new EntityFlyHelper(this);
		
		//Initialize fly helpers
		this.walkNavigator = this.navigator;// = createNavigator(worldIn);
		this.flyNavigator = createFlyNavigator(worldIn);
		
		//Initialize other
		this.timeUntilNextDust = this.rand.nextInt(4000) + 4000;
		this.timeUntilModeChange = this.rand.nextInt(300) + 300;
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
		this.dataManager.register(wingCollapseFrames, (byte)0);
	}

	/**
	 * Set up the entity's AI tasks.
	 */
	@Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
        this.tasks.addTask(2, new FairyAIWander(this, 1.0D));
        this.tasks.addTask(3, new FairyAIHover(this));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
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
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1.0D);
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
			
			//Tick down frames.			
			byte frames = getWingCollapseFrames();
			if(frames > 0) {
				dataManager.set(wingCollapseFrames, (byte)(frames - 1));
			}
		}
		super.onEntityUpdate();
	}
	
	/**
	 * Stuff to process every so often.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(!world.isRemote) {
			
			//Update wing dust state.
			timeUntilNextDust --;
			if(timeUntilNextDust > 0 && timeUntilNextDust <= 100 && timeUntilNextDust % 10 == 0) {
				this.playSound(SoundEvents.BLOCK_SAND_PLACE, 0.1F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.0F);
				this.playDustEffect(false);
			} else if(this.timeUntilNextDust <= 0) {
	            this.playSound(SoundEvents.BLOCK_SAND_PLACE, 0.3F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.0F);
	            this.playDustEffect(true);
	            this.dropItem(InitItems.FAIRY_DUST, 1);
	            this.timeUntilNextDust = this.rand.nextInt(4000) + 4000;
	        }
			
			//Update mode change counter, if the fairy is not riding on a shoulder.
			if(!isShoulderRiding()) {
				timeUntilModeChange --;
				if(this.timeUntilModeChange <= 0) {
					
					//The fairy does not start flying if there is no room above her.
					IBlockState a1 = world.getBlockState(getPosition().add(0D, 1.0D, 0.0D));
		    		boolean a1p = a1.getCollisionBoundingBox(world, getPosition().add(0D, 1.0D, 0.0D)) == Block.NULL_AABB;
		    		
		    		if(getFlightMode() || a1p) {
		    			setFlightMode(!getFlightMode());
		    		}
		            this.timeUntilModeChange = this.rand.nextInt(300) + 300;
		        }
			}
		}
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
		} else if(!onGround && !isInWater() && !isInLava() && !isOnLadder() && !hasNoGravity() && !getFlightMode()) {
			
			//Fall slowly because fairies have inherent low gravity.
			this.motionY += 0.05D;
		}
		
		super.travel(strafe, vertical, forward);
	}
	
	/**
	 * Avoid suffocation damage and player damage when riding on that player's shoulder.
	 */
	@Override //Avoid suffocation damage or player damage if we're riding on a player's shoulders.
	public boolean attackEntityFrom(DamageSource source, float amount) {	
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
			if(player.isSneaking()) {
				
				//Manually toggle flight mode
				setFlightMode(!getFlightMode());
			} else {
				
				//Get off of shoulder if already riding.
				EntityLivingBase shoulderRidingEntity = getShoulderRidingEntity();
				if(shoulderRidingEntity != null && shoulderRidingEntity == player) {
					
					//This bit is important to ensure the fairy does not get clipped into the wall when dismounting.
					double xOffset = MathHelper.cos((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (getShoulderSide() == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.1D;
		            double zOffset = MathHelper.sin((shoulderRidingEntity.renderYawOffset * (float)Math.PI / 180F) + (getShoulderSide() == EnumShoulderSide.LEFT ? (float)Math.PI : 0F)) * 0.1D;
		            this.setPosition(shoulderRidingEntity.posX + xOffset, shoulderRidingEntity.posY + 0.45D, shoulderRidingEntity.posZ + zOffset); 
					this.dismountShoulder();
					
					//Reset the mode change counter
					this.timeUntilModeChange = this.rand.nextInt(300);
					return false;
				}
				
				//Get on the shoulder if there is an empty spot.
				EnumShoulderSide playerShoulder = EntityFairyUtil.playerHasFairyOnShoulder(player);
				if(playerShoulder == EnumShoulderSide.NONE || playerShoulder == EnumShoulderSide.RIGHT) {
					dataManager.set(shoulderRidingEntityID, player.getEntityId());
					dataManager.set(shoulderSideID, EnumShoulderSide.LEFT.getByte());
					setFlightMode(false);
					return false;
				} else if(playerShoulder == EnumShoulderSide.LEFT) {
					dataManager.set(shoulderRidingEntityID, player.getEntityId());
					dataManager.set(shoulderSideID, EnumShoulderSide.RIGHT.getByte());
					setFlightMode(false);
					return false;
				}
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
        compound.setInteger("timeUntilNextDust", timeUntilNextDust);
        compound.setInteger("timeUntilModeChange", timeUntilModeChange);
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
        
        //Extra code related to navigators needs to be run.
        setFlightMode(compound.getBoolean("flightMode"));
        
        timeUntilNextDust = compound.getInteger("timeUntilNextDust");
        timeUntilModeChange = compound.getInteger("timeUntilModeChange");
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
	 * Reduce vertical knockback because fairies have low gravity.
	 */
	@Override
    public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
    	super.knockBack(entityIn, strength, xRatio, zRatio);
    	if(motionY > 0F) motionY *= 0.75F;
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
	 * A fix for Minecraft's bad code. Very naughty, Mojang!!
	 */
	@Override
    public boolean hasPath() {
        return !this.getNavigator().noPath();
    }
	
	/**
	 * Returns the fairy's gravity state.
	 */
	//@Override
	//public boolean hasNoGravity() {
	//	return dataManager.get(flightMode) || super.hasNoGravity();
	//}
	
	//========================
	//Custom Methods
	//========================
	
	/**
	 * Create the entity's flight navigator.
	 */
	private PathNavigateFlying createFlyNavigator(World worldIn) {
		PathNavigateFlying flyNavigator = new PathNavigateFlying(this, worldIn);
		flyNavigator.setCanOpenDoors(false);
		flyNavigator.setCanFloat(true);
    	flyNavigator.setCanEnterDoors(true);
    	return flyNavigator;
	}
	
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
	
	/**
	 * Set whether the fairy is currently engaged in fairy flight.
	 */
	public void setFlightMode(boolean flying) {
		boolean prevFlightMode = dataManager.get(flightMode);
		dataManager.set(flightMode, flying);
		if(prevFlightMode && !flying) {
			dataManager.set(wingCollapseFrames, (byte)16);
		} else if(prevFlightMode && !flying) {
			motionY = 0.5D;
		}
		
		this.moveHelper.action = Action.WAIT;
		this.moveHelper.onUpdateMoveHelper();
		this.navigator.clearPath();
		
		if(flying) {
			this.moveHelper = flyHelper;
			this.navigator = flyNavigator;
		} else {
			this.moveHelper = walkHelper;
			this.navigator = walkNavigator;
			this.setNoGravity(false);
		}
	}
	
	/**
	 * Returns whether the fairy is currently engaged in fairy flight.
	 */
	public boolean getFlightMode() {
		return dataManager.get(flightMode);
	}
	/**
	 * Get the remaining wing collapse frames.
	 */
	public byte getWingCollapseFrames() {
		return dataManager.get(wingCollapseFrames);
	}
	
	/**
	 * Play the smoke effect that occurs when a fairy is about to drop fairy dust.
	 */
	protected void playDustEffect(boolean large) {
        EnumParticleTypes enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;

        for (int i = 0; i < (large ? 2 : 7); ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }
}
