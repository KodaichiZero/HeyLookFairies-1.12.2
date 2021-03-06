package zero.kodai.heylookfairies.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import zero.kodai.heylookfairies.entity.EntityFairy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;

public class FairyAIHover extends EntityAIBase {
    private final EntityLiving entity;

    public FairyAIHover(EntityLiving entityIn) {
        this.entity = entityIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
    	boolean should = false;
    	if(this.entity instanceof EntityFairy) {
    		EntityFairy fairy = (EntityFairy)entity;
    		should = !fairy.isShoulderRiding() && fairy.getFlightMode() && fairy.flyNavigator != null && fairy.flyNavigator.noPath();
    	}
    	
    	return should;
    }
    
    /**
     * Returns whether the EntityAIBase should continue executing.
     */
    @Override
    public boolean shouldContinueExecuting() {
    	boolean should = false;
    	if(this.entity instanceof EntityFairy) {
    		EntityFairy fairy = (EntityFairy)entity;
    		should = !fairy.isShoulderRiding() && fairy.getFlightMode() && fairy.flyNavigator != null && fairy.flyNavigator.noPath();
    	}
    	
    	return should;
    }
    

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
	public void updateTask() {
    	if(this.entity instanceof EntityFairy) {
    		EntityFairy fairy = (EntityFairy)entity;
    		
    		//To stop executing if we somehow made it here.
    		if(fairy.isShoulderRiding() || !fairy.getFlightMode() || fairy.flyNavigator == null) {
    			return;
    		}

    		//b = below, a = above
    		IBlockState b2 = fairy.world.getBlockState(fairy.getPosition().add(0D, -1.5D, 0.0D));
    		IBlockState b1 = fairy.world.getBlockState(fairy.getPosition().add(0D, -1.0D, 0.0D));
    		IBlockState a1 = fairy.world.getBlockState(fairy.getPosition().add(0D, 1.0D, 0.0D));
    		
    		boolean b2p = b2.getCollisionBoundingBox(fairy.world, fairy.getPosition().add(0D, -1.5D, 0.0D)) == Block.NULL_AABB;
    		boolean b1p = b1.getCollisionBoundingBox(fairy.world, fairy.getPosition().add(0D, -1.0D, 0.0D)) == Block.NULL_AABB;
    		boolean a1p = a1.getCollisionBoundingBox(fairy.world, fairy.getPosition().add(0D, 1.0D, 0.0D)) == Block.NULL_AABB;
    		
    		if((!b1p || !b2p) && a1p) {
    			fairy.motionY += 0.085D;
    			if(fairy.motionY > 0.2D) {
    				fairy.motionY = 0.2D;
    			}
    		} else {
    			fairy.motionY += 0.065D;
    			if(fairy.motionY > 0.2D) {
    				fairy.motionY = 0.2D;
    			}
    		}
    	}
    }
}