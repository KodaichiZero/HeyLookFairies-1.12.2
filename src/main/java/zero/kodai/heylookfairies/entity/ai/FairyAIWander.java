package zero.kodai.heylookfairies.entity.ai;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import zero.kodai.heylookfairies.entity.EntityFairy;

public class FairyAIWander extends EntityAIWanderAvoidWater {
	
    public FairyAIWander(EntityCreature p_i47413_1_, double p_i47413_2_) {
        super(p_i47413_1_, p_i47413_2_);
    }

    @Nullable
    protected Vec3d getPosition() {
    	if(this.entity instanceof EntityFairy) {
    		boolean flying = ((EntityFairy)entity).getFlightMode();
    		if(flying) {
    			Vec3d vec3d = null;

    	        if (this.entity.isInWater() || this.entity.isOverWater()) {
    	            vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 15);
    	        }

    	        return vec3d == null ? super.getPosition() : vec3d;
    		}
    	}
    	
        return super.getPosition();
    }
}